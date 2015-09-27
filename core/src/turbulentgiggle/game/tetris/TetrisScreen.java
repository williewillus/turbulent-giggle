package turbulentgiggle.game.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.lwjgl.util.Point;
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
        board = new TetrisBoard(100, 0, 15, 10);
    }

    private int right = 0, left = 0;
    private int tick = 60;

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        controller.poll();
        if(controller.right() && right <= 0) {
            right = 10;
            board.rotateCurrentBlockClockwise();
        }
        if(controller.left() && left <= 0) {
            left = 10;
            board.rotateCurrentBlockCounterclockwise();
        }
        if(right > 0)
            right--;
        if(left > 0)
            left--;
        if(controller.action()) {
            System.out.println("ACTION");
        }
        if(tick <= 0) {
            tick = 10;
            board.tick();
        } else {
            tick--;
        }
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        board.render(shapeRenderer);
        shapeRenderer.end();
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
