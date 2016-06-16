#include <Arduino.h>
#include <ctime>
#include "toldo.hpp"

//---( Steps per OUTPUT SHAFT of gear reduction )---
#define steps2take 32 * 16  //1/4 de volta

//Limites superior e inferior dos sensores de umidade e de chuva
#define UMIDADE_MAX 400
#define UMIDADE_MIN 250
#define CHUVA_MAX 1024
#define CHUVA_MIN 0

//Estados do sensor de chuva
#define MUITA 0
#define POUCA 1
#define NENHUMA 2

//Portas dos sensores
#define SENSOR_CHUVA A0
#define SENSOR_UMIDADE A1

void Toldo::recolhe() {
  
	if (estendido){
		(*motor).setSpeed(500);
		(*motor).step(-steps2take);
		estendido = false;
		delay(2000);
	}
}

void Toldo::estende() {
	if (!estendido){
		(*motor).setSpeed(500);
		(*motor).step(steps2take);
		estendido = true;
		delay(2000);
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
    if (tipo == "varal") checaVaral();
    else if (tipo == "jardim") checaJardim();
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
  int umidade = analogRead(SENSOR_UMIDADE);
  // Mapeamento das leituras do sensor de chuva na porta analogica A0
  int chuva = map(analogRead(SENSOR_CHUVA), CHUVA_MIN, CHUVA_MAX, 0, 3);

  if(umidade >= UMIDADE_MAX){ // Solo encharcado
    switch(chuva){
      case MUITA:
      case POUCA:
        estende();
        break;
      case NENHUMA:
        recolhe();
        break;
    }
  } else if(umidade <= UMIDADE_MIN){ // Solo seco
    recolhe();
  }
}

