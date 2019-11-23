package net.mvw.delta.states.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.mvw.delta.entities.Bamboo;
import net.mvw.delta.entities.Panda;
import net.mvw.delta.input.InputBox;
import net.mvw.delta.input.Resources;

import java.util.ArrayList;

import static net.mvw.delta.ProjectDelta.SCREEN_HEIGHT;
import static net.mvw.delta.ProjectDelta.SCREEN_WIDTH;

public class GameController {

    public static ArrayList<Bamboo> bamboo_forest = new ArrayList<>();

    public static ArrayList<Panda> pandas = new ArrayList<>();


    public static boolean needToShowTutorial = true;
    private static boolean isPanda = false;
    private static long lastPanda = System.nanoTime();

    public static void init() {
        bamboo_forest.clear();
        if (needToShowTutorial) {

        }
    }


    public static void draw(SpriteBatch batch) {

        batch.begin();

        for (Bamboo sprite : bamboo_forest) {
            if (sprite.getPanda() != null) {
                sprite.getPanda().draw(batch);
            }
            sprite.draw(batch);
        }


        batch.draw(Resources.banner, 0, SCREEN_HEIGHT + InputBox.yScreenOffset - 256, SCREEN_WIDTH, 256);

        batch.end();

    }

    private static long lastBamboo = 0, height_of_sticks = -128;

    public static void update() {

//        if (bamboo_forest.size() > 300 && pandas.size() < 3 && System.nanoTime() - lastPanda > 10e7) {
//            int stick = (int) (Math.random() * bamboo_forest.size());
//
//            Bamboo bamboo = bamboo_forest.get(stick);
//            bamboo.setPanda(new Panda(Resources.panda_eating));
//            lastPanda = System.nanoTime();
//            pandas.add(bamboo.getPanda());
//        }

        if (bamboo_forest.size() < 500) {
            Bamboo b = new Bamboo(Resources.bamboos.get((int) (Math.random() * 3)), -25 + 50 * (float) (Math.random()),
                    -25 + 50 * (float) (Math.random()), 2f + (float) Math.random() * 2f, 2f + (float) Math.random() * 2f, 0);
            b.setPosition(64 + (float) (Math.random() * SCREEN_WIDTH - 128), -InputBox.yScreenOffset + height_of_sticks);
            bamboo_forest.add(0, b);
            height_of_sticks += 2;
        }

        for (Bamboo sprite : bamboo_forest) {

            if (Math.random() > 0.8d) {
                float new_angle = sprite.getTarget_angle() + (-1.5f + 3f * (float) Math.random());
                while (new_angle > 22 || new_angle < -22) {
                    new_angle = sprite.getTarget_angle() + (-1.5f + 3f * (float) Math.random());
                }

                sprite.setTarget_angle(new_angle);
            }


            sprite.getToZoom(1);
            sprite.getToTargetAngle(4);

//            if (sprite.getPanda() != null) {
//                sprite.getPanda().setPosition(sprite.getX()-(float) Math.sin(Math.toRadians(sprite.getRotation()))*(sprite.getHeight()-32)*sprite.getScaleX(),
//                        sprite.getY()+(float) Math.cos(Math.toRadians(sprite.getRotation()))*(sprite.getHeight()-32)*sprite.getScaleY());
//                sprite.getPanda().setRotation(sprite.getRotation());
//                sprite.getPanda().setScale(sprite.getScaleX()/4);
//
//            }

        }


    }


}
