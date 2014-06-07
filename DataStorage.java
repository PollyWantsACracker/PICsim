import java.util.concurrent.locks.*;

public class DataStorage {
  
  private int[] dataStorage;
  private Lock threadLock;
  
  public DataStorage() {
    
    dataStorage = new int[256];
    initDataStorage();
    threadLock = new ReentrantLock();
    
  }
  
  public int getValue(int index) {
    
    int value = 0;
    threadLock.lock();
    
    try {
      
      value = dataStorage[index];
      
    } catch(Exception e) {
      
    } finally {
      
      threadLock.unlock();
      return value;
      
    } 
  }
  
  public int getProgrammCounter() {
    
    return getValue(2);
    
  }
  
  public void setProgrammCounter(int newProgrammCounter) {
    
    setValue(130, newProgrammCounter);
    setValue(2, newProgrammCounter);
    
  }
  
  public void setValue(int index, int value) {
    
    threadLock.lock();
    
    try {
      
      if (index == 3) { // Statusregister ist über Adresse 03h (3) und Adresse 83h (131) erreichbar
        
        dataStorage[131] = value;
        
      }
      
      if (index == 131) { // siehe oben
        
        dataStorage[3] = value;
        
      } 
      
      dataStorage[index] = value;
      
    } catch(Exception e) {
      
    } finally {
      
      threadLock.unlock();
      
    } 
  }
  
  private void initDataStorage() {
    
    for (int i = 0; i < dataStorage.length; i++) {
      
      dataStorage[i] = 0;
      
    } 
    
  }
  
  public void resetDataStorageWDTInterrupt() { // WDT-Reset: Interrupt
    
    setValue(0, 0);
    setValue(2, getValue(2) + 1); // unvollständig
    setValue(3, 16 + (getValue(3) & 0xe7));
    setValue(5, getValue(5) & 0x1f);
    setValue(10, getValue(10) & 0x1f);
    setValue(128, 0);
    setValue(130, getValue(130) + 1); // unvollständig
    setValue(133, getValue(133) & 0x1f);
    setValue(136, getValue(136) & 0x0f);
    setValue(137, 0);
    setValue(138, getValue(138) & 0x1f); 
    
  }
  
  public void resetDataStorageWDTWakeUp() { // WDT-Reset: Wake Up
    
    setValue(0, 0);
    setValue(2, getValue(2) + 1);
    setValue(3, getValue(3) & 0xe7);
    setValue(5, getValue(5) & 0x1f);
    setValue(10, getValue(10) & 0x1f);
    setValue(128, 0);
    setValue(130, getValue(130) + 1);
    setValue(133, getValue(133) & 0x1f);
    setValue(136, getValue(136) & 0x0f);
    setValue(137, 0);
    setValue(138, getValue(138) & 0x1f);    
    
  }
  
  public void resetDataStorageWDTNormalOperation() { // WDT-Reset: normal Operation
    
    setValue(0, 0);
    setValue(2, 0); 
    setValue(3, 8 + (getValue(3) & 0x07));
    setValue(5, getValue(5) & 0x1f);
    setValue(10, 0);  
    setValue(11, getValue(11) & 0x01);
    setValue(128, 0);  
    setValue(129, 255);
    setValue(130, 0); 
    setValue(133, 31);
    setValue(134, 255);
    setValue(136, getValue(136) & 0x08); // Bedeutung von q nicht bekannt
    setValue(137, 0);
    setValue(138, 0);
    setValue(139, getValue(139) & 0x01);
    
  }
  
  public void resetDataStorageSleep() { // !MCLR-Reset: Sleep
    
    setValue(0, 0);
    setValue(2, 0); 
    setValue(3, 16 + (getValue(3) & 0x07));
    setValue(5, getValue(5) & 0x1f);
    setValue(10, 0);  
    setValue(11, getValue(11) & 0x01);
    setValue(128, 0);  
    setValue(129, 255);
    setValue(130, 0); 
    setValue(133, 31);
    setValue(134, 255);
    setValue(136, getValue(136) & 0x08); // Bedeutung von q nicht bekannt
    setValue(137, 0);
    setValue(138, 0);
    setValue(139, getValue(139) & 0x01);
    
    
  }
  
  public void resetDataStorageNormalOperation() { // !MCLR-Reset: normal Operation
    
    setValue(0, 0);
    setValue(2, 0); 
    setValue(3, getValue(3) & 0x1f);
    setValue(5, getValue(5) & 0x1f);
    setValue(10, 0);  
    setValue(11, getValue(11) & 0x01);
    setValue(128, 0);  
    setValue(129, 255);
    setValue(130, 0); 
    setValue(133, 31);
    setValue(134, 255);
    setValue(136, getValue(136) & 0x08); // Bedeutung von q nicht bekannt
    setValue(137, 0);
    setValue(138, 0);
    setValue(139, getValue(139) & 0x01); 
    
  }
  
  public void resetDataStoragePowerOn() { // Power-on Reset
    
    setValue(0, 0);  
    setValue(2, 0);  
    setValue(3, 24 + (getValue(3) & 0x07));  
    setValue(5, getValue(5) & 0x1f);  
    setValue(10, 0);  
    setValue(11, getValue(11) & 0x01);  
    setValue(128, 0);  
    setValue(129, 255);
    setValue(130, 0);  
    setValue(133, 31);
    setValue(134, 255);
    setValue(136, getValue(136) & 0x08);
    setValue(137, 0);
    setValue(138, 0);
    setValue(139, getValue(139) & 0x01);
    
  }
}