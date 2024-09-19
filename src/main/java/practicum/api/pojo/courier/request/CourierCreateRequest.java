package practicum.api.pojo.courier.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourierCreateRequest {
    private String login;
    private String password;
    private String firstName;
}
