public class CommandSublw extends Command {
  
  public CommandSublw (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualWValue = wRegister.getValue();
    int actualFValue = parameter1;
    int registerIndex = 0;
    
    actualWValue = (actualWValue ^ 0xff) + 1; // Zweierkomplement
    
    int lowWValue = actualWValue & 0x0f;
    int lowFValue = actualFValue & 0x0f;
    int newValue = actualWValue + actualFValue;
    
    if (lowWValue + lowFValue > 15) {
      
      setDigitCarry(true);
      
    } else {
      
      setDigitCarry(false);
      
    } 
    
    if (newValue > 255) {
      
      newValue -= 256;
      setCarry(true);
      
    } else {
      
      setCarry(false);
      
    } 
    
    if (newValue == 0) {
      
      setZero(true);
      
    } else {
      
      setZero(false);
      
    } 

    wRegister.setValue(newValue); 
    
    return -1;
    
  }
  
}
