#include <SPI.h>
#include <Ethernet.h>

#define HOST_AUTODOMUM "secure-bastion-88575.herokuapp.com"
#define PING_ENDPOINT "/ping"

class WebClient {
    public:
        // Enter a MAC address for your controller below.
        // Newer Ethernet shields have a MAC address printed on a sticker on the shield
        byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
        // if you don't want to use DNS (and reduce your sketch size)
        // use the numeric IP instead of the name for the server:
        //IPAddress server(74,125,232,128);  // numeric IP for Google (no DNS)
        char server[] = "secure-bastion-88575.herokuapp.com";    // name address for Google (using DNS)
        // Set the static IP address to use if the DHCP fails to assign
        IPAddress ip(192, 168, 25, 14);

        // Initialize the Ethernet client library
        // with the IP address and port of the server
        // that you want to connect to (port 80 is default for HTTP):
        EthernetClient client;
};