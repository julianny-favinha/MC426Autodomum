#include "sensorDeChuva.hpp"

//Limites superior e inferior do sensor de chuva
const int sensorMin = 0;     // minimo
const int sensorMax = 1024;  // maximo

void SensorDeChuva::processarLeitura() {
  // Leitura do sensor na porta analogica A0
  int sensorReading = analogRead(A0);
  // Mapeamento das leituras do sensor
  int range = map(sensorReading, sensorMin, sensorMax, 0, 3);
  switch (range) {
  case 0: // Sensor esta muito molhado
    // TODO varal estende se já não estiver estendido
    // TODO jardim estende se já não estiver estendido
  break;
  case 1: // Sensor está pouco molhado
    // TODO varal estende se já não estiver estendido
    // TODO jardim recolhe se já não estiver recolhido
  break;
  case 2:    // Sensor está seco
    // TODO varal recolhe se já não estiver recolhido
    // TODO jardim recolhe se já não estiver recolhido
  break;
  }
}

