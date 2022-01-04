package registrace2022.Gui;

import registrace2022.Data.Clen;
import registrace2022.Data.SeznamClenu;
import registrace2022.Data.ClenoveTabulka;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;

import static javax.swing.JOptionPane.*;

/**
 * graficke rozhrani
 */

public class HlavniOkno extends JFrame implements ActionListener {


    //Tlacitka
    JButton btAdd = new JButton("Přidat");
    JButton btDel = new JButton("Smazat");
    JButton btUpd = new JButton("Upravit");
    JButton btSer = new JButton("Vyhledat");
    JButton btSort =  new JButton("Seřadit");
    JButton btAbout = new JButton("O programu");

    SeznamClenu clenove = new SeznamClenu();
    ClenoveTabulka clenoveTabulka = new ClenoveTabulka(clenove);
    JTable tbClenove = new JTable(clenoveTabulka);

    JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem("Opravdu smazat záznam?", true);

    // vstupni pole

    private JTextField tfJmeno = new JTextField(20);
    private JTextField tfKategorie = new JTextField(10);
    private JTextField tfMestoBydliste = new JTextField(20);
    private JTextField tfVek = new JTextField(5);

    Action actionNew,
            actionOpen, actionSave, actionExit, actionAbout, actionFind, actionClearFilter;


    public HlavniOkno() {
        super("Evidence členů organizační jednotky Česká Skalice 2022");
//        this.setVisible(true);
        this.setTitle("Registrace2022");

        initGui();
//        setSize(300,300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
    }

    protected void initGui() {
        setBackground(Color.yellow);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/registrace2022/Data/img/coffee-icon.png")));

//        mainPanel.setBackground(Color.cyan);

        JToolBar toolBar = createToolbar();
        add(toolBar, "Center");
//        toolBar.setBackground(Color.CYAN);

        JPanel innerPanel = new JPanel(new BorderLayout());
        add(innerPanel, "Center");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createTitledBorder("Detail člena"));
//        mainPanel.setBorder(BorderFactory.createLineBorder(Color.RED));

        createActions();
        createMenu();


        //vstupni radky pro editaci
        FocusListener autoSelectListener = new FocusAdapter() {

            public void focusGained(FocusEvent e) {
                JTextComponent tc = (JTextComponent) e.getSource();
                tc.selectAll();
            }
        };

        tfJmeno.addFocusListener(autoSelectListener);
        tfJmeno.setToolTipText("jméno člena");
        tfKategorie.addFocusListener(autoSelectListener);
        tfKategorie.setToolTipText("věková kategorie");
        tfMestoBydliste.addFocusListener(autoSelectListener);
        tfMestoBydliste.setToolTipText("město bydliště člena");
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(2, 3, 10, 2));
        p2.add(new JLabel("Jméno člena"));
        p2.add(new JLabel("Členská kategorie"));
        p2.add(new JLabel("Město bydliště"));
        p2.add(tfJmeno);
        p2.add(tfKategorie);
        p2.add(tfMestoBydliste);
        mainPanel.add(p2);

        JPanel p1 = new JPanel();
        btAdd.addActionListener(this);
        btAdd.setToolTipText("Přidání nového člena do evidence");
        btUpd.addActionListener(this);
        btUpd.setToolTipText("Uloženi aktualních změn do vybraného zaznamu");
        btDel.addActionListener(this);
        btDel.setToolTipText("Smazani vybraneho clena z evidence");
        btSer.addActionListener(this);
        btSer.setToolTipText("Vyhledat člena dle jména");
        btSort.setToolTipText("Seřadit členy dle abecedy");

        p1.add(btAdd);
        p1.add(btUpd);
        p1.add(btDel);
        p1.add(btSer);
        p1.add(btSort);
        p1.add(btAbout);
        mainPanel.add(p1);

        innerPanel.add(mainPanel, "North");

        tbClenove.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbClenove.setPreferredScrollableViewportSize(new Dimension(300, 200));
        tbClenove.
                getSelectionModel()
                .addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int pos = tbClenove.getSelectedRow();
                        if (pos != -1) {
                            Clen clen = clenove.get(pos);
                            tfJmeno.setText(clen.getJmeno());
                            tfKategorie.setText(clen.getKategorie());
                            tfMestoBydliste.setText(clen.getMestoBydliste());
                        }
                    }
                });
