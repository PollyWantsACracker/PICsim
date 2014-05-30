public class CommandComf extends Command{
  
  public CommandComf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue = 0;
    int registerIndex = 0;
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      registerIndex = dataStorage.getValue(getBankOffset() + 4);
      
      if (registerIndex == 0) { // NOP
        
        return -1;
        
      } 
      
      actualValue = dataStorage.getValue(registerIndex);
      
    } else { // direkte Adressierung
      
      actualValue = dataStorage.getValue(getBankOffset() + parameter1);
      
    } 
    
    int complementedValue = actualValue ^ 0xff;
    
    if (complementedValue == 0) {
      
      setZero(true);
      
    } else {
      
      setZero(false);
      
    } 
    
    if (parameter2 == 0) { // Ergebnis in W
      
      wRegister.setValue(complementedValue);
      
    } else { // Ergebnis in F
      
      if (parameter1 == 0) { // indirekte Adressierung
        
        dataStorage.setValue(registerIndex, complementedValue);
        
      } else { // direkte Adressierung
        
        dataStorage.setValue(getBankOffset() + parameter1, complementedValue);
        
      } 
    } 
    
    return -1; 
    
  }
}