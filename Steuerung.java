import java.util.*;
import javax.swing.SwingUtilities;

public class Steuerung {
  
  private MainFrame mainFrame;
  private Parser parser;
  private DataStorage dataStorage;
  private WRegister wRegister;
  private Stack stack;
  
  private static int programmCounter = 0;
  
  public Steuerung() {
    
    Steuerung s = this;
    dataStorage = new DataStorage();
    parser = new Parser(this);
    wRegister = new WRegister();
    stack = new Stack();
    
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        mainFrame = new MainFrame("PIC16F84 Simulator", s);
      }
    });
  }
  
  public void executeOneCommand() {
    
    int newProgrammCounter = 0;
    Command c = parser.getCommand(programmCounter);
    newProgrammCounter = c.executeCommand();
    
    if (newProgrammCounter == -1) {
      
      programmCounter += 1;
      
    } else if (newProgrammCounter == -2){
      
      programmCounter += 2;
      
    } else {
      
      programmCounter = newProgrammCounter;
      
    }
    
    mainFrame.updateDataStorage();
    
  }
  
  public int getProgrammCounter() {
    
    return programmCounter;
    
  }
  
  public void setProgrammCounter(int newProgrammCounter) {
    
    programmCounter = newProgrammCounter;
    
  }
  
  public void executeCommands() {
    
    int programmCounter = 0;
    int newProgrammCounter = 0;
    
    while (mainFrame.getRunning()) { 
      
      Command c = parser.getCommand(programmCounter);
      newProgrammCounter = c.executeCommand();
      
      if (newProgrammCounter == -1) {
        
        programmCounter += 1;
        
      } else if (newProgrammCounter == -2){
        
        programmCounter += 2;
        
      } else {
        
        programmCounter = newProgrammCounter;
        
      }
      
      mainFrame.updateDataStorage(); 
      
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