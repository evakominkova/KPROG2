package registrace2022.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Trida popisujici jednoho konkretniho clena organizace - karta clena
 */

public class Clen implements Serializable {

    String jmeno= null; //atribut
    String kategorie = null; //atribut
    String mestoBydliste = null; //atribut
    LocalDate datumNarozeni = null; //atribut

    public Clen() {} //konstruktor

    public Clen(String jmeno, String kategorie, String mestoBydliste, LocalDate datumNarozeni) { //konstruktor
        this.jmeno=jmeno;
        this.kategorie=kategorie;
        this.mestoBydliste=mestoBydliste;
        this.datumNarozeni=datumNarozeni;

    }

    //gettery - čteni atributu, set - zapis atributu

    public void setJmeno(String jmeno) {
        this.jmeno=jmeno;
    } //metoda nastavuji jmeno

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
