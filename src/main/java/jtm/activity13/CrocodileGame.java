package jtm.activity13;

import java.util.List;

public class CrocodileGame {
    public static Board board;                 // Shared board of the game
    public static List<Crocodile> crocodile;   // List of crocodiles

    public static String runGame() {

    	StringBuilder sb = new StringBuilder();
        if (board == null) {
        	sb.append(" board is null;");
        }
        if (crocodile == null) {
        	sb.append(" board is null;");
        } else if (crocodile.isEmpty()) {
        	sb.append(" crocodile is empty;");
        }
        if (!sb.isEmpty()) {
        	throw new NullPointerException(
				"Game is not constructed:" + sb.toString()
			);
        }


        double bestScore = -1.0;
        Crocodile bestCroc = null;
        boolean tie = false;

        for (Crocodile c : crocodile) {

            // #1: reset board 
            Board bClone = board.getClone();

            // #2: move crocodile
            c.move(bClone);

            // #3: compute score 
            int moves = c.getMoves();
            double score = (moves > 0) ? ((double) c.getCandies() / moves) : 0.0;

            if (bestCroc == null) {
                bestCroc = c;
                bestScore = score;
            } else {
                if (Math.abs(score - bestScore) < 0.001) {
                    tie = true;
                } else if (score > bestScore) {
                    bestScore = score;
                    bestCroc = c;
                    tie = false;
                }
            }
        }

        if (tie) {
            return "Tie";
        } else {
            return bestCroc.getType() + " wins";
        }
    }
}
