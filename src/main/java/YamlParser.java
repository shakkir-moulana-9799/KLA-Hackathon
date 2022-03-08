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

    public static void getActivities(LinkedHashMap<String, Object> data, String val, String subval) {
        for (String keys : data.keySet()) {
            if(val.length() != 0) {
                LinkedHashMap<String, Object> subActivity = (LinkedHashMap<String, Object>) data.get(keys);
                LinkedHashMap<String, String> function = (LinkedHashMap<String, String>) subActivity.get("Inputs");
                val = val + "." + keys;
                printTimeNow();
                System.out.print(val + " Entry\n");
                if(function != null) {
                    subval = " Executing " + subActivity.get("Function") + " (" + function.get("FunctionInput") + ", " + function.get("ExecutionTime") + ")";
                    printTimeNow();
                    System.out.print(val + subval + "\n");
                }
            }
            else {
                val = keys;
                printTimeNow();
                System.out.print(val + " Entry\n");
            }
            LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>)data.get(keys);
            if(linkedHashMap.get("Type").equals("Flow")) {
                if(linkedHashMap.get("Execution").equals("Sequential")) {
                    LinkedHashMap<String, Object> activityList = (LinkedHashMap<String, Object>) linkedHashMap.get("Activities");
                    getActivities(activityList, val, subval);
                }
                else {

                }
            }
            else {
            }
            printTimeNow();
            System.out.print(val + " Exit\n");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Files\\Projects\\KLA-Hackathon\\DataSet\\Milestone1\\Milestone1A.yaml";

        InputStream inputStream = new FileInputStream(path);
        Yaml yaml = new Yaml();
        LinkedHashMap<String, Object> data = yaml.load(inputStream);
        getActivities(data, "", "");
    }
}
