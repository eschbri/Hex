package com.joelsharin.brianesch.hex.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel on 4/8/2015.
 * Class contains the information for one gam.
 */
public class HexGame {
    private int size;
    private boolean turn;

    private List<List<GameSquare>> board;

    /**
     *
     * @param size
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
     * This method will update the square
     * @param x is the x coordinate of the tile being placed
     * @param y is the y coordinate of the tile being placed
     * @return true if the move completed successfully
     */
    public boolean updateSquare(int x, int y) {
        if (board.get(x).get(y).playPiece(turn, x, y)) {
            turn = !turn;
            return true;
        } else
            return false;
    }


}
