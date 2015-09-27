package turbulentgiggle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by Quang on 9/26/2015.
 */
public class KeyController extends Controller {

    public KeyController() {
        super(null);
    }

    @Override
    public boolean left() {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    @Override
    public boolean right() {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

    @Override
    public boolean rotateClockwise() {
        return Gdx.input.isKeyPressed(Input.Keys.E);
    }

    @Override
    public boolean rotateCounterClockwise() {
        return Gdx.input.isKeyPressed(Input.Keys.Q);
    }

    @Override
    public boolean action() {
        return Gdx.input.isKeyPressed(Input.Keys.S);
    }
}
