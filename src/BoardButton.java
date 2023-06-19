package tictactoe;
import javax.swing.*;
import java.awt.*;

public class BoardButton extends JButton {
    private final int row;
    private final int col;
    public BoardButton(String name, String text, int row, int col) {
        setName(name);
        this.setText(text);
        this.row = row;
        this.col = col;
        setFont(new Font("Arial", Font.BOLD, 50));
        setFocusPainted(false);
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }



}