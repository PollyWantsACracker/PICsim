public class CommandComf extends Command{
  
  public CommandComf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue;
    int complementedValue;
    
    if (parameter1 == 0 && parameter2 == 0) {
      
      actualValue = dataStorage.getValue(getBankOffset() + 4);
      complementedValue = actualValue ^ 0xff;
      
      if (complementedValue == 0) {
        
        setZero(true);
        
      } 
      
      wRegister.setValue(complementedValue);
      
    } else if (parameter1 == 0 && parameter2 == 1){
      
      actualValue = dataStorage.getValue(getBankOffset() + 4);
      complementedValue = actualValue ^ 0xff;
      
      if (complementedValue == 0) {
        
        setZero(true);
        
      } 
      
      dataStorage.setValue(getBankOffset() + 4, complementedValue);
      
    } else if (parameter1 != 0 && parameter2 == 0) {
      
      actualValue = dataStorage.getValue(getBankOffset() + parameter1);
      complementedValue = actualValue ^ 0xff;
      
      if (complementedValue == 0) {
        
        setZero(true);
        
      } 
      
      wRegister.setValue(complementedValue);
      
    } else if (parameter1 != 0 && parameter2 == 1){
      
      actualValue = dataStorage.getValue(getBankOffset() + parameter1);
      complementedValue = actualValue ^ 0xff;
      
      if (complementedValue == 0) {
        
        setZero(true);
        
      } 
      
      dataStorage.setValue(getBankOffset() + parameter1, complementedValue);
      
    }
    
    return 1; 
    
  }
}
