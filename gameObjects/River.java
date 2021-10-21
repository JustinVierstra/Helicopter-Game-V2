package org.csc133.a2.gameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class River extends Fixed{
    int x;
    int y;
    int w;
    int h;

    public River(Dimension worldSize) {
        this.worldSize=worldSize;
        this.color = ColorUtil.BLUE;
        this.location = new Point2D(0, worldSize.getHeight()/5);
        this.dimension = new Dimension(worldSize.getWidth(),
                worldSize.getHeight()/6);
        //System.err.println(worldSize);
    }

    public boolean isCollidingWith(Helicopter helicopter){
        return super.isCollidingWith(helicopter);
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        g.setColor(color);
        x = containerOrigin.getX() + (int)location.getX();
        y = containerOrigin.getY() + (int)location.getY();
        w = dimension.getWidth();
        h = dimension.getHeight();
        g.drawRect(x,y,w,h);
    }
}

