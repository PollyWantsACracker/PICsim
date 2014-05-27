public class CommandSwapf extends Command {
  
  public CommandSwapf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue;
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      actualValue = dataStorage.getValue(getBankOffset() + 4);
      
    } else { // direkte Adressierung
      
      actualValue = dataStorage.getValue(getBankOffset() + parameter1);
      
    } 
    
    int newValue = (((actualValue & 0x0F) << 4) + ((actualValue & 0xF0) >> 4));
    
    if (parameter2 == 0) { // Ergebnis in W 
      
      wRegister.setValue(newValue);
      
    } else { // Ergebnis in F
      
      if (parameter1 == 0) { // indirekte Adressierung
        
        dataStorage.setValue(getBankOffset() + 4, newValue);
        
      } else { // direkte Adressierung
        
        dataStorage.setValue(getBankOffset() + parameter1, newValue);
        
      } 
    }  
    
    return -1;
    
  }
}