package tictactoe;

public class GameEngine {
    public enum Mark {
        X, O;
        char getToken() {
            return this == X ? 'X' : 'O';
        }
    }

    enum GameStatus {
        NOT_STARTED("Game is not started"),
        PLAYING("Game in progress"),
        DRAW("Draw"),
        X_WON("X wins"),
        O_WON("O wins");
        final private String message;
        GameStatus(String message) {
            this.message = message;
        }
        public String getMessage() {
            return this.message;
        }
    }
    private Mark currentMark;
    private boolean playerXWins, playerOWins, draw;
    private InternalBoard board;
    private GameStatus gameStatus = GameStatus.NOT_STARTED;
    public GameEngine(InternalBoard board){
        playerXWins = false;
        playerOWins = false;
        draw = false;
        this.board = board;
        this.currentMark = Mark.X;
    }
    private  void verticalAnalyzer() {
        for (int i = 0; i < 3; i++) {
            if (this.board.getToken(i, 0) == this.board.getToken(i, 1) && this.board.getToken(i, 1) == this.board.getToken(i, 2)) {
                if (this.board.getToken(i,0) == 'X') {
                    playerXWins = true;
                } else if (this.board.getToken(i,0) == 'O') {
                    playerOWins = true;
                }
            }
        }
    }
    //horizontal analyzer
    private  void horizontalAnalyzer(){
        for (int i = 0; i < 3; i++) {
            if (this.board.getToken(0,i) == this.board.getToken(1,i) && this.board.getToken(1,i) == this.board.getToken(2,i)) {
                if (this.board.getToken(0,i) == 'X') {
                    playerXWins = true;
                } else if (this.board.getToken(0,i) == 'O' ) {
                    playerOWins = true;
                }
            }
        }
    }
    //diagonal Analyzer
    private   void diagonalAnalyzer() {
        if (this.board.getToken(0,0) == this.board.getToken(1,1) && this.board.getToken(1,1) == this.board.getToken(2,2)) {
            if (this.board.getToken(0,0) == 'X') {
                playerXWins = true;
            } else if (this.board.getToken(0,0) == 'O') {
                playerOWins = true;
            }
        }
        if (this.board.getToken(2,0) == this.board.getToken(1,1) && this.board.getToken(1,1) == this.board.getToken(0,2)) {
            if (this.board.getToken(2,0) == 'X') {
                playerXWins = true;
            } else if (this.board.getToken(2,0) == 'O') {
                playerOWins = true;
            }
        }
    }
    //game Analyzer
    public void analyzeGame() {
        //vertical
        verticalAnalyzer();
        //horizontal
        horizontalAnalyzer();
        //diagonals
        diagonalAnalyzer();

    }

    public String getGameStatus() {
        if (playerXWins) {
            gameStatus = GameStatus.X_WON;
        } else if (playerOWins) {
            gameStatus = GameStatus.O_WON;
        } else if (this.board.getNumberOfEmptyCells() == 0) {
            gameStatus = GameStatus.DRAW;
        } else if (this.board.getNumberOfEmptyCells() < 9){
            gameStatus = GameStatus.PLAYING;
        } else {
            gameStatus = GameStatus.NOT_STARTED;
        }
        return gameStatus.getMessage();
    }
    public void switchPlayer(){
        this.currentMark = this.currentMark ==  Mark.X ? Mark.O : Mark.X;
    }

    public Mark getCurrentMark() {
        return currentMark;
    }

    public Mark getOpponentMark() {
        return currentMark == Mark.X ? Mark.O : Mark.X;
    }



    public InternalBoard getBoard() {
        return board;
    }

    //reset game
    public void resetGame() {
        currentMark = Mark.X;
        this.playerXWins = false;
        this.playerOWins = false;
        this.draw = false;
        this.board.resetBoard();
    }



    public boolean isPlayerXWins() {
        return playerXWins;
    }
    public boolean isPlayerOWins() {
        return playerOWins;
    }
    public boolean isDraw() {
        return draw;
    }

    public boolean isGameOver(){
        return GameStatus.DRAW.equals(gameStatus) || GameStatus.X_WON.equals(gameStatus) || GameStatus.O_WON.equals(gameStatus);
    }
}
