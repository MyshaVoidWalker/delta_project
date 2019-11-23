package net.mvw.delta.logic;


import net.mvw.indexio.IndexIO;


public class SaveManager {


    public static IndexIO saver = new IndexIO();

    public static void save() {
        Global.printInfo("Saving game ...", "SaveManager");

//        saver.loadVariable("needsTutorial", GameController.needToShowTutorial + "");
//        saver.loadVariable("masterSoundVolume", InputBox.masterSoundVolume + "");
//        saver.loadVariable("masterMusicVolume", InputBox.masterMusicVolume + "");
//
//        //saver.loadVariable("userID", EnvironmentData.userID + "");
//        saver.loadVariable("population", EnvironmentData.population + "");
//        saver.loadVariable("maxPopulation", EnvironmentData.maxPopulation + "");
//        saver.loadVariable("temperature", EnvironmentData.temperature + "");
//        saver.loadVariable("ecology", EnvironmentData.ecology + "");
//
//        for(Map.Entry<String, Boolean> modState : GameRegistry.modStates.entrySet()) {
//            saver.loadVariable(modState.getKey() + "_state", modState.getValue() + "");
//        }
//
//
//        ProgressController.applyToNodes(ProgressController.progressTree.rootNode, (node) -> {
//            saver.loadVariable(node.data.id + "_available", node.data.available + "");
//            saver.loadVariable(node.data.id + "_unlocked", node.data.unlocked + "");
//        });
//
//        for (Map.Entry<String, Material> material : GameRegistry.materials.entrySet()) {
//            saver.loadVariable(material.getKey(), material.getValue().amount + "");
//        }
//
//        for (GameObjectUI gameObjectUI : GameController.gameObjectUIArrayList) {
//            if (gameObjectUI.factory != null) {
//                saver.loadVariable("sfobj_" + GameController.gameObjectUIArrayList.indexOf(gameObjectUI), gameObjectUI.factory.id);
//            } else {
//                saver.loadVariable("sfobj_" + GameController.gameObjectUIArrayList.indexOf(gameObjectUI), "-");
//            }
//        }
//
//        saver.loadVariable("inCatastrophe", CatastropheController.inCatastrophe + "");
//        if (CatastropheController.inCatastrophe) {
//            saver.loadVariable("currentCatastrophe", CatastropheController.currentCatastrophe.toString());
//            saver.loadVariable("remainedDuration", CatastropheController.duration - System.nanoTime() + CatastropheController.starttime + "");
//        }
//
//        for (String catastrophe : CatastropheController.catastrophes.keySet()) {
//            saver.loadVariable(catastrophe + "_h", CatastropheController.catastrophes.get(catastrophe).happened + "");
//            saver.loadVariable(catastrophe + "_l", CatastropheController.catastrophes.get(catastrophe).level + "");
//        }

        saver.writeDataChunkTo("saves/save.gamma", "::");
    }

    /**
     * Loads the game.
     */
    public static void load() {
        if (saver.loadDataFrom("saves/save.gamma", "::")) {
//            GameController.needToShowTutorial = Boolean.parseBoolean(saver.loadVariable("needsTutorial"));
//            InputBox.masterSoundVolume = Float.parseFloat(saver.loadVariable("masterSoundVolume"));
//            InputBox.masterMusicVolume = Float.parseFloat(saver.loadVariable("masterMusicVolume"));
//
//
//            EnvironmentData.population = Integer.parseInt(saver.loadVariable("population"));
//            EnvironmentData.maxPopulation = Integer.parseInt(saver.loadVariable("maxPopulation"));
//            EnvironmentData.temperature = Float.parseFloat(saver.loadVariable("temperature"));
//            EnvironmentData.ecology = Float.parseFloat(saver.loadVariable("ecology"));
//
//            GameRegistry.modStates.replaceAll((key, value) -> Boolean.parseBoolean(saver.loadVariable(key + "_state")));
//
//            ProgressController.applyToNodes(ProgressController.progressTree.rootNode, (node) -> {
//                node.data.available = Boolean.parseBoolean(saver.loadVariable(node.data.id + "_available"));
//                node.data.unlocked = Boolean.parseBoolean(saver.loadVariable(node.data.id + "_unlocked"));
//            });
//
//            for (Map.Entry<String, Material> material : GameRegistry.materials.entrySet()) {
//                material.getValue().amount = Integer.parseInt(saver.loadVariable(material.getKey()));
//            }
//
//
//            for (int i = 0; i < 36; i++) {
//                String factoryID = saver.loadVariable("sfobj_" + i);
//                if (!factoryID.equals("-")) {
//                    GameController.gameObjectUIArrayList.get(i).setFactory(GameRegistry.factories.get(factoryID));
//                }
//            }
//
//            CatastropheController.inCatastrophe = Boolean.parseBoolean(saver.loadVariable("inCatastrophe"));
//            if (CatastropheController.inCatastrophe) {
//                CatastropheController.currentCatastrophe = Catastrophe.getBranchFromString(saver.loadVariable("currentCatastrophe"));
//                CatastropheController.trigger();
//                CatastropheController.starttime = System.nanoTime();
//                CatastropheController.duration = Long.parseLong(saver.loadVariable("remainedDuration"));
//            }
//
//            for (String catastrophe : CatastropheController.catastrophes.keySet()) {
//                CatastropheController.catastrophes.get(catastrophe).happened = Boolean.parseBoolean(saver.loadVariable(catastrophe + "_h"));
//                CatastropheController.catastrophes.get(catastrophe).level = Integer.parseInt(saver.loadVariable(catastrophe + "_l"));
//            }
        } else {
            save();
        }
    }

    @Deprecated
    public static void loadUserID()  {
//        saver.loadDataFrom("saves/save.gamma", "::");
//        if (saver.loadVariable("userID") != null&&saver.loadVariable("userID") != "")
//            EnvironmentData.userID = saver.loadVariable("userID");
//        else
//            try{
//                EnvironmentData.userID = Global.generateUserID();
//            }catch (Exception e){
//              e.printStackTrace();
//            }
    }
}
