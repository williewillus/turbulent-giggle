package turbulentgiggle.game;

import com.badlogic.gdx.Game;
import turbulentgiggle.game.utils.ResourceLoader;

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
