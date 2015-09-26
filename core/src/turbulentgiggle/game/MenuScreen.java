package turbulentgiggle.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import turbulentgiggle.game.utils.ResourceLoader;

/**
 * Created by Quang on 9/26/2015.
 */
public class MenuScreen extends CScreen {

    TextureRegion region;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        region = ResourceLoader.getTexture("ship");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(region, 100, 100);
        batch.end();
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
