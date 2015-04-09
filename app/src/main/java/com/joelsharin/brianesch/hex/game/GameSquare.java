package com.joelsharin.brianesch.hex.game;

/**
 * Created by Joel on 4/8/2015.
 * This is the representation of a single square of the board
 */
public class GameSquare {
    private boolean empty;
    private boolean player;
    private ReachRange range;

    /**
     * Initializes an empty game square.
     */
    public GameSquare() {
        empty = true;
        player = true;
    }

    /**
     *
     * @return true if nobody has played on this square yet
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * Checks if player is in this space
     * @param player is the player to check if occupies this space
     * @return true if this space is occupied by the player given, false otherwise
     */
    public boolean isPlayer(boolean player) {
        return (!empty && this.player == player);
    }

    /**
     * Plays a piece in this square, if possible.
     * @param player is a boolean signifying which player's turn it is
     * @param playerOneRange is the range of this square (location) if it is player one's square
     * @param playerTwoRange is the range of this square (location) if it is player two's square
     * @return true if it is a legal play, false if not possible and no updating was done
     */
    public boolean playPiece(boolean player, int playerOneRange, int playerTwoRange) {
        if (!isEmpty())
            // Piece is already taken!
            return false;
        this.player = player;
        empty = false;
        if (player)
            range = new ReachRange(playerOneRange);
        else
            range = new ReachRange(playerTwoRange);
        return true;
    }

    /**
     * Changes the ReachRange of this square to the given range
     * @param range is the range to update this square's range to
     * @return true if the range was updated, false otherwise
     */
    public boolean rangeUpdate(ReachRange range) {
        if (this.range.equals(range))
            return false;
        else {
            this.range.start = range.start;
            this.range.end = range.end;
            return true;
        }
    }

    /**
     *
     * @return the range of this square
     */
    public ReachRange getRange() {
        return range;
    }
}
