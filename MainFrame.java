/*
Die Instanz dieser Klasse bildet das Hauptfenster der Anwendung.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.KeyEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.concurrent.locks.*;

public class MainFrame extends JFrame {
  
  private boolean loadedFile;
  
  private Steuerung steuerung;
  private ExecutionWorker executionWorker;
  private JLabel[] jLabelDataStorage;
  private JTextField[] jTextFieldDataStorage;
  private String[] columnHeaders;
  private Object[][] tableData;
  private String[] quarzFrequenzen;
  
  private JLabel jLabelWRegister;
  private JLabel jLabelWRegisterValue;
  private JLabel jLabelFSR;
  private JLabel jLabelFSRValue;
  private JLabel jLabelPCL;
  private JLabel jLabelPCLValue;
  private JLabel jLabelStatus;
  private JLabel jLabelStatusValue;
  private JLabel jLabelLaufzeit;
  private JLabel jLabelLaufzeitEinheit;
  
  private JLabel jLabelRABit;
  private JLabel jLabelRA7;
  private JLabel jLabelRA6;
  private JLabel jLabelRA5;
  private JLabel jLabelRA4;
  private JLabel jLabelRA3;
  private JLabel jLabelRA2;
  private JLabel jLabelRA1;
  private JLabel jLabelRA0;
  private JLabel jLabelTrisA;
  private JLabel jLabelRAValue;
  
  private JLabel jLabelRBBit;
  private JLabel jLabelRB7;
  private JLabel jLabelRB6;
  private JLabel jLabelRB5;
  private JLabel jLabelRB4;
  private JLabel jLabelRB3;
  private JLabel jLabelRB2;
  private JLabel jLabelRB1;
  private JLabel jLabelRB0;
  private JLabel jLabelTrisB;
  private JLabel jLabelRBValue;
  
  private JComboBox jComboBoxQuarzFrequenzen;
  
  private JMenuBar jMenuBar;
  private JMenu jMenuFile;
  private JMenu jMenuInfo;
  private JMenu jMenuHelp;
  private JMenuItem jMenuItemFileOpen;
  private JMenuItem jMenuItemInfoView;
  private JMenuItem jMenuItemHelpView;
  private JScrollPane jScrollPaneDataStorage;
  private JScrollPane jScrollPaneSourceCode;
  private JPanel jPanelDataStorage;
  private JPanel jPanelSteuerpult;
  private JPanel jPanelSpecialFunctions;
  private JPanel jPanelLaufzeit;
  private JPanel jPanelQuarzfrequenz;
  private JPanel jPanelRA;
  private JPanel jPanelRB;
  private JButton jButtonReset = new JButton();
  private JButton jButtonStart = new JButton();
  private JButton jButtonOneStep = new JButton();
  private JButton jButtonStop = new JButton();
  private JButton jButtonZurücksetzen = new JButton();
  
  private JButton jButtonTrisA7 = new JButton();
  private JButton jButtonTrisA6 = new JButton();
  private JButton jButtonTrisA5 = new JButton();
  private JButton jButtonTrisA4 = new JButton();
  private JButton jButtonTrisA3 = new JButton();
  private JButton jButtonTrisA2 = new JButton();
  private JButton jButtonTrisA1 = new JButton();
  private JButton jButtonTrisA0 = new JButton();
  private JButton jButtonRABit7 = new JButton();
  private JButton jButtonRABit6 = new JButton();
  private JButton jButtonRABit5 = new JButton();
  private JButton jButtonRABit4 = new JButton();
  private JButton jButtonRABit3 = new JButton();
  private JButton jButtonRABit2 = new JButton();
  private JButton jButtonRABit1 = new JButton();
  private JButton jButtonRABit0 = new JButton();
  
  private JButton jButtonTrisB7 = new JButton();
  private JButton jButtonTrisB6 = new JButton();
  private JButton jButtonTrisB5 = new JButton();
  private JButton jButtonTrisB4 = new JButton();
  private JButton jButtonTrisB3 = new JButton();
  private JButton jButtonTrisB2 = new JButton();
  private JButton jButtonTrisB1 = new JButton();
  private JButton jButtonTrisB0 = new JButton();
  private JButton jButtonRBBit7 = new JButton();
  private JButton jButtonRBBit6 = new JButton();
  private JButton jButtonRBBit5 = new JButton();
  private JButton jButtonRBBit4 = new JButton();
  private JButton jButtonRBBit3 = new JButton();
  private JButton jButtonRBBit2 = new JButton();
  private JButton jButtonRBBit1 = new JButton();
  private JButton jButtonRBBit0 = new JButton();
  
  private JTable jTableSourceCode;
  
  private AbstractAction resetButtonPressed;
  private AbstractAction startButtonPressed;
  private AbstractAction oneStepButtonPressed;
  private AbstractAction stopButtonPressed;
  
  private Lock threadLock;
  
  public MainFrame(String title, Steuerung aSteuerung) { 
    
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 800; 
    int frameHeight = 600;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(true);
    Container cp = getContentPane();
    cp.setLayout(null);
    this.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/favicon.png"));
    
    loadedFile = false;
    
    steuerung = aSteuerung;
    threadLock = new ReentrantLock();
    
    
    jLabelDataStorage = new JLabel[48];
    jTextFieldDataStorage = new JTextField[256];
    
    initMenueBar();
    initScrollPanes();
    initPanels();
    initButtonPressedEvents();
    initLabels();
    initButtons();
    initDataStorage();
    initRegisters();
    
    
    setVisible(true);
    
  }
  
  private void initRegisters() {
    
    jPanelRA = new JPanel();
    jPanelRA.setBounds(307, 315, 200, 111); //107
    jPanelRA.setBorder(BorderFactory.createTitledBorder("Register A"));
    getContentPane().add(jPanelRA);
    
    jLabelRABit = new JLabel();
    jLabelRABit.setPreferredSize(new Dimension(25, 15));
    jLabelRABit.setText("Bit");
    jPanelRA.add(jLabelRABit);
    
    jLabelRA7 = new JLabel();
    jLabelRA7.setPreferredSize(new Dimension(15, 15));
    jLabelRA7.setText("7");
    jLabelRA7.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRA.add(jLabelRA7);
    
    jLabelRA6 = new JLabel();
    jLabelRA6.setPreferredSize(new Dimension(15, 15));
    jLabelRA6.setText("6");
    jLabelRA6.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRA.add(jLabelRA6);
    
    jLabelRA5 = new JLabel();
    jLabelRA5.setPreferredSize(new Dimension(15, 15));
    jLabelRA5.setText("5");
    jLabelRA5.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRA.add(jLabelRA5);
    
    jLabelRA4 = new JLabel();
    jLabelRA4.setPreferredSize(new Dimension(15, 15));
    jLabelRA4.setText("4");
    jLabelRA4.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRA.add(jLabelRA4);
    
    jLabelRA3 = new JLabel();
    jLabelRA3.setPreferredSize(new Dimension(15, 15));
    jLabelRA3.setText("3");
    jLabelRA3.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRA.add(jLabelRA3);
    
    jLabelRA2 = new JLabel();
    jLabelRA2.setPreferredSize(new Dimension(15, 15));
    jLabelRA2.setText("2");
    jLabelRA2.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRA.add(jLabelRA2);
    
    jLabelRA1 = new JLabel();
    jLabelRA1.setPreferredSize(new Dimension(15, 15));
    jLabelRA1.setText("1");
    jLabelRA1.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRA.add(jLabelRA1);
    
    jLabelRA0 = new JLabel();
    jLabelRA0.setPreferredSize(new Dimension(15, 15));
    jLabelRA0.setText("0");
    jLabelRA0.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRA.add(jLabelRA0);
    
    jLabelTrisA = new JLabel();
    jLabelTrisA.setPreferredSize(new Dimension(25, 10));
    jLabelTrisA.setText("Tris");
    jPanelRA.add(jLabelTrisA);
    
    jButtonTrisA7.setPreferredSize(new Dimension(15, 15));
    jButtonTrisA7.setText("-");
    jButtonTrisA7.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisA7.setFocusable(false);
    jButtonTrisA7.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisA7_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonTrisA7);
    
    jButtonTrisA6.setPreferredSize(new Dimension(15, 15));
    jButtonTrisA6.setText("-");
    jButtonTrisA6.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisA6.setFocusable(false);
    jButtonTrisA6.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisA6_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonTrisA6);
    
    jButtonTrisA5.setPreferredSize(new Dimension(15, 15));
    jButtonTrisA5.setText("-");
    jButtonTrisA5.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisA5.setFocusable(false);
    jButtonTrisA5.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisA5_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonTrisA5);
    
    jButtonTrisA4.setPreferredSize(new Dimension(15, 15));
    jButtonTrisA4.setText("i");
    jButtonTrisA4.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisA4.setFocusable(false);
    jButtonTrisA4.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisA4_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonTrisA4);
    
    jButtonTrisA3.setPreferredSize(new Dimension(15, 15));
    jButtonTrisA3.setText("i");
    jButtonTrisA3.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisA3.setFocusable(false);
    jButtonTrisA3.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisA3_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonTrisA3);
    
    jButtonTrisA2.setPreferredSize(new Dimension(15, 15));
    jButtonTrisA2.setText("i");
    jButtonTrisA2.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisA2.setFocusable(false);
    jButtonTrisA2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisA2_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonTrisA2);
    
    jButtonTrisA1.setPreferredSize(new Dimension(15, 15));
    jButtonTrisA1.setText("i");
    jButtonTrisA1.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisA1.setFocusable(false);
    jButtonTrisA1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisA1_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonTrisA1);
    
    jButtonTrisA0.setPreferredSize(new Dimension(15, 15));
    jButtonTrisA0.setText("i");
    jButtonTrisA0.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisA0.setFocusable(false);
    jButtonTrisA0.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisA0_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonTrisA0);
    
    jLabelRAValue = new JLabel();
    jLabelRAValue.setPreferredSize(new Dimension(25, 15));
    jLabelRAValue.setText("0/1");
    jPanelRA.add(jLabelRAValue);
    
    jButtonRABit7.setPreferredSize(new Dimension(15, 15));
    jButtonRABit7.setText("-");
    jButtonRABit7.setMargin(new Insets(1, 1, 1, 1));
    jButtonRABit7.setFocusable(false);
    jButtonRABit7.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRABit7_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonRABit7);
    
    jButtonRABit6.setPreferredSize(new Dimension(15, 15));
    jButtonRABit6.setText("-");
    jButtonRABit6.setMargin(new Insets(1, 1, 1, 1));
    jButtonRABit6.setFocusable(false);
    jButtonRABit6.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRABit6_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonRABit6);
    
    jButtonRABit5.setPreferredSize(new Dimension(15, 15));
    jButtonRABit5.setText("-");
    jButtonRABit5.setMargin(new Insets(1, 1, 1, 1));
    jButtonRABit5.setFocusable(false);
    jButtonRABit5.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRABit5_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonRABit5);
    
    jButtonRABit4.setPreferredSize(new Dimension(15, 15));
    jButtonRABit4.setText("0");
    jButtonRABit4.setMargin(new Insets(1, 1, 1, 1));
    jButtonRABit4.setFocusable(false);
    jButtonRABit4.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRABit4_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonRABit4);
    
    jButtonRABit3.setPreferredSize(new Dimension(15, 15));
    jButtonRABit3.setText("0");
    jButtonRABit3.setMargin(new Insets(1, 1, 1, 1));
    jButtonRABit3.setFocusable(false);
    jButtonRABit3.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRABit3_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonRABit3);
    
    jButtonRABit2.setPreferredSize(new Dimension(15, 15));
    jButtonRABit2.setText("0");
    jButtonRABit2.setMargin(new Insets(1, 1, 1, 1));
    jButtonRABit2.setFocusable(false);
    jButtonRABit2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRABit2_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonRABit2);
    
    jButtonRABit1.setPreferredSize(new Dimension(15, 15));
    jButtonRABit1.setText("0");
    jButtonRABit1.setMargin(new Insets(1, 1, 1, 1));
    jButtonRABit1.setFocusable(false);
    jButtonRABit1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRABit1_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonRABit1);
    
    jButtonRABit0.setPreferredSize(new Dimension(15, 15));
    jButtonRABit0.setText("0");
    jButtonRABit0.setMargin(new Insets(1, 1, 1, 1));
    jButtonRABit0.setFocusable(false);
    jButtonRABit0.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRABit0_ActionPerformed(evt);
      }
    });
    jPanelRA.add(jButtonRABit0);
    
    jPanelRB = new JPanel();
    jPanelRB.setBounds(307, 426, 200, 112);
    jPanelRB.setBorder(BorderFactory.createTitledBorder("Register B"));
    getContentPane().add(jPanelRB);
    
    jLabelRBBit = new JLabel();
    jLabelRBBit.setPreferredSize(new Dimension(25, 15));
    jLabelRBBit.setText("Bit");
    jPanelRB.add(jLabelRBBit);
    
    jLabelRB7 = new JLabel();
    jLabelRB7.setPreferredSize(new Dimension(15, 15));
    jLabelRB7.setText("7");
    jLabelRB7.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRB.add(jLabelRB7);
    
    jLabelRB6 = new JLabel();
    jLabelRB6.setPreferredSize(new Dimension(15, 15));
    jLabelRB6.setText("6");
    jLabelRB6.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRB.add(jLabelRB6);
    
    jLabelRB5 = new JLabel();
    jLabelRB5.setPreferredSize(new Dimension(15, 15));
    jLabelRB5.setText("5");
    jLabelRB5.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRB.add(jLabelRB5);
    
    jLabelRB4 = new JLabel();
    jLabelRB4.setPreferredSize(new Dimension(15, 15));
    jLabelRB4.setText("4");
    jLabelRB4.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRB.add(jLabelRB4);
    
    jLabelRB3 = new JLabel();
    jLabelRB3.setPreferredSize(new Dimension(15, 15));
    jLabelRB3.setText("3");
    jLabelRB3.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRB.add(jLabelRB3);
    
    jLabelRB2 = new JLabel();
    jLabelRB2.setPreferredSize(new Dimension(15, 15));
    jLabelRB2.setText("2");
    jLabelRB2.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRB.add(jLabelRB2);
    
    jLabelRB1 = new JLabel();
    jLabelRB1.setPreferredSize(new Dimension(15, 15));
    jLabelRB1.setText("1");
    jLabelRB1.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRB.add(jLabelRB1);
    
    jLabelRB0 = new JLabel();
    jLabelRB0.setPreferredSize(new Dimension(15, 15));
    jLabelRB0.setText("0");
    jLabelRB0.setHorizontalAlignment(SwingConstants.CENTER);
    jPanelRB.add(jLabelRB0);
    
    jLabelTrisB = new JLabel();
    jLabelTrisB.setPreferredSize(new Dimension(25, 10));
    jLabelTrisB.setText("Tris");
    jPanelRB.add(jLabelTrisB);
    
    jButtonTrisB7.setPreferredSize(new Dimension(15, 15));
    jButtonTrisB7.setText("i");
    jButtonTrisB7.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisB7.setFocusable(false);
    jButtonTrisB7.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisB7_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonTrisB7);
    
    jButtonTrisB6.setPreferredSize(new Dimension(15, 15));
    jButtonTrisB6.setText("i");
    jButtonTrisB6.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisB6.setFocusable(false);
    jButtonTrisB6.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisB6_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonTrisB6);
    
    jButtonTrisB5.setPreferredSize(new Dimension(15, 15));
    jButtonTrisB5.setText("i");
    jButtonTrisB5.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisB5.setFocusable(false);
    jButtonTrisB5.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisB5_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonTrisB5);
    
    jButtonTrisB4.setPreferredSize(new Dimension(15, 15));
    jButtonTrisB4.setText("i");
    jButtonTrisB4.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisB4.setFocusable(false);
    jButtonTrisB4.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisB4_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonTrisB4);
    
    jButtonTrisB3.setPreferredSize(new Dimension(15, 15));
    jButtonTrisB3.setText("i");
    jButtonTrisB3.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisB3.setFocusable(false);
    jButtonTrisB3.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisB3_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonTrisB3);
    
    jButtonTrisB2.setPreferredSize(new Dimension(15, 15));
    jButtonTrisB2.setText("i");
    jButtonTrisB2.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisB2.setFocusable(false);
    jButtonTrisB2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisB2_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonTrisB2);
    
    jButtonTrisB1.setPreferredSize(new Dimension(15, 15));
    jButtonTrisB1.setText("i");
    jButtonTrisB1.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisB1.setFocusable(false);
    jButtonTrisB1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisB1_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonTrisB1);
    
    jButtonTrisB0.setPreferredSize(new Dimension(15, 15));
    jButtonTrisB0.setText("i");
    jButtonTrisB0.setMargin(new Insets(1, 1, 1, 1));
    jButtonTrisB0.setFocusable(false);
    jButtonTrisB0.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonTrisB0_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonTrisB0);
    
    jLabelRBValue = new JLabel();
    jLabelRBValue.setPreferredSize(new Dimension(25, 15));
    jLabelRBValue.setText("0/1");
    jPanelRB.add(jLabelRBValue);
    
    jButtonRBBit7.setPreferredSize(new Dimension(15, 15));
    jButtonRBBit7.setText("0");
    jButtonRBBit7.setMargin(new Insets(1, 1, 1, 1));
    jButtonRBBit7.setFocusable(false);
    jButtonRBBit7.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRBBit7_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonRBBit7);
    
    jButtonRBBit6.setPreferredSize(new Dimension(15, 15));
    jButtonRBBit6.setText("0");
    jButtonRBBit6.setMargin(new Insets(1, 1, 1, 1));
    jButtonRBBit6.setFocusable(false);
    jButtonRBBit6.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRBBit6_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonRBBit6);
    
    jButtonRBBit5.setPreferredSize(new Dimension(15, 15));
    jButtonRBBit5.setText("0");
    jButtonRBBit5.setMargin(new Insets(1, 1, 1, 1));
    jButtonRBBit5.setFocusable(false);
    jButtonRBBit5.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRBBit5_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonRBBit5);
    
    jButtonRBBit4.setPreferredSize(new Dimension(15, 15));
    jButtonRBBit4.setText("0");
    jButtonRBBit4.setMargin(new Insets(1, 1, 1, 1));
    jButtonRBBit4.setFocusable(false);
    jButtonRBBit4.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRBBit4_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonRBBit4);
    
    jButtonRBBit3.setPreferredSize(new Dimension(15, 15));
    jButtonRBBit3.setText("0");
    jButtonRBBit3.setMargin(new Insets(1, 1, 1, 1));
    jButtonRBBit3.setFocusable(false);
    jButtonRBBit3.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRBBit3_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonRBBit3);
    
    jButtonRBBit2.setPreferredSize(new Dimension(15, 15));
    jButtonRBBit2.setText("0");
    jButtonRBBit2.setMargin(new Insets(1, 1, 1, 1));
    jButtonRBBit2.setFocusable(false);
    jButtonRBBit2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRBBit2_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonRBBit2);
    
    jButtonRBBit1.setPreferredSize(new Dimension(15, 15));
    jButtonRBBit1.setText("0");
    jButtonRBBit1.setMargin(new Insets(1, 1, 1, 1));
    jButtonRBBit1.setFocusable(false);
    jButtonRBBit1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRBBit1_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonRBBit1);
    
    jButtonRBBit0.setPreferredSize(new Dimension(15, 15));
    jButtonRBBit0.setText("0");
    jButtonRBBit0.setMargin(new Insets(1, 1, 1, 1));
    jButtonRBBit0.setFocusable(false);
    jButtonRBBit0.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonRBBit0_ActionPerformed(evt);
      }
    });
    jPanelRB.add(jButtonRBBit0);
    
  }
  
  public void jButtonTrisB7_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisB7.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) + 128);
        
      } else {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) - 128);
        
      } 
      
      updateElements();
      
    } 
  }
  
  public void jButtonTrisB6_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisB6.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) + 64);
        
      } else {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) - 64);
        
      } 
      
      updateElements();
      
    }
    
  }  
  
  
  public void jButtonTrisB5_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisB5.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) + 32);
        
      } else {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) - 32);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonTrisB4_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisB4.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) + 16);
        
      } else {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) - 16);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonTrisB3_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisB3.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) + 8);
        
      } else {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) - 8);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonTrisB2_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisB2.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) + 4);
        
      } else {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) - 4);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonTrisB1_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisB1.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) + 2);
        
      } else {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) - 2);
        
      } 
      
      updateElements();
      
    } 
  }
  
  public void jButtonTrisB0_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisB0.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) + 1);
        
      } else {
        
        steuerung.getDataStorage().setValue(134, steuerung.getDataStorage().getValue(134) - 1);
        
      } 
      
      updateElements();
      
    }  
  }
  
  public void jButtonRBBit7_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRBBit7.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) + 128);
        
      } else {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) - 128);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonRBBit6_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRBBit6.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) + 64);
        
      } else {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) - 64);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonRBBit5_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRBBit5.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) + 32);
        
      } else {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) - 32);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonRBBit4_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRBBit4.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) + 16);
        
      } else {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) - 16);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonRBBit3_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRBBit3.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) + 8);
        
      } else {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) - 8);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonRBBit2_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRBBit2.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) + 4);
        
      } else {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) - 4);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonRBBit1_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRBBit1.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) + 2);
        
      } else {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) - 2);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonRBBit0_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRBBit0.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) + 1);
        
      } else {
        
        steuerung.getDataStorage().setValue(6, steuerung.getDataStorage().getValue(6) - 1);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonRABit7_ActionPerformed(ActionEvent evt) {
    
    
  }
  
  public void jButtonRABit6_ActionPerformed(ActionEvent evt) {
    
    
  }
  
  public void jButtonRABit5_ActionPerformed(ActionEvent evt) {
    
    
  }
  
  public void jButtonRABit4_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRABit4.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(5, steuerung.getDataStorage().getValue(5) + 16);
        
      } else {
        
        steuerung.getDataStorage().setValue(5, steuerung.getDataStorage().getValue(5) - 16);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonRABit3_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRABit3.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(5, steuerung.getDataStorage().getValue(5) + 8);
        
      } else {
        
        steuerung.getDataStorage().setValue(5, steuerung.getDataStorage().getValue(5) - 8);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonRABit2_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRABit2.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(5, steuerung.getDataStorage().getValue(5) + 4);
        
      } else {
        
        steuerung.getDataStorage().setValue(5, steuerung.getDataStorage().getValue(5) - 4);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonRABit1_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRABit1.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(5, steuerung.getDataStorage().getValue(5) + 2);
        
      } else {
        
        steuerung.getDataStorage().setValue(5, steuerung.getDataStorage().getValue(5) - 2);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonRABit0_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonRABit0.getText().equals("0")) {
        
        steuerung.getDataStorage().setValue(5, steuerung.getDataStorage().getValue(5) + 1);
        
      } else {
        
        steuerung.getDataStorage().setValue(5, steuerung.getDataStorage().getValue(5) - 1);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonTrisA7_ActionPerformed(ActionEvent evt) {
    
    
  }
  
  public void jButtonTrisA6_ActionPerformed(ActionEvent evt) {
    
    
  }
  
  public void jButtonTrisA5_ActionPerformed(ActionEvent evt) {
    
    
  }
  
  public void jButtonTrisA4_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisA4.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(133, steuerung.getDataStorage().getValue(133) + 16);
        
      } else {
        
        steuerung.getDataStorage().setValue(133, steuerung.getDataStorage().getValue(133) - 16);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonTrisA3_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisA3.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(133, steuerung.getDataStorage().getValue(133) + 8);
        
      } else {
        
        steuerung.getDataStorage().setValue(133, steuerung.getDataStorage().getValue(133) - 8);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonTrisA2_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisA2.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(133, steuerung.getDataStorage().getValue(133) + 4);
        
      } else {
        
        steuerung.getDataStorage().setValue(133, steuerung.getDataStorage().getValue(133) - 4);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonTrisA1_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisA1.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(133, steuerung.getDataStorage().getValue(133) + 2);
        
      } else {
        
        steuerung.getDataStorage().setValue(133, steuerung.getDataStorage().getValue(133) - 2);
        
      } 
      
      updateElements();
      
    }
  }
  
  public void jButtonTrisA0_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      if (jButtonTrisA0.getText().equals("o")) {
        
        steuerung.getDataStorage().setValue(133, steuerung.getDataStorage().getValue(133) + 1);
        
      } else {
        
        steuerung.getDataStorage().setValue(133, steuerung.getDataStorage().getValue(133) - 1);
        
      } 
      
      updateElements();
      
    }
  }
  
  private void initLabels() {
    
    jLabelWRegister = new JLabel();
    jLabelWRegister.setPreferredSize(new Dimension(67, 20));
    jLabelWRegister.setHorizontalAlignment(SwingConstants.LEFT);
    jLabelWRegister.setText("W-Register:");
    jPanelSpecialFunctions.add(jLabelWRegister);
    
    jLabelWRegisterValue = new JLabel();
    jLabelWRegisterValue.setPreferredSize(new Dimension(60, 20));
    jLabelWRegisterValue.setText("0");
    jLabelWRegisterValue.setHorizontalAlignment(SwingConstants.RIGHT);
    jPanelSpecialFunctions.add(jLabelWRegisterValue);
    
    jLabelFSR = new JLabel();
    jLabelFSR.setPreferredSize(new Dimension(25, 20));
    jLabelFSR.setHorizontalAlignment(SwingConstants.LEFT);
    jLabelFSR.setText("FSR:");
    jPanelSpecialFunctions.add(jLabelFSR);
    
    jLabelFSRValue = new JLabel();
    jLabelFSRValue.setPreferredSize(new Dimension(103, 20));
    jLabelFSRValue.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabelFSRValue.setText("0");
    jPanelSpecialFunctions.add(jLabelFSRValue);
    
    jLabelPCL = new JLabel();
    jLabelPCL.setPreferredSize(new Dimension(26, 20));
    jLabelPCL.setHorizontalAlignment(SwingConstants.LEFT);
    jLabelPCL.setText("PCL:");
    jPanelSpecialFunctions.add(jLabelPCL);
    
    jLabelPCLValue = new JLabel();
    jLabelPCLValue.setPreferredSize(new Dimension(102, 20));
    jLabelPCLValue.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabelPCLValue.setText("0");
    jPanelSpecialFunctions.add(jLabelPCLValue);
    
    jLabelStatus = new JLabel();
    jLabelStatus.setPreferredSize(new Dimension(49, 20));
    jLabelStatus.setHorizontalAlignment(SwingConstants.LEFT);
    jLabelStatus.setText("STATUS:");
    jPanelSpecialFunctions.add(jLabelStatus);
    
    jLabelStatusValue = new JLabel();
    jLabelStatusValue.setPreferredSize(new Dimension(79, 20));
    jLabelStatusValue.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabelStatusValue.setText("0");
    jPanelSpecialFunctions.add(jLabelStatusValue);
    
    jLabelLaufzeit = new JLabel();
    jLabelLaufzeit.setPreferredSize(new Dimension(60, 20));
    jLabelLaufzeit.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabelLaufzeit.setText(Double.toString(steuerung.getLaufzeit()));
    jPanelLaufzeit.add(jLabelLaufzeit);
    
    jLabelLaufzeitEinheit = new JLabel();
    jLabelLaufzeitEinheit.setPreferredSize(new Dimension(60, 20));
    jLabelLaufzeitEinheit.setHorizontalAlignment(SwingConstants.LEFT);
    jLabelLaufzeitEinheit.setText("µs");
    jPanelLaufzeit.add(jLabelLaufzeitEinheit);
    
  }
  
  public boolean getBreakpoint(int programmCounter) {
    
    for (int i = 0; i < steuerung.getParser().getNumberOfLines() ; i++) {
      
      String tableProgrammCounter = tableData[i][1].toString();
      
      if (tableProgrammCounter.isEmpty()) {
        
        continue;
        
      } 
      
      if (Integer.parseInt(tableProgrammCounter, 16) == programmCounter) {
        
        return (boolean)tableData[i][0];
        
      } 
      
    } 
    
    return false;
  }
  
  private void initButtonPressedEvents() {
    
    resetButtonPressed = new AbstractAction() {
      
      public void actionPerformed(ActionEvent e) {
        
        jButtonReset_ActionPerformed(e);
        
      }
      
    };
    
    startButtonPressed = new AbstractAction() {
      
      public void actionPerformed(ActionEvent e) {
        
        jButtonStart_ActionPerformed(e);
        
      }
      
    };
    
    oneStepButtonPressed = new AbstractAction() {
      
      public void actionPerformed(ActionEvent e) {
        
        jButtonOneStep_ActionPerformed(e);
        
      }
      
    };
    
    stopButtonPressed = new AbstractAction() {
      
      public void actionPerformed(ActionEvent e) {
        
        jButtonStop_ActionPerformed(e);
        
      }
      
    };
    
  }
  
  private void initButtons() {
    
    jButtonReset.setPreferredSize(new Dimension(108, 20));
    jButtonReset.setText("Reset (F1)");
    
    jButtonReset.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonReset_ActionPerformed(evt);
      }
    });
    
    jButtonReset.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1,0), "F1_pressed");
    jButtonReset.getActionMap().put("F1_pressed", resetButtonPressed);
    jPanelSteuerpult.add(jButtonReset);
    
    jButtonStart.setPreferredSize(new Dimension(108, 20));
    jButtonStart.setText("Start (F2)");
    
    jButtonStart.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonStart_ActionPerformed(evt);
      }
    });
    
    
    jButtonStart.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2,0), "F2_pressed");
    jButtonStart.getActionMap().put("F2_pressed", startButtonPressed);
    jPanelSteuerpult.add(jButtonStart);
    
    jButtonOneStep.setPreferredSize(new Dimension(108, 20));
    jButtonOneStep.setText("OneStep (F3)");
    
    jButtonOneStep.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonOneStep_ActionPerformed(evt);
      }
    });
    
    jButtonOneStep.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3,0), "F3_pressed");
    jButtonOneStep.getActionMap().put("F3_pressed", oneStepButtonPressed);
    jPanelSteuerpult.add(jButtonOneStep);
    
    jButtonStop.setPreferredSize(new Dimension(108, 20));
    jButtonStop.setText("Stop (F4)");
    
    jButtonStop.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonStop_ActionPerformed(evt);
      }
    });
    
    jButtonStop.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4,0), "F4_pressed");
    jButtonStop.getActionMap().put("F4_pressed", stopButtonPressed);
    jPanelSteuerpult.add(jButtonStop);
    
    jButtonZurücksetzen.setPreferredSize(new Dimension(112, 20));
    jButtonZurücksetzen.setText("Zurücksetzen");
    
    jButtonZurücksetzen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonZurücksetzen_ActionPerformed(evt);
      }
    });
    
    jPanelLaufzeit.add(jButtonZurücksetzen);
    
  }
  
  private void initPanels() {
    
    jPanelDataStorage = new JPanel();
    jPanelDataStorage.setPreferredSize(new Dimension(525, 520));
    
    jPanelSteuerpult = new JPanel();
    jPanelSteuerpult.setBounds(0, 315, 153, 135);
    jPanelSteuerpult.setBorder(BorderFactory.createTitledBorder("Steuerpult"));
    getContentPane().add(jPanelSteuerpult);
    
    jPanelSpecialFunctions = new JPanel();
    jPanelSpecialFunctions.setBounds(153, 315, 154, 135);
    jPanelSpecialFunctions.setBorder(BorderFactory.createTitledBorder("Spezielle Register"));    
    getContentPane().add(jPanelSpecialFunctions);
    
    jPanelLaufzeit = new JPanel();
    jPanelLaufzeit.setBounds(0, 450, 153, 88);
    jPanelLaufzeit.setBorder(BorderFactory.createTitledBorder("Laufzeit"));    
    getContentPane().add(jPanelLaufzeit);
    
    jScrollPaneDataStorage.setViewportView(jPanelDataStorage);
    
    jPanelQuarzfrequenz = new JPanel();
    jPanelQuarzfrequenz.setBounds(153, 450, 154, 88);
    jPanelQuarzfrequenz.setBorder(BorderFactory.createTitledBorder("Quarzfrequenz"));    
    getContentPane().add(jPanelQuarzfrequenz);
    
    String[] quarzFrequenzen = {"1000000", "2000000", "3000000", "4000000", "5000000", "6000000", "8000000", "10000000"};
    
    jComboBoxQuarzFrequenzen = new JComboBox(quarzFrequenzen);
    jComboBoxQuarzFrequenzen.setPreferredSize(new Dimension(120, 20));
    jComboBoxQuarzFrequenzen.setSelectedItem("4000000");
    jPanelQuarzfrequenz.add(jComboBoxQuarzFrequenzen);
    
    jScrollPaneDataStorage.setViewportView(jPanelDataStorage);  
    
  }
  
  private void initScrollPanes() {
    
    jScrollPaneDataStorage = new JScrollPane();
    jScrollPaneDataStorage.setBounds(0, 0, 307, 315);
    jScrollPaneDataStorage.setBorder(BorderFactory.createTitledBorder("Speicher"));
    jScrollPaneDataStorage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPaneDataStorage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    getContentPane().add(jScrollPaneDataStorage);
    
    jScrollPaneSourceCode = new JScrollPane();
    jScrollPaneSourceCode.setBounds(307, 0, 475, 315);
    jScrollPaneSourceCode.setBorder(BorderFactory.createTitledBorder("Quellcode"));
    jScrollPaneSourceCode.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPaneSourceCode.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    getContentPane().add(jScrollPaneSourceCode);
    
  }
  
  private void updateRegister() {
    
    int registerA = steuerung.getDataStorage().getValue(5);
    int registerB = steuerung.getDataStorage().getValue(6);
    int trisA = steuerung.getDataStorage().getValue(133);
    int trisB = steuerung.getDataStorage().getValue(134);
    
    if ((registerA & 0x10) == 16) {
      
      jButtonRABit4.setText("1");
      
    } else {
      
      jButtonRABit4.setText("0");
      
    }
    
    if ((registerA & 0x08) == 8) {
      
      jButtonRABit3.setText("1");
      
    } else {
      
      jButtonRABit3.setText("0");
      
    }
    
    if ((registerA & 0x04) == 4) {
      
      jButtonRABit2.setText("1");
      
    } else {
      
      jButtonRABit2.setText("0");
      
    }
    
    if ((registerA & 0x02) == 2) {
      
      jButtonRABit1.setText("1");
      
    } else {
      
      jButtonRABit1.setText("0");
      
    } 
    
    if ((registerA & 0x01) == 1) {
      
      jButtonRABit0.setText("1");
      
    } else {
      
      jButtonRABit0.setText("0");
      
    }
    
    if ((registerB & 0x80) == 128) {
      
      jButtonRBBit7.setText("1");
      
    } else {
      
      jButtonRBBit7.setText("0");
      
    }
    
    if ((registerB & 0x40) == 64) {
      
      jButtonRBBit6.setText("1");
      
    } else {
      
      jButtonRBBit6.setText("0");
      
    }
    
    if ((registerB & 0x20) == 32) {
      
      jButtonRBBit5.setText("1");
      
    } else {
      
      jButtonRBBit5.setText("0");
      
    }
    
    if ((registerB & 0x10) == 16) {
      
      jButtonRBBit4.setText("1");
      
    } else {
      
      jButtonRBBit4.setText("0");
      
    }
    
    if ((registerB & 0x08) == 8) {
      
      jButtonRBBit3.setText("1");
      
    } else {
      
      jButtonRBBit3.setText("0");
      
    }
    
    if ((registerB & 0x04) == 4) {
      
      jButtonRBBit2.setText("1");
      
    } else {
      
      jButtonRBBit2.setText("0");
      
    }
    
    if ((registerB & 0x02) == 2) {
      
      jButtonRBBit1.setText("1");
      
    } else {
      
      jButtonRBBit1.setText("0");
      
    }
    
    if ((registerB & 0x01) == 1) {
      
      jButtonRBBit0.setText("1");
      
    } else {
      
      jButtonRBBit0.setText("0");
      
    }
    
    if ((trisA & 0x10) == 16) {
      
      jButtonTrisA4.setText("i");
      
    } else {
      
      jButtonTrisA4.setText("o");
      
    }
    
    if ((trisA & 0x08) == 8) {
      
      jButtonTrisA3.setText("i");
      
    } else {
      
      jButtonTrisA3.setText("o");
      
    } 
    
    if ((trisA & 0x04) == 4) {
      
      jButtonTrisA2.setText("i");
      
    } else {
      
      jButtonTrisA2.setText("o");
      
    }
    
    if ((trisA & 0x02) == 2) {
      
      jButtonTrisA1.setText("i");
      
    } else {
      
      jButtonTrisA1.setText("o");
      
    }
    
    if ((trisA & 0x01) == 1) {
      
      jButtonTrisA0.setText("i");
      
    } else {
      
      jButtonTrisA0.setText("o");
      
    }
    
    if ((trisB & 0x80) == 128) {
      
      jButtonTrisB7.setText("i");
      
    } else {
      
      jButtonTrisB7.setText("o");
      
    }
    
    if ((trisB & 0x40) == 64) {
      
      jButtonTrisB6.setText("i");
      
    } else {
      
      jButtonTrisB6.setText("o");
      
    }
    
    if ((trisB & 0x20) == 32) {
      
      jButtonTrisB5.setText("i");
      
    } else {
      
      jButtonTrisB5.setText("o");
      
    }
    
    if ((trisB & 0x10) == 16) {
      
      jButtonTrisB4.setText("i");
      
    } else {
      
      jButtonTrisB4.setText("o");
      
    }
    
    if ((trisB & 0x08) == 8) {
      
      jButtonTrisB3.setText("i");
      
    } else {
      
      jButtonTrisB3.setText("o");
      
    }
    
    if ((trisB & 0x04) == 4) {
      
      jButtonTrisB2.setText("i");
      
    } else {
      
      jButtonTrisB2.setText("o");
      
    }
    
    if ((trisB & 0x02) == 2) {
      
      jButtonTrisB1.setText("i");
      
    } else {
      
      jButtonTrisB1.setText("o");
      
    }
    
    if ((trisB & 0x01) == 1) {
      
      jButtonTrisB0.setText("i");
      
    } else {
      
      jButtonTrisB0.setText("o");
      
    }
    
  }
  
  private void initMenueBar() {
    
    jMenuBar = new JMenuBar();
    
    jMenuFile = new JMenu();
    jMenuFile.setText("Datei");
    jMenuItemFileOpen = new JMenuItem();
    jMenuItemFileOpen.setText("Öffnen");
    
    jMenuItemFileOpen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jMenuItemFileOpenActionPerformed(evt);
      }
    });
    
    jMenuFile.add(jMenuItemFileOpen);
    jMenuBar.add(jMenuFile);
    
    jMenuInfo = new JMenu();
    jMenuInfo.setText("Info");
    jMenuItemInfoView = new JMenuItem();
    jMenuItemInfoView.setText("Info Anzeigen");
    
    jMenuItemInfoView.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jMenuItemInfoViewActionPerformed(evt);
      }
    });
    
    jMenuInfo.add(jMenuItemInfoView);
    jMenuBar.add(jMenuInfo);
    
    jMenuHelp = new JMenu();
    jMenuHelp.setText("Hilfe");
    jMenuItemHelpView = new JMenuItem();
    jMenuItemHelpView.setText("Hilfe Anzeigen");
    
    jMenuItemHelpView.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jMenuItemHelpViewActionPerformed(evt);
      }
    });
    
    jMenuHelp.add(jMenuItemHelpView);
    jMenuBar.add(jMenuHelp);
    
    setJMenuBar(jMenuBar);
    
  }
  
  public void jButtonZurücksetzen_ActionPerformed(ActionEvent evt) {
    
    jLabelLaufzeit.setText("0.0");
    steuerung.setLaufzeit(0);
    
  }
  
  public void jButtonStop_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile && steuerung.getRunning()) {
      
      steuerung.setRunning(false);
      
    } 
  }
  
  public void jButtonReset_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile && steuerung.getRunning()) {
      
      steuerung.setRunning(false);
      steuerung.getDataStorage().resetDataStoragePowerOn();
      automaticTableScroll();
      
    } 
    
    if (loadedFile && !steuerung.getRunning()) {
      
      steuerung.getDataStorage().resetDataStoragePowerOn();
      automaticTableScroll();
      
    } 
    
    steuerung.setHold(false);
    updateElements();
    
  }
  
  public void jButtonStart_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile && !steuerung.getRunning()) {
      
      steuerung.setRunning(true);
      executionWorker = new ExecutionWorker(steuerung, this);
      executionWorker.execute();
      
    }  
  }
  
  public void jButtonOneStep_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      steuerung.executeOneCommand();
      updateElements();
      automaticTableScroll();
      
    } 
  }
  
  private void initDataStorage() {
    
    for (int i = 0; i < 17; i++ ) {
      
      for (int j = 0; j < 17; j++) {
        
        if (i == 0 && j == 0) { // 1. Platzhalter links oben
          
          jLabelDataStorage[j] = new JLabel();
          jLabelDataStorage[j].setPreferredSize(new Dimension(25, 25));
          jPanelDataStorage.add(jLabelDataStorage[j]);
          
        } else if (i == 0 && j != 0) { // 1. Zeile Beschriftung
          
          jLabelDataStorage[j] = new JLabel();
          jLabelDataStorage[j].setPreferredSize(new Dimension(25, 25));
          jLabelDataStorage[j].setText(Integer.toHexString(j - 1));
          jLabelDataStorage[j].setHorizontalAlignment(SwingConstants.CENTER);
          jPanelDataStorage.add(jLabelDataStorage[j]);
          
        } else if (i != 0 && j == 0) { // 1. Spalte Beschriftung
          
          jLabelDataStorage[i + 17] = new JLabel();
          jLabelDataStorage[i + 17].setPreferredSize(new Dimension(25, 25));
          jLabelDataStorage[i + 17].setText(Integer.toHexString(i - 1) + "x");
          jLabelDataStorage[i + 17].setHorizontalAlignment(SwingConstants.CENTER);
          jPanelDataStorage.add(jLabelDataStorage[i + 17]);
          
        } else if (i != 0 && j != 0) { // Alle Textfelder (DataStorage)
          
          jTextFieldDataStorage[(i - 1) * 16 + (j - 1)] = new JTextField();
          jTextFieldDataStorage[(i - 1) * 16 + (j - 1)].setName(Integer.toString((i - 1) * 16 + (j - 1)));
          jTextFieldDataStorage[(i - 1) * 16 + (j - 1)].setPreferredSize(new Dimension(25, 25));
          jTextFieldDataStorage[(i - 1) * 16 + (j - 1)].setText(Integer.toHexString(steuerung.getDataStorage().getValue((i - 1) * 16 + (j - 1))));
          jTextFieldDataStorage[(i - 1) * 16 + (j - 1)].setHorizontalAlignment(SwingConstants.CENTER);
          jTextFieldDataStorage[(i - 1) * 16 + (j - 1)].addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
              mouseClicked_Action(e);
            }
          });
          jTextFieldDataStorage[(i - 1) * 16 + (j - 1)].setFocusable(false);
          jPanelDataStorage.add(jTextFieldDataStorage[(i - 1) * 16 + (j - 1)]);
          
        } else if (i != 0 && j == 17) { // Platzhalter jeweils am ende einer Zeile
          
          jLabelDataStorage[j] = new JLabel();
          jLabelDataStorage[j].setPreferredSize(new Dimension(25, 25));
          jPanelDataStorage.add(jLabelDataStorage[j]);
          
        }
      }
    }
  }
  
  public void mouseClicked_Action(MouseEvent e) {
    
    int count = e.getClickCount();
    
    if(count == 2) {
      
      JTextField jTextFieldDummy = (JTextField) e.getComponent();
      
      String registerHexName = Integer.toHexString(Integer.parseInt(jTextFieldDummy.getName()));
      int registerAdress = Integer.parseInt(registerHexName, 16);
      
      if (registerAdress == 2 || registerAdress == 130) {
        
        steuerung.setHold(false);
        
      } 
      
      String actualHexValue = Integer.toHexString(steuerung.getDataStorage().getValue(registerAdress));
      
      JPanel jPanelRegistervalueChange = new JPanel();
      jPanelRegistervalueChange.setPreferredSize(new Dimension(0, 92));
      jPanelRegistervalueChange.setBorder(BorderFactory.createTitledBorder("Register " + registerHexName));
      
      JLabel jLabelActualValue = new JLabel("Aktueller Wert: " + actualHexValue);
      jLabelActualValue.setPreferredSize(new Dimension(155, 25));
      jPanelRegistervalueChange.add(jLabelActualValue);
      
      JLabel jLabelNewValue = new JLabel("Neuer Wert:");
      jPanelRegistervalueChange.add(jLabelNewValue);
      
      JTextFieldLimit jTextFieldNewValue = new JTextFieldLimit(2);
      jTextFieldNewValue.setPreferredSize(new Dimension(25, 25));
      jTextFieldNewValue.setHorizontalAlignment(SwingConstants.CENTER);
      jTextFieldNewValue.addAncestorListener( new RequestFocusListener() ); 
      jPanelRegistervalueChange.add(jTextFieldNewValue);
      
      JLabel jLabelHex = new JLabel("h");
      jLabelHex.setPreferredSize(new Dimension(18, 25));
      jPanelRegistervalueChange.add(jLabelHex);
      
      while (true) { 
        
        int answer = JOptionPane.showOptionDialog(this, jPanelRegistervalueChange , "Registerinhalt ändern", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null ,null);
        
        if (answer == JOptionPane.CLOSED_OPTION || answer == JOptionPane.CANCEL_OPTION) {
          
          return;
          
        } 
        
        String newValue = jTextFieldNewValue.getText();
        
        if (newValue.equals("")) {
          
          return;
          
        } 
        
        try {
          
          if (newValue.indexOf('+') != -1 || newValue.indexOf('-') != -1) {
            
            JOptionPane.showMessageDialog(this, "Der eingegebene Wert ist ungültig!", "Fehler", JOptionPane.ERROR_MESSAGE);
            
          } else {
            
            if (!loadedFile) {
              
              return;
              
            } 
            
            int hexString = Integer.parseInt(newValue, 16);
            steuerung.getDataStorage().setValue(registerAdress, hexString);
            jTextFieldDummy.setText(newValue);
            updateElements();
            automaticTableScroll();
            return;
            
          } 
          
        } catch(Exception ex) {
          
          JOptionPane.showMessageDialog(this, "Der eingegebene Wert ist ungültig!", "Fehler", JOptionPane.ERROR_MESSAGE);
          
        } 
      }
    }
  }
  
  public void updateElements(){
    
    threadLock.lock();
    
    try {
      
      jLabelWRegisterValue.setText(Integer.toHexString(steuerung.getWRegister().getValue()));
      jLabelPCLValue.setText(Integer.toHexString(steuerung.getDataStorage().getValue(2)));
      jLabelStatusValue.setText(Integer.toHexString(steuerung.getDataStorage().getValue(3)));
      jLabelFSRValue.setText(Integer.toHexString(steuerung.getDataStorage().getValue(4)));
      jLabelLaufzeit.setText(Double.toString(steuerung.getLaufzeit()));
      updateRegister();
      
      for (int i = 0;i<256;i++) {
        
        jTextFieldDataStorage[i].setText(Integer.toHexString(steuerung.getDataStorage().getValue(i)));        
        
      } 
      
    } catch(Exception e) {
      
    } finally {
      
      repaint();
      threadLock.unlock();
      
    } 
  }
  
  private void initColumns() {
    
    columnHeaders = new String[7];
    columnHeaders[0] = "B";
    columnHeaders[1] = "PC";
    columnHeaders[2] = "Hex";
    columnHeaders[3] = "Line";
    columnHeaders[4] = "Label";
    columnHeaders[5] = "Command";
    columnHeaders[6] = "Comment";   
    
  }
  
  private void initRows(int numberOfRows, int numberOfColumns) {
    
    tableData = new Object[numberOfRows][numberOfColumns];
    
    for (int row = 0; row < numberOfRows; row++ ) {
      
      for (int column = 0; column < numberOfColumns; column++) {
        
        if (column == 0) {
          
          tableData[row][column] = false;
          
        } else {
          
          tableData[row][column] = steuerung.getParser().getString(row, column - 1);
          
        }
      } 
    }  
  }
  
  private void layoutTable() {
    
    jTableSourceCode.getTableHeader().setResizingAllowed(true);
    jTableSourceCode.getTableHeader().setReorderingAllowed(false);
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    
    jScrollPaneSourceCode.setColumnHeader(new JViewport() {
      
      public Dimension getPreferredSize() {
        
        Dimension d = super.getPreferredSize();
        d.height = 25;
        return d;
        
      }
      
    });
    
    jScrollPaneSourceCode.setViewportView(jTableSourceCode);
    
    jTableSourceCode.getColumnModel().getColumn(0).setPreferredWidth(20);
    jTableSourceCode.getColumnModel().getColumn(1).setPreferredWidth(50);
    jTableSourceCode.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    jTableSourceCode.getColumnModel().getColumn(2).setPreferredWidth(50);
    jTableSourceCode.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
    jTableSourceCode.getColumnModel().getColumn(3).setPreferredWidth(55);
    jTableSourceCode.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
    jTableSourceCode.getColumnModel().getColumn(4).setPreferredWidth(75);
    jTableSourceCode.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
    jTableSourceCode.getColumnModel().getColumn(5).setPreferredWidth(100);
    jTableSourceCode.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);    
    jTableSourceCode.getColumnModel().getColumn(6).setPreferredWidth(400);
    
    jTableSourceCode.setRowHeight(22);
    jTableSourceCode.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
    jTableSourceCode.setFont(new Font("Arial", Font.BOLD, 12));
    jTableSourceCode.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTableSourceCode.setFocusable(false);
    
  }
  
  private void createTable() {
    
    initColumns();
    int numberOfRows = steuerung.getParser().getNumberOfLines();
    int numberOfColumns = columnHeaders.length;
    initRows(numberOfRows, numberOfColumns);
    
    jTableSourceCode = new MyTable(new MyTableModel(tableData, columnHeaders), steuerung);
    
    layoutTable();
    automaticTableScroll();
    
  }
  
  public void automaticTableScroll() {
    
    int index = steuerung.getParser().getCurrentCommandTableIndex(steuerung.getDataStorage().getProgrammCounter());
    jTableSourceCode.scrollRectToVisible(jTableSourceCode.getCellRect(index, 0, true));
    jTableSourceCode.repaint();
    
  }
  
  private void jMenuItemFileOpenActionPerformed(ActionEvent evt) {                                                  
    
    final JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(".lst", "lst");
    chooser.setFileFilter(filter);
    chooser.setAcceptAllFileFilterUsed(false); 
    chooser.setDialogType(JFileChooser.OPEN_DIALOG); 
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    
    final File file = new File("/"); 
    
    chooser.setCurrentDirectory(file); 
    
    chooser.addPropertyChangeListener(new PropertyChangeListener() { ;
      public void propertyChange(PropertyChangeEvent e) { 
        if (e.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) 
        || e.getPropertyName().equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) { 
          final File f = (File) e.getNewValue(); 
        } 
      } 
    }); 
    
    chooser.setVisible(true); 
    final int result = chooser.showOpenDialog(this); 
    
    if (result == JFileChooser.APPROVE_OPTION) { 
      
      File inputVerzFile = chooser.getSelectedFile();
      
      try {
        
        steuerung.getParser().parse(inputVerzFile);
        createTable();
        steuerung.getDataStorage().resetDataStoragePowerOn();
        updateElements();
        automaticTableScroll();
        loadedFile = true;
        
      } catch(Exception e) {
        
        JOptionPane.showMessageDialog(this, "Fehler beim Parsen der lst-Datei!\n" + e, "Fehler", JOptionPane.ERROR_MESSAGE);
        
      } 
      
    } 
    
    chooser.setVisible(false);   
    
  }
  
  private void jMenuItemInfoViewActionPerformed(ActionEvent evt) {                                                  
    
    JOptionPane.showMessageDialog(this, "Name:                    " + this.getTitle() + "\nMotivation:            Der Simulator ist ein Testat für die Vorlesung Rechnerarchitektur an der DHBW Karlsruhe\nBeschreibung:     Dieser Simulator liest .lst-Dateien ein und simmluiert den darin enthalten Assemblercode\nAutoren:                Christopher Heß und Alexander Burkhardt (TINF12B5)", "Info", JOptionPane.INFORMATION_MESSAGE); 
    
  }
  
  private void jMenuItemHelpViewActionPerformed(ActionEvent evt) {                                                  
    
    try {
      
      File pdfFile = new File("Hilfe.pdf"); 
      
      if (pdfFile.exists()) {
        
        if (Desktop.isDesktopSupported()) {
          
          Desktop.getDesktop().open(pdfFile);
          
        }
        
      } else {
        
        JOptionPane.showMessageDialog(this, "Die Datei Hilfe.pdf ist nicht vorhanden!", "Fehler", JOptionPane.ERROR_MESSAGE);
        
      }
      
    } catch (Exception ex) {
      
      ex.printStackTrace();
      
    }
  }
  
  public int getActualQuarzFrequence() {
    
    threadLock.lock();
    int selectedItem = 0;
    
    try {
      
      selectedItem = Integer.parseInt((String)jComboBoxQuarzFrequenzen.getSelectedItem());
      
    } catch(Exception e) {
      
    } finally {
      
      threadLock.unlock();
      return selectedItem;
      
    }
  } 
}