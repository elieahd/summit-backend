package summit.models;

public class TrackerTemplate {

    private Integer id;
    private String name;
    private String description;
    private TrackerInterval interval;

    public TrackerTemplate() {
    }

    public TrackerTemplate(String name,
                           String description,
                           TrackerInterval interval) {
        this.name = name;
        this.description = description;
        this.interval = interval;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TrackerInterval getInterval() {
        return interval;
    }

    public void setInterval(TrackerInterval interval) {
        this.interval = interval;
    }

}