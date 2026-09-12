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
            validMoves.add(new ChessMove(position, oneForward, null));
        }

        // if the pawn is at the starting place, check two in front
        if (row == pawnStartRow && oneForwardIsValid){
            ChessPosition twoForward = new ChessPosition(row + (pawnDirection * 2), col);
            if (isValidSquare(twoForward) && board.getPiece(twoForward) == null){
                validMoves.add(new ChessMove(position, oneForward, null));
            }
        }

        // check for corner kills
        ChessPosition diagonalLeft = new ChessPosition(row + pawnDirection, col - 1);
        ChessPosition diagonalRight = new ChessPosition(row + pawnDirection, col + 1);

        if (isValidSquare(diagonalRight) && board.getPiece(diagonalRight) != null && board.getPiece(diagonalRight).getTeamColor() != pieceColor){
            validMoves.add(new ChessMove(position, diagonalRight, null));
        }
        else if (isValidSquare(diagonalLeft) && board.getPiece(diagonalLeft) != null && board.getPiece(diagonalLeft).getTeamColor() != pieceColor){
            validMoves.add(new ChessMove(position, diagonalLeft, null));
        }

        return validMoves;
    }
}
