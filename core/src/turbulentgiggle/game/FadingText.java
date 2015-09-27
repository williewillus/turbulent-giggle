package turbulentgiggle.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import turbulentgiggle.game.utils.ResourceLoader;

/**
 * Created by Quang on 9/27/2015.
 */
public class FadingText {

    private GlyphLayout text;
    private BitmapFont font;
    private float x, y, alpha = 1f;
    private int frames, curFrame = 0;
    private Color color;

    public FadingText(String text, float x, float y, int frames) {
        font = ResourceLoader.getSmallFont();
        this.text = new GlyphLayout(font, text);
        this.frames = frames;
        this.x = x;
        this.y = y;
        color = new Color((float)Math.random()*0.5f + 0.5f,(float)Math.random()*0.5f + 0.5f, (float)Math.random()*0.5f + 0.5f,1f);
    }

    public boolean render(SpriteBatch batch) {
        curFrame++;
        color.a = (float)(frames-curFrame)/frames;
        font.setColor(color);
        font.draw(batch, text, (float)(x + Math.cos(curFrame/(double)frames * 6 * Math.PI)) * 10, y + curFrame/(float)frames * 50);
        font.setColor(Color.WHITE);
        return frames == curFrame;
    }

}
