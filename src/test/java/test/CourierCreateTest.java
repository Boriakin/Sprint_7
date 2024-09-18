package test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import practicum.api.common.AssertWithStep;
import practicum.api.common.OkResponse;
import practicum.api.common.apis.CourierApi;
import practicum.api.common.wrapper.TypedResponse;
import practicum.api.pojo.courier.request.CourierCreateRequest;
import practicum.api.pojo.courier.request.CourierDeleteRequest;
import practicum.api.pojo.courier.request.CourierLoginRequest;

import static test.base.ConstantsResponceMessage.LOGIN_IN_USE;
import static test.base.FakeData.*;

public class CourierCreateTest {
    private final String login = getFakeLogin();
    private final String password = getFakePassword();
    private final String firstName = getFakeFirstName();
    CourierApi api = new CourierApi();

    @Test
    @DisplayName("Создание курьера")
    @Description("Курьера можно создать")
    public void createCourierTest() {
        TypedResponse<OkResponse> response =
                api.createCourier(new CourierCreateRequest(login, password, firstName));
        AssertWithStep.assertEquals("Статус-код", 201, response.statusCode());
        AssertWithStep.assertTrue("Курьер создан.", response.body().isOk());
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Нельзя создать двух одинаковых курьеров")
    public void createTwoCouriersTest() {
        TypedResponse<OkResponse> response =
                api.createTwoCouriers(new CourierCreateRequest(login, password, firstName));
        AssertWithStep.assertEquals("Статус-код", 409, response.statusCode());
        AssertWithStep.assertEquals("Сообщение об ошибке.", LOGIN_IN_USE, response
                .error()
                .getMessage());
    }


    @After
    public void cleaningData() {
        api.deleteCourier(new CourierDeleteRequest(
                api.loginCourier(new CourierLoginRequest(login, password))
                        .body()
                        .getId()));
    }

}
