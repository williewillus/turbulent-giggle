package turbulentgiggle.game.tetris;

import com.badlogic.gdx.graphics.Color;
import com.google.common.collect.ImmutableList;
import org.lwjgl.util.Point;
import turbulentgiggle.game.utils.CardinalRotation;

import java.util.List;

/**
 * Created by Quang on 9/26/2015.
 */
public enum TetrisBlock {
    O(Color.YELLOW) {
        @Override
        protected Piece getPiece(CardinalRotation rotation) {
            return new Piece(new boolean[][]
                    {
                            {true, true},
                            {true, true},
                    });
        }
    },
    I(Color.CYAN) {
        @Override
        protected Piece getPiece(CardinalRotation rotation) {
            return new Piece(new boolean[][]
                    {
                            {true, true, true, true},
                            {false, false, false, false},
                            {false, false, false, false},
                            {false, false, false, false}
                    });
        }
    },
    S(Color.GREEN) {
        @Override
        protected Piece getPiece(CardinalRotation rotation) {
            return new Piece(new boolean[][]
                    {
                            {false, true, true},
                            {true, true, false},
                            {false, false, false}
                    });
        }
    },
    Z(Color.RED) {
        @Override
        protected Piece getPiece(CardinalRotation rotation) {
            return new Piece(new boolean[][]
                    {
                            {true, true, false},
                            {false, true, true},
                            {false, false, false}
                    });
        }
    },
    J(Color.ORANGE) {
        @Override
        protected Piece getPiece(CardinalRotation rotation) {
            return new Piece(new boolean[][]
                    {
                            {true, true, true},
                            {false, false, true},
                            {false, false, false}
                    });
        }
    },
    L(Color.BLUE) {
        @Override
        protected Piece getPiece(CardinalRotation rotation) {
            return new Piece(new boolean[][]
                    {
                            {true, true, true},
                            {true, false, false},
                            {false, false, false}
                    });
        }
    },
    T(Color.MAGENTA) {
        @Override
        protected Piece getPiece(CardinalRotation rotation) {
            return new Piece(new boolean[][]
                    {
                            {true, true, true},
                            {false, true, false},
                            {false, false, false}
                    });
        }
    };
    public final Color color;

    TetrisBlock(Color color) {
        this.color = color;
    }

    public abstract Piece getPiece();
}
