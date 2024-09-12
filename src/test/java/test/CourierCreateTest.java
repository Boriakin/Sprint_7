package test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import practicum.api.common.Assert;
import practicum.api.common.OkResponse;
import practicum.api.common.apis.CourierApi;
import practicum.api.common.wrapper.TypedResponse;
import practicum.api.pojo.courier.request.CourierCreateRequest;
import practicum.api.pojo.courier.request.CourierDeleteRequest;
import practicum.api.pojo.courier.request.CourierLoginRequest;

import static test.base.ConstantsResponceMessage.LOGIN_IN_USE;
import static test.base.RandomData.*;

public class CourierCreateTest {

    private final String login = getRandomLogin();
    private final String password = getRandomPassword();
    private final String firstName = getRandomFirstName();
    CourierApi api = new CourierApi();

    @Test
    @DisplayName("Создание курьера")
    @Description("Курьера можно создать")
    public void createCourierTest() {
        TypedResponse<OkResponse> response =
                api.createCourier(new CourierCreateRequest(login, password, firstName));
        Assert.assertEquals(response.statusCode(), 201, "Статус-код");
        Assert.assertTrue(response.body().isOk(), "Курьер создан.");
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Нельзя создать двух одинаковых курьеров")
    public void createTwoCouriersTest() {
        TypedResponse<OkResponse> response =
                api.createTwoCouriers(new CourierCreateRequest(login, password, firstName));
        Assert.assertEquals(response.statusCode(), 409, "Статус-код.");
        Assert.assertEquals(response
                .error()
                .getMessage(), LOGIN_IN_USE, "Сообщение об ошибке.");
    }


    @After
    public void cleaningData() {
        api.deleteCourier(new CourierDeleteRequest(
                api.loginCourier(new CourierLoginRequest(login, password))
                        .body()
                        .getId()));
    }

}
