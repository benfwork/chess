package chess.MoveHandler;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.HashSet;

public class RookMoveHandler extends ChessMoveHandler{
    public RookMoveHandler(ChessPosition position, ChessBoard board, ChessPiece piece) {
        super(position, board, piece);
    }

    @Override
    public HashSet<ChessMove> getMoves() {
        int row = position.getRow();
        int col = position.getColumn();

        HashSet<ChessMove> validMoves = HashSet.newHashSet(14);

        for (int i = -7; i < 8; i++) {
            /* movement along a column */
            ChessPosition newPositionCol = new ChessPosition(row, col + i);
            if (isValidSquare(newPositionCol) && isNullOrOtherTeam(newPositionCol)) {
                validMoves.add(new ChessMove(position, newPositionCol, null));
            }
            /* movement along a row */
            ChessPosition newPositionRow = new ChessPosition(row - 1, col);
            if (isValidSquare(newPositionRow) && isNullOrOtherTeam(newPositionRow)) {
                validMoves.add(new ChessMove(position, newPositionRow, null));
            }
        }
        return validMoves;
    }

}
