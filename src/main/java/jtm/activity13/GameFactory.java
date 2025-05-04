// File: src/jtm/activity13/GameFactory.java
package jtm.activity13;

import java.util.ArrayList;

public class GameFactory {

    /**
     * Sets the shared board for the game.
     * 
     * @param board reference to the new board
     */
    public static void setBoard(Board board) {
        CrocodileGame.board = board;
    }

    /**
     * Adds a new crocodile to the game by type name.
     * 
     * @param crocodileType either "CrocodileSimple" or "CrocodileGreedy"
     * @throws IllegalArgumentException if type is unknown
     */
    public static void addCrocodile(String crocodileType) {
        if (CrocodileGame.crocodile == null) {
            CrocodileGame.crocodile = new ArrayList<>();
        }

        Crocodile c;
        switch (crocodileType) {
            case "CrocodileSimple":
                c = new CrocodileSimple();
                break;
            case "CrocodileGreedy":
                c = new CrocodileGreedy();
                break;
            default:
                throw new IllegalArgumentException("Unknown crocodile type: " + crocodileType);
        }

        CrocodileGame.crocodile.add(c);
    }
}
