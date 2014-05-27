public class CommandClrf extends Command {
  
  public CommandClrf (int aProgrammCounterLine, int parameter1, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, parameter1, 0, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int newValue = 0;
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      dataStorage.setValue(getBankOffset() + 4, newValue);
      
    } else { // direkte Adressierung
      
      dataStorage.setValue(getBankOffset() + parameter1, newValue);
      
    }
    
    setZero(true);
    return -1;
    
  }
}