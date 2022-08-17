package tictactoe;

import java.util.Random;

public class AIPlayer extends Player{
    public AIPlayer(){
        isHuman = false;
    }
    @Override
    public boolean getIsHuman() {
        return isHuman;
    }
    @Override
    public void makeMove(GameEngine gameEngine) {
        //for easy level
        makeRandomMove(gameEngine);
    }
    private void makeRandomMove(GameEngine gameEngine) {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (gameEngine.getBoard().getToken(row, col) != ' ');
        if (gameEngine.getCurrentMark().equals(GameEngine.Mark.X)) {
            gameEngine.getBoard().setToken(row, col, 'X');
        } else {
            gameEngine.getBoard().setToken(row, col, 'O');
        }
    }
}


