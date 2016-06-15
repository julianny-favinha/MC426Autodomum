#include "servidor.hpp"
#include <Ethernet.h>
#include <string.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };    // ethernet shield mac address
char server[] = "secure-bastion-88575.herokuapp.com";   // name address for Autodomum server (using DNS)
IPAddress ip(192, 168, 25, 14);                         // Set the static IP address to use if the DHCP fails to assign
EthernetClient client;                                  // Initialize the Ethernet client library with the IP address and port of the server that you want to connect to (port 80 is default for HTTP)


void Servidor::connect() {
    Serial.println("connecting the ethernet shield...");
    // start the Ethernet connection:
    if (Ethernet.begin(mac) == 0) {
        Serial.println("Failed to configure Ethernet using DHCP");
        // try to congifure using IP address instead of DHCP:
        Ethernet.begin(mac, ip);
    }
    // give the Ethernet shield a second to initialize:
    delay(1000);
}

String Servidor::request(String host, String endpoint) {  
    String readString = String();

    // if you get a connection, report back via serial:
    if (client.connect(server, 80)) {
        // Make a HTTP request:
        client.println("GET " + endpoint + " HTTP/1.1");
        client.println("Host: " + host);
        client.println("Connection: close");
        client.println();
    } else {
        // if you didn't get a connection to the server:
        Serial.println("connection failed");
    }

    bool read = false;
    while(client.connected()) {
        // if there are incoming bytes available
        // from the server, read them and print them:
        if (client.available()) {
            char c = client.read();

            // we just receive simple json objects
            if(c == '{') {
                read = true;
            }

            if(read) {
                readString.concat(c);
            }

            if(c == '}') {
                read = false;
            }
        }
    }

    client.stop(); //TODO testar!

    return readString;
}
