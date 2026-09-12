package chess.moveHandler;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.HashSet;

public class KnightMoveHandler extends ChessMoveHandler{
    public KnightMoveHandler(ChessPosition position, ChessBoard board, ChessPiece piece) {
        super(position, board, piece);
    }

    @Override
    public HashSet<ChessMove> getMoves() {
        int[][] directions = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        return getRelativeMoves(directions);
    }

}
