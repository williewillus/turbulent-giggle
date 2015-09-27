package turbulentgiggle.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.thalmic.myo.DeviceListener;

/**
 * Created by Quang on 9/26/2015.
 */
public abstract class CScreen implements Screen {

    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    protected Game game;
    protected ShapeRenderer shapeRenderer;
    protected Controller controller;

    public CScreen(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }

}
