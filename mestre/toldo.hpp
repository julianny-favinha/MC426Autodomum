#include <Stepper.h>

class Toldo {
	public:
    		Toldo(String type, int pin1, int pin2, int pin3, int pin4);
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
        void checaJardim();
        void checaVaral();
};
