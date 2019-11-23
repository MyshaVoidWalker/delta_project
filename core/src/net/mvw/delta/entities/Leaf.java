package net.mvw.delta.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import net.mvw.delta.input.InputBox;
import net.mvw.delta.logic.Global;

import static net.mvw.delta.ProjectDelta.*;

public class Leaf extends Sprite {

    private float angle, base_angle, angle_range;

    private boolean right = false;
    private float dir = -0.01f;
    private float speed = 80;

    public Leaf(Texture texture, float px, float py, boolean right) {
        super(texture);
        this.right = right;
        this.angle = -10-(40*(float)Math.random());
        this.setOrigin(0, 0);
        this.setPosition(px, py);
        this.setScale(1);
        this.setAlpha(0.5f);
        this.speed += 40 * (float) Math.random();
    }

    public void move() {

        this.setRotation(angle);
        if (this.right) {
            this.setPosition(this.getX() - (float) Math.cos(Math.toRadians(angle)) * Global.getDelta() * this.speed, this.getY() + (float) Math.sin(Math.toRadians(angle)) * Global.getDelta() * this.speed);
        } else {
            this.setPosition(this.getX() + (float) Math.cos(Math.toRadians(angle)) * Global.getDelta() * this.speed, this.getY() + (float) Math.sin(Math.toRadians(angle)) * Global.getDelta() * this.speed);
        }
        this.angle += dir;
        if (this.angle > -10 || this.angle < -50) {
            dir = dir * -1;
        }

        if (right) {
            if (this.getX() < InputBox.xScreenOffset || this.getY() < -InputBox.yScreenOffset-128) {
                this.setX(viewportWidth + 128 + (float) Math.random() * SCREEN_WIDTH);
                this.setY((float) Math.random() * viewportHeight * 3 / 2);
            }
        } else {
            if (this.getX() > viewportWidth || this.getY() < -InputBox.yScreenOffset-128) {
                this.setX(-InputBox.xScreenOffset - 128 - (float) Math.random() * SCREEN_WIDTH);
                this.setY((float) Math.random() * viewportHeight * 3 / 2);
            }
        }

    }

}
