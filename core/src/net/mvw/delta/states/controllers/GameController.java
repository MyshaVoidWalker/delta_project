package net.mvw.delta.states.controllers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.mvw.delta.entities.Bamboo;
import net.mvw.delta.input.InputBox;
import net.mvw.delta.input.Resources;

import java.util.ArrayList;

import static net.mvw.delta.ProjectDelta.SCREEN_HEIGHT;
import static net.mvw.delta.ProjectDelta.SCREEN_WIDTH;

public class GameController {

    public static ArrayList<Bamboo> bamboo_forest = new ArrayList<>();


    public static float zoomFactor = 1;
    public static float hookX = 0;
    public static float hookY = 0;
    public static boolean isObjectSelected = false;
    public static boolean needToShowTutorial = true;


    public static void init() {

        if (needToShowTutorial) {

        }

    }


    public static void draw(SpriteBatch batch) {

        batch.begin();

//        if (DEBUG_MODE ) {
//            batch.draw(Resources.vertex, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
//            batch.setColor(Color.RED);
//            batch.draw(Resources.vertex, camera.position.x - viewportWidth / 2 * camera.zoom, camera.position.y - viewportHeight / 2 * camera.zoom, viewportWidth * camera.zoom, viewportHeight * camera.zoom);
//            batch.setColor(Color.WHITE);
//        }

        for (Sprite sprite : bamboo_forest) {
            sprite.draw(batch);
        }

        batch.draw(Resources.banner, 0, SCREEN_HEIGHT + InputBox.yScreenOffset - 256, SCREEN_WIDTH, 256);

        batch.end();

    }

    private static long lastBamboo = 0, height_of_sticks = -128;

    public static void update() {

//        if(bamboo_forest.size()>=100 && System.nanoTime() - lastBamboo>10e7){
//            bamboo_forest.remove(0);
//            lastBamboo = System.nanoTime();
//        }

        if (bamboo_forest.size() < 500) {
            Bamboo b = new Bamboo(Resources.bamboos.get((int) (Math.random() * 3)), -25 + 50 * (float) (Math.random()),
                    -25 + 50 * (float) (Math.random()), 2f + (float) Math.random() * 2f, 2f + (float) Math.random() * 2f, 0);
            b.setPosition((float) (Math.random() * SCREEN_WIDTH), -InputBox.yScreenOffset + height_of_sticks);
            bamboo_forest.add(0, b);
            height_of_sticks += 2;
        }

        for (Bamboo sprite : bamboo_forest) {

            if (Math.random()>0.8d){
                float new_angle = sprite.getTarget_angle() + (-1.5f+3f*(float)Math.random());
                while (new_angle>25||new_angle<-25){
                    new_angle = sprite.getTarget_angle() + (-1.5f+3f*(float)Math.random());

                }
                sprite.setTarget_angle(new_angle);
            }

            sprite.getToZoom(1);
            sprite.getToTargetAngle(4);
        }


    }


}
