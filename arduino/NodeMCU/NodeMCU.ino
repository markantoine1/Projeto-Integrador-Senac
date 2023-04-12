#include <Wire.h>               //INCLUSÃO DE BIBLIOTECA
#include <LiquidCrystal_I2C.h>  //INCLUSÃO DE BIBLIOTECA

#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
#include <ArduinoJson.h>

#include <SPI.h>
#include <MFRC522.h>

#define RST_PIN D3
#define SS_PIN D4

MFRC522 mfrc522(SS_PIN, RST_PIN);    // Create MFRC522 instance.
LiquidCrystal_I2C lcd(0x27, 16, 2);  //FUNÇÃO DO TIPO "LiquidCrystal_I2C"


const char* ssid = "network";
const char* password = "12345678";
int readsuccess;
byte readcard[4];
char str[32] = "";
String StrUID;

String nomeProfessor;
String nomeCurso = "Técnico em Desenvolvimento de Sistemas";
String nomeSala = "005";

String url = "http://34.232.52.48:8080/alocacoes/fromnode";

void setup() {
  Serial.begin(115200);  // Initialize serial communications with the PC
  SPI.begin();           // Init SPI bus
  mfrc522.PCD_Init();    // Init MFRC522 card
  lcd.init();            // INICIALIZA O DISPLAY LCD
  lcd.backlight();       // HABILITA O BACKLIGHT (LUZ DE FUNDO)
  WiFi.begin(ssid, password);

}
// --------------------------------------------------------------------
void loop() {
  readsuccess = getid();

  if (readsuccess) {
    if (StrUID == "E1D3A91B") {
      nomeProfessor = "Mark";
    }
    if (StrUID == "E3F066BD") {
      nomeProfessor = "Julia";
    }
    lcd.clear();

    lcd.setCursor(0, 0);
    lcd.print("Bem vindo(a)");
    lcd.setCursor(0, 1);
    lcd.print(nomeProfessor);

    delay(2000);
    lcd.clear();

    while(!mfrc522.PICC_IsNewCardPresent()){
    lcd.setCursor(0, 0);
    lcd.print("Aproxime");
    lcd.setCursor(0, 1);
    lcd.print("para confirmar");
    }

    StaticJsonDocument<200> doc;
    doc["sala"] = nomeSala;
    doc["curso"] = nomeCurso;
    doc["professor"] = nomeProfessor;
    String json;
    serializeJson(doc, json);

    WiFiClient client;
    HTTPClient http;
    http.begin(client, url);
    http.addHeader("Content-Type", "application/json");
    int httpResponseCode = http.POST(json);

    // Verificar se a solicitação foi bem sucedida
    if (httpResponseCode == 201) {
      lcd.clear();
      lcd.setCursor(0, 0);        //SETA A POSIÇÃO EM QUE O CURSOR INCIALIZA(LINHA 1)
      lcd.print("Alocoado com");  //ESCREVE O TEXTO NA PRIMEIRA LINHA DO DISPLAY LCD
      lcd.setCursor(0, 1);
      lcd.print("sucesso!");
      delay(3000);
    } else {
      lcd.clear();
      lcd.setCursor(0, 0);   //SETA A POSIÇÃO EM QUE O CURSOR INCIALIZA(LINHA 1)
      lcd.print("Erro de");  //ESCREVE O TEXTO NA PRIMEIRA LINHA DO DISPLAY LCD
      lcd.setCursor(0, 1);
      lcd.print("conexao");
    }

    // Finalizar a conexão HTTP
    http.end();
  }
}
// --------------------------------------------------------------------
int getid() {
  if (!mfrc522.PICC_IsNewCardPresent()) {
    return 0;
  }
  if (!mfrc522.PICC_ReadCardSerial()) {
    return 0;
  }

  for (int i = 0; i < 4; i++) {
    readcard[i] = mfrc522.uid.uidByte[i];  //storing the UID of the tag in readcard
    array_to_string(readcard, 4, str);
    StrUID = str;
  }
  mfrc522.PICC_HaltA();
  return 1;
}
// --------------------------------------------------------------------
void array_to_string(byte array[], unsigned int len, char buffer[]) {
  for (unsigned int i = 0; i < len; i++) {
    byte nib1 = (array[i] >> 4) & 0x0F;
    byte nib2 = (array[i] >> 0) & 0x0F;
    buffer[i * 2 + 0] = nib1 < 0xA ? '0' + nib1 : 'A' + nib1 - 0xA;
    buffer[i * 2 + 1] = nib2 < 0xA ? '0' + nib2 : 'A' + nib2 - 0xA;
  }
  buffer[len * 2] = '\0';
}
