import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.LinkedHashMap;

public class YamlParser {

//    public static class MyThread extends Thread {
//
//        LinkedHashMap<String, Object> data;
//        String val;
//        String subval;
//        String concurrency;
//        MyThread[] td;
//        int id;
//
//        public MyThread(LinkedHashMap<String, Object> data, String val, String subval, String concurrency, MyThread[] td, int id) {
//            this.data = data;
//            this.val = val;
//            this.subval = subval;
//            this.concurrency = concurrency;
//            this.td = td;
//            this.id = id;
//        }
//
//        @Override
//        public void run() {
//            try {
//                getActivities(data, val, subval, concurrency, td, id);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }



    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String path = "C:\\Files\\Projects\\KLA-Hackathon\\DataSet\\Examples\\Milestone1\\Milestone1_Example.yaml";

        InputStream inputStream = new FileInputStream(path);
        Yaml yaml = new Yaml();
        LinkedHashMap<String, Object> data = yaml.load(inputStream);
        Activities activities = new Activities();
        activities.getActivities(data, "", "", "");
    }
}