//
//        TableRowSorter<ClenoveTabulka> sorter = new TableRowSorter<ClenoveTabulka>(clenoveTabulka);
//        tbClenove.setRowSorter(sorter);
//        sorter.setRowFilter(RowFilter.regexFilter());

        tbClenove.setToolTipText("Seznam členů v registraci");
        JScrollPane sp = new JScrollPane(tbClenove);
        sp.setBorder(BorderFactory.createTitledBorder("Seznam členů"));
//        sp.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        innerPanel.add(sp, "Center");
    }

    private JToolBar createToolbar() {
        JToolBar tb = new JToolBar("Nástroje", JToolBar.HORIZONTAL);
        tb.setFloatable(true);
        tb.addSeparator();
        tb.add(actionNew);
        tb.addSeparator();
        tb.add(actionOpen);
        tb.addSeparator();
        tb.add(actionSave);
        tb.addSeparator();
        tb.add(actionAbout);
        return tb;
    }

    //funkce tlacitek
    @Override
    public void actionPerformed(ActionEvent e) {
        int pos;

        if (e.getSource() == btAdd) { //pracuji s tlacitkem pridat
            Clen clen =
                    new Clen(
                            tfJmeno.getText(),
                            tfKategorie.getText(),
                            tfMestoBydliste.getText());

            clenove.add(clen);
            clenoveTabulka.refresh();
            clearInput();
            tfJmeno.grabFocus();
        } else if (e.getSource() == btUpd) { //pracuji s tlacitkem upravit
            if ((pos = tbClenove.getSelectedRow()) != -1) { //-1 protoze indexuji od nuly!!!
                Clen clen = new Clen(
                        tfJmeno.getText(),
                        tfKategorie.getText(),
                        tfMestoBydliste.getText());
                clenove.set(clen, pos);
                clenoveTabulka.refresh();
                clenove.remove(pos);
                clenove.add(clen);
            }
        } else if (e.getSource() == btDel) {
            if ((pos = tbClenove.getSelectedRow()) != -1) {
                if (!checkBoxMenuItem.isSelected()
                        || showConfirmDialog(this, "Opravdu chcete smazat záznam?", "Potvrd!", YES_NO_OPTION) == OK_OPTION)
                    clenove.remove(pos);
                clenoveTabulka.refresh();
            }
        } else if (e.getSource()== btSer) {
            Comparator<Clen> porovnavac = new Comparator<Clen>() {
                @Override
                public int compare(Clen o1, Clen o2) {
                    return 0;
                }
            };
        }else  if (e.getSource()== btAbout) {
            about();

        } else if (e.getSource()== btSort) {
//            Arrays.sort(clenove);
//            System.out.println(Arrays.toString(clenove));
        }
    }

//    private void editace() {
//        String poradiStr = JOptionPane.showInputDialog("Zadejte čislo člena pro úpravu");
//        try {
//            int poradi = Integer.valueOf(poradiStr);
//            poradi--;
//            if (poradi >= 0 && clenove.size()) {
//                Clen c = new Clen()
//            }
//
//        }
//    }

