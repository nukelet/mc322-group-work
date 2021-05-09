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

    public Position up() {
        return new Position(row, column - 1);
    }

    public Position down() {
        return new Position(row, column + 1);
    }

    public Position left() {
        return new Position(row - 1, column);
    }

    public Position right() {
        return new Position(row + 1, column);
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
