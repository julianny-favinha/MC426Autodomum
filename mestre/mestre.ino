#include <SPI.h>
#include <string.h>
#include "servidor.hpp"
#include "processadorDeComandos.hpp"
#include "sensorDeChuva.hpp"

#define HOST "secure-bastion-88575.herokuapp.com"
#define COMMAND_ENDPOINT "/casa/listening"

Servidor servidor;

void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial); // wait for serial port to connect. Needed for native USB port only

  servidor.connect();
}

void loop() {
  recebeComandosExternos();

  //lerSensorDeChuva();
}

void recebeComandosExternos() {
  ProcessadorDeComandos processadorDeComandos;

  String readString = servidor.request(HOST, COMMAND_ENDPOINT);
  processadorDeComandos.executar(readString);  
}

void leSensorDeChuva() {
  SensorDeChuva sensorDeChuva;

  sensorDeChuva.processarLeitura();
}
