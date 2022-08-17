package tictactoe;

public class HumanPlayer extends  Player{
    public HumanPlayer(){
        isHuman = true;
    }
    @Override
    public boolean getIsHuman() {
        return isHuman;
    }
    @Override
    public void makeMove(GameEngine gameEngine) {

    }

    public static void main(String[] args) {
        Player p = new HumanPlayer();
        System.out.println(p.isHuman);
    }

}
