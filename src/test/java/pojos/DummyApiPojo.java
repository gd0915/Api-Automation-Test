package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyApiPojo {

    private String status;
    private DummyApiDataPojo data;
    private String message;

    public DummyApiPojo(String status, DummyApiDataPojo data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public DummyApiPojo() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyApiDataPojo getData() {
        return data;
    }

    public void setData(DummyApiDataPojo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummyApiPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}

/*
{
    "status": "success",
    "data": {
        "employee_name": "Ali Can",
        "employee_salary": 111111,
        "employee_age": 23,
        "profile_image": "Perfect image",
        "id": 5951
    },
    "message": "Successfully! Record has been added."
}
 */
