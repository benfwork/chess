package chess;

import java.util.Collection;
import java.util.Objects;

/**
 * A class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private ChessBoard board;
    private TeamColor teamTurn;

    public ChessGame() {
        this.board = new ChessBoard();
        this.board.resetBoard();
        this.teamTurn = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.teamTurn;
    }

    /**
     * Sets which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets all valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece piece = board.getPiece(startPosition);
        Collection<ChessMove> validMoves = piece.pieceMoves(board, startPosition); // valid moves before considering checkmate

        for (var move : validMoves){
            ChessPiece endPiece = board.getPiece(move.getEndPosition());
            board.removePiece(startPosition); // remove piece from start
            board.addPiece(move.getEndPosition(), piece); // move piece to new place
            if (isInCheck(piece.getTeamColor())){
                validMoves.remove(move);
            }
            // reset board
            board.addPiece(startPosition, piece);
            board.addPiece(move.getEndPosition(), endPiece);
        }

        return validMoves;
    }

    /**
     * Makes a move in the chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        boolean moveIsValid = validMoves(move.getStartPosition()).contains(move);
        // if the move is not valid, throw the exception
        if (!moveIsValid){
            throw new InvalidMoveException("Move is not valid");
        }

        // adds it to new square
        board.addPiece(move.getEndPosition(), move.getPromotionPiece() == null ? board.getPiece(move.getStartPosition()) : new ChessPiece(teamTurn, move.getPromotionPiece()));
        // removes the piece from old square
        board.removePiece(move.getStartPosition());
    }

    public ChessPosition findKingPos(TeamColor color){
        for (int row = 1; row < 9; row++){
            for (int col = 1; col < 9; col++){
                ChessPiece piece = board.getPiece(new ChessPosition(row, col));
                if (piece == null){
                    continue;
                }
                if (piece.getTeamColor() == color && piece.getPieceType() == ChessPiece.PieceType.KING){
                    return(new ChessPosition(row, col));
                }
            }
        }
        return null;
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        // find kings position
        ChessPosition kingPos = findKingPos(teamColor);

        for (int row = 1; row < 9; row++){
            for (int col = 1; col < 9; col++){
                ChessPosition newPos = new ChessPosition(row, col);
                ChessPiece newPiece = board.getPiece(newPos);
                if (newPiece != null && newPiece.getTeamColor() != teamColor) {
                    var newMoves = newPiece.pieceMoves(board, newPos);
                    for (var move : newMoves){
                        if (move.getEndPosition().equals(kingPos)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        // if there's no valid moves and you're in check, it's checkmate;
        return isInCheck(teamColor) && isInStalemate(teamColor);
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        for (int row = 1; row < 9; row++){
            for (int col = 1; col < 9; col++){
                ChessPosition newPos = new ChessPosition(row, col);
                ChessPiece newPiece = board.getPiece(newPos);

                if (newPiece != null && newPiece.getTeamColor() == teamColor){
                    // if there's any valid move on your team, you're not in stalemate
                    if (validMoves(newPos) != null && !validMoves(newPos).isEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Sets this game's chessboard to a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.board;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(getBoard(), chessGame.getBoard()) && getTeamTurn() == chessGame.getTeamTurn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoard(), getTeamTurn());
    }
}
