package org.csc133.a2.gameObjects;

import com.codename1.location.Location;
import com.codename1.ui.geom.Point2D;

public abstract class Fixed extends GameObject{

    public Fixed(){
        super();
    }

    public boolean isCollidingWith(Helicopter helicopter){
        return helicopter.getLocation().getX() + helicopter.getHeliSize() > (int)location.getX() &&
                helicopter.getLocation().getX() < (int)location.getX() + dimension.getWidth() &&
                helicopter.getLocation().getY() + helicopter.getHeliSize()
                        > (int)location.getY() &&
                helicopter.getLocation().getY() < ((int)location.getY() + dimension.getHeight());
    }
}
