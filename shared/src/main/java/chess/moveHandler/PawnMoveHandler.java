package chess.moveHandler;

import chess.*;

import java.util.HashSet;

public class PawnMoveHandler extends ChessMoveHandler {

    public PawnMoveHandler(ChessPosition position, ChessBoard board, ChessPiece piece) {
        super(position, board, piece);
    }

    public final HashSet<ChessMove> checkCornerKills(int[][] relativeMoves){
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

    public final HashSet<ChessMove> addAllPromoPieces(ChessPosition startPos, ChessPosition endPos){
        HashSet<ChessMove> moves = new HashSet<>();
        moves.add(new ChessMove(startPos, endPos, ChessPiece.PieceType.ROOK));
        moves.add(new ChessMove(startPos, endPos, ChessPiece.PieceType.QUEEN));
        moves.add(new ChessMove(startPos, endPos, ChessPiece.PieceType.BISHOP));
        moves.add(new ChessMove(startPos, endPos, ChessPiece.PieceType.KNIGHT));
        return moves;
    }

    // should only be invoked for moves already determined to be valid
    public final HashSet<ChessMove> determinePromotionPiece(ChessPosition startPos, ChessPosition endPos){
        HashSet<ChessMove> moves = new HashSet<>();
        // if pawn is in promotion row, add all items to hashset
        if ((endPos.getRow() == 8) && piece.getTeamColor() == ChessGame.TeamColor.WHITE){
            return (addAllPromoPieces(startPos, endPos));
        }
        else if ((endPos.getRow() == 1) && piece.getTeamColor() == ChessGame.TeamColor.BLACK){
            return (addAllPromoPieces(startPos, endPos));
        }
        else {
            moves.add(new ChessMove(startPos, endPos, null));
            return moves;
        }
    }


    @Override
    public HashSet<ChessMove> getMoves() {
        var pieceColor = piece.getTeamColor();
        int pawnDirection = pieceColor == ChessGame.TeamColor.WHITE ? 1 : -1;
        int pawnStartRow = pieceColor == ChessGame.TeamColor.WHITE ? 2 : 7;
        HashSet<ChessMove> validMoves = new HashSet<>();

        int row = position.getRow();
        int col = position.getColumn();

        // check in front to see if it's null
        ChessPosition oneForward = new ChessPosition(row + pawnDirection, col);
        boolean oneForwardIsValid = isValidSquare(oneForward) && board.getPiece(oneForward) == null;
        if (oneForwardIsValid){
            validMoves.addAll(determinePromotionPiece(position, oneForward));
        }

        // if the pawn is at the starting place, check two in front
        if (row == pawnStartRow && oneForwardIsValid) {
            ChessPosition twoForward = new ChessPosition(row + (pawnDirection * 2), col);
            if (isValidSquare(twoForward) && board.getPiece(twoForward) == null) {
                validMoves.addAll(determinePromotionPiece(position, twoForward));
            }
        }

        // check for corner kills
        ChessPosition diagonalLeft = new ChessPosition(row + pawnDirection, col - 1);
        ChessPosition diagonalRight = new ChessPosition(row + pawnDirection, col + 1);

        if (isValidSquare(diagonalRight) && board.getPiece(diagonalRight) != null && board.getPiece(diagonalRight).getTeamColor() != pieceColor){
            validMoves.addAll(determinePromotionPiece(position, diagonalRight));
        }
        if (isValidSquare(diagonalLeft) && board.getPiece(diagonalLeft) != null && board.getPiece(diagonalLeft).getTeamColor() != pieceColor){
            validMoves.addAll(determinePromotionPiece(position, diagonalLeft));
        }

        return validMoves;
    }
}
