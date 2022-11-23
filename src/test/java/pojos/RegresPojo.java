package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegresPojo {

    private String name;
    private String job;

    public RegresPojo(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public RegresPojo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "RegresPojo{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
