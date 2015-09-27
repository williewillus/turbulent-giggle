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
        board = new TetrisBoard(100, 0, 10, 15);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        controller.poll();
        if(controller.right()) {
            System.out.println("RIGHT");
        }
        if(controller.left()) {
            System.out.println("LEFT");
        }
        if(controller.action()) {
            System.out.println("ACTION");
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