//    private void clear

    private void createMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu fileMenu = new JMenu("Soubor");
        fileMenu.add(actionNew);
        fileMenu.addSeparator();
        fileMenu.add(actionOpen);
        fileMenu.addSeparator();
        fileMenu.add(actionSave);
        fileMenu.addSeparator();
        fileMenu.add(actionExit);



        JMenu helpMenu = new JMenu("Ostatní");

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lfName = null;
                if (e.getActionCommand().equals("JAVA")) {
                    lfName = "javax.swing.plaf.metal.MetalLookAndFeel";
                } else if (e.getActionCommand().equals("SYS")) {
                    lfName = UIManager.getSystemLookAndFeelClassName();
                } else if (e.getActionCommand().equals("MOTIF")) {
                    lfName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                }
                try {
                    UIManager.setLookAndFeel(lfName);
                    SwingUtilities.updateComponentTreeUI(HlavniOkno.this);
                    pack();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        JMenu lookMenu = new JMenu("Vzhled");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem itemJava = new JRadioButtonMenuItem("Java", true);
        group.add(itemJava);
        //Podle ActionCommand se vetvi obsluha v listeneru vyse
        itemJava.setActionCommand("JAVA");
        itemJava.addActionListener(al);
        lookMenu.add(itemJava);
        JRadioButtonMenuItem itemWin = new JRadioButtonMenuItem("System", true);
        group.add(itemWin);
        itemWin.setActionCommand("SYS");
        itemWin.addActionListener(al);
        lookMenu.add(itemWin);
        JRadioButtonMenuItem itemMotif = new JRadioButtonMenuItem("Motif", true);
        group.add(itemMotif);
        itemMotif.setActionCommand("MOTIF");
        itemMotif.addActionListener(al);
        lookMenu.add(itemMotif);

        checkBoxMenuItem.setToolTipText("Potvrzovaci okno pri mazani");

        helpMenu.add(lookMenu);
        helpMenu.addSeparator();
        helpMenu.add(checkBoxMenuItem);
        helpMenu.addSeparator();
        helpMenu.add(actionAbout);

        bar.add(fileMenu);
        bar.add(helpMenu);

        setJMenuBar(bar);
    }

    private void about () {
        JOptionPane.showMessageDialog(this,
                "Evidence Clenu",
                "O Programu",
                JOptionPane.INFORMATION_MESSAGE + OK_OPTION);}

    private void clearInput() {
        tfJmeno.setText("");
        tfKategorie.setText("");
        tfMestoBydliste.setText("");
    }

    private void close() {
        System.exit(0);
    }

    private void createActions() {
        actionNew = new AbstractAction("Nový", new ImageIcon(getClass().getResource("/registrace2022/Data/img/newBinder.png"))) {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                clenove.clear();
                clenoveTabulka.refresh();
                clearInput();
            }
        };
        actionNew.putValue(Action.SHORT_DESCRIPTION,"Prázdný soubor");

            actionOpen = new AbstractAction("Otevřít", new ImageIcon(getClass().getResource("/registrace2022/Data/img/loadBinder.png"))) {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    openFile();
                    clenoveTabulka.refresh();

                }
            };

            actionSave = new AbstractAction("Uložit", new ImageIcon(getClass().getResource("/registrace2022/Data/img/saveBinder.png"))) {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    saveFile();

                }
            };

            actionAbout = new AbstractAction("O programu") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    about();

                }
            };

            actionClearFilter = new AbstractAction("Zruš hledany filtr") {
                @Override
                public void actionPerformed(ActionEvent arg0) {
//                    odeberFiltr();

                }

            };
        }

        private void openFile() {
        JFileChooser fc = new FileClen();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fis = new FileInputStream(fc.getSelectedFile());
                clenove.readData(fis);
                fis.close();
            } catch (FileNotFoundException e) {
                showMessageDialog(
                        this, "Soubor nelze otevřít", "chyba", ERROR_MESSAGE);
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                }
            }
        }

//        private void odeberFiltr() {
//        RowFilter <
//        }

        private void saveFile () {
            JFileChooser fc = new FileClen();
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    String fileName = fc.getSelectedFile().getAbsolutePath();
                    if (!fileName.endsWith(".clen"))
                        fileName += ".car";
                    FileOutputStream fos =
                            new FileOutputStream(fileName);
                    clenove.writeData(fos);
                    fos.close();
                } catch (FileNotFoundException e) {
                    showMessageDialog(
                            this,
                            "Soubor nelze ulozit" + "!",
                            "Chyba",
                            ERROR_MESSAGE);
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main (String[]args){
            SwingUtilities.invokeLater(() -> {
                new HlavniOkno().setVisible(true);
            });


        }
}







