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

    final List<Point> defaultState;
    final List<Point> ninetyDegreeState;
    final List<Point> oneEightyDegreeState;
    final List<Point> twoSeventyDegreeState;

    TetrisBlock() {
        defaultState = getDefaultState(CardinalRotation.ZERO);
        ninetyDegreeState = getDefaultState(CardinalRotation.NINETY);
        oneEightyDegreeState = getDefaultState(CardinalRotation.ONE_EIGHTY);
        twoSeventyDegreeState = getDefaultState(CardinalRotation.TWO_SEVENTY);
    }

    protected abstract List<Point> getDefaultState(CardinalRotation rotation);
}
