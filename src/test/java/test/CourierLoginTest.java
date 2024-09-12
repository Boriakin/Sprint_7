package test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import practicum.api.common.Assert;
import practicum.api.common.apis.CourierApi;
import practicum.api.common.wrapper.TypedResponse;
import practicum.api.pojo.courier.request.CourierLoginRequest;
import practicum.api.pojo.courier.response.CourierLoginResponse;

import static test.base.ConstantsResponceMessage.NOT_VALID_DATA;
import static test.base.RandomData.*;
import static test.base.ValidData.VALID_LOGIN;
import static test.base.ValidData.VALID_PASSWORD;

public class CourierLoginTest {

    private final String login = getRandomLogin();
    private final String password = getRandomPassword();;
    CourierApi api = new CourierApi();

    @Test
    @DisplayName("Логин курьера")
    @Description("Успешный логин курьера")
    public void loginCourierTest() {
        TypedResponse<CourierLoginResponse> response =
                api.loginCourier(new CourierLoginRequest(VALID_LOGIN, VALID_PASSWORD));
        Assert.assertEquals(response.statusCode(), 200, "Статус-код");
        Assert.assertNotNull(response.body().getId(), "Получение ID после логина.");
    }

    @Test
    @DisplayName("Авторизация под несуществующим логином.")
    @Description("Логин с несуществующими данными.")
    public void loginNotValidDataTest() {
        TypedResponse<CourierLoginResponse> response =
                api.loginCourier(new CourierLoginRequest(login, password));
        Assert.assertEquals(response.statusCode(), 404, "Статус-код");
        Assert.assertEquals(response
                .error()
                .getMessage(), NOT_VALID_DATA, "Сообщение об ошибке");
    }

}
