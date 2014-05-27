import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.beans.*;
import java.io.*;
import javax.swing.filechooser.*;
import javax.swing.table.*;
import org.w3c.dom.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.*;

public class MainFrame extends JFrame {
  
  private Steuerung steuerung;
  
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
  
  private JLabel[] jLabelDataStorage;
  private JTextField[] jTextFieldDataStorage;
  
  private JButton jButtonReset = new JButton();
  private JButton jButtonStart = new JButton();
  private JButton jButtonStop = new JButton();
  
  private JTable jTableSourceCode;
  
  private String[] columnHeaders;
  private Object[][] tableData;
  
  public MainFrame(String title, Steuerung s) { 
    
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
    
    steuerung = s;
    
    this.setIconImage(Toolkit.getDefaultToolkit().getImage("Images/favicon.png"));
    
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
    
    jScrollPaneDataStorage = new JScrollPane();
    jScrollPaneDataStorage.setBounds(0, 0, 307, 315);
    jScrollPaneDataStorage.setBorder(BorderFactory.createTitledBorder("Speicher"));
    jScrollPaneDataStorage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPaneDataStorage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    cp.add(jScrollPaneDataStorage);
    
    jScrollPaneSourceCode = new JScrollPane();
    jScrollPaneSourceCode.setBounds(307, 0, 475, 315);
    jScrollPaneSourceCode.setBorder(BorderFactory.createTitledBorder("Quellcode"));
    jScrollPaneSourceCode.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPaneSourceCode.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    cp.add(jScrollPaneSourceCode);
    
    jPanelDataStorage = new JPanel();
    jPanelDataStorage.setPreferredSize(new Dimension(525, 520)); // 525
    
    jPanelSteuerpult = new JPanel();
    jPanelSteuerpult.setBounds(0, 315, 115, 125);
    jPanelSteuerpult.setBorder(BorderFactory.createTitledBorder("Steuerpult"));
    cp.add(jPanelSteuerpult);
    
    jScrollPaneDataStorage.setViewportView(jPanelDataStorage); 
    
    jLabelDataStorage = new JLabel[48];
    jTextFieldDataStorage = new JTextField[256];
    
    jButtonReset.setPreferredSize(new Dimension(80, 25));
    jButtonReset.setText("Reset");
    jButtonReset.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonReset_ActionPerformed(evt);
      }
    });
    jPanelSteuerpult.add(jButtonReset);
    
    jButtonStart.setPreferredSize(new Dimension(80, 25));
    jButtonStart.setText("Start");
    jButtonStart.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonStart_ActionPerformed(evt);
      }
    });
    jPanelSteuerpult.add(jButtonStart);
    
    jButtonStop.setPreferredSize(new Dimension(80, 25));
    jButtonStop.setText("Stop");
    jButtonStop.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonStop_ActionPerformed(evt);
      }
    });
    jPanelSteuerpult.add(jButtonStop);
    
    initSpeicher();
    
    setVisible(true);
    
  }
  
  public void jButtonReset_ActionPerformed(ActionEvent evt) {
    
    
    
  }
  
  public void jButtonStart_ActionPerformed(ActionEvent evt) {
    
    
    
  }
  
  public void jButtonStop_ActionPerformed(ActionEvent evt) {
    
    
    
  }
  
  
  private void initSpeicher() {
    
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
      jPanelRegistervalueChange.add(jTextFieldNewValue);
      
      JLabel jLabelHex = new JLabel("h");
      jLabelHex.setPreferredSize(new Dimension(17, 25));
      jPanelRegistervalueChange.add(jLabelHex);
      
      int answer = JOptionPane.showOptionDialog(this, jPanelRegistervalueChange , "Registerinhalt �ndern", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null ,null);
      
      if (answer == JOptionPane.CLOSED_OPTION || answer == JOptionPane.CANCEL_OPTION) {
        
        return;
        
      } 
      
      String newValue = jTextFieldNewValue.getText();
      
      if (newValue.equals("")) {
        
        return;
        
      } 
      
      try {
        
        int hexString = Integer.parseInt(newValue, 16);
        steuerung.getDataStorage().setValue(registerAdress, hexString);
        jTextFieldDummy.setText(newValue);
        
      } catch(Exception ex) {
        
        JOptionPane.showMessageDialog(this, "Der eingegebene Wert ist ung�ltig!", "Fehler", JOptionPane.ERROR_MESSAGE);
        
      } finally {
        
        return;
        
      }
    }
  }
  
  public void updateDataTable(){
    
    for (int i = 0;i<256;i++) {
      
      jTextFieldDataStorage[i].setText(Integer.toHexString(steuerung.getDataStorage().getValue(i)));
      
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
    
    jTableSourceCode.getTableHeader().setResizingAllowed(false);
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
    
    jTableSourceCode = new JTable(new MyTableModel(tableData, columnHeaders)) {
      
      public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {  
        
        Component cell = super.prepareRenderer(renderer, row, column);  
        String selectedCellValue = getValueAt(row, column).toString();  
        
        if( column != 0 ) {
          
          JLabel cellLabel = (JLabel)cell;  
          boolean showRow = (Boolean)jTableSourceCode.getModel().getValueAt(row, 0); 
          
          if( showRow == false ) {  
            
            cellLabel.setForeground(Color.BLACK);
            cellLabel.setBackground( Color.WHITE );
            
          }  
          
          else {  
            
            cellLabel.setForeground(Color.WHITE);
            cellLabel.setBackground( Color.RED );  
            
          }  
          
          
          return cellLabel;  
        }  
        
        repaint();
        return cell;  
      }  
      
    };
    
    layoutTable();
    
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
        updateDataTable();
        
      } catch(Exception e) {
        
        JOptionPane.showMessageDialog(this, "Fehler beim Parsen der lst-Datei!\n" + e, "Fehler", JOptionPane.ERROR_MESSAGE);
        
      } finally {
        
        
        
      } 
      
      
    } else {
      
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
  
  public class JTextFieldLimit extends JTextField {
    
    private int limit;
    
    public JTextFieldLimit(int limit) {
      
      super();
      this.limit = limit;
      
    }
    
    protected javax.swing.text.Document createDefaultModel() {
      
      return new LimitDocument();
      
    } 
    
    private class LimitDocument extends PlainDocument {
      
      public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        
        if (str == null) return;
        
        if ((getLength() + str.length()) <= limit) {
          
          super.insertString(offset, str, attr);
          
        }
      }       
    }
  }
  
  class MyTableModel extends AbstractTableModel {
    
    private String[] columnNames;
    private Object[][] data;
    
    public MyTableModel (Object[][] tableData, String[] columnHeaders) {
      
      columnNames = columnHeaders;
      data = tableData;
      
    }
    
    public int getColumnCount() {
      
      return columnNames.length;
      
    }
    
    public int getRowCount() {
      
      return data.length;
      
    }
    
    public String getColumnName(int col) {
      
      return columnNames[col];
      
    }
    
    public Object getValueAt(int row, int col) {
      
      return data[row][col];
      
    }
    
    public Class getColumnClass(int c) {
      
      return getValueAt(0, c).getClass();
      
    }
    
    public boolean isCellEditable(int row, int col) {
      
      if (col == 0 && data[row][col + 1] != "") {
        
        return true ;  
        
      } else {
        
        return false;
        
      }
    }
    /*
    * Don't need to implement this method unless your table's
    * data can change.
    */
    public void setValueAt(Object value, int row, int col) {
      
      data[row][col] = value;
      fireTableCellUpdated(row, col);
      
    }
  }
}