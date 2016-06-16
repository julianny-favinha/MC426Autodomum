#include <SPI.h>
#include <ArduinoJson.h>
#include "toldo.hpp"

class ProcessadorDeComandos {
  public:
    ProcessadorDeComandos(Toldo &v, Toldo &j) :
      varal(v),
      jardim(j)
    {}
    void executar(String json);
  private:
    Toldo& varal;
    Toldo& jardim;
    void tratarComandoDeToldo(JsonObject& root);
    void printStatus(int aberto, bool automatico, String toldo);
};
