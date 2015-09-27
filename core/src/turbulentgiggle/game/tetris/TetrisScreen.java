package turbulentgiggle.game.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import turbulentgiggle.game.CScreen;
import turbulentgiggle.game.Controller;
import turbulentgiggle.game.GameOver;
import turbulentgiggle.game.utils.ResourceLoader;

/**
 * Created by Quang on 9/26/2015.
 */
public class TetrisScreen extends CScreen {

    private TetrisBoard board;
    private Music music;

    public TetrisScreen(Game game, Controller controller) {
        super(game, controller);
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        board = new TetrisBoard(210, 0, 16, 20);
        font = ResourceLoader.getFont();
        gameover = new GameOver();
        music = ResourceLoader.getSound();
        music.play();
    }

    private int rotClockwise = DELAY, rotCounterClockwise = DELAY, left = MOVE_DELAY, right = MOVE_DELAY, action = DELAY;
    private int tick = TICK;
    private static final int TICK = 20;
    private static final int DELAY = 20, MOVE_DELAY = 10;
    private BitmapFont font;
    private GameOver gameover;

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        controller.poll();
        if(!board.isGameover()) {
            if (controller.rotateClockwise() && rotClockwise <= 0) {
                rotClockwise = DELAY;
                board.rotateCurrentBlockClockwise();
            }
            if (controller.rotateCounterClockwise() && rotCounterClockwise <= 0) {
                rotCounterClockwise = DELAY;
                board.rotateCurrentBlockCounterclockwise();
            }
            if (controller.left() && left <= 0) {
                left = MOVE_DELAY;
                board.moveLeft();
            }
            if (controller.right() && right <= 0) {
                right = MOVE_DELAY;
                board.moveRight();
            }
            if (rotClockwise > 0)
                rotClockwise--;
            if (rotCounterClockwise > 0)
                rotCounterClockwise--;
            if (left > 0) {
                left--;
            }
            if (right > 0) {
                right--;
            }
            if (action > 0) {
                action--;
            }
            if (controller.action() && action <= 0) {
                board.drop();
                action = DELAY;
            }
            if (tick <= 0) {
                tick = TICK;
                board.tick();
            }
            if (tick > 0) {
                tick--;
            }
        }
        board.render(shapeRenderer);
        batch.begin();
        calcScoreString();
        font.draw(batch, scoreString, 10, 470);
        batch.end();
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || board.isGameover()) {
            if(gameover.render(controller, batch, shapeRenderer)) {
                board.reset();
            }
            music.setVolume(music.getVolume()*0.95f);
        }
    }

    private int score = 0;
    private String scoreString = "0000000000";

    public void calcScoreString() {
        if(board.getScore() != score) {
            score = board.getScore();
            scoreString = String.valueOf(score);
            for(int i = scoreString.length(); i < 10; i++) {
                scoreString = "0" + scoreString;
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        music.pause();
    }
}
