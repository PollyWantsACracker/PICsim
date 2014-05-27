public class CommandRetlw extends Command{
  
  private Stack stack;
  
  public CommandRetlw (int aProgrammCounterLine, int aParameter1, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister, Stack aStack) {
    
    super(aProgrammCounterLine, aParameter1, 0, aMachineCycles, aDataStorage, aWRegister);
    stack = aStack;

  }

  public int executeCommand() {
    
    wRegister.setValue(parameter1);
    return stack.getLastAddress();
    
  } 
}