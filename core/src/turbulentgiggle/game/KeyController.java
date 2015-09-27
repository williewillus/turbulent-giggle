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
        return Gdx.input.isButtonPressed(Input.Keys.A);
    }

    @Override
    public boolean right() {
        return Gdx.input.isButtonPressed(Input.Keys.D);
    }

    @Override
    public boolean rotateClockwise() {
        return Gdx.input.isButtonPressed(Input.Keys.E);
    }

    @Override
    public boolean rotateCounterClockwise() {
        return Gdx.input.isButtonPressed(Input.Keys.Q);
    }

    @Override
    public boolean action() {
        return Gdx.input.isButtonPressed(Input.Keys.S);
    }
}
