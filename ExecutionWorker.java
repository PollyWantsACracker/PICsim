import javax.swing.SwingWorker;

class ExecutionWorker extends SwingWorker<Integer, Integer> {
  
  private Steuerung steuerung;
  
  public ExecutionWorker(Steuerung aSteuerung) {
    
    super();
    steuerung = aSteuerung;
    
  }
  
  protected Integer doInBackground() throws Exception {
    
    
    steuerung.executeCommands();
    return 0;
    
  }
  
  protected void done() {
    
    
    
  }
}