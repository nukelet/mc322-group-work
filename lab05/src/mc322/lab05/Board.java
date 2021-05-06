package mc322.lab05;

public class Board {
    private Piece[][] board;
    private Color currentTurnColor;

    public Board() {
        // 0 = empty, 1 = white, 2 = black
        int[][] piecesRepr = {
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 2, 0, 2, 0, 2},
                {2, 0, 2, 0, 2, 0, 2, 0},
                {0, 2, 0, 2, 0, 2, 0, 2},
        };

        board = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (piecesRepr[i][j]) {
                case 1:
                    board[i][j] = new Pawn(new Position(i, j), Color.WHITE);
                    break;
                case 2:
                    board[i][j] = new Pawn(new Position(i, j), Color.BLACK);
                    break;
                default:
                    board[i][j] = null;
                    break;
                }
            }
        }

        currentTurnColor = Color.WHITE;
    }

    private Piece pieceAt(Position position) {
        return board[position.getI()][position.getJ()];
    }

    private void setPieceAt(Piece piece, Position position) {
        board[position.getI()][position.getJ()] = piece;
    }

    private void removePieceAt(Position position) {
        board[position.getI()][position.getJ()] = null;
    }

    public boolean solicitaMovimento(Position source, Position destination) {
        System.out.printf("Source: %s\nTarget: %s\n", source, destination);

        if (!source.isWithinBounds()) {
            // System.err.println("Invalid move: source out of bounds");
            return false;
        } 

        if (!destination.isWithinBounds()) {
            // System.err.println("Invalid move: destination out of bounds");
            return false;
        }

        Piece sourcePiece = pieceAt(source);
        if (sourcePiece == null) {
            // System.err.printf("Invalid move: source (%s) is empty\n", source.toString());
            return false;
        }

        if(sourcePiece.getColor() != currentTurnColor) {
            switch (currentTurnColor) {
            case WHITE:
                // System.err.println("Invalid move: source is not white");
                return false;
            case BLACK:
                // System.err.println("Invalid move: source is not black");
                return false;
            }
        }

        if (pieceAt(destination) != null) {
            // System.err.println("Invalid move: destination is not empty");
            return false;
        }

        if (!sourcePiece.isValidMove(destination)) {
            // System.err.println("Invalid move: illegal movement for source piece");
            return false;
        }

        if (sourcePiece instanceof Pawn) {
            return doPawnMove(source, destination);
        } else if (sourcePiece instanceof Queen) {
            return doQueenMove(source, destination);
        }

        return false;
    }

    private void toggleCurrentTurnColor() {
        switch (currentTurnColor) {
        case WHITE:
            currentTurnColor = Color.BLACK;
            break;
        case BLACK:
            currentTurnColor = Color.WHITE;
            break;
        }
    }

    private boolean hasPendingCapture() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.getColor() == currentTurnColor) {
                    if (hasPendingCapture(piece)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean hasPendingCapture(Piece piece) {
        if (piece instanceof Pawn) {
            return hasPendingCapture((Pawn) piece);
        } else if (piece instanceof Queen) {
            return hasPendingCapture((Queen) piece);
        }
        return false;
    }

    private boolean hasPendingCapture(Pawn pawn) {
        Position[] capturePositions = new Position[4];
        capturePositions[0] = pawn.getPosition().nextNortheast(2);
        capturePositions[1] = pawn.getPosition().nextNorthwest(2);
        capturePositions[2] = pawn.getPosition().nextSouthwest(2);
        capturePositions[3] = pawn.getPosition().nextSoutheast(2);
        for (Position capturePosition : capturePositions) {
            if (capturePosition.isWithinBounds() && pieceAt(capturePosition) == null) {
                Piece middlePiece = pieceAt(new Position(
                        (pawn.getPosition().getI() + capturePosition.getI()) / 2,
                        (pawn.getPosition().getJ() + capturePosition.getJ()) / 2));
                if (middlePiece != null && middlePiece.getColor() != pawn.getColor()) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasPendingCapture(Queen queen) {
        // first orientation
        Piece nearestPiece = nearestNortheastPieceAfter(queen.getPosition());
        if (nearestPiece != null && nearestPiece.getColor() != queen.getColor()) {
            Position after = nearestPiece.getPosition().nextNortheast();
            if (after.isWithinBounds() && pieceAt(after) == null) {
                return true;
            }
        }
        // second orientation
        nearestPiece = nearestNorthwestPieceAfter(queen.getPosition());
        if (nearestPiece != null && nearestPiece.getColor() != queen.getColor()) {
            Position after = nearestPiece.getPosition().nextNorthwest();
            if (after.isWithinBounds() && pieceAt(after) == null) {
                return true;
            }
        }
        // third orientation
        nearestPiece = nearestSouthwestPieceAfter(queen.getPosition());
        if (nearestPiece != null && nearestPiece.getColor() != queen.getColor()) {
            Position after = nearestPiece.getPosition().nextSouthwest();
            if (after.isWithinBounds() && pieceAt(after) == null) {
                return true;
            }
        }
        // fourth orientation
        nearestPiece = nearestSoutheastPieceAfter(queen.getPosition());
        if (nearestPiece != null && nearestPiece.getColor() != queen.getColor()) {
            Position after = nearestPiece.getPosition().nextSoutheast();
            if (after.isWithinBounds() && pieceAt(after) == null) {
                return true;
            }
        }

        return false;
    }

    private Piece nearestNortheastPieceAfter(Position position) {
        for (int k = 1; ; k++) {
            Position other = new Position(position.getI() + k, position.getJ() + k);
            if (!other.isWithinBounds()) {
                return null;
            }
            if (pieceAt(other) != null) {
                return pieceAt(other);
            }
        }
    }

    private Piece nearestNorthwestPieceAfter(Position position) {
        for (int k = 1; ; k++) {
            Position other = new Position(position.getI() - k, position.getJ() + k);
            if (!other.isWithinBounds()) {
                return null;
            }
            if (pieceAt(other) != null) {
                return pieceAt(other);
            }
        }
    }

    private Piece nearestSouthwestPieceAfter(Position position) {
        for (int k = 1; ; k++) {
            Position other = new Position(position.getI() - k, position.getJ() - k);
            if (!other.isWithinBounds()) {
                return null;
            }
            if (pieceAt(other) != null) {
                return pieceAt(other);
            }
        }
    }

    private Piece nearestSoutheastPieceAfter(Position position) {
        for (int k = 1; ; k++) {
            Position other = new Position(position.getI() + k, position.getJ() - k);
            if (!other.isWithinBounds()) {
                return null;
            }
            if (pieceAt(other) != null) {
                return pieceAt(other);
            }
        }
    }

    private boolean doPawnMove(Position source, Position destination) {
        if (source.equals(secondToLastPosition(source, destination))) {
            // the pawn is moving without capturing anything
            if (hasPendingCapture()) {
                // System.err.println("Invalid move: capture move pending");
                return false;
            }

            movePiece(source, destination);
            if (isPawnPromotionPosition(destination)) {
                removePieceAt(destination);
                setPieceAt(new Queen(destination, currentTurnColor), destination);
            }
            toggleCurrentTurnColor();
        } else {
            Position nearestPiecePosition = nearestPiecePosition(source, destination);
            if (nearestPiecePosition == null) {
                // System.err.println("Invalid move: pawn must capture a piece to move two positions");
                return false;
            } 

            // System.out.println("Removing piece at " + nearestPiecePosition.toString());
            removePieceAt(nearestPiecePosition);
            movePiece(source, destination);
            if (isPawnPromotionPosition(destination)) {
                removePieceAt(destination);
                setPieceAt(new Queen(destination, currentTurnColor), destination);
            }
            if (!hasPendingCapture(pieceAt(destination))) {
                toggleCurrentTurnColor();
            }
        }

        return true;
    }

    private boolean isPawnPromotionPosition(Position destination) {
        if (currentTurnColor == Color.WHITE) {
            return destination.getI() == 7;
        } else {
            return destination.getI() == 0;
        }
    }

    private boolean doQueenMove(Position source, Position destination) {
        Position nearestPiecePosition = nearestPiecePosition(source, destination);
        if (nearestPiecePosition == null) {
            // the queen is moving without capturing anything
            if (hasPendingCapture()) {
                // System.err.println("Invalid move: capture move pending");
                return false;
            }

            movePiece(source, destination);
            toggleCurrentTurnColor();
        } else if (nearestPiecePosition.equals(secondToLastPosition(source, destination))) {
            // System.out.println("Removing piece at " + nearestPiecePosition.toString());
            removePieceAt(nearestPiecePosition);
            movePiece(source, destination);
            if (!hasPendingCapture(pieceAt(destination))) {
                toggleCurrentTurnColor();
            }
        } else {
            // System.err.println("Invalid move: queen must capture the piece at " +
            //       nearestPiecePosition.toString());
            return false;
        }

        return true;
    }

    private void movePiece(Position source, Position destination) {
        Piece sourcePiece = pieceAt(source);
        sourcePiece.setPosition(destination);
        removePieceAt(source);
        setPieceAt(sourcePiece, destination);
    }

    private Position secondToLastPosition(Position source, Position destination) {
        int i1 = source.getI(), j1 = source.getJ();
        int i2 = destination.getI(), j2 = destination.getJ();

        int i_offset = i2 > i1 ? 1 : -1;
        int j_offset = j2 > j1 ? 1 : -1;

        return new Position(i2 - i_offset, j2 - j_offset);
    }

    // find the nearest piece to source in the path source->destination
    private Position nearestPiecePosition(Position source, Position destination) {
        int i1 = source.getI(), j1 = source.getJ();
        int i2 = destination.getI(), j2 = destination.getJ();

        int offset_i = i2 > i1 ? 1 : -1;
        int offset_j = j2 > j1 ? 1 : -1;

        for (int i = i1 + offset_i, j = j1 + offset_j; i != i2 && j != j2; i += offset_i, j += offset_j) {
            if (board[i][j] != null) {
                return new Position(i, j);
            }
        }

        return null;
    }

    public String getStateString() {
        String result = "";
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                result += ((board[i][j] == null) ? "-" : board[i][j].toString());
            }
            result += "\n";
        }
        return result;
    }

    public void imprimirTabuleiro() {
        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1));;
            for (int j = 0; j <= 7; j++) {
                System.out.print(" " + ((board[i][j] == null) ? "-" : board[i][j].toString()));;
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public void exportarArquivo(String outCSVPath) {
        String[] state = new String[64];
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                Position pos = new Position(i, j);

                Piece piece = pieceAt(pos);
                String str = pos.toString();
                str += (piece == null ? "_" : piece.toString());

                int linearIndex = 8*j + i;
                state[linearIndex] = str;
            }
        }

        CSVHandling csvHandling = new CSVHandling();
        csvHandling.setDataExport(outCSVPath);
        csvHandling.exportState(state);
    }

    public void exportarArquivoErro(String outCSVPath) {
        CSVHandling csvHandling = new CSVHandling();
        csvHandling.setDataExport(outCSVPath);
        String[] state = {"erro"};
        csvHandling.exportState(state);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 7; i >= 0; i--) {
            result += (i + 1);
            for (int j = 0; j <= 7; j++) {
                result += " " + ((board[i][j] == null) ? "-" : board[i][j].toString());
            }
            result += "\n";
        }
        result += "  a b c d e f g h";
        return result;
    }
}
