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
public class GameOver {

    private BitmapFont font, fontSmall;
    private Color color = Color.DARK_GRAY;
    private GlyphLayout gameover, responses, arrow;
    private boolean startover = true;
    private int startoverTimer = 0;
    private static final int DELAY = 20;

    public GameOver() {
        font = ResourceLoader.getFont();
        fontSmall = ResourceLoader.getSmallFont();
        gameover = new GlyphLayout(font, "GAME OVER!\nStart Over?", Color.WHITE, 300, 1, true);
        responses = new GlyphLayout(fontSmall, "Yes       No");
        arrow = new GlyphLayout(fontSmall, ">");
    }

    public boolean render(Controller controller, SpriteBatch batch, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.rect((Constants.WIDTH - gameover.width)/2 - 40, (Constants.HEIGHT - gameover.height)/2 - 10, gameover.width + 80, gameover.height + 60);
        shapeRenderer.end();
        batch.begin();
        font.draw(batch, gameover, Constants.WIDTH/2 - gameover.width/2 - 40, Constants.HEIGHT/2 - gameover.height/2 + 80);
        fontSmall.draw(batch, responses, Constants.WIDTH/2 - responses.width/2, Constants.HEIGHT/2 - gameover.height/2 + 20);
        fontSmall.draw(batch, arrow, Constants.WIDTH/2 - responses.width/2 - 30 + (startover ? 0 : 120), Constants.HEIGHT/2 - gameover.height/2 + 20);
        batch.end();
        if((controller.left() || controller.right()) && startoverTimer <= 0) {
            startover = !startover;
            startoverTimer = DELAY;
        }
        if(controller.action2()) {
            if(startover) {
                return true;
            } else {
                Gdx.app.exit();
            }
        }
        if(startoverTimer > 0) {
            startoverTimer--;
        }
        return false;
    }

}
