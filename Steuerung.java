import java.util.*;

public class Steuerung {
  
  private MainFrame mainFrame;
  private Parser parser;
  private DataStorage dataStorage;
  private WRegister wRegister;
  private Stack stack;
  
  public Steuerung() {
    
    dataStorage = new DataStorage();
    parser = new Parser(this);
    wRegister = new WRegister();
    stack = new Stack();
    mainFrame = new MainFrame("PIC16F84 Simulator", this);
    
  }
  
  
  public void executeCommands() {
    
    int programmCounter = 0;
    int newProgrammCounter = 0;
    
    while (true) { 
      
      Command c = parser.getCommand(programmCounter);
      newProgrammCounter = c.executeCommand();
      
      if (newProgrammCounter == -1) {
        
        programmCounter += 1;
        
      } else if (newProgrammCounter == -2){
        
        programmCounter += 2;
        
      } else {
        
        programmCounter = newProgrammCounter;
        
      } 
      
    } 
    
  }
  
  public Parser getParser() {
    
    return parser;
    
  }
  
  public DataStorage getDataStorage() {
    
    return dataStorage;
    
  }
  
  public WRegister getWRegister() {
    
    return wRegister;
    
  }
  
  public Stack getStack() {
    
    return stack;
    
  }  
}