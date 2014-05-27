public class CommandClrw extends Command {
  
  public CommandClrw (int aProgrammCounterLine, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, 0, 0, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    wRegister.setValue(0);
    setZero(true);
    return -1;
    
  }
}