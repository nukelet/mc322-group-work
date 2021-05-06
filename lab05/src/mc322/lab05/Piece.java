package mc322.lab05;

public abstract class Piece {
    enum PieceType { PAWN, QUEEN };

    protected Position position;
    protected Color color;

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean isValidMove(Position destination) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }
}
