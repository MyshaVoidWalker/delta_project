package net.mvw.delta.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import net.mvw.delta.ProjectDelta;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1440 / 4;
        config.height = 2560 / 4;
        //config.fullscreen = true;
        //config.addIcon("icons/icon_128.png", Files.FileType.Internal);
        //config.addIcon("icons/icon_32.png", Files.FileType.Internal);
        //Config.addIcon("icons/icon_16.png", Files.FileType.Internal);
        new LwjglApplication(new ProjectDelta(), config);
    }
}
