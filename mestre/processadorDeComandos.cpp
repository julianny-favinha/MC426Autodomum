#include "processadorDeComandos.hpp"

#define ESTENDER_TOLDO 0
#define RECOLHER_TOLDO 1
#define DESLIGA_OU_LIGA_TOLDO 2

void ProcessadorDeComandos::executar(String json) {
  StaticJsonBuffer<200> jsonBuffer;

  JsonObject& root = jsonBuffer.parseObject(json);
  const char* func = root["funcionalidade"];
  
  if(strcmp(func,"TOLDO") == 0) {
    tratarComandoDeToldo(root);
  }
}

void ProcessadorDeComandos::tratarComandoDeToldo(JsonObject& root) {
  int aberto = root["aberto"];
  bool automatico = root["automatico"];
  const char* toldo = root["toldo"];

  //DEBUG
  printStatus(aberto, automatico, toldo);

  switch(aberto){
    case ESTENDER_TOLDO: {
      if(strcmp(toldo,"VARAL") == 0) {
        varal.estende();
      } else {
        jardim.estende();
      }
    }
    break;
    case RECOLHER_TOLDO: {
      if(strcmp(toldo,"VARAL") == 0) {
        varal.recolhe();
      } else {
        jardim.recolhe();
      }
    }
  }

  if(strcmp(toldo,"VARAL") == 0) {
    varal.setAutomatico(automatico);
  } else {
    jardim.setAutomatico(automatico);
  }
}

void ProcessadorDeComandos::printStatus(int aberto, bool automatico, String toldo) {
  Serial.print("aberto: ");
  Serial.println(aberto);
  Serial.print("automatico: ");
  Serial.println(automatico);
  Serial.print("toldo: ");
  Serial.println(toldo);
}

