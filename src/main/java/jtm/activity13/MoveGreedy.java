// File: src/jtm/activity13/MoveGreedy.java
package jtm.activity13;

public class MoveGreedy implements MoveStrategy {
    @Override
    public void move(Crocodile crocodile, Board board) {
        CrocodileGreedy c = (CrocodileGreedy) crocodile;
        int maxX = board.getX(), maxY = board.getY();
        int x = 0, y = 0;

        c.recordStep(board, x, y);
        while (x < maxX - 1 || y < maxY - 1) {
            boolean canE = x < maxX - 1, canS = y < maxY - 1;
            int nx = x, ny = y;

            if (canE && canS) {
                char e = board.getCandy(x + 1, y), s = board.getCandy(x, y + 1);
                if (e == '●' && s != '●')       nx++;
                else if (s == '●' && e != '●')  ny++;
                else                            nx++;
            } else if (canE) {
                nx++;
            } else {
                ny++;
            }

            x = nx;  y = ny;
            c.recordStep(board, x, y);
        }
    }
}
