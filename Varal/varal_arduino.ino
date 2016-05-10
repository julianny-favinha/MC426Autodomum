#include <CustomStepper.h> 

//Limites superior e inferior do sensor de chuva
const int sensorMin = 0;     // minimo
const int sensorMax = 1024;  // maximo

boolean toldo_extendido = false;
boolean mudou = false;

//Define os parametros iniciais de ligacao do motor de passo  
CustomStepper stepper(8, 9, 10, 11, (byte[]){8, B1000, B1100, B0100, 
B0110, B0010, B0011, B0001, B1001}, 4075.7728395, 12, CW);  

void setup() {
  //Define a velocidade do motor  
  stepper.setRPM(12);  
  //Define o numero de passos por rotacao  
  stepper.setSPR(4075.7728395);  
}
void loop() {
  // Leitura do sensor na porta analogica A0
	int sensorReading = analogRead(A0);
  // Mapeamento das leituras do sensor
	int range = map(sensorReading, sensorMin, sensorMax, 0, 3);

  // range value:
  switch (range) {
    case 0:    // Sensor esta (muito) molhado - Usar este estado no jardim
      if(toldo_extendido == false){
          toldo_extendido = true;
          mudou = true;
      }
    break;
    case 1:    // Sensor esta molhado
      if(toldo_extendido == false){
          toldo_extendido = true;
          mudou = true;
      }
    break;
    case 2:    // Sensor esta seco
      if(toldo_extendido == true){
        toldo_extendido = false;
        mudou = true;
      }
    break;
  }

  //Verifica se motor está parado
  if (stepper.isDone()){
   //Intervalo entre acionamentos/leituras de estado
   delay(2000);//2000ms = 2s
   //Define o sentido de rotacao (CW = Horario, CCW = AntiHorario)
   if(mudou == true){
      if(toldo_extendido){//Recolher Toldo
        stepper.setDirection(CW);
        stepper.rotateDegrees(90);
      }else{//Extender Toldo
        stepper.setDirection(CCW);
        stepper.rotateDegrees(90);
      }
   }else{
      //Se nao houve mudanca, parar(STOP)
      stepper.setDirection(STOP);
   }
  }  

  //Movimenta motor de acordo com definicoes
  stepper.run();
  mudou = false;

}
