package chess.MoveHandler;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.ChessMove;

import java.util.HashSet;
import java.util.Objects;


public class ChessMoveHandler {
    public final ChessPosition position;
    public final ChessBoard board;
    public final ChessPiece piece;

    public ChessMoveHandler(ChessPosition position, ChessBoard board, ChessPiece piece) {
        this.position = position;
        this.board = board;
        this.piece = piece;
    }

    public HashSet<ChessMove> getMoves(){
        return null;
    }

    static boolean isValidSquare(ChessPosition square){
        int row = square.getRow();
        int col = square.getColumn();
        return (1 <= row && row <= 8 && 1 <= col && col <= 8);
    }

    boolean isNullOrOtherTeam(ChessPosition position){
        boolean isNull = (board.getPiece(position) == null);
        if (isNull) {
            return isNull;
        }
        else {
            boolean isOtherTeam = (board.getPiece(position).getTeamColor() != piece.getTeamColor());
            return isOtherTeam;
        }
    }

    static boolean newBoardPutsKingInCheck(ChessBoard board){
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessMoveHandler that = (ChessMoveHandler) o;
        return Objects.equals(position, that.position) && Objects.equals(board, that.board) && Objects.equals(piece, that.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, board, piece);
    }
}
