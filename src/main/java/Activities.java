import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Activities {
    private String Type;
    private String Function;
    private String Input;
    private String Execution;
    private Map<String, Activities> Activities = new LinkedHashMap<String, Activities>();

    public Map<String, Activities> getActivities() {
        return Activities;
    }

    public String getExecution() {
        return Execution;
    }

    public String getType() {
        return Type;
    }

    public String getFunction() {
        return Function;
    }

    public String getInput() {
        return Input;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public void setFunction(String function) {
        this.Function = function;
    }

    public void setInput(String input) {
        this.Input = input;
    }

    public void setExecution(String execution) {
        Execution = execution;
    }

    public void setActivities(Map<String, Activities> activities) {
        Activities = activities;
    }
}
