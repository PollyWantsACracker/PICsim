public abstract class Command {
  
  protected int programmCounterLine;
  protected int machineCycles;
  protected int parameter1;
  protected int parameter2;
  protected DataStorage dataStorage;
  protected WRegister wRegister;
  
  public Command(int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    programmCounterLine = aProgrammCounterLine;
    parameter1 = aParameter1;
    parameter2 = aParameter2;
    machineCycles = aMachineCycles;
    dataStorage = aDataStorage;
    wRegister = aWRegister;
    
  }
  
  abstract public int executeCommand();
  
  public int getProgrammCounterLine() {
   
    return programmCounterLine;
    
  }
  
  public int getMachineCycles() {
   
    return machineCycles; 
    
  }
  
  public void setCarry(boolean carry) {
    
    if (carry && !checkCarry()) {
      
      dataStorage.setValue(3, dataStorage.getValue(3) + 1);
      
    } else if (!carry && checkCarry()) {
      
      dataStorage.setValue(3, dataStorage.getValue(3) - 1);
      
    } 
  }
  
  public void setDigitCarry(boolean digitCarry) {
    
    if (digitCarry && !checkDigitCarry()) {
      
      dataStorage.setValue(3, dataStorage.getValue(3) + 2);
      
    } else if (!digitCarry && checkDigitCarry()) {
      
      dataStorage.setValue(3, dataStorage.getValue(3) - 2);
      
    } 
  }
  
  public void setZero(boolean zero) {
    
    if (zero && !checkZero()) {
      
      dataStorage.setValue(3, dataStorage.getValue(3) + 4);
      
    } else if (!zero && checkZero()) {
      
      dataStorage.setValue(3, dataStorage.getValue(3) - 4);
      
    } 
  }
  
  public int getBankOffset() {
    
    if ((dataStorage.getValue(3) & 0x5) == 1) { 
      
      return 128;
      
    }
    
    return 0; 
    
  } 
  
  public boolean checkCarry() { // true --> Carry = 1, false --> Carry = 0
    
    if ((dataStorage.getValue(3) & 0x1) == 1)  {
      
      return true;
      
    }
    
    return false;
    
  }
  
  public boolean checkDigitCarry() { // true --> DigitCarry = 1, false --> DigitCarry = 0
    
    if ((dataStorage.getValue(3) & 0x02) == 1)  {
      
      return true;
      
    }
    
    return false;
    
  } 
  
  public boolean checkZero() { // true --> Zero = 1, false --> Zero = 0
    
    if ((dataStorage.getValue(3) & 0x04) == 1)  {
      
      return true;
      
    }
    
    return false;
    
  } 
}