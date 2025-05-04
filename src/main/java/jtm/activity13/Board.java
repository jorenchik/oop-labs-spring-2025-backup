package jtm.activity13;

public class Board {

    // ○ — empty cell
    // ● — is cell with candy
    // ◎ — is cell with crocodile footstep
    final char[][] board;

    public Board(char[][] board) {
        this.board = board;
    }

    public void setCandy(int x, int y, char value) {
        board[y][x] = value;
    }

    public char getCandy(int x, int y) {
        return board[y][x];
    }

    public int getX() {
        return board[0].length;
    }

    public int getY() {
        return board.length;
    }

    public int countBoardCandies() {
        int candies = 0;
        for (int j = 0; j < getY(); j++) {
            for (int i = 0; i < getX(); i++) {
                if (getCandy(i, j) == '●')
                    candies++;
            }
        }
        return candies;
    }

    public Board getClone() {
        char[][] tmpArr = new char[getY()][getX()];
        for (int j = 0; j < getY(); j++) {
            System.arraycopy(board[j], 0, tmpArr[j], 0, getX());
        }
        return new Board(tmpArr);
    }

    /*-
     * String representation of the board 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < getY(); j++) {
            for (int i = 0; i < getX(); i++) {
                sb.append(getCandy(i, j));
            }
            if (j < getY() - 1)
                sb.append('\n');
        }
        return sb.toString();
    }
}
