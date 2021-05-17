package mc322.lab06;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Position up() {
        return new Position(row-1, column);
    }

    public Position down() {
        return new Position(row + 1, column);
    }

    public Position left() {
        return new Position(row, column - 1);
    }

    public Position right() {
        return new Position(row, column + 1);
    }

    public boolean isEqual(Position position) {
        return row == position.getRow() &&
                column == position.getColumn();
    }

    public boolean isEqual(int row, int column) {
        return this.row == row && this.column == column;
    }

    public Position add(Position position) {
        int row = this.row + position.getRow();
        int column = this.column + position.getColumn();
        return new Position(row, column);
    }

    @Override
    public String toString() {
        return "Position(row=" + row + ", column=" + column + ")";
    }
}
