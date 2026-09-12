package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    ChessPiece[][] squares = new ChessPiece[8][8];
    public ChessBoard() {
        
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        squares[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return squares[position.getRow()-1][position.getColumn()-1];
    }

    /* takes a row and column and the default piece type for a new board */
    public ChessPiece.PieceType getStartingPieceType(int row, int col){
        if (row == 1 | row == 8){
            /* rooks */
            if (col == 1 | col == 8){
                return ChessPiece.PieceType.ROOK;
            }
            if (col == 2 | col == 7){
                return ChessPiece.PieceType.KNIGHT;
            }
            if (col == 3 | col == 6){
                return ChessPiece.PieceType.BISHOP;
            }
            if (col == 4){
                return ChessPiece.PieceType.QUEEN;
            }
            if (col == 5){
                return ChessPiece.PieceType.KING;
            }
        }
        else if (row == 2 | row == 7) {
            return ChessPiece.PieceType.PAWN;
        }
        return null;
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        /*  row */
        squares = new ChessPiece[8][8];
        for (int row = 1; row < 9; row ++){
            for (int col = 1; col < 9; col++){
                ChessPiece.PieceType chessPieceType = getStartingPieceType(row, col);
                /* do not attempt to add a piece where there shouldn't be one */
                if (chessPieceType != null){
                    ChessGame.TeamColor teamColor = col < 5 ? ChessGame.TeamColor.WHITE : ChessGame.TeamColor.BLACK;
                    ChessPosition chessPosition = new ChessPosition(row, col);
                    ChessPiece newPiece = new ChessPiece(teamColor, chessPieceType);
                    addPiece(chessPosition, newPiece);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return 31 * Arrays.deepHashCode(squares);
    }
}
