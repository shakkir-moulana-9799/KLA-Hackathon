import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

public class YamlParser {

    public static void delay(int t) throws InterruptedException {
        Thread.sleep(t*1000);
    }

    public static void printTimeNow() throws InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.000000;");
        LocalDateTime now = LocalDateTime.now();
        System.out.print(dtf.format(now));
    }

    public static void getActivities(LinkedHashMap<String, Object> data, String val, String subval) throws InterruptedException {
        for (String keys : data.keySet()) {
            LinkedHashMap<String, Object> subActivity = (LinkedHashMap<String, Object>) data.get(keys);

            if(subActivity.get("Type").equals("Flow")) {
                if(val.length() != 0)
                    val = val + "." + keys;
                else
                    val = keys;
                printTimeNow();
                System.out.print(val + " Entry\n");
                if(subActivity.get("Execution").equals("Sequential")) {
                    LinkedHashMap<String, Object> activityList = (LinkedHashMap<String, Object>) subActivity.get("Activities");
                    getActivities(activityList, val, subval);
                    printTimeNow();
                    System.out.print(val + " Exit\n");
                }
                else {

                }
            }
            else {
                    LinkedHashMap<String, String> function = (LinkedHashMap<String, String>) subActivity.get("Inputs");
                    printTimeNow();
                    System.out.print(val + "." + keys + " Entry\n");
                    subval = " Executing " + subActivity.get("Function") + " (" + function.get("FunctionInput") + ", " + function.get("ExecutionTime") + ")";
                    printTimeNow();
                    System.out.print(val + "." + keys + subval + "\n");
                    delay(Integer.parseInt(function.get("ExecutionTime")));
                    printTimeNow();
                    System.out.print(val + "." + keys + " Exit\n");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String path = "C:\\Files\\Projects\\KLA-Hackathon\\DataSet\\Milestone1\\Milestone1A.yaml";

        InputStream inputStream = new FileInputStream(path);
        Yaml yaml = new Yaml();
        LinkedHashMap<String, Object> data = yaml.load(inputStream);
        getActivities(data, "", "");
    }
}
