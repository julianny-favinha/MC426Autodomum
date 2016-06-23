#include <Stepper.h>
#include "servidor.hpp"

//Porta Módulo Relé - Válvula Solenoide
#define RELE 3

//---( Number of steps per revolution of INTERNAL motor in 4-step mode )---
#define STEPS_PER_MOTOR_REVOLUTION 32

class Toldo {
	public:
        Toldo(String type, int pin1, int pin2, int pin3, int pin4, Servidor &s, int sentido_motor) : servidor(s){
          estendido = false;
          automatico = true;
          tipo = type;
          sentido = sentido_motor;
          //Setup do rele
          pinMode(RELE, OUTPUT);
          digitalWrite(RELE, HIGH);
          motor = new Stepper(STEPS_PER_MOTOR_REVOLUTION, pin1, pin2, pin3, pin4);
        }
        void recolhe();
    		void estende();
    		String retornaEstado();
        void setAutomatico(bool a);
        void checa();
  private:
        bool estendido;
        bool automatico;
        int sentido;
        String tipo;
        Stepper *motor;
        Servidor& servidor;
        void checaJardim();
        void checaVaral();
        void registrarHistorico();
        void registraHistoricoJardim(int chuva, float temperatura);
        void ligaValvula();
};
