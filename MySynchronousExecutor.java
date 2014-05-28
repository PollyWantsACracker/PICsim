import javax.swing.SwingUtilities;

public abstract class MySynchronousExecutor {
  
  private boolean myIsRunning = false;
  
  protected abstract Integer doInBackground() throws Exception;
  
  private void doLoop() {
    try {
      if (!myIsRunning) {
        return;
      }
      
      doInBackground();
      
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          doLoop();
        }
      });
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
  
  public void execute() {
    if (!myIsRunning) {
      myIsRunning = true;
      
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          doLoop();
        }
      });
    }
  }
  
  public void cancel(boolean mayInterruptIfRunning) {
    myIsRunning = false;
  }
}