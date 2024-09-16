package practicum.api.pojo.order.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderGetListRequest {
    private int courierId;
    private String nearestStation;
    private int limit;
    private int page;
}
