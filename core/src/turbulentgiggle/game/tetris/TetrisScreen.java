package turbulentgiggle.game.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import turbulentgiggle.game.CScreen;

/**
 * Created by Quang on 9/26/2015.
 */
public class TetrisScreen extends CScreen {

    public TetrisScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

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
