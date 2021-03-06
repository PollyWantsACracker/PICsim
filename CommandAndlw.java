public class CommandAndlw extends Command {
  
  public CommandAndlw(int aProgrammCounterLine, int aParameter1, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, 0, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue = wRegister.getValue();
    int newValue = actualValue & parameter1;
    
    if (newValue == 0) {
      
      setZero(true);
      
    } else {
      
      setZero(false);
      
    }
    
    wRegister.setValue(newValue);
    return -1;
    
  }    
}