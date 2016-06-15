#include <SPI.h>
#include <string.h>
#include <Wire.h>
#include "toldo.hpp"
#include "servidor.hpp"
#include "processadorDeComandos.hpp"
#include "sensorDeChuva.hpp"

//Informacoes para conectar no servidor
#define HOST "secure-bastion-88575.herokuapp.com"
#define COMMAND_ENDPOINT "/casa/listening"

//Portas dos sensores
#define SENSOR_CHUVA A0
#define SENSOR_UMIDADE A1

//Vetor da conexão serial com o arduino do RFID
int serNum[5];

//Toldos conectados ao Arduino
Toldo jardim("jardim", 8, 10, 9, 11);
Toldo varal("varal", 4, 6, 5, 7);

Servidor servidor;

void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial); // wait for serial port to connect. Needed for native USB port only

  // Conexao com o ethernet shield
  servidor.connect();
  
  // Inicia a conexão I2C BUS como escravo pelo endereço 9
  Wire.begin(9);
  Wire.onReceive(receiveEvent);
}

void receiveEvent(int bytes) {
  serNum[0] = Wire.read();
  serNum[1] = Wire.read();
  serNum[2] = Wire.read();
  serNum[3] = Wire.read();
  serNum[4] = Wire.read();
}

void loop() {
  recebeComandosExternos();

  varal.checa();
  jardim.checa();
  printStats();

  delay(1000);
}

void recebeComandosExternos() {
  ProcessadorDeComandos processadorDeComandos;

  String readString = servidor.request(HOST, COMMAND_ENDPOINT);
  processadorDeComandos.executar(readString);  
}

void printStats(){  
  Serial.print(" ");
  Serial.print(serNum[0],DEC);  
  Serial.print(" ");  
  Serial.print(serNum[1],DEC);  
  Serial.print(" ");  
  Serial.print(serNum[2],DEC);  
  Serial.print(" ");  
  Serial.print(serNum[3],DEC);  
  Serial.print(" ");  
  Serial.println(serNum[4],DEC);
  Serial.print(" jardim "); 
  Serial.println(jardim.retornaEstado());
  Serial.print(" varal "); 
  Serial.println(varal.retornaEstado());
  Serial.print(" umidade: ");
  Serial.println(analogRead(SENSOR_UMIDADE)); 
  Serial.print(" chuva: ");
  Serial.println(analogRead(SENSOR_CHUVA));
  Serial.println("--------------------");
}
