// File: src/jtm/activity13/MoveGreedy.java
package jtm.activity13;

public class MoveGreedy implements MoveStrategy {
	@Override
	public void move(Crocodile crocodile, Board board) {
		CrocodileGreedy c = (CrocodileGreedy) crocodile;
		int maxX = board.getX(), maxY = board.getY();
		int x = 0, y = 0;

		// handle start
		if (board.getCandy(x, y) == '●') {
			c.recordCandyOnly();
		}
		board.setCandy(x, y, '◎');

		while (x < maxX - 1 || y < maxY - 1) {
			boolean canE = x < maxX - 1;
			boolean canS = y < maxY - 1;

			int nx = x, ny = y;

			char e = canE ? board.getCandy(x + 1, y) : 0;
			char s = canS ? board.getCandy(x, y + 1) : 0;

			if (canE && e == '●') {
				nx++;
			} else if (canS && s == '●') {
				ny++;
			} else if (canE) {
				nx++;
			} else {
				ny++;
			}

			x = nx;
        y = ny;
        c.recordStep(board, x, y);
    }
}
}
