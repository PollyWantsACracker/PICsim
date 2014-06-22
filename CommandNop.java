public class CommandNop extends Command {
  
  public CommandNop(int aProgrammCounterLine, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, 0, 0, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    return -1;
    
  }
}