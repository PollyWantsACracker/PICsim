public class CommandMovwf extends Command {
  
  public CommandMovwf (int aProgrammCounterLine, int aParameter1, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, 0, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    if (parameter1 == 0){ // indirekte Addresierung
      
      dataStorage.setValue(getBankOffset() + 4, wRegister.getValue());
      
    } else {
      
      dataStorage.setValue(getBankOffset() + parameter1, wRegister.getValue());
      
    } 
    
    return 1;
    
  }
}