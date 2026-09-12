package chess.MoveHandler;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.ChessMove;

import java.util.HashSet;


public class ChessMoveHandler {
    public final ChessPosition position;
    public final ChessBoard board;
    public final ChessPiece piece;

    public ChessMoveHandler(ChessPosition position, ChessBoard board, ChessPiece piece) {
        this.position = position;
        this.board = board;
        this.piece = piece;
    }

    HashSet<ChessMove> getMoves(){
        return null;
    }

    static boolean isValidSquare(ChessPosition square){
        int row = square.getRow();
        int col = square.getColumn();
        return (1 <= row && row <= 8 && 1 <= col && col <= 8);
    }

    boolean isNullOrOtherTeam(ChessPosition position){
        boolean isNull = (board.getPiece(position) == null);
        boolean isOtherTeam = (board.getPiece(position).getTeamColor() != piece.getTeamColor());
        return isNull | isOtherTeam;
    }
}
