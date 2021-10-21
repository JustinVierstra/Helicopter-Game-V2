package org.csc133.a2.gameObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class FireCollection extends GameObjectCollection<Fire>{

    public FireCollection(){
        super();
        this.color = ColorUtil.MAGENTA;
    }

    // a collection of fire
    //
    @Override
    public void draw(Graphics g, Point containerOrigin) {
        for(Fire fires : getGameObjects()){
            fires.draw(g, containerOrigin);
        }
    }
}
