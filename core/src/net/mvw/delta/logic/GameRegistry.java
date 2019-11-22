package net.mvw.delta.logic;

/**
 * Contains methods and fields used for registering and storing game objects.
 *
 * @author UnexomWid
 */
public class GameRegistry {

//    /**
//     * The list of mods.
//     */
//    public static HashMap<String, Mod> mods = new HashMap<String, Mod>();
//
//    /**
//     * The list of mod factories.
//     */
//    public static HashMap<String, ArrayList<Factory>> modFactories = new HashMap<String, ArrayList<Factory>>();
//
//    /**
//     * The list of mod materials.
//     */
//    public static HashMap<String, ArrayList<Material>> modMaterials = new HashMap<String, ArrayList<Material>>();
//
//    /**
//     * The list of mod states.
//     */
//    public static HashMap<String, Boolean> modStates = new HashMap<String, Boolean>();
//
//    /**
//     * The list of factories.
//     */
//    public static HashMap<String, Factory> factories = new HashMap<String, Factory>();
//
//    /**
//     * The list of materials.
//     */
//    public static HashMap<String, Material> materials = new HashMap<String, Material>();

//    /**
//     * Registers a mod.
//     *
//     * @param mod The mod to register.
//     *
//     * @return True, if the mod was registered. False if the mod ID already exists.
//     */
//    public static boolean registerMod(Mod mod) {
//        if(!mods.containsKey(mod.id)) {
//            mods.put(mod.id, mod);
//            modStates.put(mod.id, false);
//            printDebug("Registered mod with ID '" + mod.id + "'");
//            return true;
//        } else {
//            printError("Mod ID '" + mod.id + "' already exists. The culprit object is as follows:\n" + Global.JSON.prettyPrint(mod));
//            return false;
//        }
//    }
//
//    /**
//     * Activates a mod.
//     *
//     * @param modID The ID of the mod to activate.
//     */
//    public static void activateMod(String modID) {
//        if(modStates.containsKey(modID)) {
//                modStates.put(modID, true);
//
//                for(Factory factory : modFactories.get(modID)) {
//                    registerFactory(factory);
//                }
//
//                if(modMaterials.get(modID) != null) {
//                    for (Material material : modMaterials.get(modID)) {
//                        registerMaterial(material);
//                    }
//                }
//        }
//        //ProgressController.regenerate();
//    }
//
//    /**
//     * Registers a factory object.
//     *
//     * @param factory The factory to register.
//     *
//     * @return True, if the factory was registered. False if the factory ID already exists.
//     */
//    public static boolean registerFactory(Factory factory) {
//        if(!factories.containsKey(factory.id)) {
//            factories.put(factory.id, factory);
//            printDebug("Registered factory with ID '" + factory.id + "'");
//            return true;
//        } else {
//            printError("Factory ID '" + factory.id + "' already exists. The culprit object is as follows:\n" + Global.JSON.prettyPrint(factory));
//            return false;
//        }
//    }
//
//    /**
//     * Registers a material object.
//     *
//     * @param material The material to register.
//     *
//     * @return True, if the material was registered. False, if the material ID already exists.
//     */
//    public static boolean registerMaterial(Material material) {
//        if(!materials.containsKey(material.id)) {
//            materials.put(material.id, material);
//            printDebug("Registered material with ID '" + material.id + "'");
//            return true;
//        } else {
//            printError("Material ID '" + material.id + "' already exists. The culprit object is as follows:\n" + Global.JSON.prettyPrint(material));
//            return false;
//        }
//    }
//
//    /**
//     * Prints a Verbose message.
//     *
//     * @param message The message.
//     */
//    public static void printVerbose(String message) {
//        Global.printVerbose(message, "GameRegistry");
//    }
//
//    /**
//     * Prints a Debug message.
//     *
//     * @param message The message.
//     */
//    public static void printDebug(String message) {
//        Global.printDebug(message, "GameRegistry");
//    }
//
//    /**
//     * Prints an Info message.
//     *
//     * @param message The message.
//     */
//    public static void printInfo(String message) {
//        Global.printInfo(message, "GameRegistry");
//    }
//
//    /**
//     * Prints a Warning message.
//     *
//     * @param message The message.
//     */
//    public static void printWarning(String message) {
//        Global.printWarning(message, "GameRegistry");
//    }
//
//    /**
//     * Prints an Error message.
//     *
//     * @param message The message.
//     */
//    public static void printError(String message) {
//        Global.printError(message, "GameRegistry");
//    }
}
