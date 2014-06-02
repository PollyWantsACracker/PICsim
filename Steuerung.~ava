import java.util.*;
import javax.swing.SwingUtilities;

public class Steuerung {
  
  private MainFrame mainFrame;
  private Parser parser;
  private DataStorage dataStorage;
  private WRegister wRegister;
  private Stack stack;
  private int laufzeit;
  private int quarzFrequenz = 0;
  
  public Steuerung() {
    
    laufzeit = 0; // in microSekunden  
    quarzFrequenz = 4000000; //in Hz
    
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
  
  public void setQuarzFrequenz(int aQuarzFrequenz) {
    
    quarzFrequenz = aQuarzFrequenz;
    
  }
  
  public void setLaufzeit(int aLaufzeit) {
    
    laufzeit = aLaufzeit;
    
  }
  
  public int getLaufzeit() {
    
    return laufzeit;
    
  }
  
  public void executeOneCommand() {
    
    int newProgrammCounter = 0;
    int actualProgrammCounter = dataStorage.getProgrammCounter();
    
    Command c = parser.getCommand(actualProgrammCounter);
    newProgrammCounter = c.executeCommand();
    
    if (newProgrammCounter == -1) {
      
      newProgrammCounter = actualProgrammCounter + 1;
      
    } else if (newProgrammCounter == -2){
      
      newProgrammCounter = actualProgrammCounter + 2;
      
    } 
    
    laufzeit += (c.getMachineCycles() * 4) / (quarzFrequenz / 1000000);
    dataStorage.setProgrammCounter(newProgrammCounter);
    mainFrame.updateElements();
    
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
      
      mainFrame.updateElements(); 
      
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