public class CommandBcf extends Command {
  
  public CommandBcf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue;
    
    if (parameter1 == 0) {
      
      actualValue = dataStorage.getValue(getBankOffset() + 4);
      int value = ((int)(Math.pow(2, parameter2))) ^ 0xFF;
      dataStorage.setValue(getBankOffset() + 4 , actualValue & value);
      
      
    } else {
      
      actualValue = dataStorage.getValue(getBankOffset() + parameter1);
      int value = ((int)(Math.pow(2, parameter2))) ^ 0xFF;
      dataStorage.setValue(getBankOffset() + parameter1 , actualValue & value);
      
    } 
    
    return 1;
    
  }
}
