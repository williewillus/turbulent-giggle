package turbulentgiggle.game.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.google.common.io.Files;
import turbulentgiggle.game.CScreen;
import turbulentgiggle.game.Controller;
import turbulentgiggle.game.GameOver;
import turbulentgiggle.game.Pause;
import turbulentgiggle.game.utils.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by Quang on 9/26/2015.
 */
public class TetrisScreen extends CScreen {

    private TetrisBoard board;
    private Music music;
    private String highScore;
    private int hiscore;
    public TetrisScreen(Game game, Controller controller) {
        super(game, controller);
    }

    @Override
    public void show() {
        try {
            highScore = Files.readFirstLine(new File("assets/hiscore.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(highScore.length() < 10) {
            highScore = "0" + highScore;
        }
        hiscore = Integer.valueOf(highScore);
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        board = new TetrisBoard(210, 0, 16, 20);
        font = ResourceLoader.getFont();
        gameover = new GameOver();
        pause = new Pause();
        music = ResourceLoader.getSound();
        music.play();
    }

    private int rotClockwise = DELAY, rotCounterClockwise = DELAY, left = MOVE_DELAY, right = MOVE_DELAY, action = DELAY, action2 = DELAY, action3 = DELAY;
    private int tick = TICK;
    private static int TICK = 20;
    private static final int DELAY = 20, MOVE_DELAY = 10;
    private BitmapFont font;
    private GameOver gameover;
    private Pause pause;
    private boolean paused = false, speedUp = false;
    private int level = 0, prevLevel;
    private String levelStr;

    @Override
    public void render(float delta) {
        prevLevel = level;
        level = board.getScore()/10000 + 1;
        if(level > 99) {
            level = 99;
        }
        if(level != prevLevel) {
            levelStr = String.valueOf(level);
            while(levelStr.length() < 2) {
                levelStr = "0" + levelStr;
            }
        }
        TICK = 21 - level;
        if(TICK < 5) {
            TICK = 5;
        }
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        controller.poll();
        if (!board.isGameover() && !paused) {
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
            if (controller.down()) {
                speedUp = true;
            } else {
                speedUp = false;
            }
            if(controller.action() && action <= 0) {
                board.drop();
                action = DELAY;
            }
            if (controller.action2() && action2 <= 0) {
                board.hold();
                action2 = DELAY;
            }
            if (action2 > 0) {
                action2--;
            }
            if (tick <= 0) {
                tick = TICK;
                if(speedUp) {
                    tick = TICK/6;
                }
                board.tick();
            }
            if (tick > 0) {
                tick--;
            }
        }
        if(action3 > 0) {
            action3--;
        }
        board.render(shapeRenderer, batch);
        batch.begin();
        calcScoreString();
        font.draw(batch, scoreString, 10, 470);
        font.draw(batch, "HISCORE", 10, 60);
        font.draw(batch, highScore, 10, 30);
        font.draw(batch, "LVL", 10, 90);
        font.draw(batch, levelStr, 80, 90);
        batch.end();
        if (board.isGameover()) {
            if (gameover.render(controller, batch, shapeRenderer)) {
                if(board.getScore() > hiscore) {
                    hiscore = board.getScore();
                    highScore = String.valueOf(hiscore);
                    while(highScore.length() < 10) {
                        highScore = "0" + highScore;
                    }
                    try {
                        Files.write(String.valueOf(hiscore), new File("assets/hiscore.txt"), Charset.defaultCharset());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                board.reset();
                music.setVolume(1f);
            }
            music.setVolume(music.getVolume() * 0.95f);
        }
        if (controller.action3() && !board.isGameover() && action3 <= 0) {
            paused = true;
        }
        if(paused) {
            paused = pause.render(controller, batch, shapeRenderer);
            action3 = DELAY;
            music.setVolume(paused ? 0.3f : 1f);
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
