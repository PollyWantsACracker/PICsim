import javax.swing.SwingWorker;

class ExecutionWorker extends SwingWorker<Integer, Integer> {
  
  private Steuerung steuerung;
  private MainFrame mainFrame;
  
  public ExecutionWorker(Steuerung aSteuerung, MainFrame aMainFrame) {
    
    super();
    steuerung = aSteuerung;
    mainFrame = aMainFrame;
    
  }
  
  protected Integer doInBackground() throws Exception {
    
    while (steuerung.getRunning()) { 
      
      steuerung.executeOneCommand();
      mainFrame.updateElements();
      mainFrame.automaticTableScroll();
      Thread.sleep(50);
      
    }
    
    return 0;
    
  }
  
  protected void done() {
    
    
  }
  
  
  
}