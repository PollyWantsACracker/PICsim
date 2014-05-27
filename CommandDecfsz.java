public class CommandDecfsz extends Command{
  
  public CommandDecfsz (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue;
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      actualValue = dataStorage.getValue(getBankOffset() + 4);
      
    } else { // direkte Adressierung
      
      actualValue = dataStorage.getValue(getBankOffset() + parameter1);
      
    } 
    
    int newValue = actualValue - 1;
    
    if (newValue < 0) {
      
      newValue += 256;
      
    } 
    
    if (parameter2 == 0) { // Ergenis in W
      
      wRegister.setValue(newValue);
      
    } else { // Ergebnis in F
      
      if (parameter1 == 0) { // indirekte Adressierung
        
        dataStorage.setValue(getBankOffset() + 4, newValue);
        
      } else { // direkte Adressierung
       
        dataStorage.setValue(getBankOffset() + parameter1, newValue);
        
      }
    }
    
    if (newValue == 0) {
      
      this.machineCycles = 2;
      return -2;
      
    } else {
      
      this.machineCycles = 1;
      return -1;
      
    }
  }
}