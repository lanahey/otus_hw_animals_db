package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBConfigurator {

    /*public Map<String, String> getDBConfig(){
        Map<String, String> myMap = new HashMap<>();
        myMap.put("db_url","jdbc:mysql//127.0.0.1:3306");
        myMap.put("db_name","lanahey_animals_homework");
        myMap.put("username","root");
        return myMap;
    }*/

    public Properties getDBConfig() throws IOException {
        Properties properties = new Properties();
        InputStream fileInputStream = new FileInputStream(
//                System.getProperty("user.dir") + "/src/resources/db.properties"
                System.getProperty("user.dir") + "/OtusHomework/src/main/resources/db.properties"
                ///Users/ozakharova/IdeaProjects/OTUS/otus-homework-animals/OtusHomework/src/main/resources/db.properties
        );
        System.out.println(fileInputStream);
        properties.load(fileInputStream);
        return properties;
    }
}
