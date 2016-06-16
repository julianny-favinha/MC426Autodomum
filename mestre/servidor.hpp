#include <SPI.h>

class Servidor {
    public:
        Servidor(String h) {
          host = h;
        }
        void connect();
        String get(String endpoint);
        String post(String endpoint, String data);
    private:
        String host;
        String readResult();
};
