package com.dewdropgames.chipmunk2djna;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Created by etsvigun on 11/16/15.
 */
public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Paper";
        config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
        config.fullscreen = true;
        config.forceExit = true;

        new LwjglApplication(new Demo(), config);
    }

}
