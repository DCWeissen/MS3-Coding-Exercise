package CodingExercise;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.PatternLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogClass {

    private static Logger log =  Logger.getLogger(LogClass.class);
    private static boolean initializationFlag = false;
    private static String fileName;

    private static void intializeLogger(){
        log.setLevel(Level.DEBUG);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        RollingFileAppender appender = new RollingFileAppender();
        appender.setAppend(true);
        appender.setMaxFileSize("1MB");
        appender.setMaxBackupIndex(1);
        appender.setFile(fileName);
        appender.activateOptions();

        PatternLayout layOut = new PatternLayout();
        layOut.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} - %m%n");
        appender.setLayout(layOut);

        log.addAppender(appender);
    }

    public static Logger getLogger(){
        if(initializationFlag == false){
            intializeLogger();
            initializationFlag = true;
            return LogClass.log;
        }
        else{
            return LogClass.log;
        }
    }

    public static void setFileName(String fileName){
        LogClass.fileName = fileName;
    }
}
