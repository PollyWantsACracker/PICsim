public class CommandBtfsc extends Command {
  
  public CommandBtfsc (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
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
    
    if ((actualValue & (1 << parameter2)) == 0) {
      
      this.machineCycles = 2;
      return -2;
      
    } else {
      
      this.machineCycles = 1;
      return -1;
      
    }
  }
}