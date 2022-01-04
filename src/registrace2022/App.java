package registrace2022;

import registrace2022.Gui.HlavniOkno;

import javax.swing.*;
import java.lang.invoke.SwitchPoint;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new HlavniOkno();

                f.pack();
                f.setVisible(true);
            }
        });
    }
}
