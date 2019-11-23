package net.mvw.delta.logic;


import net.mvw.delta.input.InputBox;
import net.mvw.delta.states.controllers.GameController;
import net.mvw.indexio.IndexIO;


public class SaveManager {


    public static IndexIO saver = new IndexIO();

    public static void save() {
        Global.printInfo("Saving game ...", "SaveManager");

        saver.loadVariable("needsTutorial", GameController.needToShowTutorial + "");
        saver.loadVariable("masterSoundVolume", InputBox.masterSoundVolume + "");
        saver.loadVariable("masterMusicVolume", InputBox.masterMusicVolume + "");
        saver.loadVariable("player_level",GameController.level+"");

        saver.writeDataChunkTo("saves/save.delta", "::");
    }

    /**
     * Loads the game.
     */
    public static void load() {
        if (saver.loadDataFrom("saves/save.delta", "::")) {
            GameController.needToShowTutorial = Boolean.parseBoolean(saver.loadVariable("needsTutorial"));
            InputBox.masterSoundVolume = Float.parseFloat(saver.loadVariable("masterSoundVolume"));
            InputBox.masterMusicVolume = Float.parseFloat(saver.loadVariable("masterMusicVolume"));
            System.out.println("Level: " +saver.loadVariable("player_level"));
            GameController.level = Integer.parseInt(saver.loadVariable("player_level"));
        } else {
            save();
        }
    }


}
