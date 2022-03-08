import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class YamlParser {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Files\\Projects\\KLA-Hackathon\\DataSet\\Examples\\Milestone1\\Milestone1_Example.yaml";

        InputStream inputStream = new FileInputStream(path);
        Yaml yaml = new Yaml();
        Map<String, WorkFlow> data = yaml.load(inputStream);
        System.out.println(data);
        for (Map.Entry<String, WorkFlow> entry : data.entrySet()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.000000;");
            LocalDateTime now = LocalDateTime.now();
            System.out.print(dtf.format(now));
            System.out.print(entry.getKey() + " Entry\n");
            if(entry.getValue().getType() == "Flow") {

            }
            else {
                System.out.println("Task");
            }
        }
    }
}
