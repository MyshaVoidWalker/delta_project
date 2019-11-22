package net.mvw.delta.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import static net.mvw.delta.ProjectDelta.SCREEN_HEIGHT;
import static net.mvw.delta.ProjectDelta.SCREEN_WIDTH;
import static net.mvw.delta.ProjectDelta.batch;
import static net.mvw.delta.ProjectDelta.camera;
import static net.mvw.delta.input.InputBox.continualInputs;
import static net.mvw.delta.input.Resources.fade_aura;
import static net.mvw.delta.logic.Global.GameState;
import static net.mvw.delta.logic.Global.state;
import static net.mvw.delta.logic.Global.timePassed;

/**
 * Contains the main logic of the game.
 *
 * @author MyshaVoidWalker
 */
public class MainLogic {

    /**
     * The camera target X coordinate.
     */
    public static float cameraTargetX = SCREEN_WIDTH / 2;
    /**
     * The camera target Y coordinate.
     */
    public static float cameraTargetY = SCREEN_HEIGHT / 2;
    /**
     * The camera target zoom.
     */
    public static float cameraTargetZoom = 1;
    /**
     * The camera target speed.
     */
    public static float cameraTargetSpeed = 12;
    /**
     * The camera target zoom speed.
     */
    public static float cameraTargetZoomSpeed = 12;

    /**
     * The list of fades.
     */
    private static ArrayList<Sprite> fades = new ArrayList<Sprite>();

    private static Sprite sunSprite;

    /**
     * Sets the camera target position.
     *
     * @param targetX     The target X coordinate.
     * @param targetY     The target Y coordinate.
     * @param targetSpeed The target speed.
     */
    public static void setCameraTargetPosition(float targetX, float targetY, float targetSpeed) {
        cameraTargetX = targetX;
        cameraTargetY = targetY;
        cameraTargetSpeed = targetSpeed;
    }

    /**
     * Sets the camera target zoom.
     *
     * @param targetZoom  The target zoom.
     * @param targetSpeed The target speed.
     */
    public static void setCameraTargetZoom(float targetZoom, float targetSpeed) {
        cameraTargetZoom = targetZoom;
        cameraTargetZoomSpeed = targetSpeed;
    }

    /**
     * Initializes the game sprite batch.
     *
     * @param batch The batch to initialize on.
     */
    public static void init(SpriteBatch batch) {

        //IntroController.init();
        //MenuController.init();
        //GameController.init();

        for (int i = 1; i <= 5; i++) {
            Sprite fade = new Sprite(fade_aura);
            fade.setPosition(SCREEN_WIDTH / 2 - fade.getWidth() / 2, SCREEN_HEIGHT / 2 - fade.getHeight() / 2);
            fade.setScale(6 + i * 3);
            fade.setAlpha(8 / 9f);
            fades.add(fade);
        }


        //GameController.update();

    }

    /**
     * Updates the game.
     */
    public static void updateGame() {
        continualInputs();
        handleVariables();
        updateCameraPosition();


//        if (state != GameState.INTRO && state != GameState.GAME_PROGRESS) MenuController.update();
//        if (state == GameState.GAME || state == GameState.GAME_PROGRESS || state == GameState.GAME_BUILDINGS)
//            GameController.update();
    }

    /**
     * Handles the variables.
     */
    private static void handleVariables() {
        timePassed += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camera.combined);
        camera.update();
    }

    /**
     * Updates the camera position.
     */
    private static void updateCameraPosition() {
        //setCameraTargetPosition(540, 960, 8);
        camera.position.x = camera.position.x + (cameraTargetX - camera.position.x) / cameraTargetSpeed;
        camera.position.y = camera.position.y + (cameraTargetY - camera.position.y) / cameraTargetSpeed;
        camera.zoom = camera.zoom + (cameraTargetZoom - camera.zoom) / cameraTargetZoomSpeed;
    }

    /**
     * Renders the game.
     *
     * @param batch The batch to render on.
     */
    public static void renderGame(SpriteBatch batch) {
        drawBackground(batch);

        switch (state) {
            case INTRO:
                //IntroController.draw(batch);
                break;

            case MENU_MAIN:
            case MENU_OPTIONS:
            case MENU_EXTRAS:
            case MENU_MODS:
            case MENU_ACHIEVEMENTS:
            case MENU_CREDITS:
                //MenuController.draw(batch);
                break;

            case GAME:
            case GAME_BUILDINGS:
            case GAME_PROGRESS:
            case GAME_END:
                //GameController.draw(batch);
                break;


        }

        drawOverground(batch);
    }

    /**
     * The rotation of the planet.
     */
    private static float angle;

    /**
     * Draws the background.
     *
     * @param batch The batch to draw on.
     */
    private static void drawBackground(SpriteBatch batch) {
        batch.begin();

        for (Sprite fade : fades) {
            fade.draw(batch);
        }


        if (state == GameState.MENU_OPTIONS ||
                state == GameState.MENU_ACHIEVEMENTS ||
                state == GameState.MENU_EXTRAS ||
                state == GameState.MENU_MAIN || state == GameState.MENU_CREDITS ||
                state == GameState.MENU_MODS) {
            angle = 0;// PlanetControllerMenu.planetSprite.getRotation() + 135;
            sunSprite.setRotation(angle);
            sunSprite.setPosition(SCREEN_WIDTH / 2 - sunSprite.getWidth() / 2 + (float) Math.cos(Math.toRadians(angle)) * 2800, SCREEN_HEIGHT / 2 - sunSprite.getHeight() / 2 + (float) Math.sin(Math.toRadians(angle)) * 2800);
            sunSprite.draw(batch);
        }

        if (state == GameState.GAME || state == GameState.GAME_BUILDINGS || state == GameState.GAME_PROGRESS || state == GameState.GAME_END) {
            angle =0;// PlanetControllerMenu.planetSprite.getRotation() + 135;
            sunSprite.setRotation(angle);
            sunSprite.setPosition(SCREEN_WIDTH / 2 - sunSprite.getWidth() / 2 + (float) Math.cos(Math.toRadians(angle)) * 2800, SCREEN_HEIGHT / 2 - sunSprite.getHeight() / 2 + (float) Math.sin(Math.toRadians(angle)) * 2800);
            sunSprite.draw(batch);
        }

        batch.end();
    }

    /**
     * Draws the overground.
     *
     * @param batch The batch to draw the overground on.
     */
    private static void drawOverground(SpriteBatch batch) {

        batch.begin();

        batch.end();
    }
}
