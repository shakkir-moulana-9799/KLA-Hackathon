import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class YamlParser {
    public static void printTimeNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.000000;");
        LocalDateTime now = LocalDateTime.now();
        System.out.print(dtf.format(now));
    }

    public static void getActivities(Map<String, Object> data) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            printTimeNow();
            System.out.print(entry.getKey() + "\n");
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>)entry.getValue();
            if(linkedHashMap.get("Type").equals("Flow")) {
                if(linkedHashMap.get("Execution").equals("Sequential")) {
                    LinkedHashMap<String, Object> activityList = (LinkedHashMap<String, Object>) linkedHashMap.get("Activities");
                    System.out.println(activityList);
                    for (String key : activityList.keySet()) {
                        printTimeNow();
                        System.out.println(entry.getKey() + "." + key + " Executing " + linkedHashMap.get(key));
                    }
                }
                else {

                }
            }
            else {
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Files\\Projects\\KLA-Hackathon\\DataSet\\tempdata.yaml";

        InputStream inputStream = new FileInputStream(path);
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);
        getActivities(data);
    }
}
