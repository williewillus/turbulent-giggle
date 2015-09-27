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
    public void poll() {

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
    public boolean up() {
        return Gdx.input.isKeyPressed(Input.Keys.W);
    }

    @Override
    public boolean down() {
        return Gdx.input.isKeyPressed(Input.Keys.S);
    }

    @Override
    public boolean action2() {
        return Gdx.input.isKeyPressed(Input.Keys.NUM_1);
    }

    @Override
    public boolean action3() {
        return Gdx.input.isKeyPressed(Input.Keys.NUM_2);
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
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }
}
