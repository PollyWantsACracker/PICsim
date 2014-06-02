public class DataStorage {
  
  private int[] dataStorage;
  
  public DataStorage() {
    
    dataStorage = new int[256];
    initDataStorage();
    
  }
  
  public int getValue(int index) {
    
    return dataStorage[index];
    
  }
  
  public int getProgrammCounter() {

    return dataStorage[2];
    
  }
  
  public void setProgrammCounter(int newProgrammCounter) {
    
    dataStorage[130] = newProgrammCounter;
    dataStorage[2] = newProgrammCounter;
    
  }
  
  public void setValue(int index, int value) {
    
    if (index == 3) { // Statusregister ist über Adresse 03h (3) und Adresse 83h (131) erreichbar
      
      dataStorage[131] = value;
      
    }
    
    if (index == 131) { // siehe oben
      
      dataStorage[3] = value;
      
    } 
    
    dataStorage[index] = value;
    
  }
  
  private void initDataStorage() {
    
    for (int i = 0; i < dataStorage.length; i++) {
      
      dataStorage[i] = 0;
      
    } 
    
  }
  
  public void resetDataStorageWDTInterrupt() { // WDT-Reset: Interrupt
    
    dataStorage[0] = 0;
    dataStorage[2] = dataStorage[2] + 1; // unvollständig
    dataStorage[3] = 16 + (dataStorage[3] & 0xe7);
    dataStorage[5] = dataStorage[5] & 0x1f;
    dataStorage[10] = dataStorage[10] & 0x1f;
    dataStorage[128] = 0;
    dataStorage[130] = dataStorage[130] + 1; // unvollständig
    dataStorage[131] = 16 + (dataStorage[131] & 0xe7);
    dataStorage[133] = dataStorage[133] & 0x1f;
    dataStorage[136] = dataStorage[136] & 0x0f;
    dataStorage[137] = 0;
    dataStorage[138] = dataStorage[138] & 0x1f;    
    
  }
  
  public void resetDataStorageWDTWakeUp() { // WDT-Reset: Wake Up
    
    dataStorage[0] = 0;
    dataStorage[2] = dataStorage[2] + 1;
    dataStorage[3] = dataStorage[3] & 0xe7;
    dataStorage[5] = dataStorage[5] & 0x1f;
    dataStorage[10] = dataStorage[10] & 0x1f;
    dataStorage[128] = 0;
    dataStorage[130] = dataStorage[130] + 1;
    dataStorage[131] = dataStorage[131] & 0xe7;
    dataStorage[133] = dataStorage[133] & 0x1f;
    dataStorage[136] = dataStorage[136] & 0x0f;
    dataStorage[137] = 0;
    dataStorage[138] = dataStorage[138] & 0x1f;    
    
  }
  
  public void resetDataStorageWDTNormalOperation() { // WDT-Reset: normal Operation
    
    dataStorage[0] = 0;
    dataStorage[2] = 0;
    dataStorage[3] = 8 + (dataStorage[3] & 0x07);
    dataStorage[5] = dataStorage[5] & 0x1f;
    dataStorage[10] = 0;
    dataStorage[11] = dataStorage[11] & 0x01;
    dataStorage[128] = 0;
    dataStorage[129] = 255;
    dataStorage[130] = 0;
    dataStorage[131] = 8 + (dataStorage[131] & 0x07);
    dataStorage[133] = 31;
    dataStorage[134] = 255;
    dataStorage[136] = dataStorage[136] & 0x08; // Bedeutung von q nicht bekannt
    dataStorage[137] = 0;
    dataStorage[138] = 0;
    dataStorage[139] = dataStorage[139] & 0x01;   
    
  }
  
  public void resetDataStorageSleep() { // !MCLR-Reset: Sleep
    
    dataStorage[0] = 0;
    dataStorage[2] = 0;
    dataStorage[3] = 16 + (dataStorage[3] & 0x07);
    dataStorage[5] = dataStorage[5] & 0x1f;
    dataStorage[10] = 0;
    dataStorage[11] = dataStorage[11] & 0x01;
    dataStorage[128] = 0;
    dataStorage[129] = 255;
    dataStorage[130] = 0;
    dataStorage[131] = 16 + (dataStorage[131] & 0x07);
    dataStorage[133] = 31;
    dataStorage[134] = 255;
    dataStorage[136] = dataStorage[136] & 0x08; // Bedeutung von q nicht bekannt
    dataStorage[137] = 0;
    dataStorage[138] = 0;
    dataStorage[139] = dataStorage[139] & 0x01;   
    
  }
  
  public void resetDataStorageNormalOperation() { // !MCLR-Reset: normal Operation
    
    dataStorage[0] = 0;
    dataStorage[2] = 0;
    dataStorage[3] = dataStorage[3] & 0x1f;
    dataStorage[5] = dataStorage[5] & 0x1f;
    dataStorage[10] = 0;
    dataStorage[11] = dataStorage[11] & 0x01;
    dataStorage[128] = 0;
    dataStorage[129] = 255;
    dataStorage[130] = 0;
    dataStorage[131] = dataStorage[131] & 0x1f;
    dataStorage[133] = 31;
    dataStorage[134] = 255;
    dataStorage[136] = dataStorage[136] & 0x08; // Bedeutung von q nicht bekannt
    dataStorage[137] = 0;
    dataStorage[138] = 0;
    dataStorage[139] = dataStorage[139] & 0x01;   
    
  }
  
  public void resetDataStoragePowerOn() { // Power-on Reset
    
    dataStorage[0] = 0;  
    dataStorage[2] = 0;  
    dataStorage[3] = 24 + (dataStorage[3] & 0x07);  
    dataStorage[5] = dataStorage[5] & 0x1f;  
    dataStorage[10] = 0;  
    dataStorage[11] = dataStorage[11] & 0x01;  
    dataStorage[128] = 0;  
    dataStorage[129] = 255;
    dataStorage[130] = 0;  
    dataStorage[131] = 24 + (dataStorage[131] & 0x07);
    dataStorage[133] = 31;
    dataStorage[134] = 255;
    dataStorage[136] = dataStorage[136] & 0x08;
    dataStorage[137] = 0;
    dataStorage[138] = 0;
    dataStorage[139] = dataStorage[139] & 0x01;
    
  }
}