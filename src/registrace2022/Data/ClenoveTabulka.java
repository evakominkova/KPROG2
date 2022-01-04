package registrace2022.Data;

import javax.swing.table.AbstractTableModel;

/**
 * Trida pro zobrazeni clenu v tabulce
 */

public class ClenoveTabulka  extends AbstractTableModel {

    SeznamClenu data;
    String[] names = {"Jméno", "Kategorie", "Město bydliště"};


    public ClenoveTabulka(SeznamClenu data) { this.data = data;

    }


    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    public String getColumnName(int col) {
        return names[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        String s = null;

        Clen clen = data.get(row);

        switch (col) {//nula protoze indexuji od nuly!!!
            case 0:
                s = clen.getJmeno();
                break;

            case 1:
                s = clen.getKategorie();
                break;

            case 2:
            s = clen.getMestoBydliste();
            break;
        }

        return s;
    }
    
    public boolean isCellEditable (int row, int col) {
        return false;
    }
    
    public void refresh() {
        fireTableDataChanged();
    }

    private void fireTableChanged() {
    }
}
