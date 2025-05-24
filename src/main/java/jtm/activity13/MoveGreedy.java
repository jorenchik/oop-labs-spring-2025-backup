package jtm.activity13;

public class MoveGreedy implements MoveStrategy {

    @Override
    public void move(Crocodile crocodile, Board board) {

        CrocodileGreedy c = (CrocodileGreedy) crocodile;

        int width = board.getX();
        int height = board.getY();
        boolean firstStep = true;

        // Zigzag traversal.
        for (int row = 0; row < height; row++) {
            if (row % 2 == 0) {
                for (int col = 0; col < width; col++) {
                    firstStep = step(c, board, col, row, firstStep);
                }
            } else {
                for (int col = width - 1; col >= 0; col--) {
                    firstStep = step(c, board, col, row, firstStep);
                }
            }
        }

        // Extra left-to-right pass over last row (if height is even)
        if (height % 2 == 0) {
            int row = height - 1;
            boolean firstExtraStep = true;
            for (int col = 0; col < width; col++) {
                firstExtraStep = step(c, board, col, row, firstExtraStep);
            }
        }
    }

    private boolean step(CrocodileGreedy c, Board board, int x, int y, boolean firstStep) {
        char cell = board.getCandy(x, y);

        if (cell == '●') {
            c.recordCandyOnly();
        }

        board.setCandy(x, y, '◎');

        if (!firstStep) {
            c.incrementMoves();
        }

        return false; // All future calls are not the first
    }
}
