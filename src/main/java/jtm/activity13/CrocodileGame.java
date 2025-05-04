// File: src/jtm/activity13/CrocodileGame.java
package jtm.activity13;

import java.util.List;

public class CrocodileGame {
    public static Board board;                 // Shared board of the game
    public static List<Crocodile> crocodile;   // List of crocodiles

    /**
     * Runs one round of the game: each crocodile starts from [0,0] on a fresh clone
     * of the board, moves according to its strategy, and then we compare their
     * candies/moves ratio.
     *
     * @return either "{type} wins" or "Tie"
     */
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
            // #1: reset board for this crocodile
            Board bClone = board.getClone();

            // reset counters (assumes implementations reset themselves each run)
            // #2: move crocodile
            c.move(bClone);

            // #3: compute score = candies / moves
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
