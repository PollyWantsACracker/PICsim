public class CommandMovf extends Command {
  
  public CommandMovf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualFValue = 0;
    int registerIndex = 0;
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      registerIndex = dataStorage.getValue(getBankOffset() + 4);
      
      if (registerIndex == 0) { // NOP
        
        return -1;
        
      } 
      
      actualFValue = dataStorage.getValue(registerIndex);
      
    } else { // direkte Adressierung
      
      actualFValue = dataStorage.getValue(getBankOffset() + parameter1);
      
    } 
    
    if (actualFValue == 0) {
      
      setZero(true);
      
    } else {
      
      setZero(false);
      
    } 
    
    if (parameter2 == 0) { // Ergebnis in W
      
      wRegister.setValue(actualFValue);
      
    } else { // Ergebnis in F
      
      if (parameter1 == 0) { // indirekte Adressierung
        
        dataStorage.setValue(registerIndex, actualFValue);
        
      } else { // direkte Adressierung
        
        dataStorage.setValue(getBankOffset() + parameter1, actualFValue);
        
      } 
    }
    
    return -1;
    
  }
}