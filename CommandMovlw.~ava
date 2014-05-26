public class CommandMovlw extends Command {
  
  public CommandMovlw (int aProgrammCounterLine, int aParameter1, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, 0, aMachineCycles, aDataStorage, aWRegister);
    
  }

  public int executeCommand() {
    
    wRegister.setValue(parameter1);
    return 1;
    
  }
}