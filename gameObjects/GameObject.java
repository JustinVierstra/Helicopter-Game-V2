package org.csc133.a2.gameObjects;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

public abstract class GameObject implements Drawable {
    int color;
    Point2D location;
    Dimension dimension;
    Dimension worldSize;

    public String toString(){
        return this.getClass().getSimpleName();
    }

    public Point2D getLocation(){
        return location;
    }

    protected void setLocation(double xLocation, double yLocation) {
        this.location = new Point2D(xLocation, yLocation);
    }
}
