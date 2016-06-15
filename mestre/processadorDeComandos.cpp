#include "processadorDeComandos.hpp"
#include <ArduinoJson.h>

void tratarComandoDeToldo(JsonObject& root);

void ProcessadorDeComandos::executar(String json) {
  StaticJsonBuffer<200> jsonBuffer;

  JsonObject& root = jsonBuffer.parseObject(json);
  const char* func = root["funcionalidade"];
  
  if(strcmp(func,"TOLDO") == 0) {
    tratarComandoDeToldo(root);
  }
}

void tratarComandoDeToldo(JsonObject& root) {
  bool aberto = root["aberto"];
  bool automatico = root["automatico"];
  const char* toldo = root["toldo"];

  // REPLACE ME
  // Processar o comando, abrir/fechar toldo
  Serial.print("aberto: ");
  Serial.println(aberto);
  Serial.print("automatico: ");
  Serial.println(automatico);
  Serial.print("toldo: ");
  Serial.println(toldo); // VARAL ou JARDIM
  // REPLACE ME  
}

