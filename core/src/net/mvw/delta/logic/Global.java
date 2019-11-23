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

public class Global {


    public static final boolean DEBUG_MODE = false;
    public static boolean soundToggle = true;
    public static boolean musicToggle = true;
    public  enum GameState{
        INTRO, MENU_MAIN, MENU_OPTIONS,MENU_EXTRAS,MENU_ACHIEVEMENTS,MENU_CREDITS,GAME,GAME_PROGRESS, GAME_END,GAME_TUTORIAL, NEUTRAL
    }
    public static GameState state = GameState.INTRO;
    public static GlyphLayout layout = new GlyphLayout();
    public static float timePassed = 0;
    public static final Json JSON = new Json();
    public static final JsonValue.PrettyPrintSettings jsonSettings = new JsonValue.PrettyPrintSettings() {{ outputType = JsonWriter.OutputType.json; }};
    public static StringBuilder logs = new StringBuilder();
    public static DeltaConsole console;
    public static void printVerbose(String message) {
        printVerbose(message, "VERBOSE");
    }
    public static void printDebug(String message) {
        printDebug(message, "DEBUG");
    }
    public static void printInfo(String message) {
        printInfo(message, "INFO");
    }
    public static void printWarning(String message) {
        printWarning(message, "WARNING");
    }
    public static void printError(String message) {
        printError(message, "ERROR");
    }
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
    public static String formatException(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString().replace("\n", "<br>");
    }

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

    public static String md5(byte[] data) throws NoSuchAlgorithmException {
        return toHex(RawMD5(data));
    }


    public static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data)
            sb.append(String.format("%02X", b));
        return sb.toString();
    }


    @Deprecated
    public static String generateUserID() throws SocketException, NoSuchAlgorithmException {
        return md5((System.nanoTime() + "" + (Math.random() * 1E6)).getBytes(US_ASCII));
    }

    private static byte[] RawMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(data);
    }

    public static float getDelta(){
        return Gdx.graphics.getDeltaTime();
    }

}
