public class CommandBcf extends Command {
  
  public CommandBcf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue;
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      actualValue = dataStorage.getValue(getBankOffset() + 4);
      
    } else { // direkte Adressierung
      
      actualValue = dataStorage.getValue(getBankOffset() + parameter1);
      
    } 
    
    int newValue = actualValue & ((int)(Math.pow(2, parameter2)) ^ 0xFF);
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      dataStorage.setValue(getBankOffset() + 4, actualValue);
      
    } else { // direkte Adressierung
      
      dataStorage.setValue(getBankOffset() + parameter1, actualValue);
      
    } 
    
    return -1;
    
  }
}