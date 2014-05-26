public class CommandIncf extends Command {
  
  public CommandIncf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualIndirectValue = dataStorage.getValue(getBankOffset() + 4);
    int actualDirectValue = dataStorage.getValue(getBankOffset() + parameter1);
    
    if (parameter1 == 0 && parameter2 == 0) { // indirekt, Ergebnis in W
      
      actualIndirectValue += 1;
      
      if (actualIndirectValue > 255) {
        
        actualIndirectValue = 0;
        setZero(true);
        
      } 
      
      wRegister.setValue(actualIndirectValue);
      
    } else if (parameter1 == 0 && parameter2 == 1) { // indirekt, Ergebnis in F
      
      actualIndirectValue += 1;
      
      if (actualIndirectValue > 255) {
        
        actualIndirectValue = 0;
        setZero(true);
        
      } 
      
      dataStorage.setValue(getBankOffset() + 4, actualIndirectValue);
      
    } else if (parameter1 != 0 && parameter2 == 0) { // direkt, Ergebnis in W
      
      actualDirectValue += 1;
      
      if (actualDirectValue > 255) {
        
        actualDirectValue = 0;
        setZero(true);
        
      } 
      
      wRegister.setValue(actualDirectValue);
      
    } else if (parameter1 != 0 && parameter2 == 1) { //direkt, Ergebnis in W
      
      actualDirectValue += 1;
      
      if (actualDirectValue > 255) {
        
        actualDirectValue = 0;
        setZero(true);
        
      } 
      
      dataStorage.setValue(getBankOffset() + parameter1, actualDirectValue);
      
    } 
    
    return 1;
    
  }
}