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
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        HashSet<ChessMove> directionMoves = getDirectionMoves(directions);
        return directionMoves;
    }

}
