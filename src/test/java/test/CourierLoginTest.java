package test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import practicum.api.common.Assert;
import practicum.api.common.apis.CourierApi;
import practicum.api.common.wrapper.TypedResponse;
import practicum.api.pojo.courier.request.CourierCreateRequest;
import practicum.api.pojo.courier.request.CourierLoginRequest;
import practicum.api.pojo.courier.response.CourierLoginResponse;

import static test.base.ConstantsResponceMessage.NOT_VALID_DATA;
import static test.base.FakeData.*;

public class CourierLoginTest {

    private final String login = getFakeLogin();
    private final String password = getFakePassword();
    private final String firstName = getFakeFirstName();
    CourierApi api = new CourierApi();


    @Test
    @DisplayName("Логин курьера")
    @Description("Успешный логин курьера")
    public void loginCourierTest() {
        api.createCourier(new CourierCreateRequest(login, password, firstName));
        TypedResponse<CourierLoginResponse> response =
                api.loginCourier(new CourierLoginRequest(login, password));
        Assert.assertEquals("Статус-код", 200, response.statusCode());
        Assert.assertNotNull("Получение ID после логина.", response.body().getId());
    }

    @Test
    @DisplayName("Авторизация под несуществующим логином.")
    @Description("Логин с несуществующими данными.")
    public void loginNotValidDataTest() {
        TypedResponse<CourierLoginResponse> response =
                api.loginCourier(new CourierLoginRequest(login, password));
        Assert.assertEquals("Статус-код", 404, response.statusCode());
        Assert.assertEquals("Сообщение об ошибке", NOT_VALID_DATA, response
                .error()
                .getMessage());
    }

}
