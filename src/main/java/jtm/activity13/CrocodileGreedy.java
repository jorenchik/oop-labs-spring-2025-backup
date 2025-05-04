package jtm.activity13;

public class CrocodileGreedy implements Crocodile {
    private final MoveStrategy strategy = new MoveGreedy();
    private int moves, candies;

    @Override
    public void move(Board board) {
        moves = 0;
        candies = 0;
        strategy.move(this, board);
    }

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

	public void incrementMoves() {
		this.moves++;
	}

    @Override public int   getMoves()   { return moves;   }
    @Override public int   getCandies() { return candies; }
    @Override public String getType()   { return "CrocodileGreedy"; }
}
