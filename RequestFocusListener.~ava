/*
Die Instanz dieser Klasse wird benötigt, damit man den Focus von Objekten innerhalb
eines JPane-Message-Dialogs verändern kann.
*/

import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JComponent;

public class RequestFocusListener implements AncestorListener {
  
  private boolean removeListener;
  
  public RequestFocusListener() {
    
    this(true);
    
  }
  
  public RequestFocusListener(boolean removeListener) {
    
    this.removeListener = removeListener;
    
  }
  
  public void ancestorAdded(AncestorEvent e) {
    
    JComponent component = e.getComponent();
    component.requestFocusInWindow();
    
    if (removeListener) component.removeAncestorListener( this );
    
  }
  
  public void ancestorMoved(AncestorEvent e) {}
  
  public void ancestorRemoved(AncestorEvent e) {}
  
}