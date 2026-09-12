package chess.MoveHandler;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.HashSet;

public class KingMovesHandler extends ChessMoveHandler{
    public KingMovesHandler(ChessPosition position, ChessBoard board, ChessPiece piece) {
        super(position, board, piece);
    }

    public HashSet<ChessMove> getMoves(){
        int row = position.getRow();
        int col = position.getColumn();

        HashSet<ChessMove> validMoves = HashSet.newHashSet(8);

        int[][] relativeMoves = {{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        for (int[] move : relativeMoves){
            int x = move[0];
            int y = move[1];
            int newRow = row + x;
            int newCol = col + y;
            ChessPosition newPosition = new ChessPosition(newRow, newCol)
            if (isValidSquare(newPosition) && isNullOrOtherTeam(newPosition)){
                validMoves.add(new ChessMove(position, newPosition, null));
            }
        }

    }
}
