package turbulentgiggle.game.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import turbulentgiggle.game.CScreen;
import turbulentgiggle.game.Controller;

/**
 * Created by Quang on 9/26/2015.
 */
public class TetrisScreen extends CScreen {

    private TetrisBoard board;

    public TetrisScreen(Game game, Controller controller) {
        super(game, controller);

    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        board = new TetrisBoard(100, 0, 10, 15);
    }

    private int rotClockwise = DELAY, rotCounterClockwise = DELAY, left = DELAY, right = DELAY, action = DELAY;
    private int tick = TICK;
    private static final int TICK = 20;
    private static final int DELAY = 10;

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        controller.poll();
        if(controller.rotateClockwise() && rotClockwise <= 0) {
            rotClockwise = DELAY;
            board.rotateCurrentBlockClockwise();
        }
        if(controller.rotateCounterClockwise() && rotCounterClockwise <= 0) {
            rotCounterClockwise = DELAY;
            board.rotateCurrentBlockCounterclockwise();
        }
        if(controller.left() && left <= 0) {
            left = DELAY;
            board.moveLeft();
        }
        if(controller.right() && right <= 0) {
            right = DELAY;
            board.moveRight();
        }
        if(rotClockwise > 0)
            rotClockwise--;
        if(rotCounterClockwise > 0)
            rotCounterClockwise--;
        if(left > 0) {
            left--;
        }
        if(right > 0) {
            right--;
        }
        if(action > 0) {
            action--;
        }
        if(controller.action() && action <= 0) {
            board.drop();
            action = DELAY;
        }
        if(tick <= 0) {
            tick = TICK;
            board.tick();
        } else {
            tick--;
        }
        board.render(shapeRenderer);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
