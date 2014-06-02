public class CommandRrf extends Command{
  
  public CommandRrf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualFValue = 0;
    int registerIndex = 0;
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      registerIndex = dataStorage.getValue(getBankOffset() + 4);
      
      if (registerIndex == 0) { // NOP
        
        return -1;
        
      } 
      
      actualFValue = dataStorage.getValue(registerIndex);
      
    } else { // direkte Adressierung
      
      actualFValue = dataStorage.getValue(getBankOffset() + parameter1);
      
    } 
    
    int newValue = (actualFValue >> 1);
    int bitBeforeCarry = actualFValue & 0x01;
    
    if (bitBeforeCarry == 0 && checkCarry()) {
      
      setCarry(false);
      newValue += 128;
      
    } else if (bitBeforeCarry == 1 && !checkCarry()) {
      
      setCarry(true);
      
    } else if (bitBeforeCarry == 1 && checkCarry()) {
      
      newValue += 128;
      
    }
    
    if (newValue > 255) {
      
      newValue -= 256;
      setCarry(true);
      
    }  
    
    if (parameter2 == 0) { // Ergebnis in W
      
      wRegister.setValue(newValue);
      
    } else { // Ergebnis in F
      
      if (parameter1 == 0) { // indirekte Adressierung
        
        dataStorage.setValue(registerIndex, newValue);
        
      } else { // direkte Adressierung
        
        dataStorage.setValue(getBankOffset() + parameter1, newValue);
        
      } 
    }
    
    return -1;
    
  }
}