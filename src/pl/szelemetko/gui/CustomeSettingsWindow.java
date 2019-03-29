package pl.szelemetko.gui;

import pl.szelemetko.game.GameController;
import pl.szelemetko.game.GameSettings;
import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Okno aplikacji do ustawienia własnych ustawień gry.
 */
public class CustomeSettingsWindow extends JFrame {

    /**
     * Tworzy nowe okno do ustawienia własnych ustawień gry.
     *
     * @param gameController kontroler gry
     */
    public CustomeSettingsWindow(GameController gameController) {
        super("Settings");
        this.setLayout(new GridLayout(4, 2));
        this.add(new JLabel("Height"));
        JTextField height = new JTextField(String.valueOf(10));
        this.add(height);
        this.add(new JLabel("Width"));
        JTextField width = new JTextField(String.valueOf(10));
        this.add(width);
        this.add(new JLabel("Mines"));
        JTextField mines = new JTextField(String.valueOf(10));
        this.add(mines);
        JButton save = new JButton("Save");

        // stwórz nowe ustawienia i ustaw je dla gry po kliknięciu przycisku save
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int h,w,m;
                try {
                    h = Integer.parseInt(height.getText());
                    w = Integer.parseInt(width.getText());
                    m = Integer.parseInt(mines.getText());
                } catch(Exception ex) {
                    return;
                }

                GameSettings gameSettings = new GameSettings(h,w,m);
                gameController.setGameSettings(gameSettings);
            }
        };

        save.addActionListener(actionListener);
        this.add(save);
        this.setSize(200, 300);
        this.setResizable(false);
        this.setVisible(true);
    }
}
