import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

public class Activities {

    void doSomething(final LinkedHashMap<String, Object> data, final String val, final String subval, final String concurrency) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getActivities(data, val, subval, concurrency);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void delay(int t, String concurrency) throws InterruptedException {
        Thread.sleep(t*1000);
    }

    public synchronized void printTimeNow() throws InterruptedException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.000000;");
        LocalDateTime now = LocalDateTime.now();
        System.out.print(dtf.format(now));
    }
    public void getActivities(LinkedHashMap<String, Object> data, String val, String subval, String concurrency) throws InterruptedException {
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
                    getActivities(activityList, val, subval, (String) subActivity.get("Execution"));
                    printTimeNow();
                    System.out.print(val + " Exit\n");
                }
                else {
                    LinkedHashMap<String, Object> activityList = (LinkedHashMap<String, Object>) subActivity.get("Activities");
                    for(String key: activityList.keySet()) {
                        doSomething(activityList, val, subval, concurrency);
                    }
                }
            }
            else {
                LinkedHashMap<String, String> function = (LinkedHashMap<String, String>) subActivity.get("Inputs");
                printTimeNow();
                System.out.print(val + "." + keys + " Entry\n");
                subval = " Executing " + subActivity.get("Function") + " (" + function.get("FunctionInput") + ", " + function.get("ExecutionTime") + ")";
                printTimeNow();
                System.out.print(val + "." + keys + subval + "\n");
                delay(Integer.parseInt(function.get("ExecutionTime")), concurrency);
                printTimeNow();
                System.out.print(val + "." + keys + " Exit\n");
            }
        }
    }
}
