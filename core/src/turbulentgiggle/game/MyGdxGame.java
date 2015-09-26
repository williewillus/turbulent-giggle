package turbulentgiggle.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import turbulentgiggle.game.utils.ResourceLoader;

import javax.annotation.Resource;

public class MyGdxGame extends Game {

    private CScreen menuScreen, gameScreen;

	@Override
	public void create () {
		ResourceLoader.load();
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        setScreen(menuScreen);
	}

    @Override
    public void dispose() {
        ResourceLoader.dispose();
    }
}
