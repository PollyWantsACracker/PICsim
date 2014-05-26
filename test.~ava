import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 19.05.2014
  * @author 
  */

public class test extends JFrame {
  // Anfang Attribute
  private JPanel jPanel1 = new JPanel(null, true);
    private JButton jButton1 = new JButton();
  // Ende Attribute
  
  public test(String title) { 
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 568; 
    int frameHeight = 399;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    
    jPanel1.setBounds(112, 104, 321, 129);
    jPanel1.setOpaque(false);
    cp.add(jPanel1);
    jButton1.setBounds(64, 32, 57, 25);
    jButton1.setText("jButton1");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
      }
    });
    jPanel1.add(jButton1);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public test
  
  // Anfang Methoden
  public void jButton1_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
  } // end of jButton1_ActionPerformed

  // Ende Methoden
  
  public static void main(String[] args) {
    new test("test");
  } // end of main
  
} // end of class test
