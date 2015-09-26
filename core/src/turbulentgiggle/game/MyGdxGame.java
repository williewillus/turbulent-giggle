package turbulentgiggle.game;

import com.badlogic.gdx.Game;
import turbulentgiggle.game.tetris.TetrisScreen;
import turbulentgiggle.game.utils.ResourceLoader;

public class MyGdxGame extends Game {

    private CScreen menuScreen, gameScreen, tetrisScreen;

	@Override
	public void create () {
		ResourceLoader.load();
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        tetrisScreen = new TetrisScreen(this);
        setScreen(tetrisScreen);
	}

    @Override
    public void dispose() {
        ResourceLoader.dispose();
    }
}
