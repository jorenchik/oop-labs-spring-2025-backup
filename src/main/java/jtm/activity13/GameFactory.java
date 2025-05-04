// File: src/jtm/activity13/GameFactory.java
package jtm.activity13;

import java.util.ArrayList;

public class GameFactory {

    public static void setBoard(Board board) {
        CrocodileGame.board = board;
    }

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
