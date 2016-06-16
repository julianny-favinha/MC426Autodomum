#include <Stepper.h>
#include "servidor.hpp"

//---( Number of steps per revolution of INTERNAL motor in 4-step mode )---
#define STEPS_PER_MOTOR_REVOLUTION 32

class Toldo {
	public:
        Toldo(String type, int pin1, int pin2, int pin3, int pin4, Servidor &s) : servidor(s){
          estendido = false;
          automatico = true;
          tipo = type;
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
        String tipo;
        Stepper *motor;
        Servidor& servidor;
        void checaJardim();
        void checaVaral();
        void registrarHistorico();
};
