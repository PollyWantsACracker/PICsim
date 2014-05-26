public class CommandSubwf extends Command {
  
  public CommandSubwf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue = wRegister.getValue();
    int newIndirectValue = dataStorage.getValue(getBankOffset() + 4) - actualValue; 
    int newDirectValue = dataStorage.getValue(getBankOffset() + parameter1) - actualValue; 
    int lowActualValue = actualValue & 0xf0;
    int lowNewDirectValue = newDirectValue & 0xf0;
    int lowNewIndirectValue = newIndirectValue & 0xf0;
    
    if (parameter1 == 0 && parameter2 == 0) { // indirekt, Ergebnis in W
      
      if (lowNewIndirectValue - lowActualValue != 0) {
        
        setDigitCarry(true);
        
      } 
      
      if (newIndirectValue < 0) {
        
        newIndirectValue += 256;
        setCarry(true);
        
      }
      
      if (newIndirectValue == 0) {
        
        setZero(true);
        
      }  
      
      wRegister.setValue(newIndirectValue);
      
    } else if (parameter1 == 0 && parameter2 == 1) { // indirekt, Ergebnis in F
      
      if (lowNewIndirectValue - lowActualValue != 0) {
        
        setDigitCarry(true);
        
      } 
      
      if (newIndirectValue < 0) {
        
        newIndirectValue += 256;
        setCarry(true);
        
      }
      
      if (newIndirectValue == 0) {
        
        setZero(true);
        
      } 
      
      dataStorage.setValue(getBankOffset() + 4, newIndirectValue);
      
    } else if (parameter1 != 0 && parameter2 == 0) { // direkt, ERgebnis in W
      
      if (lowNewDirectValue - lowActualValue != 0) {
        
        setDigitCarry(true);
        
      } 
      
      if (newDirectValue < 0) {
        
        newDirectValue += 256;
        setCarry(true);
        
      }
      
      if (newDirectValue == 0) {
        
        setZero(true);
        
      } 
      
      wRegister.setValue(newDirectValue);  
      
    } else if (parameter1 != 0 && parameter2 == 1) { // direkt, Ergebnis in F
      
      if (lowNewDirectValue - lowActualValue != 0) {
        
        setDigitCarry(true);
        
      } 
      
      if (newDirectValue < 0) {
        
        newDirectValue += 256;
        setCarry(true);
        
      }
      
      if (newDirectValue == 0) {
        
        setZero(true);
        
      } 
      
      dataStorage.setValue(getBankOffset() + parameter1, newDirectValue);
      
    } 
    
    return 1;
    
  }
}