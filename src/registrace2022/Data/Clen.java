package registrace2022.Data;

import java.io.Serializable;

/**
 * Trida popisujici jednoho konkretniho clena organizace - karta clena
 */

public class Clen implements Serializable {

    String jmeno= null;
    String kategorie = null;
    String mestoBydliste = null;

    /**
     * konstruktor
     * @param text
     * @param tfKategorieText
     * @param tfMestoBydlisteText
     */

    public Clen() {}

    public Clen(String jmeno, String kategorie, String mestoBydliste) {
        this.jmeno=jmeno;
        this.kategorie=kategorie;
        this.mestoBydliste=mestoBydliste;
    }

    public void setJmeno(String jmeno) {
        this.jmeno=jmeno;
    }

    public void setKategorie(String kategorie) {
        this.kategorie=kategorie;
    }

    public void setMestoBydliste(String mestoBydliste) {
        this.mestoBydliste=mestoBydliste;
    }


    public String getJmeno() {
        return jmeno;
    }

    public String getKategorie() {
        return kategorie;
    }

    public String getMestoBydliste() {
        return mestoBydliste;
    }
}
