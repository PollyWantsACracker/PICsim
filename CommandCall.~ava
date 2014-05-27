public class CommandCall extends Command {
  
  private Stack stack;
  
  public CommandCall (int aProgrammCounterLine, int aParameter1, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister, Stack aStack) {
    
    super(aProgrammCounterLine, aParameter1, 0, aMachineCycles, aDataStorage, aWRegister);
    stack = aStack;
    
  }
  
  public int executeCommand() {
    
    stack.addAddress(programmCounterLine + 1);
    return parameter1;
    
  }
}