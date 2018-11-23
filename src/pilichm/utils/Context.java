package pilichm.utils;

import javafx.scene.layout.BorderPane;

import java.io.*;
import java.util.Properties;

public class Context {
    private static Context context;
    private static BorderPane borderPane;
    private static boolean DEBUG_ENABLED;
    private static int NO_OF_REPETITIONS;
    private static int SIZE_OF_INTERSECTION;
    private Properties properties;

    private Context(){}

    public static Context getContext(){
        if (context==null){
            context = new Context();
            context.properties = new Properties();
            try {
                File file = new File(Constants.PREF_FILE_NAME);

                if (!file.exists()){
                    if (file.createNewFile()){
                        Utils._log("Creating empty preferences file.");
                    }
                }

                InputStream inStream = new FileInputStream(Constants.PREF_FILE_NAME);
                context.properties.load(inStream);

                if (!context.properties.containsKey(Constants.PREF_DEBUG_ENABLED)){
                    context.saveIntProperty(Constants.PREF_DEBUG_ENABLED, "false");
                    context.setDEBUG_ENABLED(false);
                } else {
                    context.setDEBUG_ENABLED(Boolean.valueOf(context.properties.getProperty(Constants.PREF_DEBUG_ENABLED)));
                }

                if (!context.properties.containsKey(Constants.PREF_NO_OF_REPETITIONS)){
                    context.saveIntProperty(Constants.PREF_NO_OF_REPETITIONS, "3000");
                    context.setNO_OF_REPETITIONS(3000);
                } else {
                    context.setNO_OF_REPETITIONS(Integer.valueOf(context.properties.getProperty(Constants.PREF_NO_OF_REPETITIONS)));
                }

                if (!context.properties.containsKey(Constants.PREF_SIZE_OF_INTERSECTION)){
                    context.saveIntProperty(Constants.PREF_SIZE_OF_INTERSECTION, "5");
                    context.setSIZE_OF_INTERSECTION(5);
                } else {
                    context.setSIZE_OF_INTERSECTION(Integer.valueOf(context.properties.getProperty(Constants.PREF_SIZE_OF_INTERSECTION)));
                }

                inStream.close();
            } catch (FileNotFoundException e) {
                Utils._log("Error opening properties file.");
                e.printStackTrace();
            } catch (IOException e) {
                Utils._log("Error loading properties file.");
                e.printStackTrace();
            }
        }

        return context;
    }

    public void saveIntProperty(String key, String value){
        try {
            OutputStream outputStream = new FileOutputStream(Constants.PREF_FILE_NAME);
            context.properties.setProperty(key, value);
            context.properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            Utils._log("Error while saving property: " + key + " with value: " + value);
            e.printStackTrace();
        }
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public boolean isDEBUG_ENABLED() {
        return DEBUG_ENABLED;
    }

    public int getNO_OF_REPETITIONS() {
        return NO_OF_REPETITIONS;
    }

    public int getSIZE_OF_INTERSECTION() {
        return SIZE_OF_INTERSECTION;
    }

    public void setBorderPane(BorderPane borderPane) {
        Context.borderPane = borderPane;
    }

    public void setDEBUG_ENABLED(boolean DEBUG_ENABLED) {
        Context.DEBUG_ENABLED = DEBUG_ENABLED;
        context.saveIntProperty(Constants.PREF_DEBUG_ENABLED, String.valueOf(DEBUG_ENABLED));
    }

    public void setNO_OF_REPETITIONS(int NO_OF_REPETITIONS) {
        Context.NO_OF_REPETITIONS = NO_OF_REPETITIONS;
        context.saveIntProperty(Constants.PREF_NO_OF_REPETITIONS, String.valueOf(NO_OF_REPETITIONS));
    }

    public void setSIZE_OF_INTERSECTION(int SIZE_OF_INTERSECTION) {
        Context.SIZE_OF_INTERSECTION = SIZE_OF_INTERSECTION;
        context.saveIntProperty(Constants.PREF_SIZE_OF_INTERSECTION, String.valueOf(SIZE_OF_INTERSECTION));
    }
}
