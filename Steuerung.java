import java.util.*;
import javax.swing.SwingUtilities;

public class Steuerung {
  
  private MainFrame mainFrame;
  private Parser parser;
  private DataStorage dataStorage;
  private WRegister wRegister;
  private Stack stack;
  private Hardwareansteuerung hardwareansteuerung;
  
  private double laufzeit;
  private int quarzFrequenz = 0;
  private boolean running;
  private boolean hold;
  private int machineCycleCounter = 0;
  
  public Steuerung() {
    
    laufzeit = 0.0; // in microSekunden  
    quarzFrequenz = 4000000; //in Hz
    running = false;
    hold = false;
    
    final Steuerung s = this;
    dataStorage = new DataStorage(this);
    parser = new Parser(this);
    wRegister = new WRegister();
    stack = new Stack();
    
    hardwareansteuerung = new Hardwareansteuerung(dataStorage);
    
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        mainFrame = new MainFrame("PIC16F84 Simulator", s);
      }
    });
  }
  
  public Hardwareansteuerung getHardwareansteuerung() {
    
    return hardwareansteuerung;
    
  }
  
  public void setHold(boolean aHold) {
  
    hold = aHold;
  
  }
  
  public void setQuarzFrequenz(int aQuarzFrequenz) {
    
    quarzFrequenz = aQuarzFrequenz;
    
  }
  
  public void setLaufzeit(double aLaufzeit) {
    
    laufzeit = aLaufzeit;
    
  }
  
  public double getLaufzeit() {
    
    return laufzeit;
    
  }
  
  public void executeOneCommand() {
    
    int newProgrammCounter = 0;
    int actualProgrammCounter = dataStorage.getProgrammCounter();
    
    if (mainFrame.getBreakpoint(actualProgrammCounter)) {
      
      if (!hold) {     
        
        hold = true;
        running = false;
        return;
        
      } else {
        
        hold = false;
        
      } 
      
    } else {
      
      hold = false;
      
    } 
    
    Command c = parser.getCommand(actualProgrammCounter);
    newProgrammCounter = c.executeCommand();
    
    if (newProgrammCounter == -1) {
      
      newProgrammCounter = actualProgrammCounter + 1;
      
    } else if (newProgrammCounter == -2){
      
      newProgrammCounter = actualProgrammCounter + 2;
      
    } 
    
    laufzeit += (c.getMachineCycles() * 4) / ((mainFrame.getActualQuarzFrequence()) / 1000000.0);
    dataStorage.setProgrammCounter(newProgrammCounter);
    machineCycleCounter += c.getMachineCycles();
    
    dataStorage.timer0(machineCycleCounter);
    
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
  
  public boolean getRunning() {
    
    return running;
    
  }
  
  public void setRunning(boolean aRunning) {
    
    running = aRunning;
    
  }
  
  public MainFrame getMainFrame() {
    
    return mainFrame;
    
  }
}