package registrace2022.Data;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida agregujici cleny strediska
 * Seznam clenu
 */
public class SeznamClenu {

    private List<Clen> data;

    /**
     * vytvoreni konstruktoru
     */

    public SeznamClenu() {
        this.data = new ArrayList<Clen>();

    }
    /**
     * Smazani vsech zaznamu
     */
    public void clear() {
        data.clear();
    }


    /**
     * pridani clena do evidence
     */

    public void add(Clen clen) {
        data.add(clen);
    }

    //metoda pro tlacitko najdi
    public Clen najdiClena (String jmeno) {
        Clen clen = data.get(0);
        for (int i = 0; i< size();i++) {
            if(data.get(i).getJmeno().equals(jmeno)){
                clen = data.get(i) ;
            }
        }
        return clen;
    }

    public Clen najdiClena (int i) {
        return data.get(i);
    }

    /**
     * vraceni poctu evidovanych clenu v danem obdobi
     */

    public int size() {
        return data.size();
    }

    /**
     *Vrací clena s konkrétní zadanou pozicí
     */
    public Clen get(int pos) {
        return data.get(pos);
    }

    /**
     * nastavuje clena na konkrétni pozici
     */
    public void set(Clen clen,int pos) {
        data.set(pos,clen);
    }

    /**
     * Odstraneni člena ze seznamu
     * @param pos
     */
    public void remove (int pos) {
        data.remove(pos);
    }

    /**
     * ulozeni dat do streamu
     */
public void writeData (OutputStream out) throws IOException {
    ObjectOutputStream oStream = new ObjectOutputStream(out);
    oStream.writeObject(data);
}

/**
 * nacteni dat z otevreneho vstupniho streamu. Vstupem je pro mne samotny stream - input
 */

public void readData (InputStream input) throws IOException {
    try {
        ObjectInputStream ois = new ObjectInputStream(input);
        data = (List<Clen>) ois.readObject();
    }catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}
}

