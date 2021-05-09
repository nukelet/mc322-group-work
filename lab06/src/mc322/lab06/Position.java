package mc322.lab06;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean isEqual(Position position) {
        return this.row == position.getRow() &&
            this.column == position.getColumn();
    }

    public Position add(Position position) {
        int row = this.row + position.getRow();
        int column = this.column + position.getColumn();
        return new Position(row, column);
    }
}
