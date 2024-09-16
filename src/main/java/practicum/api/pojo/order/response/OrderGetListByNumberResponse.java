package practicum.api.pojo.order.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class OrderGetListByNumberResponse {
    private ArrayList<String> order;
}
