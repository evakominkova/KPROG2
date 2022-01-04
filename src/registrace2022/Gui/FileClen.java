package registrace2022.Gui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class FileClen extends JFileChooser {
    public FileClen() {
        super(System.getProperty("user.dir"));

        // filtr
        addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory() || f.getName().endsWith(".clen")) {
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Clen data file (*.clen)";
            }
        });
    }

    public int showOpenDialog (Component arg0) throws HeadlessException {
        setDialogTitle("Vyberte soubor pro otevření");
        setApproveButtonText("Otevřít");
        return super.showOpenDialog(arg0);
    }

    public int showSaveDialog (Component arg0) throws HeadlessException {
        setDialogTitle("zadejte jméno pro uložení");
        setApproveButtonText("Uložit");
        return super.showSaveDialog(arg0);
    }
}