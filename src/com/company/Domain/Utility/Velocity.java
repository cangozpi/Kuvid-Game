package com.company.Domain.Utility;

public class Velocity {

    private int angle;
    private double speed;

    public Velocity(int angle, double speed) {
        this.angle = angle;
        this.speed = speed;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
