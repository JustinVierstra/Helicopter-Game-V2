package org.csc133.a2.gameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;

public class Helipad extends Fixed{

    public Helipad(Dimension worldSize){
        this.worldSize = worldSize;
        this.color = ColorUtil.GRAY;
        this.dimension = new Dimension(200,200);
        this.location = new Point2D(worldSize.getWidth()/2
                - dimension.getWidth()/2,
                worldSize.getHeight()/2 - dimension.getHeight()/2
                        + worldSize.getHeight()/3);
    }

    public boolean isCollidingWith(Helicopter helicopter){
        return super.isCollidingWith(helicopter);
    }

    @Override
    public void draw(Graphics g, com.codename1.ui.geom.Point containerOrigin) {
        g.setColor(color);

        int x = containerOrigin.getX() + (int)location.getX();
        int y = containerOrigin.getY() + (int)location.getY();
        int w = dimension.getWidth();
        int h = dimension.getHeight();

        g.drawArc(x + w/8, y + h/8, w*3/4, h*3/4,
                0, 360);
        g.drawRect(x, y, w, h,5);
    }
}