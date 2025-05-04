// File: src/jtm/activity13/MoveSimple.java
package jtm.activity13;

public class MoveSimple implements MoveStrategy {
    @Override
    public void move(Crocodile crocodile, Board board) {
        // cast back to the concrete type so we can call recordStep(...)
        CrocodileSimple c = (CrocodileSimple) crocodile;
        int maxX = board.getX(), maxY = board.getY();
        int x = 0, y = 0;

        c.recordStep(board, x, y);
        while (x < maxX - 1 || y < maxY - 1) {
            if (x < maxX - 1) {
                x++;
            } else {
                y++;
            }
            c.recordStep(board, x, y);
        }
    }
}
