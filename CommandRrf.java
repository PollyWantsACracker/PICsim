public class CommandRrf extends Command{
  
  public CommandRrf (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualFValue;
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      actualFValue = dataStorage.getValue(getBankOffset() + 4);
      
    } else { // direkte Adressierung
      
      actualFValue = dataStorage.getValue(getBankOffset() + parameter1);
      
    } 
    
    int newValue = (actualFValue >> 8);
    int bitBeforeCarry = actualFValue & 0x01;
    
    if (bitBeforeCarry == 0 && checkCarry()) {
      
      setCarry(false);
      newValue += 128;
      
    } else if (bitBeforeCarry == 1 && !checkCarry()) {
      
      setCarry(true);
      
    } else if (bitBeforeCarry == 1 && checkCarry()) {
      
      newValue += 128;
      
    } 
    
    if (parameter2 == 0) { // Ergebnis in W
      
      wRegister.setValue(actualFValue);
      
    } else { // Ergebnis in F
      
      if (parameter1 == 0) { // indirekte Adressierung
        
        dataStorage.setValue(getBankOffset() + 4, actualFValue);
        
      } else { // direkte Adressierung
        
        dataStorage.setValue(getBankOffset() + parameter1, actualFValue);
        
      } 
    }
    
    return -1;
    
  }
}