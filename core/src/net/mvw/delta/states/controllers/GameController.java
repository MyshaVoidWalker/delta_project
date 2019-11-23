package net.mvw.delta.states.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.mvw.delta.entities.Bamboo;
import net.mvw.delta.entities.Panda;
import net.mvw.delta.input.InputBox;
import net.mvw.delta.input.Resources;

import java.util.ArrayList;
import java.util.Iterator;

import static net.mvw.delta.ProjectDelta.SCREEN_HEIGHT;
import static net.mvw.delta.ProjectDelta.SCREEN_WIDTH;
import static net.mvw.delta.ProjectDelta.gamefont;
import static net.mvw.delta.logic.Global.*;

public class GameController {

    public static ArrayList<Bamboo> bamboo_forest = new ArrayList<>();
    public static ArrayList<Panda> pandas = new ArrayList<>();
    public static ArrayList<Panda> traitors = new ArrayList<>();


    public static boolean needToShowTutorial = true;
    private static boolean isPanda = false;
    private static long lastPanda = System.nanoTime(),level = 0;

    private static float score = 0,pandas_score = 0;


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

        for (Panda traitor:traitors){
            traitor.draw(batch);
        }


        batch.draw(Resources.banner, 0, SCREEN_HEIGHT + InputBox.yScreenOffset - 256, SCREEN_WIDTH, 256);
        layout.setText(gamefont,"Score: "+pandas_score);

        gamefont.draw(batch,"Score: "+pandas_score,SCREEN_WIDTH/2-layout.width/2,SCREEN_HEIGHT + InputBox.yScreenOffset - 128+layout.height/2);
        batch.end();

    }

    private static long lastBamboo = 0, height_of_sticks = -128;

    public static void update() {

        if (bamboo_forest.size() > 100 && pandas.size() < 10 && System.nanoTime() - lastPanda > 10e8) {
            int stick = (int) (Math.random() * bamboo_forest.size());

            Bamboo bamboo = bamboo_forest.get(stick);
            bamboo.setPanda(new Panda(Resources.panda_eating));
            bamboo.getPanda().setBamboo(bamboo);
            lastPanda = System.nanoTime();
            pandas.add(bamboo.getPanda());
        }

        if (bamboo_forest.size() < 400) {
            Bamboo b = new Bamboo(Resources.bamboos.get((int) (Math.random() * 3)), -20 + 40 * (float) (Math.random()),
                    -20 + 40 * (float) (Math.random()), 2f + (float) Math.random() * 2f, 2f + (float) Math.random() * 2f, 0);
            b.setPosition(64 + (float) (Math.random() * SCREEN_WIDTH - 128), -InputBox.yScreenOffset + height_of_sticks);
            bamboo_forest.add(0, b);
            height_of_sticks += 2;
        }


        Iterator<Panda> pandaIterator = pandas.iterator();
        while (pandaIterator.hasNext()) {
            Panda panda = pandaIterator.next();
            if (panda.toBeRemoved) {
                pandas_score+=panda.getScaleX();
                Panda traitor = new Panda(Resources.panda_grumpy);
                traitor.setPosition(panda.getX(),panda.getY());
                traitor.setScale(panda.getScaleX());
                traitors.add(traitor);
                pandaIterator.remove();
            }

        }

        for (Panda panda:traitors){
            panda.setPosition(panda.getX(),panda.getY() - 200*getDelta());
            panda.setScale(panda.getScaleX()*0.9f);

        }

        Iterator<Panda> traitorIterator = traitors.iterator();
        while (pandaIterator.hasNext()) {
            Panda panda = traitorIterator.next();
            if (panda.getScaleX()<0.1) {
                traitorIterator.remove();
            }

        }

        for (Bamboo sprite : bamboo_forest) {
            if (!pandas.contains(sprite.getPanda())) {
                sprite.setPanda(null);
            }

            sprite.getToZoom(1);
            sprite.getToTargetAngle(4);

            if (sprite.getPanda() != null) {
                sprite.getPanda().setPosition(sprite.getX() - (float) Math.sin(Math.toRadians(sprite.getRotation())) * (sprite.getHeight() - 32) * sprite.getScaleX(),
                        sprite.getY() + (float) Math.cos(Math.toRadians(sprite.getRotation())) * (sprite.getHeight() - 32) * sprite.getScaleY());
                sprite.getPanda().setRotation(sprite.getRotation());
                sprite.getPanda().setScale(sprite.getScaleX() / 4);

            }

        }


    }


}
