package registrace2022.Data;


import java.io.*;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Trida agregujici cleny strediska
 * Seznam clenu
 */
public class SeznamClenu {

    private List<Clen> dataClenu; //atribut do ktereho nacitam cleny - seznam

    /**
     * vytvoreni konstruktoru
     */
    public SeznamClenu() {
        this.dataClenu = new ArrayList<Clen>(); //arrayList, protoze neznam predem pocet clenu
    }

    /**
     * Smazani vsech zaznamu
     */
    public void clear() {
        dataClenu.clear();
    } //mazu zaznam clena ze seznamu


    /**
     * pridani clena do evidence
     */
    public void add(Clen clen) {
        dataClenu.add(clen);
    }

    //metoda pro tlacitko najdi
    public int najdiClena (String jmeno) {
        int pozice = -1;
        for (int i = 0; i < dataClenu.size(); i++) {
            if (dataClenu.get(i).getJmeno().equals(jmeno)) {
                pozice = i;
                break;
            }
        }
        return pozice;
    }

    public Clen najdiClena (int i) {
        return dataClenu.get(i);
    }

    /**
     * vraceni poctu evidovanych clenu v danem obdobi
     */

    public int size() {
        return dataClenu.size();
    }

    /**
     * razeni clenu v evidenci dle jmena a abecedy
     */

//    public void seradit() {
//        Comparator<Clen> porovnavac = new Comparator<Clen>() {
//            @Override
//            public int compare(Clen c1, Clen c2) {
//                return Collator.getInstance().compare(c1.getJmeno(),c2.getJmeno());
//            }
//        };
//        dataClenu = dataClenu.stream().sorted(porovnavac).collect(Collectors.toList());
//
//    }

    public void seradit() {
        for (int i = 0; i < dataClenu.size() - 1; i++) {
            int imin = i;
            for (int j = i + 1; j < dataClenu.size(); j++) {
                if (dataClenu.get(imin).getJmeno().compareTo(dataClenu.get(j).getJmeno()) > 0) {
                    imin = j;
                }
            }
            if (imin != i) {
                Clen pom = dataClenu.get(i); //Clen = typ promenné, název promenne je pom
                dataClenu.set(i, dataClenu.get(imin));
                dataClenu.set(imin, pom);
            }
        }
    }

    /**
     *Vrací clena s konkrétní zadanou pozicí
     */
    public Clen get(int pos) {
        return dataClenu.get(pos);
    }

    /**
     * nastavuje clena na konkrétni pozici
     */
    public void set(Clen clen,int pos) {
        dataClenu.set(pos,clen);
    }

    /**
     * Odstraneni člena ze seznamu
     * @param pos
     */
    public void remove (int pos) {
        dataClenu.remove(pos);
    }

    /**
     * ulozeni dat do streamu
     */
public void writeData (OutputStream out) throws IOException {
    ObjectOutputStream oStream = new ObjectOutputStream(out);
    oStream.writeObject(dataClenu);
}

/**
 * nacteni dat z otevreneho vstupniho streamu. Vstupem je pro mne samotny stream - input
 */

public void readData (InputStream input) throws IOException {
    try {
        ObjectInputStream ois = new ObjectInputStream(input);
        dataClenu = (List<Clen>) ois.readObject();
    }catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}
}

