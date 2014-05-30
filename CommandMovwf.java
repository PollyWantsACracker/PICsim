public class CommandMovwf extends Command {
  
  public CommandMovwf (int aProgrammCounterLine, int aParameter1, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, 0, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue = wRegister.getValue();
    int registerIndex = 0;
    
    if (parameter1 == 0){ // indirekte Addresierung
      
      registerIndex = dataStorage.getValue(getBankOffset() + 4);
      
      if (registerIndex == 0) { // NOP
        
        return -1;
        
      } 
      
      dataStorage.setValue(registerIndex, actualValue);
      
    } else { // direkte Adressierung
      
      dataStorage.setValue(getBankOffset() + parameter1, actualValue);
      
    } 
    
    return -1;
    
  }
}