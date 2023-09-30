package gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TictactueWindow extends JFrame implements ActionListener {
    private String spieler1 = "x";
    private String spieler2 = "o";
    private int counter = 1;

    private JTextField screen;
    private JButton[] fields;
    private JPanel gameField;

    public TictactueWindow(String title) {
        super(title);
        
        this.fields = createButtons();
        this.screen = createScreen();
        this.gameField = createGameField();

        
        add(this.screen);
        add(this.gameField);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(null);
    }

    private JTextField createScreen() {
        JTextField textField = new JTextField("Spieler 1 fängt an!");
        textField.setBounds(0, 0, 400, 50);
        //textField.setBackground(Color.BLACK);
        textField.setFont(new Font("Ink Free", Font.PLAIN, 18));
        textField.setAlignmentX(JTextField.CENTER_ALIGNMENT);

        textField.setEditable(false);
        return textField;
    }

    private JPanel createGameField() {
        JPanel gameField = new JPanel();
        gameField.setBounds(0, 60, 400, 300);
        gameField.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < fields.length; i++) {
            gameField.add(fields[i]);
        }

        
        return gameField;
    }

    private JButton[] createButtons() {
        JButton[] fields = new JButton[9];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new JButton("");
            fields[i].addActionListener(this);
        }
        return fields;
    }

    private void restart() {
        for (JButton b: fields) {
            b.setText("");
        }
        counter = 1;
        screen.setText("Spieler 1 fängt an!");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = null;
        counter++;

        if (validateGame()) {
            validateWinner();
        }
        else if (counter > 9) {
            if (validateGame()) {
                button = (JButton) e.getSource();
                button.setText(spieler2);
                button.setFont(new Font("Ink Free", Font.BOLD, 40));
                validateWinner();
            } else {
                button = (JButton) e.getSource();
                button.setText(spieler2);
                button.setFont(new Font("Ink Free", Font.BOLD, 40));

                screen.setText("Das Spiel ist vorbei!! UNENTSCHIEDEN!!!");
                JOptionPane.showMessageDialog(null, "Das Spiel ist vorbei!! UNENTSCHIEDEN!!!");
                
                try {
                    Thread.sleep(300);
                }
                catch (InterruptedException ie) {
                    System.out.println(ie.getMessage());
                }
                restart();
            }
        }
        else if((counter % 2) == 0) {
            screen.setText("Spieler 2 ist an der Reihe!");

            button = (JButton) e.getSource();
            button.setText(spieler2);
            button.setFont(new Font("Ink Free", Font.BOLD, 40));
        }
        else if ((counter % 2) != 0) {
            screen.setText("Spieler 1 ist an der Reihe!");

            button = (JButton) e.getSource();
            button.setText(spieler1);
            button.setFont(new Font("Ink Free", Font.BOLD, 40));
        }

        if (validateGame()) {
            validateWinner();
        }
    }

    private void validateWinner() {
        if((counter % 2) == 0) {
            System.out.println("Spieler 2 hat gewonnen!");
            JOptionPane.showMessageDialog(null, "Spieler 2 hat gewonnen!");
            restart();
        }
        else if ((counter % 2) != 0) {
            System.out.println("Spieler 1 hat gewonnen!");
            JOptionPane.showMessageDialog(null, "Spieler 1 hat gewonnen!");
            restart();
        }
    }

    private boolean validateGame() {
        boolean winner = false;

        if((fields[0].getText() == spieler1 & fields[1].getText() == spieler1 & fields[2].getText() == spieler1) | (fields[0].getText() == spieler2 & fields[1].getText() == spieler2 & fields[2].getText() == spieler2)) {
            winner = true;
            System.out.println("Win 1");
        }
        else
        if((fields[3].getText() == spieler1 & fields[4].getText() == spieler1 & fields[5].getText() == spieler1) | (fields[3].getText() == spieler2 & fields[4].getText() == spieler2 & fields[5].getText() == spieler2)) {
            winner = true;
            System.out.println("Win 2");
        }
        else
        if((fields[6].getText() == spieler1 & fields[7].getText() == spieler1 & fields[8].getText() == spieler1) | (fields[6].getText() == spieler2 & fields[7].getText() == spieler2 & fields[8].getText() == spieler2)) {
            winner = true;
            System.out.println("Win 3");
        }
        else
        if((fields[0].getText() == spieler1 & fields[3].getText() == spieler1 & fields[6].getText() == spieler1) | (fields[0].getText() == spieler2 & fields[3].getText() == spieler2 & fields[6].getText() == spieler2)) {
            winner = true;
            System.out.println("Win 4");
        }
        else
        if((fields[1].getText() == spieler1 & fields[4].getText() == spieler1 & fields[7].getText() == spieler1) | (fields[1].getText() == spieler2 & fields[4].getText() == spieler2 & fields[7].getText() == spieler2)) {
            winner = true;
            System.out.println("Win 5");
        }
        else
        if((fields[2].getText() == spieler1 & fields[5].getText() == spieler1 & fields[8].getText() == spieler1) | (fields[2].getText() == spieler2 & fields[5].getText() == spieler2 & fields[8].getText() == spieler2)) {
            winner = true;
            System.out.println("Win 6");
        }
        else
        if((fields[0].getText() == spieler1 & fields[4].getText() == spieler1 & fields[8].getText() == spieler1) | (fields[0].getText() == spieler2 & fields[4].getText() == spieler2 & fields[8].getText() == spieler2)) {
            winner = true;
            System.out.println("Win 7");
        }
        else
        if((fields[2].getText() == spieler1 & fields[4].getText() == spieler1 & fields[6].getText() == spieler1) | (fields[2].getText() == spieler2 & fields[4].getText() == spieler2 & fields[6].getText() == spieler2)) {
            winner = true;
            System.out.println("Win 8");
        }

        return winner;
    }

}
