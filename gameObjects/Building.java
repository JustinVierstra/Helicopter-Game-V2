package org.csc133.a2.gameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

import java.util.Random;

import static com.codename1.ui.CN.*;

public class Building extends GameObject{
private int value;
private int damage;
    public Building(int buildingObject, Dimension worldSize){
        this.color = ColorUtil.rgb(255,0,0);
        if(buildingObject == 0) {
            this.dimension = new Dimension(1200, 200);
            this.worldSize = worldSize;
            value = 33333;
            location = new Point2D(
                    worldSize.getWidth()/2 - dimension.getWidth()/2,
                    worldSize.getHeight()/2 - dimension.getHeight()/2
                            - worldSize.getHeight()/2.5);
        }else if(buildingObject == 1){
            this.dimension = new Dimension(200, 500);
            value = 33333;
            location = new Point2D(
                    worldSize.getWidth()/4 - dimension.getWidth(),
                    worldSize.getHeight()/2 - dimension.getHeight()*3/4
                            + worldSize.getHeight()/3);
        }else{
            this.dimension = new Dimension(200, 400);
            value = 33333;
            location = new Point2D(
                    worldSize.getWidth()*3/4 - dimension.getWidth()/2,
                    worldSize.getHeight()/2 - dimension.getHeight()/2
                            + worldSize.getHeight()/4);
        }
    }

    public void adjustValue(int loss){
        this.value = loss;
    }

    public int getBuildingArea() {
        return (dimension.getWidth() * dimension.getHeight());
    }

    public void setFireInBuilding(Fire fire){
        fire.setLocation(new Random().nextInt(
                this.dimension.getWidth() - 20) + this.location.getX(),
                (int) (new Random().nextInt(this.dimension.getHeight()
                        - 20) + this.location.getY()));
    }

    public void setDMG(int damage){
        this.damage = damage;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        g.setColor(color);
        int x = containerOrigin.getX() + (int)location.getX();
        int y = containerOrigin.getY() + (int)location.getY();
        int w = dimension.getWidth();
        int h = dimension.getHeight();

        g.drawRect(x,y,w,h,5);

        g.setFont(Font.createSystemFont
                (FACE_MONOSPACE, STYLE_BOLD, SIZE_MEDIUM));
        g.drawString("V  : $" + this.value,
                containerOrigin.getX() + (int) location.getX()
                        + dimension.getWidth(),
                containerOrigin.getY() + (int) (location.getY()
                        + dimension.getHeight() + 10));
        g.drawString("D  : " + this.damage + "%",
                containerOrigin.getX() + (int) location.getX()
                        + dimension.getWidth(),
                containerOrigin.getY() + (int) (location.getY()
                        + dimension.getHeight() + 40));
    }
}
