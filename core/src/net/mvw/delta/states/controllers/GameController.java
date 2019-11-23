package net.mvw.delta.states.controllers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.mvw.delta.input.InputBox;
import net.mvw.delta.input.Resources;

import java.util.ArrayList;

import static net.mvw.delta.ProjectDelta.SCREEN_HEIGHT;
import static net.mvw.delta.ProjectDelta.SCREEN_WIDTH;
import static net.mvw.delta.logic.Global.getDelta;

public class GameController {

    public static ArrayList<Sprite> bamboo_forest = new ArrayList<>();


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

        for (Sprite sprite:bamboo_forest){
            sprite.draw(batch);
        }

        batch.draw(Resources.banner,0,SCREEN_HEIGHT+InputBox.yScreenOffset-256,SCREEN_WIDTH,256);

        batch.end();

    }
private static long lastBamboo = 0, height_of_sticks = -128;
    public static void update() {

//        if(bamboo_forest.size()>=100 && System.nanoTime() - lastBamboo>10e7){
//            bamboo_forest.remove(0);
//            lastBamboo = System.nanoTime();
//        }

        if(bamboo_forest.size()<600){
            Sprite s = new Sprite(Resources.bamboos.get((int)(Math.random()*3)));
            s.setPosition((float)(Math.random()*SCREEN_WIDTH),-InputBox.yScreenOffset+height_of_sticks);
            s.setRotation(-25+50*(float)(Math.random()));
            bamboo_forest.add(0,s);
            height_of_sticks+=2;
        }

        for (Sprite sprite:bamboo_forest){
            sprite.setScale(sprite.getScaleX() + (4 - sprite.getScaleX()) / 6 * getDelta());
        }






    }


}
