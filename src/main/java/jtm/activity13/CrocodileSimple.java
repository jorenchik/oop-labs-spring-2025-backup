package jtm.activity13;

public class CrocodileSimple implements Crocodile {
    private final MoveStrategy strategy = new MoveSimple();
    private int moves, candies;

    @Override
    public void move(Board board) {
        moves = 0;
        candies = 0;
        strategy.move(this, board);
    }

    /** Called by MoveSimple to eat/mark & count */
    void recordStep(Board board, int x, int y) {
        if (board.getCandy(x, y) == '●') {
            candies++;
        }
        board.setCandy(x, y, '◎');
        moves++;
    }

	void recordCandyOnly() {
		candies++;
	}

    @Override public int   getMoves()   { return moves;   }
    @Override public int   getCandies() { return candies; }
    @Override public String getType()   { return "CrocodileSimple"; }
}
