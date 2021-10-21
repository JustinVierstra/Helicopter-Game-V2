package org.csc133.a2.gameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;

import java.util.Random;

import static com.codename1.ui.CN.*;

public class Fire extends Fixed{
    private int greatestSize;
    private int size;

    public Fire(Dimension worldSize){
        this.worldSize = worldSize;
        this.color = ColorUtil.MAGENTA;
        size = new Random().nextInt(4) + 8;
        greatestSize = size;
        this.dimension = new Dimension(size, size);
        location = new Point2D(0,0);
    }

    // Checks to see if the helicopter is currently colliding with fire
    //
    public boolean isCollidingWith(Helicopter helicopter){
        return super.isCollidingWith(helicopter);
    }

    public void grow(){
        if (dimension.getWidth() < 25) {
            dimension.setWidth(dimension.getWidth() + 4);
            dimension.setHeight(dimension.getHeight() + 4);
            size += 4;

            location.setX(location.getX() - 2);
            location.setY(location.getY() - 2);
        }
        if(size > greatestSize){
            greatestSize = size;
        }
    }

    public void shrink(int water){
        dimension.setWidth(dimension.getWidth() -10);
        dimension.setHeight(dimension.getHeight() -10);

        location.setX(location.getX()+5);
        location.setY(location.getY()+5);
    }

    public int getSize(){
        return dimension.getWidth();
    }

    public int getGreatestSize(){
        return greatestSize;
    }

    public double currentSize() {
        double x = (Math.pow(dimension.getWidth()/2, 2) * Math.PI);
        return x;
    }

    @Override
    public void draw(Graphics g, com.codename1.ui.geom.Point containerOrigin) {
        g.setColor(color);
        int x = containerOrigin.getX() + (int)location.getX() ;
        int y = containerOrigin.getY() + (int)location.getY();
        int w = dimension.getWidth();
        int h = dimension.getHeight();

        g.fillArc(x,y,w,h,0,360);

        g.setFont(Font.createSystemFont
                (FACE_MONOSPACE, STYLE_BOLD, SIZE_MEDIUM));
        g.drawString("" + w, x + w, y + h);
    }
}

