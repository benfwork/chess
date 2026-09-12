package chess.moveHandler;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.HashSet;

public class KingMoveHandler extends ChessMoveHandler{
    public KingMoveHandler(ChessPosition position, ChessBoard board, ChessPiece piece) {
        super(position, board, piece);
    }

    @Override
    public HashSet<ChessMove> getMoves() {
        int[][] relativeMoves = {{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        return getRelativeMoves(relativeMoves);
    }
}
