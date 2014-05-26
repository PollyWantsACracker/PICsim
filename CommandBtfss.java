public class CommandBtfss extends Command{
  
  public CommandBtfss (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue;
    
    if (parameter1 == 0) {
      
      actualValue = dataStorage.getValue(getBankOffset() + 4);
      
    } else {
      
      actualValue = dataStorage.getValue(getBankOffset() + parameter1);
      
    } 
    
    if ((actualValue & (int)(Math.pow(2, parameter2))) == 0) {
      
      return 1;
      
    } 

    return 2;
    
  }
}
