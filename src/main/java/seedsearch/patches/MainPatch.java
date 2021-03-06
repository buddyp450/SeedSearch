package seedsearch.patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.headless.HeadlessNativesLoader;
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
import com.badlogic.gdx.graphics.GL20;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.desktop.DesktopLauncher;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import static org.mockito.Mockito.mock;

@SpirePatch(
        clz = DesktopLauncher.class,
        method = "main"
)
public class MainPatch {

    public static void Replace(String[] args) {
        try {
            Configurator.setRootLevel(Level.OFF);
            HeadlessNativesLoader.load();
            Gdx.graphics = new MockGraphics();
            Gdx.gl = Gdx.gl20 = mock(GL20.class);
            Gdx.audio = null;
            HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
            new HeadlessApplication(new CardCrawlGame(config.preferencesDirectory), config);
        } catch (Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

}
