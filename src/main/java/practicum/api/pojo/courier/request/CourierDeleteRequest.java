package practicum.api.pojo.courier.request;

public class CourierDeleteRequest {
    private String id;

    public CourierDeleteRequest(String id) {
        this.id = id;
    }

    public CourierDeleteRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
