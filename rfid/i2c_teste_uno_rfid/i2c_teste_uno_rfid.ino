// Include the required Wire library for I2C
#include <Wire.h>
#include <SPI.h>  
#include <RFID.h>  
#define SS_PIN 10  
#define RST_PIN 9  
RFID rfid(SS_PIN,RST_PIN);
int serNum[5];  
int x = 0;
void setup() {
  Serial.begin(9600); // Inicia a serial
  // Start the I2C Bus as Master
  Wire.begin(); 
  SPI.begin();  
  rfid.init();  
}
void loop() {
  //ESCREVE
  if(rfid.isCard()){  
   if(rfid.readCardSerial()) {  
    Serial.print(rfid.serNum[0],DEC);  
    Serial.print(" ");  
    Serial.print(rfid.serNum[1],DEC);  
    Serial.print(" ");  
    Serial.print(rfid.serNum[2],DEC);  
    Serial.print(" ");  
    Serial.print(rfid.serNum[3],DEC);  
    Serial.print(" ");  
    Serial.print(rfid.serNum[4],DEC);  
    Serial.println("");  
    Wire.beginTransmission(9); // transmit to device #9
    Wire.write(rfid.serNum[0]);
    Wire.write(rfid.serNum[1]);
    Wire.write(rfid.serNum[2]);
    Wire.write(rfid.serNum[3]);
    Wire.write(rfid.serNum[4]);
    Wire.endTransmission();    // stop transmitting
   }  
  }
  rfid.halt();
  delay(500);
}
