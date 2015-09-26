package turbulentgiggle.game.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.lwjgl.util.Point;
import turbulentgiggle.game.CScreen;

/**
 * Created by Quang on 9/26/2015.
 */
public class TetrisScreen extends CScreen {

    private TetrisBoard board;

    public TetrisScreen(Game game) {
        super(game);

    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        board = new TetrisBoard(100, 0, 10, 15);
        board.setPosition(new Point(1,0), Color.YELLOW);
        board.setPosition(new Point(1,1), Color.RED);
        board.setPosition(new Point(1,2), Color.BLUE);
        board.setPosition(new Point(1,3), Color.GREEN);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
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
