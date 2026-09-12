package chess.moveHandler;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.HashSet;

public class BishopMoveHandler extends ChessMoveHandler{

    public BishopMoveHandler(ChessPosition myPosition, ChessBoard board, ChessPiece piece) {
        super(myPosition, board, piece);
    }

    @Override
    public HashSet<ChessMove> getMoves() {
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        HashSet<ChessMove> directionMoves = getDirectionMoves(directions);
        return directionMoves;
    }
}
