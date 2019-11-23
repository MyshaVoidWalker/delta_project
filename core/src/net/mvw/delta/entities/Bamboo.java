package net.mvw.delta.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static net.mvw.delta.logic.Global.getDelta;

public class Bamboo extends Sprite {

    private float base_angle, target_angle, base_zoom, target_zoom;

    private Panda panda = null;

    public Bamboo(Texture texture,float base_angle,float target_angle, float base_zoom , float target_zoom, float start_Zoom) {
        super(texture);
        this.setOrigin(0,0);
        this.base_angle = base_angle;
        this.target_angle = target_angle;
        this.base_zoom = base_zoom;
        this.target_zoom = target_zoom;
        this.setScale(start_Zoom);

    }

    public void getToTargetAngle(float speed){
        this.setRotation(this.getRotation() + (target_angle - this.getRotation()) /speed * getDelta());
        if(this.getRotation()/this.target_angle>0.8){
            this.target_angle = -this.target_angle;
        }
    }

    public void getToZoom(float speed){
        this.setScale(this.getScaleX() + (target_zoom - this.getScaleX()) /speed * getDelta());
    }

    public void resetTargetToBase(float speed){
        this.target_zoom = this.base_zoom;
        this.target_angle = this.base_angle;
    }

    public float getBase_angle() {
        return base_angle;
    }

    public void setBase_angle(float base_angle) {
        this.base_angle = base_angle;
    }

    public float getTarget_angle() {
        return target_angle;
    }

    public void setTarget_angle(float target_angle) {
        this.target_angle = target_angle;
    }

    public float getBase_zoom() {
        return base_zoom;
    }

    public void setBase_zoom(float base_zoom) {
        this.base_zoom = base_zoom;
    }

    public float getTarget_zoom() {
        return target_zoom;
    }

    public void setTarget_zoom(float target_zoom) {
        this.target_zoom = target_zoom;
    }

    public Panda getPanda() {
        return panda;
    }

    public void setPanda(Panda panda) {
        this.panda = panda;
    }
}
