#include "servidor.hpp"
#include <Ethernet.h>
#include <string.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };    // ethernet shield mac address
char server[] = "secure-bastion-88575.herokuapp.com";   // name address for Autodomum server (using DNS)
//192.168.1.133:8080
IPAddress ip(10, 42, 0, 2);                         // Set the static IP address to use if the DHCP fails to assign
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

String Servidor::get(String endpoint) {  
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

    String readString = readResult();
    Serial.print("[Servidor] resposta");
    Serial.println(readString);

    client.stop(); //TODO testar!

    return readString;
}

String Servidor::post(String endpoint, String data) {  
    // if you get a connection, report back via serial:
    if (client.connect(server, 80)) {
        // Make a HTTP request:
        client.println("POST " + endpoint + " HTTP/1.1");
        client.println("Host: " + host);
        client.println("Content-Type: application/json");
        client.print("Content-Length: ");
        client.println(data.length());
        client.println();
        client.print(data);
    } else {
        // if you didn't get a connection to the server:
        Serial.println("connection failed");
    }

    String readString = readResult();
    Serial.print("[Servidor] resposta");
    Serial.println(readString);

    client.stop(); //TODO testar!

    return readString;
}

String Servidor::readResult() {
    String readString = String();

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

   return readString;
}

