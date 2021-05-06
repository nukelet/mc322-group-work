package mc322.lab05;

class Position {
    private final int i;
    private final int j;

    Position(String str) {
        this((int)(str.charAt(1) - '1'), (int)(str.charAt(0) - 'a'));
    }

    Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return this.i;
    }

    public int getJ() {
        return this.j;
    }

    public Position nextNortheast(int distance) {
        return new Position(i + distance, j + distance);
    }

    public Position nextNorthwest(int distance) {
        return new Position(i - distance, j + distance);
    }

    public Position nextSouthwest(int distance) {
        return new Position(i - distance, j - distance);
    }

    public Position nextSoutheast(int distance) {
        return new Position(i + distance, j - distance);
    }

    public Position nextNortheast() {
        return nextNortheast(1);
    }

    public Position nextNorthwest() {
        return nextNorthwest(1);
    }

    public Position nextSouthwest() {
        return nextSouthwest(1);
    }

    public Position nextSoutheast() {
        return nextSoutheast(1);
    }

    public boolean isWithinBounds() {
        return (this.i >= 0 && this.i <= 7 && this.j >= 0 && this.j <= 7);
    }

    public boolean equals(Position position) {
        return (this.i == position.getI() && this.j == position.getJ());
    }

    @Override
    public String toString() {
        return String.format("%c%c", (char) 'a' + j, (char) '1' + i);
    }
}
