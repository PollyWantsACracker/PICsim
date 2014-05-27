public class CommandAddlw extends Command {
  
  public CommandAddlw(int aProgrammCounterLine, int aParameter1, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, 0, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue = wRegister.getValue();
    int newValue = actualValue + parameter1;
    int lowActualValue = actualValue & 0x0f;
    int lowParameterValue = parameter1 & 0x0f;
    
    if (lowActualValue + lowParameterValue > 15) {
      
      setDigitCarry(true);
      
    } 
    
    if (newValue > 255) {
      
      newValue -= 256;
      setCarry(true);
      
    }
    
    if (newValue == 0) {
      
      setZero(true);
      
    } 
    
    wRegister.setValue(newValue);
    return -1;
    
  }    
}