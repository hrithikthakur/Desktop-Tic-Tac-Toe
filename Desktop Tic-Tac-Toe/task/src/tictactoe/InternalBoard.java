package tictactoe;

public class InternalBoard {
    private char[][] board;
    public InternalBoard(){
        this.board = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    }

    //a function to count the no. of X on the board
    public  int xCounter() {
        int countX = 0;
        for (int row = 0; row < 3; row++) {
            for (int col  = 0; col  < 3; col++) {
                if (this.getToken(row , col) == 'X') {
                    countX++;
                }
            }
        }
        return countX;
    }
    //a function to count the no. of O on the board
    public  int oCounter() {
        int countO = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (this.getToken(row , col) == 'O') {
                    countO++;
                }
            }
        }
        return countO;
    }
    public void setToken(int row, int col, char token) {
        if (token == 'X' || token == 'O' || token == ' ') {
            this.board[row][col] = token;
        } else{
            System.out.println("Invalid token!");
        }
    }
    
    public char getToken(int row, int col) {
        return this.board[row][col];
    }

    public int getNumberOfEmptyCells() {
        int count = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (this.getToken(row , col) == ' ') {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isEmpty(int row, int col) {
        return this.getToken(row, col) == ' ';
    }

    public void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                this.setToken(row, col, ' ');
            }
        }
    }

}
