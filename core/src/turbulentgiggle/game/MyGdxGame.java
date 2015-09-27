package turbulentgiggle.game;

import com.badlogic.gdx.Game;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.enums.LockingPolicy;
import turbulentgiggle.game.tetris.TetrisScreen;
import turbulentgiggle.game.utils.ResourceLoader;

public class MyGdxGame extends Game {

    private CScreen menuScreen, gameScreen, tetrisScreen;
    private Hub hub;
    private Myo myo;
    private Controller controller;

    private boolean KEY = false;

	@Override
	public void create () {
		ResourceLoader.load();
        if(KEY) {
            controller = new KeyController();
        } else {
            hub = new Hub("Piece.of.Garbage");
            System.out.println("Attempting to find a Myo...");
            myo = hub.waitForMyo(1000);
            hub.setLockingPolicy(LockingPolicy.LOCKING_POLICY_NONE);
            System.out.println("Connected to a Myo armband!");
            controller = new Controller(hub);
            hub.addListener(controller);
        }
        menuScreen = new MenuScreen(this, controller);
        gameScreen = new GameScreen(this, controller);
        tetrisScreen = new TetrisScreen(this, controller);
        setScreen(tetrisScreen);
	}

    @Override
    public void dispose() {
        ResourceLoader.dispose();
    }
}
