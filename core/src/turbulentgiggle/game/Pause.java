package turbulentgiggle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import turbulentgiggle.game.utils.ResourceLoader;

/**
 * Created by Quang on 9/27/2015.
 */
public class Pause {

    private BitmapFont font, fontSmall;
    private GlyphLayout paused, options, arrow;
    private int curOption = 0;

    public Pause() {
        font = ResourceLoader.getFont();
        fontSmall = ResourceLoader.getSmallFont();
        paused = new GlyphLayout(font, "Paused");
        options = new GlyphLayout(fontSmall, "Resume\nRecalibrate\nExit");
        arrow = new GlyphLayout(fontSmall, ">");
    }

    private int up, down, delay = DELAY;
    private static final int DELAY = 20;

    public boolean render(Controller controller, SpriteBatch batch, ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect((Constants.WIDTH - paused.width)/2 - 32, (Constants.HEIGHT - paused.height)/2 - 90, paused.width + 60, paused.height + 110);
        shapeRenderer.end();
        batch.begin();
        font.draw(batch, paused, (Constants.WIDTH - paused.width)/2, (Constants.HEIGHT - paused.height)/2 + 20);
        fontSmall.draw(batch, options, (Constants.WIDTH - options.width)/2,(Constants.HEIGHT - options.height)/2);
        fontSmall.draw(batch, arrow, (Constants.WIDTH - options.width)/2 - 20, (Constants.HEIGHT - options.height)/2 - curOption * fontSmall.getLineHeight());
        batch.end();
        if(controller.up() && up <= 0) {
            up = DELAY;
            curOption--;
            if(curOption < 0)
                curOption = 2;
        }
        if(controller.down() && down <= 0) {
            down = DELAY;
            curOption++;
            if(curOption >= 3) {
                curOption = 0;
            }
        }
        if(controller.action3() && delay <= 0) {
            delay = DELAY;
            if(curOption == 0) {
                return false;
            } else if(curOption == 1) {
                controller.recalibrate();
            } else {
                Gdx.app.exit();
            }
        }
        if(delay > 0) {
            delay--;
        }
        if(up > 0) {
            up--;
        }
        if(down > 0) {
            down--;
        }
        return true;
    }

}
