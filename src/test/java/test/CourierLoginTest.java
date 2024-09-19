package test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import practicum.api.common.AssertWithStep;
import practicum.api.common.apis.CourierApi;
import practicum.api.common.wrapper.TypedResponse;
import practicum.api.pojo.courier.request.CourierCreateRequest;
import practicum.api.pojo.courier.request.CourierDeleteRequest;
import practicum.api.pojo.courier.request.CourierLoginRequest;
import practicum.api.pojo.courier.response.CourierLoginResponse;

import static test.base.ConstantsResponceMessage.NOT_VALID_DATA;
import static test.base.FakeData.*;

public class CourierLoginTest {

    private final String login = getFakeLogin();
    private final String password = getFakePassword();
    private final String firstName = getFakeFirstName();
    String id;
    CourierApi api = new CourierApi();

    @Before
    public void setUp() {
        id = null;
    }

    @Test
    @DisplayName("Логин курьера")
    @Description("Успешный логин курьера")
    public void loginCourierTest() {
        api.createCourier(new CourierCreateRequest(login, password, firstName));
        TypedResponse<CourierLoginResponse> response =
                api.loginCourier(new CourierLoginRequest(login, password));
        AssertWithStep.assertEquals("Статус-код", 200, response.statusCode());
        id = response.body().getId();
        AssertWithStep.assertNotNull("Получение ID после логина.", id);
    }

    @Test
    @DisplayName("Авторизация под несуществующим логином.")
    @Description("Логин с несуществующими данными.")
    public void loginNotValidDataTest() {
        TypedResponse<CourierLoginResponse> response =
                api.loginCourier(new CourierLoginRequest(login, password));
        AssertWithStep.assertEquals("Статус-код", 404, response.statusCode());
        id = response.body().getId();
        AssertWithStep.assertEquals("Сообщение об ошибке", NOT_VALID_DATA, response
                .error()
                .getMessage());
    }

    @After
    public void cleaningData() {
        if (id != null) {
            api.deleteCourier(new CourierDeleteRequest(id));
        }
    }
}
