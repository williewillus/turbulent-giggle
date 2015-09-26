package turbulentgiggle.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

/**
 * Created by Quang on 9/26/2015.
 */
public class PackTextures {

    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.filterMag = Texture.TextureFilter.Linear;
        settings.filterMin = Texture.TextureFilter.Linear;
        TexturePacker.process(settings, "game_textures/", "res/textures/", "game");
    }

}
