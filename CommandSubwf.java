public class CommandSubwf extends Command {
  
  public CommandSubwf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualWValue = wRegister.getValue();
    int actualFValue;
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      actualFValue = dataStorage.getValue(getBankOffset() + 4);
      
    } else { // direkte Adressierung
      
      actualFValue = dataStorage.getValue(getBankOffset() + parameter1);
      
    }
    
    actualFValue = (actualFValue ^ 0xff) + 1; // Zweierkomplement
    
    int lowWValue = actualWValue & 0x0f;
    int lowFValue = actualFValue & 0x0f;
    int newValue = actualWValue + actualFValue;
    
    if (lowWValue + lowFValue > 15) {
      
      setDigitCarry(true);
      
    } 
    
    if (newValue > 255) {
      
      newValue -= 256;
      setCarry(true);
      
    } 
    
    if (newValue == 0) {
      
      setZero(true);
      
    } 
    
    newValue = (newValue - 1) ^ 0xff;
    
    if (parameter2 == 0) { // Ergebnis in W 
      
      wRegister.setValue(newValue);
      
    } else { // Ergebnis in F
      
      if (parameter1 == 0) { // indirekte Adressierung
        
        dataStorage.setValue(getBankOffset() + 4, newValue);
        
      } else { // direkte Adressierung
        
        dataStorage.setValue(getBankOffset() + parameter1, newValue);
        
      } 
    }  
    
    return -1;
    
  }    
}