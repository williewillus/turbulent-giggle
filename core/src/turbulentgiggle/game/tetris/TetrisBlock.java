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
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            return ImmutableList.of(
                    new Point(0, 0),
                    new Point(0, 1),
                    new Point(1, 0),
                    new Point(1, 1)
            );
        }
    },
    I(Color.CYAN) {
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
                case ONE_EIGHTY: {
                    return points.add(new Point(0, 0))
                            .add(new Point(0, -1))
                            .add(new Point(0, -2))
                            .add(new Point(0, -3))
                            .add(new Point(0, -4)).build();
                }
                case TWO_SEVENTY: {
                    return points.add(new Point(0, 0))
                            .add(new Point(-1, 0))
                            .add(new Point(-2, 0))
                            .add(new Point(-3, 0))
                            .add(new Point(-4, 0)).build();
                }
                default: return points.build();
            }
        }
    },
    S(Color.GREEN) {
        @Override
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            ImmutableList.Builder<Point> points = ImmutableList.builder();
            switch (rotation) {
                case ZERO: {
                    return points.add(new Point(0, 0))
                            .add(new Point(1, 0))
                            .add(new Point(1, 1))
                            .add(new Point(2, 2)).build();
                }
                case NINETY: {
                    return points.add(new Point(0, 0))
                            .add(new Point(0, -1))
                            .add(new Point(1, -1))
                            .add(new Point(1, -2)).build();
                }
            }
            return points.build();
        }
    },
    Z(Color.RED) {
        @Override
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            ImmutableList.Builder<Point> points = ImmutableList.builder();
            switch (rotation) {

            }
            return points.build();
        }
    },
    J(Color.ORANGE) {
        @Override
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            ImmutableList.Builder<Point> points = ImmutableList.builder();
            switch (rotation) {
                case ZERO:
                    points = points.add(new Point(2, 0))
                            .add(new Point(2, 1))
                            .add(new Point(2, 2))
                            .add(new Point(1, 2));
                    break;
                case NINETY:
                    points = points.add(new Point(2, 1))
                            .add(new Point(2, 2))
                            .add(new Point(1, 2))
                            .add(new Point(0, 2));
                case ONE_EIGHTY:
                    points =  points.add(new Point(1, 2))
                            .add(new Point(0, 0))
                            .add(new Point(0, 1))
                            .add(new Point(0, 2));
                    break;
                case TWO_SEVENTY:
                    points = points.add(new Point(0, 1))
                            .add(new Point(0, 0))
                            .add(new Point(1, 0))
                            .add(new Point(2, 0));
                    break;
            }
            return points.build();
        }
    },
    L(Color.BLUE) {
        @Override
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            ImmutableList.Builder<Point> points = ImmutableList.builder();
            switch (rotation) {
                case ZERO:
                    points = points.add(new Point(0, 0))
                            .add(new Point(0, 1))
                            .add(new Point(0, 2))
                            .add(new Point(1, 2));
                    break;
                case NINETY:
                    points = points.add(new Point(0, 0))
                            .add(new Point(1, 0))
                            .add(new Point(2, 0))
                            .add(new Point(0, 1));
                case ONE_EIGHTY:
                    points =  points.add(new Point(1, 0))
                            .add(new Point(2, 0))
                            .add(new Point(2, 1))
                            .add(new Point(2, 2));
                    break;
                case TWO_SEVENTY:
                    points = points.add(new Point(2, 1))
                            .add(new Point(0, 2))
                            .add(new Point(1, 2))
                            .add(new Point(2, 2));
                    break;
            }
            return points.build();
        }
    },
    T(Color.MAGENTA) {
        @Override
        protected List<Point> getDefaultState(CardinalRotation rotation) {
            ImmutableList.Builder<Point> points = ImmutableList.builder();
            switch (rotation) {
                case ZERO:
                    points = points.add(new Point(0, 0))
                                .add(new Point(1, 0))
                                .add(new Point(2, 0))
                                .add(new Point(1, 1));
                    break;
                case NINETY:
                    points = points.add(new Point(1, 1))
                                .add(new Point(2, 0))
                                .add(new Point(2, 1))
                                .add(new Point(2, 2));
                case ONE_EIGHTY:
                    points =  points.add(new Point(1, 1))
                                .add(new Point(0, 2))
                                .add(new Point(1, 2))
                                .add(new Point(2, 2));
                    break;
                case TWO_SEVENTY:
                    points = points.add(new Point(1, 1))
                                .add(new Point(0, 0))
                                .add(new Point(0, 1))
                                .add(new Point(0, 2));
                    break;
            }
            return points.build();
        }
    };

    public final List<Point> defaultState;
    public final List<Point> ninetyDegreeState;
    public final List<Point> oneEightyDegreeState;
    public final List<Point> twoSeventyDegreeState;
    public final Color color;

    TetrisBlock(Color color) {
        defaultState = getDefaultState(CardinalRotation.ZERO);
        ninetyDegreeState = getDefaultState(CardinalRotation.NINETY);
        oneEightyDegreeState = getDefaultState(CardinalRotation.ONE_EIGHTY);
        twoSeventyDegreeState = getDefaultState(CardinalRotation.TWO_SEVENTY);
        this.color = color;
    }

    protected abstract List<Point> getDefaultState(CardinalRotation rotation);
}
