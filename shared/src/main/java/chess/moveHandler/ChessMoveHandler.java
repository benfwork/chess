package chess.moveHandler;

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

     public final HashSet<ChessMove> getDirectionMoves(int[][] directions){
         HashSet<ChessMove> moves = new HashSet<>();

         for (int[] direction : directions){
             int row = position.getRow() + direction[0];
             int col = position.getColumn() + direction[1];

             while (isValidSquare(new ChessPosition(row, col))){
                ChessPosition newSquare = new ChessPosition(row, col);
                ChessPiece occupant = board.getPiece(newSquare);

                if (occupant == null){
                    moves.add(new ChessMove(position, newSquare, null));
                }
                else {
                    if (occupant.getTeamColor() != piece.getTeamColor()) {
                        moves.add(new ChessMove(position, newSquare, null));
                    }
                    break;
                }
                 row += direction[0];
                 col += direction[1];
             }
         }
         return moves;
    }

    public final HashSet<ChessMove> getRelativeMoves(int[][] relativeMoves){
        HashSet<ChessMove> validMoves = new HashSet<>();

        for (int[] move : relativeMoves) {
            int row = position.getRow();
            int col = position.getColumn();
            int x = move[0];
            int y = move[1];
            int newRow = row + x;
            int newCol = col + y;
            ChessPosition newPosition = new ChessPosition(newRow, newCol);
            if (isValidSquare(newPosition) && isNullOrOtherTeam(newPosition)) {
                validMoves.add(new ChessMove(position, newPosition, null));
            }
        }
        return validMoves;
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
