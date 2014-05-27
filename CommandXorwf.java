public class CommandXorwf extends Command {
  
  public CommandXorwf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualWValue = wRegister.getValue();
    int actualFValue;
    
    if (parameter1 == 0) { // indirkete Adressierung
      
      actualFValue = dataStorage.getValue(getBankOffset() + 4);
      
    } else { // direkte Adressierung
      
      actualFValue = dataStorage.getValue(getBankOffset() + parameter1);
      
    } 
    
    int newValue = actualWValue ^ actualFValue;
    
    if (newValue == 0) {
      
      setZero(true);
      
    }
    
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