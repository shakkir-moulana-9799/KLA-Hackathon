import java.util.List;

public class WorkFlow {
    private String Type;
    private String Execution;
    List<Activities> Activities;

    public List<Activities> getActivities() {
        return Activities;
    }

    public String getExecution() {
        return Execution;
    }

    public String getType() {
        return Type;
    }

    public void setActivities(List<Activities> activities) {
        this.Activities = activities;
    }

    public void setExecution(String execution) {
        this.Execution = execution;
    }

    public void setType(String type) {
        this.Type = type;
    }
}
