public class CommandDecfsz extends Command{
  
  public CommandDecfsz (int aProgrammCounterLine, int aParameter1, int aParameter2, int aMachineCycles, DataStorage aDataStorage, WRegister aWRegister) {
    
    super(aProgrammCounterLine, aParameter1, aParameter2, aMachineCycles, aDataStorage, aWRegister);
    
  }
  
  public int executeCommand() {
    
    int actualIndirectValue = dataStorage.getValue(getBankOffset() + 4);
    int actualDirectValue = dataStorage.getValue(getBankOffset() + parameter1);
    
    if (parameter1 == 0 && parameter2 == 0) { // indirekt, Ergenis in W
      
      actualIndirectValue -= 1;
      wRegister.setValue(actualIndirectValue);
      
      if (actualIndirectValue == 0) { // Nächster Befehl überspringen
        
        return 2;
        
      } 
      
      return 1;
      
    } else if (parameter1 == 0 && parameter2 == 1) { // indirekt, Ergebnis in F
      
      actualIndirectValue -= 1;
      dataStorage.setValue(getBankOffset() + 4, actualIndirectValue);
      
      if (actualIndirectValue == 0) { // Nächster Befehl überspringen
        
        return 2;
        
      } 
      
      return 1;
      
    } else if (parameter1 != 0 && parameter2 == 0) { // direkt, Ergebnis in W
      
      actualDirectValue -= 1;
      wRegister.setValue(actualDirectValue);
      
      if (actualDirectValue == 0) { // Nächster Befehl überspringen
        
        return 2;
        
      } 
      
      return 1;
      
    } else if (parameter1 != 0 && parameter2 == 1) { // direkt, Ergebnis in F
      
      actualDirectValue -= 1;
      dataStorage.setValue(getBankOffset() + parameter1, actualDirectValue);
      
      if (actualDirectValue == 0) { // Nächster Befehl überspringen
        
        return 2;
        
      } 
      
      return 1;
      
    } else { // Fehler
      
      return 0;
      
    } 
  }
}
