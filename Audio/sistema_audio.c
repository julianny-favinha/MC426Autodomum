int mpPin = 12;
int voltPin = 11;
int avancPin = 10;
int val = 0;
int playlist = 0

void setup()
{
  pinMode(mpPin, OUTPUT);
  pinMode(voltPin, OUTPUT);
  pinMode(avancPin, OUTPUT);
  Serial.begin(9600);
  Serial.flush();
}

void loop()
{
  /* Definir como ligar mp3 etc */
}
/* Funcao que liga e desliga mp3 */
void MpOnOff()
{
  Serial.println("On / Off");
  digitalWrite(mpPin, HIGH);
  delay(5000);
  digitalWrite(mpPin, LOW);
  delay(100);
}

/* Funcao que da play/pause mp3 */
void MpPlayPause()
{
  Serial.println("Play / Pause");
  digitalWrite(mpPin, HIGH);
  delay(100);
  digitalWrite(mpPin, LOW);
  delay(100);
}

/* Funcao que avanca musica no mp3 */
void MpForward()
{
  Serial.println("Avançar Musica");
  digitalWrite(avancPin, HIGH);
  delay(100);
  digitalWrite(avancPin, LOW);
  delay(100);
  val += 1;
}

/* Funcao que retrocede musica no mp3 */
void MpBackforward()
{
  Serial.println("Retroceder Musica");
  digitalWrite(voltPin, HIGH);
  delay(100);
  digitalWrite(voltPin, LOW);
  delay(100);
  digitalWrite(voltPin, HIGH);
  delay(100);
  digitalWrite(avancPin, LOW);
  delay(100);
  val -= 1;
}