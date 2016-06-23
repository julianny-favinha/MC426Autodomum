#include <Arduino.h>
#include "DHT.h"
#include "toldo.hpp"

//Sensor de Temperatura - DHT22
#define DHTPIN 2     // what pin we're connected to
#define DHTTYPE DHT22   // DHT 22  (AM2302)

//---( Steps per OUTPUT SHAFT of gear reduction )---
#define steps2take 32 * 16  //1/4 de volta

//Limites superior e inferior dos sensores de umidade e de chuva
#define UMIDADE_MAX 400
#define UMIDADE_MIN 250
#define CHUVA_MAX 1024
#define CHUVA_MIN 0
#define TEMP_MAX 40

//Estados do sensor de chuva
#define MUITA 0
#define POUCA 1
#define NENHUMA 2

//Portas dos sensores
#define SENSOR_CHUVA A0
#define SENSOR_UMIDADE A1

//Porta Módulo Relé - Válvula Solenoide
#define RELE 3

#define HOSTORICO_TOLDO_ENDPOINT "/toldo/historico"

DHT dht(DHTPIN, DHTTYPE);
boolean dhtBegin = false;

void Toldo::recolhe() {
  
	if (estendido){
		(*motor).setSpeed(500);
		(*motor).step((-steps2take) * this->sentido);
		estendido = false;
    registrarHistorico();
	}
}

void Toldo::estende() {
  
	if (!estendido){
		(*motor).setSpeed(500);
		(*motor).step(steps2take * this->sentido);
		estendido = true;
    registrarHistorico();
	}
}

String Toldo::retornaEstado(){
	return ((estendido) ? "estendido" : "recolhido");
}

void Toldo::setAutomatico(bool a){
  automatico = a;
}

void Toldo::checa(){
  if (automatico){
    if (tipo == "VARAL") checaVaral();
    else if (tipo == "JARDIM") checaJardim();
  }
}

void Toldo::checaVaral(){  
  // Mapeamento das leituras do sensor de chuva na porta analogica A0
  int chuva = map(analogRead(SENSOR_CHUVA), CHUVA_MIN, CHUVA_MAX, 0, 3);

  switch(chuva){
       case MUITA:
       case POUCA:
         estende();
         break;
       case NENHUMA:
         recolhe();
         break;
   }
}

void Toldo::checaJardim(){
  // Leitura do sensor de umidade na porta analogica A1
  if(!dhtBegin){
    dhtBegin = true;
    dht.begin();
  }
  int umidade = analogRead(SENSOR_UMIDADE);
  float temperatura = dht.readTemperature();
  Serial.print("Temperatura: ");
  Serial.println(temperatura);
  // Mapeamento das leituras do sensor de chuva na porta analogica A0
  int chuva = map(analogRead(SENSOR_CHUVA), CHUVA_MIN, CHUVA_MAX, 0, 3);

  if(umidade >= UMIDADE_MAX){ // Solo encharcado
    switch(chuva){
      case MUITA:
      case POUCA:
        estende();
        break;
      case NENHUMA:
        if(temperatura < TEMP_MAX){
          recolhe();
        }else{
          estende();
        }
        break;
    }
  } else if(umidade <= UMIDADE_MIN){ // Solo seco
    if(temperatura < TEMP_MAX){
      recolhe();
      ligaValvula();
    }else{
      estende();
    }
  }
}

void Toldo::registrarHistorico() {
  String data = "{\"estendido\" : ";
  if(estendido) {
    data.concat("\"true\"");
  } else {
    data.concat("\"false\"");
  }
  data.concat(", \"automatico\" : \"");
  if(automatico) {
    data.concat("\"true\"");
  } else {
    data.concat("\"false\"");
  }

  data.concat(", \"toldo\" : \"");
  data.concat(tipo);
  data.concat("\"}");

  Serial.print("[TOLDO] Registrando historico: ");
  Serial.println(data);

  servidor.post(HOSTORICO_TOLDO_ENDPOINT, data);
}

void Toldo::ligaValvula(){
  digitalWrite(RELE, LOW);
  Serial.println("Rele Ligado");
  delay(6000);
  digitalWrite(RELE, HIGH);
  Serial.println("Rele Desligado");
}
