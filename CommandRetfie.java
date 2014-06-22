public class CommandRetfie extends Command{
  
  private Stack stack;
  
  public CommandRetfie (int aProgrammCounterLine, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister, Stack aStack) {
    
    super(aProgrammCounterLine, 0, 0, aMachineCycles, aDataStorage, aWRegister);
    stack = aStack;
    
  }
  
  public int executeCommand() {
    
    dataStorage.setValue(0xb, (dataStorage.getValue(0xb) & 0x7f) + 128);
    return stack.getLastAddress();
    
  } 
}