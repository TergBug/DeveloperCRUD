package org.mycode.testutil;

import org.mycode.config.TestConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;

public final class TestUtils {
    private static final ApplicationContext applicationContext;
    private static final String LINK_TO_PROPERTIES = "./src/main/resources/config.properties";
    private static final String WORK;
    private static final String TEST= "# JDBC\n" +
            "jdbc.driver=org.h2.Driver\n" +
            "jdbc.url=jdbc:h2:~/test\n" +
            "jdbc.user=sa\n" +
            "jdbc.password=";
    static {
        applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fr = new FileReader(LINK_TO_PROPERTIES)){
            int c;
            while ((c=fr.read())!=-1){
                stringBuilder.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        WORK = stringBuilder.toString();
    }
    public static void switchConfigToTestMode(){
        try(FileWriter fw = new FileWriter(LINK_TO_PROPERTIES, false)){
            fw.write(TEST);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void switchConfigToWorkMode(){
        try(FileWriter fw = new FileWriter(LINK_TO_PROPERTIES, false)){
            fw.write(WORK);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
