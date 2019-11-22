package net.mvw.delta;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;

import net.mvw.delta.input.InputBox;
import net.mvw.delta.logic.DeltaConsole;
import net.mvw.delta.logic.Global;
import net.mvw.delta.logic.SaveManager;

import static net.mvw.delta.input.Resources.disposeResources;
import static net.mvw.delta.input.Resources.loadResources;
import static net.mvw.delta.states.MainLogic.cameraTargetX;
import static net.mvw.delta.states.MainLogic.cameraTargetY;
import static net.mvw.delta.states.MainLogic.cameraTargetZoom;
import static net.mvw.delta.states.MainLogic.init;
import static net.mvw.delta.states.MainLogic.renderGame;
import static net.mvw.delta.states.MainLogic.updateGame;

public class ProjectDelta extends ApplicationAdapter implements Music.OnCompletionListener {

    public static SpriteBatch batch;
    public static OrthographicCamera camera;
    public static BitmapFont gamefont, gamefontfliped;
    public static final float SCREEN_WIDTH = 1080;
    public static final float SCREEN_HEIGHT = 1920;
    public static float viewportWidth = SCREEN_WIDTH;
    public static float viewportHeight = SCREEN_HEIGHT;

    public static float factor = 1;


    @Override
    public void create() {

        try {

            if (Global.DEBUG_MODE  && Gdx.app.getType().equals(Application.ApplicationType.Desktop)) {
                Global.console = new DeltaConsole();
                //Global.console.setIconImage(new ImageIcon(Gdx.files.internal("icons/gammaconsole.png").readBytes()).getImage());
                Global.console.run();
            }

            batch = new SpriteBatch();
            gamefont = new BitmapFont(Gdx.files.internal("fonts/gammafontf.fnt"));
            gamefontfliped = new BitmapFont(Gdx.files.internal("fonts/gammafontf.fnt"), true);

            float height = Gdx.graphics.getHeight();
            float width = Gdx.graphics.getWidth();
            generateDeviceData(width, height);


            //BaseLoader.load();
            //ModLoader.load();
            loadResources();
            //ProgressController.init();

            InputMultiplexer processor = new InputMultiplexer();
            processor.addProcessor(new InputBox());
            processor.addProcessor(new GestureDetector(new InputBox()));
            Gdx.input.setInputProcessor(processor);
            Gdx.input.setCatchBackKey(true);

            init(batch);

            SaveManager.load();

            //activateMods();
            //ProgressController.regenerate();

            //playingTrack = getRandomCalmTrack();
            //playingTrack.setOnCompletionListener(this);
            //playingTrack.setVolume(masterMusicVolume);
            //playingTrack.play();

        } catch (Exception ex) {
            Global.printError(Global.formatException(ex));
        }
    }

    /**
     * Renders the game.
     */
    @Override
    public void render() {
        try {
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            if (Global.state.equals(Global.GameState.GAME_PROGRESS)) {
                Gdx.gl.glClearColor(0f / 256f, 0f / 256f, 23f / 256f, 1);
            } else {
                Gdx.gl.glClearColor(80f / 256f, 233f / 256f, 242f / 256f, 1);
            }
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);




//			if(playingTrack == null){
//				playingTrack = getRandomCalmTrack();
//				playingTrack.setOnCompletionListener(this);
//				playingTrack.setVolume(masterMusicVolume);
//				playingTrack.play();
//			}
//			playingTrack.setVolume(masterMusicVolume);

            updateGame();
            renderGame(batch);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Disposes the resources.
     */
    @Override
    public void dispose() {
        batch.dispose();
        disposeResources();
        SaveManager.save();
        System.exit(0);
    }

    /**
     * Changes the size of the window.
     *
     * @param width  The width.
     * @param height The height.
     */
    @Override
    public void resize(int width, int height) {
        //SaveManager.save();
        float nheight = Gdx.graphics.getHeight();
        float nwidth = Gdx.graphics.getWidth();
        Global.printDebug("NEW size: " + nwidth + " s " + nheight, "WINDOW");
        generateDeviceData(nwidth, nheight);
    }

    /**
     * Sizes the game window on any aspect ratio (it's black magic).
     *
     * @param width  The width.
     * @param height The height.
     */
    private static void generateDeviceData(float width, float height) {

        viewportWidth = SCREEN_WIDTH;
        viewportHeight = SCREEN_HEIGHT;

        float ratio = height / width;
        float defaultRatio = SCREEN_HEIGHT / SCREEN_WIDTH;
        factor = ratio / defaultRatio;

        Global.printDebug("Factor: " + factor, "WINDOW");

        if (factor > 1) {
            viewportHeight = SCREEN_HEIGHT * factor;
        }
        if (factor < 1) {
            viewportWidth = SCREEN_WIDTH * (1 / factor);
        }

        InputBox.setxScreenOffset((viewportWidth - SCREEN_WIDTH) / 2);
        InputBox.setyScreenOffset((viewportHeight - SCREEN_HEIGHT) / 2);

        Global.printDebug("Offsets: " + InputBox.xScreenOffset + ":::" + InputBox.yScreenOffset, "WINDOW");

        camera = new OrthographicCamera(viewportWidth, viewportHeight);
        camera.setToOrtho(false, viewportWidth, viewportHeight);
        camera.position.x = cameraTargetX;
        camera.position.y = cameraTargetY;
        camera.zoom = cameraTargetZoom;

        Global.printDebug(Gdx.graphics.getWidth() + ":x:" + Gdx.graphics.getHeight(), "WINDOW");
        camera.update();


    }

    @Override
    public void onCompletion(Music music) {
//		playingTrack = getRandomCalmTrack();
//		playingTrack.setOnCompletionListener(this);
//		playingTrack.setVolume(masterMusicVolume);
//		playingTrack.play();
    }
}
