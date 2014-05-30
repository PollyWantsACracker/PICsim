/*
Die Instanz dieser Klasse bildet das Hauptfenster der Anwendung.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;

public class MainFrame extends JFrame {
  
  private boolean running;
  private boolean loadedFile;
  
  private Steuerung steuerung;
  private ExecutionWorker executionWorker;
  private JLabel[] jLabelDataStorage;
  private JTextField[] jTextFieldDataStorage;
  private String[] columnHeaders;
  private Object[][] tableData;
  
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
  private JButton jButtonReset = new JButton();
  private JButton jButtonStart = new JButton();
  private JButton jButtonOneStep = new JButton();
  private JTable jTableSourceCode;
  
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
    running = false;
    
    steuerung = aSteuerung;
    jLabelDataStorage = new JLabel[48];
    jTextFieldDataStorage = new JTextField[256];
    
    initMenueBar();
    initScrollPanes();
    initPanels();
    initButtons();
    initDataStorage();
    
    setVisible(true);
    
  }
  
  private void initButtons() {
    
    jButtonReset.setPreferredSize(new Dimension(100, 25));
    jButtonReset.setText("Reset");
    
    jButtonReset.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonReset_ActionPerformed(evt);
      }
    });
    
    jPanelSteuerpult.add(jButtonReset);
    
    jButtonStart.setPreferredSize(new Dimension(100, 25));
    jButtonStart.setText("Start");
    
    jButtonStart.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonStart_ActionPerformed(evt);
      }
    });
    
    jPanelSteuerpult.add(jButtonStart);
    
    jButtonOneStep.setPreferredSize(new Dimension(100, 25));
    jButtonOneStep.setText("OneStep");
    
    jButtonOneStep.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonOneStep_ActionPerformed(evt);
      }
    });
    
    jPanelSteuerpult.add(jButtonOneStep);
    
  }
  
  private void initPanels() {
    
    jPanelDataStorage = new JPanel();
    jPanelDataStorage.setPreferredSize(new Dimension(525, 520));
    
    jPanelSteuerpult = new JPanel();
    jPanelSteuerpult.setBounds(0, 315, 130, 125);
    jPanelSteuerpult.setBorder(BorderFactory.createTitledBorder("Steuerpult"));
    getContentPane().add(jPanelSteuerpult);
    
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
  
  private void initMenueBar() {
    
    jMenuBar = new JMenuBar();
    
    jMenuFile = new JMenu();
    jMenuFile.setText("Datei");
    jMenuItemFileOpen = new JMenuItem();
    jMenuItemFileOpen.setText("�ffnen");
    
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
  
  public void jButtonReset_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile && running) {
      
      running = false; 
      executionWorker.cancel(true);
      jButtonStart.setEnabled(true);
      automaticTableScroll();
      
    } 
    
    steuerung.setProgrammCounter(0);
    
    if (loadedFile) {
      
      automaticTableScroll();
      
    } 
  }
  
  public void jButtonStart_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      jButtonStart.setEnabled(false);
      running = true;
      executionWorker = new ExecutionWorker(steuerung);
      executionWorker.execute();
      
    }  
  }

  public void jButtonOneStep_ActionPerformed(ActionEvent evt) {
    
    if (loadedFile) {
      
      steuerung.executeOneCommand();
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
        
        int answer = JOptionPane.showOptionDialog(this, jPanelRegistervalueChange , "Registerinhalt �ndern", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null ,null);
        
        if (answer == JOptionPane.CLOSED_OPTION || answer == JOptionPane.CANCEL_OPTION) {
          
          return;
          
        } 
        
        String newValue = jTextFieldNewValue.getText();
        
        if (newValue.equals("")) {
          
          return;
          
        } 
        
        try {
          
          if (newValue.indexOf('+') != -1 || newValue.indexOf('-') != -1) {
            
            JOptionPane.showMessageDialog(this, "Der eingegebene Wert ist ung�ltig!", "Fehler", JOptionPane.ERROR_MESSAGE);
            
          } else {
            
            int hexString = Integer.parseInt(newValue, 16);
            steuerung.getDataStorage().setValue(registerAdress, hexString);
            jTextFieldDummy.setText(newValue);
            return;
            
          } 
          
        } catch(Exception ex) {
          
          JOptionPane.showMessageDialog(this, "Der eingegebene Wert ist ung�ltig!", "Fehler", JOptionPane.ERROR_MESSAGE);
          
        } 
      }
    }
  }
  
  public void updateDataStorage(){
    
    for (int i = 0;i<256;i++) {
      
      int oldValue = Integer.parseInt(jTextFieldDataStorage[i].getText(),16);
      
      if (steuerung.getDataStorage().getValue(i) != oldValue) {
        
        jTextFieldDataStorage[i].setText(Integer.toHexString(steuerung.getDataStorage().getValue(i)));
        jTextFieldDataStorage[i].setBackground(Color.YELLOW);
        
      } else {
        
        jTextFieldDataStorage[i].setBackground(Color.WHITE);
        
      } 
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
  
  private void automaticTableScroll() {
    
    int index = steuerung.getParser().getCurrentCommandTableIndex(steuerung.getProgrammCounter());
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
        updateDataStorage();
        loadedFile = true;
        
      } catch(Exception e) {
        
        JOptionPane.showMessageDialog(this, "Fehler beim Parsen der lst-Datei!\n" + e, "Fehler", JOptionPane.ERROR_MESSAGE);
        
      } 
      
    } 
    
    chooser.setVisible(false);   
    
  }
  
  private void jMenuItemInfoViewActionPerformed(ActionEvent evt) {                                                  
    
    JOptionPane.showMessageDialog(this, "Name:                    " + this.getTitle() + "\nMotivation:            Der Simulator ist ein Testat f�r die Vorlesung Rechnerarchitektur an der DHBW Karlsruhe\nBeschreibung:     Dieser Simulator liest .lst-Dateien ein und simmluiert den darin enthalten Assemblercode\nAutoren:                Christopher He� und Alexander Burkhardt (TINF12B5)", "Info", JOptionPane.INFORMATION_MESSAGE); 
    
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
}