public class CommandGoto extends Command {
  
  public CommandGoto (int aProgrammCounterLine, int aParameter1, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, 0, aMachineCycles, aDataStorage, aWRegister);
    
  }

  public int executeCommand() {
    
    return parameter1;
    
  }
}
