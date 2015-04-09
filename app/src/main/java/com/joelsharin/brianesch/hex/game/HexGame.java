package com.joelsharin.brianesch.hex.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel on 4/8/2015.
 * Class contains the information for one game.
 */
public class HexGame {
    private final int size;
    private boolean turn;
    private final List<List<GameSquare>> board;

    /**
     * Constructs one game of the given size x size
     * @param size is the dimension of the game.
     */
    public HexGame(int size) {
        this.size = size;
        turn = true;
        ArrayList<GameSquare> row;

        board = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            row = new ArrayList<>(size);
            for (int j = 0; j < size; ++j) {
                row.add(new GameSquare());
            }
            board.add(row);
        }
    }

    /**
     * This method will play a tile on the square indicated
     * @param x is the x coordinate of the tile being placed
     * @param y is the y coordinate of the tile being placed
     * @return -1 for a failed move, 0 for a successful move,
     *          1 for player one wins, 2 for player two wins
     */
    public int playSquare(int x, int y) {
        if (board.get(x).get(y).playPiece(turn, x, y)) {
            ReachRange range = getRangeOfSurround(x, y);
            if (range.start == 0 && range.end == size - 1) {
                // Somebody has won
                if (turn)
                    return 1;
                else
                    return 2;
            }
            updateSquare(x, y, range);
            turn = !turn;
            return 0;
        } else
            return -1;
    }

    /**
     * Returns the maximum range of the surrounding squares
     * @param x is the x coordinate of the square being checked for
     * @param y is the y coordinate of the square being checked for
     * @return the maximum range (largest range) of the surrounding tiles
     */
    private ReachRange getRangeOfSurround(int x, int y) {
        ReachRange range;
        if (turn)
            range = new ReachRange(x);
        else
            range = new ReachRange(y);

        if (x + 1 < size) range.mergeRange(board.get(x + 1).get(y).getRange());
        if (x > 0) range.mergeRange(board.get(x - 1).get(y).getRange());
        if (y + 1 < size) range.mergeRange(board.get(x).get(y + 1).getRange());
        if (y > 0) range.mergeRange(board.get(x).get(y - 1).getRange());

        // Can reach top left and bottom right
        if (y > 0 && x > 0) range.mergeRange(board.get(x - 1).get(y - 1).getRange());
        if (y + 1 < size && x + 1 < size) range.mergeRange(board.get(x + 1).get(y + 1).getRange());


        return range;
    }

    /**
     * Updates a connected set of squares owned by the current player to have the proper range
     * @param x is the x coordinate of the tile being updated
     * @param y is the y coordinate of the tile being updated
     * @param range is the range of the tiles that can be reached
     */
    public void updateSquare(int x, int y, ReachRange range) {
        // Don't update if either not one of the player's tiles, or the update is unnecessary
        if (board.get(x).get(y).isPlayer(turn) && board.get(x).get(y).rangeUpdate(range)) {
            // Update each of: cardinal directions, top left, and bottom right, if possible
            if (x + 1 < size) updateSquare(x + 1, y, range);
            if (x > 0) updateSquare(x - 1, y, range);
            if (y + 1 < size) updateSquare(x, y + 1, range);
            if (y > 0) updateSquare(x, y - 1, range);

            // Can reach top left and bottom right
            if (y > 0 && x > 0) updateSquare(x - 1, y - 1, range);
            if (y + 1 < size && x + 1 < size) updateSquare(x + 1, y + 1, range);
        }
    }

    /**
     * Undoes the last move
     * @return true if undo completed successfully, false otherwise
     */
    public boolean undo() {
        //TODO: Implement this
        return false;
    }

    /**
     * Resets the game to the state of about to begin
     */
    public void restartGame() {
        //TODO: Implement this
    }
}
