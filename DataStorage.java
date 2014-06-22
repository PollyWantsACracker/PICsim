import java.util.concurrent.locks.*;
import java.util.ArrayList;

public class DataStorage {
  
  private int[] dataStorage;
  private Lock threadLock;
  private Steuerung steuerung;
  private int taktCounterHL = 0;
  private int taktCounterLH = 0;
  private boolean inhibit = false;
  private int inhibitValue = 0;
  
  public DataStorage(Steuerung aSteuerung) {
    
    dataStorage = new int[256];
    initDataStorage();
    threadLock = new ReentrantLock();
    steuerung = aSteuerung;
    
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
  
  private void proofTimer0(int t0ckiOld) {
    
    if ((dataStorage[0x81] & 32) == 32) { // Takt von RA4
      
      if ((dataStorage[0x81] & 16) == 16) { // high to low
        
        if (t0ckiOld - (dataStorage[5] & 16) == 16) {
          
          if ((dataStorage[0x81] & 8) == 8) { // ohne Vorteiler
            
            dataStorage[1] += 1;
            
            if (dataStorage[1] == 256) { // overflow
              
              dataStorage[1] = 0;
              
              if ((dataStorage[0xb] & 4) == 0 ) {
                
                dataStorage[0xb] += 4;
                
              } 
            } 
            
          } else { // mit Vorteiler
            
            taktCounterHL++;
            
            if (taktCounterHL % ((int) Math.pow(2.0,(double) (dataStorage[0x81] & 7)) * 2 ) == 0) {
              
              dataStorage[1] += 1;
              
              if (dataStorage[1] == 256) { // overflow
                
                dataStorage[1] = 0;
                
                if ((dataStorage[0xb] & 4) == 0 ) {
                  
                  dataStorage[0xb] += 4;
                  
                } 
              }
              
              taktCounterHL = 0;
              
            } 
          } 
        } 
        
      } else { // low to high
        
        if (t0ckiOld - (dataStorage[0x5] & 16) == -16) {
          
          if ((dataStorage[0x81] & 8) == 8) { // ohne Vorteiler
            
            dataStorage[1] += 1;
            
            if (dataStorage[1] == 256) { // overflow
              
              dataStorage[1] = 0;
              
              if ((dataStorage[0xb] & 4) == 0 ) {
                
                dataStorage[0xb] += 4;
                
              } 
            }
            
          } else { // mit Vorteiler
            
            taktCounterLH++;
            
            if (taktCounterLH % ((int) Math.pow(2.0,(double) (dataStorage[0x81] & 7)) * 2 ) == 0) {
              
              dataStorage[1] += 1;
              
              if (dataStorage[1] == 256) { // overflow
                
                dataStorage[1] = 0;
                
                if ((dataStorage[0xb] & 4) == 0 ) {
                  
                  dataStorage[0xb] += 4;
                  
                } 
              }
              
              taktCounterLH = 0;
              
            }
            
          } 
        } 
      }
    }  
  }
  
  public void timer0(int oldMachineCycleCounter, int machineCyclesCounter) {
    
    if ((dataStorage[0x81] & 32) == 32) { // Takt von RA4
      
      return;
      
    }
    
    if (inhibit) {
      
      inhibitValue -= (machineCyclesCounter - oldMachineCycleCounter);
      
      if (inhibitValue < 0) {
        
        inhibit = false;
        
      } else {
        
        steuerung.setMachineCycleCounter(oldMachineCycleCounter - 1, machineCyclesCounter - 1);
        return;
        
      } 
    } 
    
    if ((dataStorage[0x81] & 8) == 8) { // ohne Prescaler
      
      dataStorage[1] += 1;
      
      if (dataStorage[1] == 256) { // overflow
        
        dataStorage[1] = 0;
        
        if ((dataStorage[0xb] & 4) == 0 ) {
          
          dataStorage[0xb] += 4;
          
        } 
      } 
      
    } else { // Prescaler
      
      int preScaler = ((int) Math.pow(2.0,(double)(dataStorage[0x81] & 7)) * 2);
      
      if (machineCyclesCounter - oldMachineCycleCounter == 2) { // gotos and so on...
        
        if (machineCyclesCounter % preScaler == 0) {
          
          steuerung.setMachineCycleCounter(0, 0);
          dataStorage[1] += 1;
          
          if (dataStorage[1] == 256) { // overflow
            
            dataStorage[1] = 0;
            
            if ((dataStorage[0xb] & 4) == 0 ) {
              
              dataStorage[0xb] += 4;
              
            } 
          }
        } else if ((machineCyclesCounter - 1) % preScaler == 0){
          
          dataStorage[1] += 1;
          
          if (dataStorage[1] == 256) { // overflow
            
            dataStorage[1] = 0;
            
            if ((dataStorage[0xb] & 4) == 0 ) {
              
              dataStorage[0xb] += 4;
              
            } 
          }
        } 
        
      } else {
        
        if (machineCyclesCounter % preScaler == 0) {
          
          steuerung.setMachineCycleCounter(0, 0);
          dataStorage[1] += 1;
          
          if (dataStorage[1] == 256) { // overflow
            
            dataStorage[1] = 0;
            
            if ((dataStorage[0xb] & 4) == 0 ) {
              
              dataStorage[0xb] += 4;
              
            } 
          }
        }
      }
    } 
  }
  
  public void setProgrammCounter(int newProgrammCounter) {
    
    setValue(130, newProgrammCounter);
    setValue(2, newProgrammCounter);
    
  }
  
  public void setValue(int index, int value) {
    
    threadLock.lock();
    
    int t0ckiOld = dataStorage[5] & 16;
    
    if (index == 3) { // Statusregister ist über Adresse 03h (3) und Adresse 83h (131) erreichbar
      
      dataStorage[131] = value;
      
    }
    
    if (index == 131) { // siehe oben
      
      dataStorage[3] = value;
      
    } 
    
    dataStorage[index] = value;
    
    if (index == 1) {
      
      inhibit = true;
      inhibitValue = 2; //* ((int) Math.pow(2.0,(double)(dataStorage[0x81] & 7)) * 2);
      
    } // end of if
    
    try {
      
      if (steuerung.getMainFrame().getStatus().getText().equals("connected") && (index == 5 || index == 6 || index == 0x85 || index == 0x86)) {
        try {
          
          steuerung.getHardwareansteuerung().sendRS232();
          ArrayList<Integer> answer = steuerung.getHardwareansteuerung().read();
          int registerA = answer.get(0);
          int registerB = answer.get(1);
          
          dataStorage[5] = registerA & dataStorage[0x85];
          dataStorage[6] = registerB & dataStorage[0x86];
          
        } catch(Exception e) {
          
        } finally {
          
        } 
        
      }
      
      proofTimer0(t0ckiOld);
      
      
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