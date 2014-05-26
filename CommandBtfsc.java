public class CommandBtfsc extends Command {
  
  public CommandBtfsc (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    super (aLineNumber,2);
    this.dataStorage = aStack;
  }
  
  @Override
  public void readParameters(int [] aParameters) {
    parameters [0] = aParameters [0];
    parameters [1] = aParameters [1];
  }
  
  @Override
  public int executeCommand() {
    if (parameters[0] == 0){
      parameters[0] = dataStorage.getValue(4);
      if (parameters[0] == 0) {
        return -1;
      }
    }
    if ((dataStorage.getValue(parameters[0]) & (int)(Math.pow(2, parameters[1]))) == 0) {
      return -2;
    } else {
      return -1;
    }
  }
}
