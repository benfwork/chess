package chess.MoveHandler;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.ChessMove

import java.util.HashSet;


public interface ChessMoveHandler {
    static HashSet<ChessMove> getMoves(ChessBoard board, ChessPosition position){
        return null;
    }

    static boolean isValidSquare(ChessPosition square){
        int row = square.getRow();
        int col = square.getColumn();
        return (1 <= row && row <= 8 && 1 <= col && col <= 8);
    }
}
