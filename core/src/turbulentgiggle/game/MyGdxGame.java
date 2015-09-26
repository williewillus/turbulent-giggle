package turbulentgiggle.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import turbulentgiggle.game.utils.ResourceLoader;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	
	@Override
	public void create () {
		ResourceLoader.load();
		batch = new SpriteBatch();
	}

}
