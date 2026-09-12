package chess.MoveHandler;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.ChessPosition;

public class BishopMoveHandler extends ChessMoveHandler{

    public BishopMoveHandler(ChessPosition myPosition, ChessBoard board, ChessPiece piece) {
        super(myPosition, board, piece);
    }
}
