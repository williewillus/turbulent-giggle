package turbulentgiggle.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import turbulentgiggle.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Turbulent Giggle";
		config.width = 640;
		config.height = 480;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
