#include <SPI.h>

class Servidor {
    public:
        void connect();
        String request(String host, String endpoint);
        void endRequest();
};
