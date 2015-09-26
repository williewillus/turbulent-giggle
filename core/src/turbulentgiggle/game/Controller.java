package turbulentgiggle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by Quang on 9/26/2015.
 */
public class Controller {

    public static boolean left() {
        return Gdx.input.isButtonPressed(Input.Keys.LEFT);
    }

    public static boolean right() {
        return Gdx.input.isButtonPressed(Input.Keys.RIGHT);
    }

    public static boolean up() {
        return Gdx.input.isButtonPressed(Input.Keys.UP);
    }

    public static boolean down() {
        return Gdx.input.isButtonPressed(Input.Keys.DOWN);
    }

    public static boolean action() {
        return Gdx.input.isButtonPressed(Input.Keys.Z);
    }

    public static boolean action2() {
        return Gdx.input.isButtonPressed(Input.Keys.X);
    }

}
