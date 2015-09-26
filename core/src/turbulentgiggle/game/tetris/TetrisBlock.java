package turbulentgiggle.game.tetris;

import com.badlogic.gdx.math.Vector2;
import com.google.common.collect.ImmutableList;

/**
 * Created by Quang on 9/26/2015.
 */
public enum TetrisBlock {
    O,
    I,
    S,
    Z,
    L,
    J,
    T;

    final ImmutableList<Vector2> defaultState;

    TetrisBlock(Vector2... positions) {
        defaultState = ImmutableList.copyOf(positions);
    }
}
