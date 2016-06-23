// Autenticar RFID
String endpoint = "/usuario/rfid?rfid="
endpoint.concat(rfid);

String response = servidor.get(endpoint);
JsonObject& root = jsonBuffer.parseObject(response);

bool autenticou = root["success"];

if(autenticou) {
    // Ok vai tocar
} else {
    // Invalido, nao vai tocar nada
}

// Salvar Historico do Jardim
String data = "{\"estadoChuva:\":";
if(<SECO>) {
    data.concat("\"SECO\"");
} else if(<CHUVA_FRACA>) {
    data.concat("\"CHUVA_FRACA\"");
} else {
    data.concat("\"CHUVA_FORTE\"");
}

data.concat(",\"temperatura\":");
data.concat(temperatura);
data.concat("}");

String endpoint = "/jardim/historico";

servidor.post(endpoint, data);