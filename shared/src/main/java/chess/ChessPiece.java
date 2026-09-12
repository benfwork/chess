package chess;

import chess.moveHandler.ChessMoveHandler;
import chess.moveHandler.KingMoveHandler;
import chess.moveHandler.RookMoveHandler;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece piece = board.getPiece(myPosition);
        if (piece.getPieceType() == PieceType.ROOK){
            ChessMoveHandler calculator = new RookMoveHandler(myPosition, board, piece);
            return calculator.getMoves();
        }
        else if (piece.getPieceType() == PieceType.KING){
            ChessMoveHandler calculator = new KingMoveHandler(myPosition, board, piece);
            return calculator.getMoves();
        }
        else if (piece.getPieceType() == )
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(pieceColor, type);
    }

    @Override
    public String toString() {
        boolean pieceIsWhite =  (pieceColor == ChessGame.TeamColor.WHITE);
        if (type == PieceType.KING){
            return pieceIsWhite ? "K" : "k";
        }
        else if (type == PieceType.BISHOP){
            return pieceIsWhite ? "B" : "b";
        }
        else if (type == PieceType.KNIGHT){
            return pieceIsWhite ? "N" : "n";
        }
        else if (type == PieceType.QUEEN){
            return pieceIsWhite ? "Q" : "w";
        }
        else if (type == PieceType.PAWN){
            return pieceIsWhite ? "P" : "p";
        }
        else if (type == PieceType.ROOK){
            return pieceIsWhite ? "R" : "r";
        }
        return " ";
    }
}
