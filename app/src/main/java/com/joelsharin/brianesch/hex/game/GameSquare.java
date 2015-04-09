package com.joelsharin.brianesch.hex.game;

/**
 * Created by Joel on 4/8/2015.
 * This is the representation of a single square of the board
 */
public class GameSquare {
    private boolean empty;
    private boolean player;
    private int start_reach;
    private int end_reach;

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

    public boolean playPiece(boolean player, int start, int end) {
        if (!isEmpty())
            // Piece is already taken!
            return false;
        this.player = player;
        empty = false;
        start_reach = start;
        end_reach = end;
        return true;
    }
}
