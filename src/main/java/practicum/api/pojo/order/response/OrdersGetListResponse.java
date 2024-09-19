package practicum.api.pojo.order.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersGetListResponse {
    private String[] orders;
}
