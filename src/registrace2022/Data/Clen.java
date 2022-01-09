package registrace2022.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Trida popisujici jednoho konkretniho clena organizace - karta clena
 */

public class Clen implements Serializable {

    String jmeno= null;
    String kategorie = null;
    String mestoBydliste = null;
    LocalDate datumNarozeni = null;

    public Clen() {}

    public Clen(String jmeno, String kategorie, String mestoBydliste, LocalDate datumNarozeni) {
        this.jmeno=jmeno;
        this.kategorie=kategorie;
        this.mestoBydliste=mestoBydliste;
        this.datumNarozeni=datumNarozeni;

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

    public void setDatum(LocalDate datumNarozeni) {
        this.datumNarozeni = datumNarozeni;
    }

    public LocalDate getDatum() {
        return datumNarozeni;
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
