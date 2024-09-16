package practicum.api.pojo.courier.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourierLoginRequest {
    private String login;
    private String password;
}

