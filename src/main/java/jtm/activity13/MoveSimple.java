// File: src/jtm/activity13/MoveSimple.java
package jtm.activity13;

public class MoveSimple implements MoveStrategy {
	@Override
	public void move(Crocodile crocodile, Board board) {
		CrocodileSimple c = (CrocodileSimple) crocodile;
		int maxX = board.getX(), maxY = board.getY();
		int x = 0, y = 0;

		// Ensure (0,0) is marked and candy is counted
		if (board.getCandy(x, y) == '●') {
			c.recordCandyOnly(); // new helper method
		}
		board.setCandy(x, y, '◎'); // <-- this must happen

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
