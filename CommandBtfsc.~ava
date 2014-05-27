public class CommandBtfsc extends Command {
  
  public CommandBtfsc (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualValue;
    
    if (parameter1 == 0) { // indirekte Adressierung
      
      actualValue = dataStorage.getValue(getBankOffset() + 4);
      
    } else { // direkte Adressierung
      
      actualValue = dataStorage.getValue(getBankOffset() + parameter1);
      
    }
    
    if ((actualValue & (int)(Math.pow(2, parameter2))) == 0) {
      
      this.machineCycles = 2;
      return -2;
      
    } else {
      
      this.machineCycles = 1;
      return -1;
      
    }
  }
}