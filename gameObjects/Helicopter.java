package org.csc133.a2.gameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.codename1.ui.geom.Dimension;
import org.csc133.a2.Game;
import org.csc133.a2.interfaces.Steerable;

import static com.codename1.ui.CN.*;

public class Helicopter extends Movable implements Steerable {
    private int fuel;
    private int water;

    public Helicopter(Dimension worldSize){
        this.worldSize = worldSize;
        this.color = ColorUtil.YELLOW;
        this.dimension = new Dimension(100, 100);
        this.location = new Point2D(worldSize.getWidth()/2 - getHeliSize()/2,
                worldSize.getHeight()*5/6 - getHeliSize()/2);
        super.setHeading(270);
        super.setSpeed(0);
        fuel = 25000;
        water = 0;
    }

    // Returns the size of the helicopter scaled in relation to the screen
    //
    int getHeliSize(){
        return Math.min(worldSize.getWidth(), worldSize.getHeight())
                - (int)(0.97 * Math.min(worldSize.getWidth(),
                worldSize.getHeight()));
    }

    public void move(){
        super.move();
    }

    // Adjust the heading in either direction depending on what value
    // of turnAmount passed in, turnAmount can be -15 or 15
    //
    public void turn(int turnAmount){
        super.turn(turnAmount);
    }

    // Adjust the speed of the helicopter based on the value of speedChange
    // passed in, speedChange can be -1 or 1
    //
    public void adjustSpeed(int speedChange){
        super.adjustSpeed(speedChange);
    }

    // Calculates the direction of the heading line based off of the current
    // heading
    //
    public double calculateHeading(){
        return super.calculateHeading();
    }

    public void grabWaterFromRiver(){
        if(water<1000 && getSpeed() <=2){
            water += 100;
        }
    }

    public int getCurrentWaterInTank(){
        return water;
    }

    public void useWater(){
        if(water>0){
            water-=100;
        }
    }

    public void useFuel(){
        if(fuel>0){
            fuel -= (int) Math.pow(getSpeed(),2) + 5;
        }else{
            fuel = 0;
            if(Dialog.show("Game Over", "You Won!\nScore: "
                    + fuel + "\nPlay Again?", "Heck Yea!",
                    "Some Other Time")) {
                // user clicked yes
                new Game();
            } else {
                //If user clicked no
                Display.getInstance().exitApplication();
            }
        }
    }

    public int getFuel(){
        return fuel;
    }

    public Point2D getLocation(){
        return super.getLocation();
    }

    public int getSpeed(){
        return super.getSpeed();
    }

    public int getHeading(){
        return super.getHeading();
    }

    @Override
    public void draw(Graphics g, com.codename1.ui.geom.Point containerOrigin) {
        g.setColor(color);
        int x = containerOrigin.getX() + (int)location.getX() ;
        int y = containerOrigin.getY() + (int)location.getY();
        int w = getHeliSize();
        int h = getHeliSize();

        g.fillArc(x,y,w,h,0,360);

        int x1Line = containerOrigin.getX() + (int)location.getX()
                + getHeliSize()/2;
        int y1Line = containerOrigin.getY() + (int)location.getY()
                + getHeliSize()/2;
        int x2Line = containerOrigin.getX() + (int)location.getX()
                + getHeliSize()/2 + (int)(50*Math.cos(calculateHeading()));
        int y2Line = containerOrigin.getY() +(int)location.getY()
                + getHeliSize()/2 + (int)(50*Math.sin(calculateHeading()));

        g.drawLine(x1Line, y1Line, x2Line, y2Line);

        g.setFont(Font.createSystemFont
                (FACE_MONOSPACE, STYLE_BOLD, SIZE_MEDIUM));
        g.drawString("F   : " + fuel,
                containerOrigin.getX() + (int) location.getX()
                        + getHeliSize(),
                containerOrigin.getY() + (int) (location.getY()
                        + getHeliSize()*1.3));
        g.drawString("W : " + water,
                containerOrigin.getX() + (int) location.getX()
                        + getHeliSize(),
                containerOrigin.getY() + (int) (location.getY()
                        + getHeliSize()*2));
    }

    @Override
    public void steer(int steer) {
        if (steer < 0){
            this.setHeading(steer);
        }
    }
}

