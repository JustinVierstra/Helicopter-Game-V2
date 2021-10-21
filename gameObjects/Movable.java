package org.csc133.a2.gameObjects;

import com.codename1.ui.geom.Point2D;

public abstract class Movable extends GameObject{
    private int speed;
    private double heading;

    public Movable(){
        speed = 0;
        heading = 270;
    }

    public void move(){
        if (speed > 0){
            location.setX(location.getX()
                    + (10*Math.cos(Math.toRadians(heading))));
            location.setY(location.getY() +
                    (10*Math.sin(Math.toRadians(heading))));
        }
    }

    // Adjust the speed of the helicopter based on the value of speedChange
    // passed in, speedChange can be -1 or 1
    //
    public void adjustSpeed(int speedChange){
        if (speed + speedChange <= 10 && speed + speedChange >= 0){
            speed += speedChange;
        }
    }

    // Adjust the heading in either direction depending on what value
    // of turnAmount passed in, turnAmount can be -15 or 15
    //
    public void turn(int turnAmount){
        heading = heading + turnAmount;

    }

    // Calculates the direction of the heading line based off of the current
    // heading
    //
    double calculateHeading(){
        double headingRadians = Math.toRadians(heading);

        double Cos = (speed * Math.cos(headingRadians));
        double Sin = (speed * Math.sin(headingRadians));

        location.setX(location.getX() + speed * Cos);
        location.setY(location.getY() + speed * Sin);

        return headingRadians;
    }

    public int getHeading(){
        return (int)heading;
    }

    public int getSpeed(){
        return speed;
    }

    public Point2D getLocation(){
        return super.getLocation();
    }

    protected void setHeading(int heading) {
        this.heading = heading;
    }

    protected void setSpeed(int speed) {
        this.speed = speed;
    }
}
