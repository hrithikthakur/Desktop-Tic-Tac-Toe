package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

public class TicTacToe extends JFrame {
    private BoardButton[][] buttons;

    private boolean madeMove = false;

    int delay = 1000;

    private JButton buttonStartReset;

    private JButton buttonPlayer1;

    private JButton buttonPlayer2;
    private GameEngine gameEngine;
    private JLabel status;
    private ActionListener gameActionListener;
    private boolean hasStarted = false;

    private boolean isPlayerXTurn = true; // TODO make this a variable of Player class
    private Player playerX = new Player();
    private Player playerO = new Player();
    private boolean ignoreClicks = false;
//    Timer t; TODO add timer later

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panelForCells = new JPanel();
//        panelForCells.setBorder(BorderFactory.createMatteBorder(30, 20, 30, 20, Color.white));
        panelForCells.setLayout(new GridLayout(3,3));
        JPanel panelForStatus = new JPanel();
        panelForStatus.setBorder(BorderFactory.createMatteBorder(0, 10, 10, 0, Color.getColor("#f5f5f5")));
        panelForStatus.setLayout(new GridLayout(1,0));
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1,0, 0, 0));
        add(controlPanel, BorderLayout.NORTH);
        add(panelForCells, BorderLayout.CENTER);
        add(panelForStatus, BorderLayout.SOUTH);
        buttonPlayer1 = new JButton("Human");
        buttonPlayer2 = new JButton("Human");
        buttonPlayer1.setName("ButtonPlayer1");
        buttonPlayer2.setName("ButtonPlayer2");
        buttons = new BoardButton[3][3];
        for (int row = 2; row >= 0; row--) {
            for (int col = 0; col < 3; col++) {
                String name = "Button" + ((char) (col + 65))+ (row + 1);
                buttons[row][col] = new BoardButton(name, " ", row, col);
                panelForCells.add(buttons[row][col]);
                buttons[row][col].setEnabled(false);
            }
        }
        InternalBoard board = new InternalBoard();

        gameEngine = new GameEngine(board);

        status = new JLabel();
        status.setName("LabelStatus");
        status.setText(gameEngine.getGameStatus());

        buttonStartReset = new JButton("Start");
        buttonStartReset.setName("ButtonStartReset");
        buttonStartReset.setEnabled(true);

        buttonPlayer1.addActionListener(e -> {
            buttonPlayer1.setText(buttonPlayer1.getText().equals("Human") ? "Robot" : "Human");
            playerX = buttonPlayer1.getText().equals("Human") ? new HumanPlayer() : new AIPlayer();
//            playerX.setIsHuman(!playerX.getIsHuman());
        });
        buttonPlayer2.addActionListener(e ->
        {
            buttonPlayer2.setText(buttonPlayer2.getText().equals("Human") ? "Robot" : "Human");
            playerO = buttonPlayer2.getText().equals("Human") ? new HumanPlayer() : new AIPlayer();
        });
        buttonStartReset.addActionListener(e -> {
            hasStarted = !hasStarted;
            if (hasStarted) {
                status.setText("Game in progress");
                buttonStartReset.setText("Reset");
                buttonPlayer1.setEnabled(false);
                buttonPlayer2.setEnabled(false);
                enableAllCells(true);
                if (!playerX.getIsHuman()) {
                    makeBestMove(gameEngine);
                    if (!playerO.getIsHuman()) {
                        ignoreClicks = true;
                        while (!gameEngine.isGameOver()) {
                            makeBestMove(gameEngine);
                        }
                        enableAllCells(false);
                    }
                }
                if (!playerX.getIsHuman() || !playerO.getIsHuman()) {
                    gameActionListener = humanAndComputer;
                } else {
                    gameActionListener = humanAndHuman;
                }
                Arrays.stream(buttons).flatMap(Arrays::stream).forEach(button -> button.addActionListener(gameActionListener));
            } else {
                buttonStartReset.setText("Start");
                buttonPlayer1.setEnabled(true);
                buttonPlayer2.setEnabled(true);
                gameEngine.resetGame();
                enableAllCells(false);
                Arrays.stream(buttons).
                        flatMap(Arrays::stream).
                        forEach(button -> button.setText(gameEngine.getBoard().getToken(button.getRow(), button.getCol()) + ""));
                status.setText(gameEngine.getGameStatus());
            }



        });
        JMenuBar menuBar = new JMenuBar();

        JMenu menuGame = new JMenu("Game");
        menuGame.setName("MenuGame");
        menuGame.setMnemonic(KeyEvent.VK_G);

        JMenuItem menuHumanHuman = new JMenuItem("Human vs Human");
        menuHumanHuman.setName("MenuHumanHuman");
        menuHumanHuman.addActionListener(e -> {
            buttonPlayer1.setText("Robot");
            buttonPlayer1.doClick();
            buttonPlayer2.setText("Robot");
            buttonPlayer2.doClick();
            buttonStartReset.doClick();
        });
        menuHumanHuman.setMnemonic(KeyEvent.VK_H);

        JMenuItem menuHumanRobot = new JMenuItem("Human vs Robot");
        menuHumanRobot.setName("MenuHumanRobot");
        menuHumanRobot.addActionListener(e -> {
            buttonPlayer2.setText("Robot");
            buttonPlayer2.doClick();
            buttonPlayer1.setText("Human");
            buttonPlayer1.doClick();
            buttonStartReset.doClick();
        });
        menuHumanRobot.setMnemonic(KeyEvent.VK_R);

        JMenuItem menuRobotHuman = new JMenuItem("Robot vs Human");
        menuRobotHuman.setName("MenuRobotHuman");
        menuRobotHuman.addActionListener(e -> {
            buttonPlayer1.setText("Human");
            buttonPlayer1.doClick();
            buttonPlayer2.setText("Robot");
            buttonPlayer2.doClick();
            buttonStartReset.doClick();
        });
        menuRobotHuman.setMnemonic(KeyEvent.VK_U);

        JMenuItem menuRobotRobot = new JMenuItem("Robot vs Robot");
        menuRobotRobot.setName("MenuRobotRobot");
        menuRobotRobot.addActionListener(e -> {
            buttonPlayer1.setText("Human");
            buttonPlayer1.doClick();
            buttonPlayer2.setText("Human");
            buttonPlayer2.doClick();
            buttonStartReset.doClick();
        });
        menuRobotRobot.setMnemonic(KeyEvent.VK_O);

        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.setName("MenuExit");
        menuExit.setMnemonic(KeyEvent.VK_X);


        // you can rewrite it with a lambda if you prefer this
        menuExit.addActionListener(event -> System.exit(0));

        menuGame.add(menuHumanHuman);
        menuGame.add(menuHumanRobot);
        menuGame.add(menuRobotHuman);
        menuGame.add(menuRobotRobot);
        menuGame.addSeparator();
        menuGame.add(menuExit);

        menuBar.add(menuGame);
        setJMenuBar(menuBar);

        panelForStatus.add(status);
        controlPanel.add(buttonPlayer1);
        controlPanel.add(buttonStartReset);
        controlPanel.add(buttonPlayer2);
        setVisible(true);
    }
    public void enableAllCells(boolean b) {
        Arrays.stream(buttons).flatMap(Arrays::stream).forEach(button -> button.setEnabled(b));
    }

    public GameEngine getGame(){return gameEngine;}

    public Player getPlayerX() {
        return playerX;
    }

    private Player getPlayerO() {
        return playerO;
    }

    private void makeRandomMove() {
        do {
            int row = new Random().nextInt(3);
            int col = new Random().nextInt(3);
            if (buttons[row][col].getText().equals(" ")) {
                updateGame(gameEngine, row, col);
                return;
            }
        } while (true);

    }

    private Player getCurrentPlayer(GameEngine gameEngine) {
        return gameEngine.getCurrentMark() == GameEngine.Mark.X ? playerX : playerO;
    }

    private Player getOpponentPlayer(GameEngine gameEngine) {
        return gameEngine.getCurrentMark() == GameEngine.Mark.X ? playerO : playerX;
    }

    ActionListener humanAndHuman = e -> {
        basicActionPerformed((BoardButton) e.getSource());
    };

    ActionListener humanAndComputer = e -> {
        BoardButton button = (BoardButton) e.getSource();
        boolean validMove = button.getText().equals(" ");
        basicActionPerformed(button);
        if (!gameEngine.isGameOver() && validMove) {
            makeBestMove(gameEngine);
        }
    };

    private void basicActionPerformed(BoardButton button) {
        if (button.getText().equals("X") || button.getText().equals("O") || ignoreClicks) {
            return;
        }
//        button.setText(game.getCurrentMark().getToken() + "");
        updateGame(gameEngine, button.getRow(), button.getCol());
    }
    private static int minimax(GameEngine gameEngine, boolean isMax) {
        int score = evaluate(gameEngine);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10 || score == -10){
            return score;
        } else if (gameEngine.getBoard().getNumberOfEmptyCells() == 0) {
            return 0;
        }

        // If this maximizer's move
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (gameEngine.getBoard().isEmpty(i, j))
                    {
                        // Make the move
                        gameEngine.getBoard().setToken(i,j, gameEngine.getCurrentMark().getToken());

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(gameEngine, !isMax));

                        // Undo the move
                        gameEngine.getBoard().setToken(i,j,' ');
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (gameEngine.getBoard().isEmpty(i, j))
                    {
                        // Make the move
                        gameEngine.getBoard().setToken(i,j, gameEngine.getOpponentMark().getToken());

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(gameEngine, !isMax));

                        // Undo the move
                        gameEngine.getBoard().setToken(i,j,' ');
                    }
                }
            }
            return best;
        }
    }

    private void makeBestMove(GameEngine gameEngine)
    {
        int bestVal = -1000;
        int bestRow = -1;
        int bestCol = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if (gameEngine.getBoard().isEmpty(i, j))
                {
                    // Make the move
                    gameEngine.getBoard().setToken(i, j, gameEngine.getCurrentMark().getToken());

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(gameEngine, false);

                    // Undo the move
                    gameEngine.getBoard().setToken(i, j, ' ');

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        bestRow = i;
                        bestCol = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        updateGame(gameEngine, bestRow, bestCol);
    }

    private void updateGame(GameEngine gameEngine, int bestRow, int bestCol) {
        buttons[bestRow][bestCol].setText(gameEngine.getCurrentMark() == GameEngine.Mark.X ? "X" : "O");
        gameEngine.getBoard().setToken(bestRow, bestCol, gameEngine.getCurrentMark().getToken());
        gameEngine.analyzeGame();
        status.setText(gameEngine.getGameStatus());
        if (gameEngine.isGameOver()) {
            enableAllCells(false);
            return;
        }
        gameEngine.switchPlayer();
    }

    private static int evaluate(GameEngine gameEngine)
    {
        InternalBoard b = gameEngine.getBoard();
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++)
        {
            if (b.getToken(row, 0) == b.getToken(row, 1) &&
                    b.getToken(row, 1) == b.getToken(row, 2))
            {
                if (b.getToken(row, 0) == gameEngine.getCurrentMark().getToken())
                    return +10;
                else if (b.getToken(row, 0) == gameEngine.getOpponentMark().getToken())
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++)
        {
            if (b.getToken(0, col) == b.getToken(1, col) &&
                    b.getToken(1, col) == b.getToken(2, col))
            {
                if (b.getToken(0, col) == gameEngine.getCurrentMark().getToken())
                    return +10;
                else if (b.getToken(0, col) == gameEngine.getOpponentMark().getToken())
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b.getToken(0, 0) == b.getToken(1, 1) &&
                b.getToken(1, 1) == b.getToken(2, 2))
        {
            if (b.getToken(0, 0) == gameEngine.getCurrentMark().getToken())
                return +10;
            else if (b.getToken(0, 0) == gameEngine.getOpponentMark().getToken())
                return -10;
        }

        if (b.getToken(0, 2) == b.getToken(1, 1) && b.getToken(1, 1) == b.getToken(2, 0))
        {
            if (b.getToken(0, 2) == gameEngine.getCurrentMark().getToken())
                return +10;
            else if (b.getToken(0, 2) == gameEngine.getOpponentMark().getToken())
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }





}


