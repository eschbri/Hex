package com.joelsharin.brianesch.hex.game;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Joel on 4/9/2015.
 * This class describes how far a piece can 'reach.' This is basically a range of how far back
 * or forward you could get if you started at this piece and only could move to adjacent pieces
 * of the same player
 */
public class ReachRange {
    public int start, end;

    /**
     * Initializes a range to one value
     * @param val initializes a range to [val, val]
     */
    public ReachRange(int val) {
        start = val;
        end = val;
    }

    /**
     * Updates this range to the union of the two ranges, taking the minimum of starts and maximum
     * of the ends of the ranges
     * @param range is the range to merge with this current range
     */
    public void mergeRange(ReachRange range) {
        start = min(start, range.start);
        end = max(end, range.end);
    }

    /**
     * Does a logical equals check to see if the two ranges are equivalent
     * @param range is the range to check for equality with
     * @return true if the two ranges are the same
     */
    public boolean equals(ReachRange range) {
        return (start == range.start && end == range.end);
    }
}
