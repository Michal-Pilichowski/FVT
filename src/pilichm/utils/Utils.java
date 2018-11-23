package pilichm.utils;

public class Utils {

    public static void _log(String logMessage){
        Context context = Context.getContext();
        if (context.isDEBUG_ENABLED()){
            System.out.println(logMessage);
        }
    }

}
