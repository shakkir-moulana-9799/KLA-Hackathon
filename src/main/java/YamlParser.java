import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.LinkedHashMap;

public class YamlParser {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String path = "C:\\Files\\Projects\\KLA-Hackathon\\DataSet\\Examples\\Milestone1\\Milestone1_Example.yaml";

        InputStream inputStream = new FileInputStream(path);
        Yaml yaml = new Yaml();
        LinkedHashMap<String, Object> data = yaml.load(inputStream);
        Activities activities = new Activities();
        activities.getActivities(data, "", "", "");
    }
}
