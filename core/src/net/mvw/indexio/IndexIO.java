package net.mvw.indexio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import net.mvw.delta.logic.Global;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Mysha VoidWalker
 * on May 13, 2018
 * Personal Copyright. All rights reserved.
 */
public final class IndexIO {

    private HashMap<String, String> generalData = new HashMap<>();

    private File ioFile = null;

    /**
     * Returns the data of key name.
     *
     * @param key - name
     * @return data
     */
    public String loadVariable(String key) {
        if (generalData.containsKey(key))
            return generalData.get(key);
        return "";
    }

    /**
     * Loads the data of a key name.
     *
     * @param key      - name
     * @param variable - data
     */
    public void loadVariable(String key, String variable) {
        generalData.put(key, variable);
    }

    /**
     * Loads the data table from file.
     *
     * @param path      - of the file
     * @param separator - data key and name regex
     */
    public boolean loadDataFrom(String path, String separator) {
        FileHandle reader = null;

        reader = Gdx.files.local(path);
        if (reader.exists()) {
            try {
                ioFile = reader.file();
                String[] lines = reader.readString().split("\\r?\\n");
                for (String line : lines) {
                    String lineData[] = line.split(separator);
                    if (lineData.length == 2) {
                        StringBuilder sSB = new StringBuilder(lineData[0]).reverse();
                        StringBuilder gSB = new StringBuilder(lineData[1]).reverse();
                        generalData.put(sSB.toString(), gSB.toString());
                    }
                }
                Global.printInfo("Data loaded successfully", "IndexIO");
            } catch (Exception e) {
                Global.printError("An error has occurred while loading data. The exception message is as follows:\n" + e.getMessage(), "IndexIO");
            } finally {
                return true;
            }
        } else return false;
    }

    /**
     * Saves the data table with desired separator.
     *
     * @param path      - of the file
     * @param separator - data key and name regex
     */
    public void writeDataChunkTo(String path, String separator) {
        try {
            FileHandle writer = Gdx.files.local(path);
            // Overwrite/Create.
            writer.writeString("", false);

            for (String s : generalData.keySet()) {
                StringBuilder sSB = new StringBuilder(s).reverse();
                StringBuilder gSB = new StringBuilder(generalData.get(s)).reverse();
                writer.writeString(sSB + separator + gSB + "\n", true);
            }

        } catch (Exception e) {
            Global.printError("An error has occurred while saving data. The exception message is as follows:\n" + e.getMessage(), "IndexIO");
        }
        Global.printInfo("Data chunk written successfully", "IndexIO");
    }
}