#include <SPI.h>
#include <string.h>
#include <Wire.h>
#include "processadorDeComandos.hpp"

//Informacoes para conectar no servidor
#define HOST "secure-bastion-88575.herokuapp.com"
#define COMMAND_ENDPOINT "/casa/comando"

//Portas dos sensores
#define SENSOR_CHUVA A0
#define SENSOR_UMIDADE A1

//Portas dos LEDs do RFID
#define TAG_IDENTIFICADA 13
#define TAG_NAO_IDENTIFICADA 12

//Vetor da conexão serial com o arduino do RFID
int serNum[5];

String rfidLido;

Servidor servidor(HOST);

//Toldos conectados ao Arduino
Toldo jardim("JARDIM", 8, 10, 9, 11, servidor, 1);
Toldo varal("VARAL", 4, 6, 5, 7, servidor, -1);

void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial); // wait for serial port to connect. Needed for native USB port only

  // Conexao com o ethernet shield
  servidor.connect();
  
  // Inicia a conexão I2C BUS como escravo pelo endereço 9
  Wire.begin(9);
  Wire.onReceive(receiveEvent);

  // Define modo de pinos e LEDs como Output
  pinMode(13, OUTPUT);
  pinMode(12, OUTPUT);

  Serial.println("Ending setup...");

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
  rfid_checa();
  printStats();

  delay(1000);
}

void rfid_checa(){
  String rfid = "";
  for (int i=0; i < 5; i++)
  {
    rfid.concat(serNum[i]);
  }

  Serial.print("RFID: ");
  Serial.println(rfid);

  if(rfidLido != rfid) {
    String endpoint = "/usuario/rfid?rfid=";
    endpoint.concat(rfid);
    
    String response = servidor.get(endpoint);
    StaticJsonBuffer<200> jsonBuffer;
    JsonObject& root = jsonBuffer.parseObject(response);
    
    bool identificado = root["sucesso"];
    Serial.print("indentificado: ");
    Serial.println(identificado);
    if(identificado){
      digitalWrite(TAG_NAO_IDENTIFICADA, LOW);
      digitalWrite(TAG_IDENTIFICADA, HIGH);
    }else{
      digitalWrite(TAG_IDENTIFICADA, LOW);
      digitalWrite(TAG_NAO_IDENTIFICADA, HIGH);
    }
    rfidLido = rfid;
  }
}

void recebeComandosExternos() {
  ProcessadorDeComandos processadorDeComandos(varal, jardim);

  String readString = servidor.get(COMMAND_ENDPOINT);
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
