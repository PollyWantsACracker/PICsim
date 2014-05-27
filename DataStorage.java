public class DataStorage {
  
  private int[] dataStorage;
  
  public DataStorage() {
    
    dataStorage = new int[256];
    initDataStorage();
    
  }
  
  public int getValue(int index) {
    
    return dataStorage[index];
    
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
  
  public void resetDataStorage() { 
    
    for (int i = 0; i < dataStorage.length; i++ ) {
      
      switch (i) {
        
        case  0: dataStorage[i] = 0;
        continue;
        
        case  1: 
        continue;
        
        case  2: dataStorage[i] = 0;
        continue;
        
        case  3: dataStorage[i] = 0;
        continue;  
        
  
        default: 
        
      } // end of switch
      
    }
  }
  
  public void resetDataStoragePowerOn() { // Power-on Reset
    
    for (int i = 0; i < dataStorage.length; i++ ) {
      
      switch (i) {
        
        case  3: dataStorage[i] = 24;  
        continue;
        
        case  129: dataStorage[i] = 255;
        continue; 
        
        case 131: dataStorage[i] = 24;
        continue;
        
        case 133: dataStorage[i] = 31;
        continue;
        
        case 134: dataStorage[i] = 255;
        continue;
        
        default: dataStorage[i] = 0;
        continue;
        
      }  
    } 
  }
}