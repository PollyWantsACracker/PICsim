public class CommandClrf extends Command {
  
  public CommandClrf (int aProgrammCounterLine, int parameter1, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, parameter1, 0, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int newValue = 0;
    int registerIndex = 0;
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      registerIndex = dataStorage.getValue(getBankOffset() + 4);
      
      if (registerIndex == 0) { // NOP
        
        return -1;
        
      } 
      
      dataStorage.setValue(registerIndex, newValue);
      
    } else { // direkte Adressierung
      
      dataStorage.setValue(getBankOffset() + parameter1, newValue);
      
    }
    
    setZero(true);
    return -1;
    
  }
}