public class CommandIncfsz extends Command{
  
  public CommandIncfsz (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue = 0;
    int registerIndex = 0;
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      registerIndex = dataStorage.getValue(getBankOffset() + 4);
      
      if (registerIndex == 0) { // NOP
        
        return -1;
        
      } 
      
      actualValue = dataStorage.getValue(registerIndex);
      
    } else { // direkte Adressierung
      
      actualValue = dataStorage.getValue(getBankOffset() + parameter1);
      
    } 
    
    int newValue = actualValue + 1;
    
    if (newValue > 255) {
      
      newValue -= 256;
      
    } 
    
    if (parameter2 == 0) { // Ergenis in W
      
      wRegister.setValue(newValue);
      
    } else { // Ergebnis in F
      
      if (parameter1 == 0) { // indirekte Adressierung
        
        dataStorage.setValue(registerIndex, newValue);
        
      } else { // direkte Adressierung
        
        dataStorage.setValue(getBankOffset() + parameter1, newValue);
        
      }
    }
    
    if (newValue == 0) {
      
      this.machineCycles = 2;
      return -2;
      
    } else {
      
      this.machineCycles = 1;
      return -1;
      
    }
  }
}