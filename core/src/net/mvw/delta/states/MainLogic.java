package net.mvw.delta.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.mvw.delta.input.InputBox;
import net.mvw.delta.logic.Global;
import net.mvw.delta.states.controllers.IntroController;

import static net.mvw.delta.ProjectDelta.SCREEN_HEIGHT;
import static net.mvw.delta.ProjectDelta.SCREEN_WIDTH;
import static net.mvw.delta.ProjectDelta.batch;
import static net.mvw.delta.ProjectDelta.camera;
import static net.mvw.delta.ProjectDelta.viewportHeight;
import static net.mvw.delta.ProjectDelta.viewportWidth;
import static net.mvw.delta.input.InputBox.continualInputs;
import static net.mvw.delta.input.Resources.background;
import static net.mvw.delta.input.Resources.side;
import static net.mvw.delta.logic.Global.state;
import static net.mvw.delta.logic.Global.timePassed;


public class MainLogic {


    public static float cameraTargetX = SCREEN_WIDTH / 2;
    public static float cameraTargetY = SCREEN_HEIGHT / 2;
    public static float cameraTargetZoom = 1;
    public static float cameraTargetSpeed = 12;
    public static float cameraTargetZoomSpeed = 12;

    public static void setCameraTargetPosition(float targetX, float targetY, float targetSpeed) {
        cameraTargetX = targetX;
        cameraTargetY = targetY;
        cameraTargetSpeed = targetSpeed;
    }

    public static void setCameraTargetZoom(float targetZoom, float targetSpeed) {
        cameraTargetZoom = targetZoom;
        cameraTargetZoomSpeed = targetSpeed;
    }

    public static void init(SpriteBatch batch) {

        IntroController.init();
        //MenuController.init();
        //GameController.init();



        //GameController.update();

    }


    public static void updateGame() {
        continualInputs();
        handleVariables();
        updateCameraPosition();


//        if (state != GameState.INTRO && state != GameState.GAME_PROGRESS) MenuController.update();
//        if (state == GameState.GAME || state == GameState.GAME_PROGRESS || state == GameState.GAME_BUILDINGS)
//            GameController.update();
    }


    private static void handleVariables() {
        timePassed += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camera.combined);
        camera.update();
    }

    private static void updateCameraPosition() {
        //setCameraTargetPosition(540, 960, 8);
        camera.position.x = camera.position.x + (cameraTargetX - camera.position.x) / cameraTargetSpeed;
        camera.position.y = camera.position.y + (cameraTargetY - camera.position.y) / cameraTargetSpeed;
        camera.zoom = camera.zoom + (cameraTargetZoom - camera.zoom) / cameraTargetZoomSpeed;
    }

    public static void renderGame(SpriteBatch batch) {
        drawBackground(batch);

        switch (state) {
            case INTRO:
                IntroController.draw(batch);
                break;

            case MENU_MAIN:
            case MENU_OPTIONS:
            case MENU_EXTRAS:

            case MENU_ACHIEVEMENTS:
            case MENU_CREDITS:
                //MenuController.draw(batch);
                break;

            case GAME:
            case GAME_PROGRESS:
            case GAME_END:
                //GameController.draw(batch);
                break;


        }

        drawOverground(batch);
    }


    private static float angle;


    private static float side_size = 256;
    private static void drawBackground(SpriteBatch batch) {
        batch.begin();

        batch.draw(background, -InputBox.xScreenOffset, -InputBox.yScreenOffset, viewportWidth, viewportHeight);
        if (Global.state != Global.GameState.INTRO) {
            batch.draw(side, -side_size / 2, -InputBox.yScreenOffset, side_size, viewportHeight);
            batch.draw(side, SCREEN_WIDTH - side_size / 2, -InputBox.yScreenOffset, side_size, viewportHeight);
        }

        batch.end();
    }


    private static void drawOverground(SpriteBatch batch) {

        batch.begin();

        batch.end();
    }
}
