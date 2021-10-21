package org.csc133.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a2.GameWorld;

public class Right extends Command {
    private GameWorld gw;

    public Right(GameWorld gw){
        super("Right");
        this.gw=gw;
    }

    @Override
    public void actionPerformed(ActionEvent event){
        gw.right();
    }
}
