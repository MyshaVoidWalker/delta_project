package net.mvw.delta.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.function.Consumer;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * Contains global fields and methods.
 *
 * @author MyshaVoidWalker, UnexomWid
 */
public class Global {

    /**
     * Whether or not the game is in debug mode.
     */
    public static final boolean DEBUG_MODE = false;

    /**
     * The sound flag.
     */
    public static boolean soundToggle = true;
    public static boolean musicToggle = true;

    /**
     * Represents a game state.
     */
    public  enum GameState{
        INTRO, MENU_MAIN, MENU_OPTIONS,MENU_EXTRAS,MENU_MODS,MENU_ACHIEVEMENTS,MENU_CREDITS,GAME,GAME_BUILDINGS,GAME_PROGRESS, GAME_END, NEUTRAL
    }

    /**
     * The game state.
     */
    public static GameState state = GameState.INTRO;

    /**
     * The layout.
     */
    public static GlyphLayout layout = new GlyphLayout();

    /**
     * The amount of time that has passed.
     */
    public static float timePassed = 0;

    /**
     * An object used to work with JSON.
     */
    public static final Json JSON = new Json();

    /**
     * The global JSON settings.
     */
    public static final JsonValue.PrettyPrintSettings jsonSettings = new JsonValue.PrettyPrintSettings() {{ outputType = JsonWriter.OutputType.json; }};

    /**
     * Contains all logs.
     */
    public static StringBuilder logs = new StringBuilder();

    /**
     * The main DeltaConsole.
     */
    public static DeltaConsole console;

    /**
     * Prints a Verbose message.
     *
     * @param message The message.
     */
    public static void printVerbose(String message) {
        printVerbose(message, "VERBOSE");
    }

    /**
     * Prints a Debug message.
     *
     * @param message The message.
     */
    public static void printDebug(String message) {
        printDebug(message, "DEBUG");
    }

    /**
     * Prints an Info message.
     *
     * @param message The message.
     */
    public static void printInfo(String message) {
        printInfo(message, "INFO");
    }

    /**
     * Prints a Warning message.
     *
     * @param message The message.
     */
    public static void printWarning(String message) {
        printWarning(message, "WARNING");
    }

    /**
     * Prints an Error message.
     *
     * @param message The message.
     */
    public static void printError(String message) {
        printError(message, "ERROR");
    }

    /**
     * Prints a Verbose message.
     *
     * @param message The message.
     * @param caller The caller.
     */
    public static void printVerbose(String message, String caller) {
        try {
            logs.append("[");
            logs.append(new Timestamp(System.currentTimeMillis()).toString());
            logs.append("] [");
            logs.append(caller);
            logs.append("] ");
            logs.append(message);
            logs.append("\n");

            if (Global.DEBUG_MODE)
                Global.console.printVerbose(message, caller);
        } catch(Exception ex) {

        }
    }

    /**
     * Prints a Debug message.
     *
     * @param message The message.
     * @param caller The caller.
     */
    public static void printDebug(String message, String caller) {
        try {
            logs.append("[");
            logs.append(new Timestamp(System.currentTimeMillis()).toString());
            logs.append("] [");
            logs.append(caller);
            logs.append("] ");
            logs.append(message);
            logs.append("\n");

            if (Global.DEBUG_MODE)
                Global.console.printDebug(message, caller);
        } catch(Exception ex) {

        }
    }

    /**
     * Prints an Info message.
     *
     * @param message The message.
     * @param caller The caller.
     */
    public static void printInfo(String message, String caller) {
        try {
            logs.append("[");
            logs.append(new Timestamp(System.currentTimeMillis()).toString());
            logs.append("] [");
            logs.append(caller);
            logs.append("] ");
            logs.append(message);
            logs.append("\n");

            if (Global.DEBUG_MODE)
                Global.console.printInfo(message, caller);
        } catch(Exception ex) {

        }
    }

    /**
     * Prints a Warning message.
     *
     * @param message The message.
     * @param caller The caller.
     */
    public static void printWarning(String message, String caller) {
        try {
            logs.append("[");
            logs.append(new Timestamp(System.currentTimeMillis()).toString());
            logs.append("] [");
            logs.append(caller);
            logs.append("] ");
            logs.append(message);
            logs.append("\n");

            if (Global.DEBUG_MODE)
                Global.console.printWarning(message, caller);
        } catch(Exception ex) {

        }
    }

    /**
     * Prints an Error message.
     *
     * @param message The message.
     * @param caller The caller.
     */
    public static void printError(String message, String caller) {
        try {
            logs.append("[");
            logs.append(new Timestamp(System.currentTimeMillis()).toString());
            logs.append("] [");
            logs.append(caller);
            logs.append("] ");
            logs.append(message);
            logs.append("\n");

            if (Global.DEBUG_MODE)
                Global.console.printError(message, caller);
        } catch(Exception ex) {

        }
    }

    /**
     * Saves the logs in 'latest.log'.
     */
    public static void saveLogs() {
        try {
            File log = new File("." + File.separator + "latest.log");
            log.createNewFile();

            FileOutputStream fos = new FileOutputStream(log, false);
            fos.write(logs.toString().getBytes());
            fos.close();
        } catch(Exception ex) {

        }
    }

    /**
     * Converts the stacktrace of an exception to a string.
     *
     * @param ex The exception.
     *
     * @return The string.
     */
    public static String formatException(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString().replace("\n", "<br>");
    }

    /**
     * Applies an operation to all files in a directory that have a specified suffix.
     *
     * @param directory The directory.
     * @param suffix The suffix.
     * @param lambda The operation, as a Consumer object.
     */
    public static void processFiles(FileHandle directory, String suffix, Consumer<FileHandle> lambda) {
        FileHandle[] handles = directory.list();
        for(FileHandle handle : handles) {
            if (!handle.isDirectory()) {
                try {
                    lambda.accept(handle);
                } catch(Exception ex) {

                }
            }
        }
        for (FileHandle handle : handles) {
            if (handle.isDirectory()) {
                try {
                    processFiles(handle, suffix, lambda);
                } catch(Exception ex) {

                }
            }
        }
    }

    /**
     * Computes the MD5 hash of a byte array.
     *
     * @param data The byte array.
     *
     * @return The MD5 hash of the byte array.
     */
    public static String md5(byte[] data) throws NoSuchAlgorithmException {
        return toHex(RawMD5(data));
    }

    /**
     * Converts a byte array to a hex string.
     *
     * @param data The byte array.
     *
     * @return The hex string.
     */
    public static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data)
            sb.append(String.format("%02X", b));
        return sb.toString();
    }

    /**
     * Generates a new user ID.
     *
     * @return The new user ID.
     */
    @Deprecated
    public static String generateUserID() throws SocketException, NoSuchAlgorithmException {
        return md5((System.nanoTime() + "" + (Math.random() * 1E6)).getBytes(US_ASCII));
    }

    /**
     * Computes the raw MD5 hash of a byte array.
     *
     * @param data The byte array.
     *
     * @return The raw MD5 hash of the byte array.
     */
    private static byte[] RawMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(data);
    }

    /**
     * Gets the delta time.
     *
     * @return The delta time.
     */
    public static float getDelta(){
        return Gdx.graphics.getDeltaTime();
    }

}
