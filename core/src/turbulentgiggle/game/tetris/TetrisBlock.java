package turbulentgiggle.game.tetris;

import com.google.common.collect.ImmutableList;
import org.lwjgl.util.Point;
import turbulentgiggle.game.utils.CardinalRotation;

import java.util.List;

/**
 * Created by Quang on 9/26/2015.
 */
public enum TetrisBlock {
    O {
        @Override
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            return ImmutableList.of(
                    new Point(0, 0),
                    new Point(0, 1),
                    new Point(1, 0),
                    new Point(1, 1)
            );
        }
    },
    I {
        @Override
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            ImmutableList.Builder<Point> points = ImmutableList.builder();
            switch (rotation) {
                case ZERO: {
                    return points.add(new Point(0, 0))
                            .add(new Point(0, 1))
                            .add(new Point(0, 2))
                            .add(new Point(0, 3))
                            .add(new Point(0, 4)).build();
                }
                case NINETY: {
                    return points.add(new Point(0, 0))
                            .add(new Point(1, 0))
                            .add(new Point(2, 0))
                            .add(new Point(3, 0))
                            .add(new Point(4, 0)).build();
                }
                case 
            }
            return points.build();
        }
    },
    S {
        @Override
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            ImmutableList.Builder<Point> points = ImmutableList.builder();
            switch (rotation) {

            }
            return points.build();
        }
    },
    Z {
        @Override
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            ImmutableList.Builder<Point> points = ImmutableList.builder();
            switch (rotation) {

            }
            return points.build();
        }
    },
    L {
        @Override
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            ImmutableList.Builder<Point> points = ImmutableList.builder();
            switch (rotation) {

            }
            return points.build();
        }
    },
    J {
        @Override
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            ImmutableList.Builder<Point> points = ImmutableList.builder();
            switch (rotation) {

            }
            return points.build();
        }
    },
    T {
        @Override
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            ImmutableList.Builder<Point> points = ImmutableList.builder();
            switch (rotation) {

            }
            return points.build();
        }
    };

    public final List<Point> defaultState;
    public final List<Point> ninetyDegreeState;
    public final List<Point> oneEightyDegreeState;
    public final List<Point> twoSeventyDegreeState;

    TetrisBlock() {
        defaultState = getDefaultState(CardinalRotation.ZERO);
        ninetyDegreeState = getDefaultState(CardinalRotation.NINETY);
        oneEightyDegreeState = getDefaultState(CardinalRotation.ONE_EIGHTY);
        twoSeventyDegreeState = getDefaultState(CardinalRotation.TWO_SEVENTY);
    }

    protected abstract List<Point> getDefaultState(CardinalRotation rotation);
}
