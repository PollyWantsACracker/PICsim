public class DataStorage {
  
  private int[] dataStorage;
  
  public DataStorage() {
    
    dataStorage = new int[256]; 
    
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
}