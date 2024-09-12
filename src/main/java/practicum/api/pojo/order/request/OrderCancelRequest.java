package practicum.api.pojo.order.request;

public class OrderCancelRequest {

    private int track;

    public OrderCancelRequest(int track) {
        this.track = track;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }
}
