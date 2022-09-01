package tictactoe;

public class TicTacToeBoard {

    static public enum Opponent {X,O};

    private Opponent activePlayer = Opponent.X;
    private Opponent[][] content = new Opponent[3][3];

    public Opponent getActivePlayer() {
        return this.activePlayer;
    }

    public Opponent[][] getContent(){
        return this.content;
    }
    public void reset() {
        this.activePlayer = Opponent.X;
        for(int index=0;index < content.length;++index) {
            for(int index2=0;index2 < content[index].length;++index2) {
                this.content[index][index2] = null;
            }
        }

    }
    //  another option for reset
    //   public void reset () {
    //        for (final Opponent[] contentRow : this.content) {
    //            Arrays.fill(contentRow, null);
    public void set(int rank, int file) {

        if (this.content[rank][file] == null) {
            this.content[rank][file] = this.getActivePlayer();
            if(activePlayer == Opponent.X) {
                activePlayer = Opponent.O;
            } else activePlayer = Opponent.X;
        } else throw new IllegalArgumentException("Fehler! Feld schon genutzt.");
        if(isWon()) {
            System.out.println("GEWONNEN");
        }
    }

    //static private void processSetCommand (final TicTacToeBoard board, final String arguments) {
    //       final Opponent activePlayer = board.getActivePlayer();
    //       final String[] coordinateTexts = arguments.split("\\s+");
    //       final int rank = Integer.parseInt(coordinateTexts[0]);
    //       final int file = Integer.parseInt(coordinateTexts[1]);
    //       board.set(rank, file);
    //       if (board.isWon())
    //       System.out.format("Player %s won this match, reset board to continue!%n", activePlayer);
    //       }
    public boolean isWon() {
        //TODO:

        for(int index=0;index < content.length;++index) {
            if(content[0][index]==content[1][index] && content[1][index] == content[2][index] && content[0][index] != null) return true;
        }
        for(int index=0;index < content.length;++index) {
            if(content[index][0]==content[index][1] && content[index][1] == content[index][2] && content[index][0] != null) return true;
        }
        if(content[0][0]==content[1][1] && content[1][1] == content[2][2] && content[0][0] != null) return true;
        if(content[0][2]==content[1][1] && content[1][1] == content[2][0] && content[0][2] != null) return true;
        return false;
    }
    public String toDisplayString() {
        StringBuilder factory = new StringBuilder();
        for(Opponent[] rank: content) {
            for(int index=0; index < rank.length; ++index) {
                if(rank[index] == null) {
                    factory.append("-");
                }else {
                    factory.append(rank[index]);
                }
                if(rank.length -1 == index) factory.append("\n");
            }
        }
        return factory.toString();
    }
}


