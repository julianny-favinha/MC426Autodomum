#include <SPI.h>
#include "servidor.hpp"
#include <ArduinoJson.h>
#include <string.h>

#define HOST "secure-bastion-88575.herokuapp.com"
#define COMMAND_ENDPOINT "/casa/listening"

Servidor servidor;

void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }

  servidor.connect();
  Serial.println("connecting the ethernet shield...");

}

void loop() {

  Serial.println("checking for new commands...");
  String readString = servidor.request(HOST, COMMAND_ENDPOINT);
  servidor.endRequest();

  processCommand(readString);

}

/*************** FUNCOES *****************/
void processCommand(String json) {
  StaticJsonBuffer<200> jsonBuffer;

  JsonObject& root = jsonBuffer.parseObject(json);
  const char* func = root["funcionalidade"];
  
  if(strcmp(func,"TOLDO") == 0) {
      bool aberto = root["aberto"];
      bool automatico = root["automatico"];
      const char* toldo = root["toldo"];

      // REPLACE ME
      // Processar o comando, abrir/fechar toldo
      Serial.print("funcionalidade: ");
      Serial.println(func);
      Serial.print("aberto: ");
      Serial.println(aberto);
      Serial.print("automatico: ");
      Serial.println(automatico);
      Serial.print("toldo: ");
      Serial.println(toldo); // VARAL ou JARDIM
      // REPLACE ME
  }
}



