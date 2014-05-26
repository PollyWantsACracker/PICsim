public class CommandBsf extends Command {
  
  public CommandBsf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
  }
  
  public int executeCommand() {
    
    int actualValue;
    
    if (parameter1 == 0) {
      
      actualValue = dataStorage.getValue(getBankOffset() + 4);
      dataStorage.setValue(getBankOffset() + 4, actualValue | ((int)(Math.pow(2, parameter2))));
      
    } else {
      
      actualValue = dataStorage.getValue(getBankOffset() + parameter1);
      dataStorage.setValue(getBankOffset() + parameter1, actualValue | ((int)(Math.pow(2, parameter2))));
      
    } 
    
    return 1;
    
  }
}
