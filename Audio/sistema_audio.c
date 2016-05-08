#include <SPI.h>
#include <RFID.h>
 
#define SS_PIN 10
#define RST_PIN 9
 
RFID rfid(SS_PIN,RST_PIN);
int serNum[5];
 
int cards[][5] = {
  {209,128,106,69,126}, // card 1
  {101,220,213,229,137} // card 2
};
 
bool access = false;

void setup()
{
  pinMode(mpPin, OUTPUT);
  pinMode(voltPin, OUTPUT);
  pinMode(avancPin, OUTPUT);
  Serial.begin(9600);
  Serial.flush();
}

/* Função que le RFID e verifica se faz parte da
matriz cards */
void loop()
{
   
  if(rfid.isCard()){
  
      if(rfid.readCardSerial()){
          Serial.print(rfid.serNum[0]);
          Serial.print(",");
          Serial.print(rfid.serNum[1]);
          Serial.print(",");
          Serial.print(rfid.serNum[2]);
          Serial.print(",");
          Serial.print(rfid.serNum[3]);
          Serial.print(",");
          Serial.print(rfid.serNum[4]);
          Serial.println("");
          
          for(int x = 0; x < sizeof(cards); x++){
            for(int i = 0; i < sizeof(rfid.serNum); i++ ){
                if(rfid.serNum[i] != cards[x][i]) {
                    access = false;
                    break;
                } else {
                    access = true;
                }
            }
            if(access) break;
          }
         
      }
      
     if(access){
        Serial.println("Allowed!"); 
     } else {
         Serial.println("Not allowed!"); 
     }        
  }
  
  rfid.halt();

}