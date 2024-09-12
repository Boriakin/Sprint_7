package test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.api.common.Assert;
import practicum.api.common.apis.CourierApi;
import practicum.api.common.wrapper.TypedResponse;
import practicum.api.pojo.courier.request.CourierLoginRequest;
import practicum.api.pojo.courier.response.CourierLoginResponse;

import static test.base.ConstantsResponceMessage.EMPTY_FIELDS_WHEN_LOGIN;
import static test.base.RandomData.getRandomLogin;
import static test.base.RandomData.getRandomPassword;

@RunWith(Parameterized.class)
public class CourierLoginWithEmptyFieldsParamTest {

    private final String login;
    private final String password;

    CourierApi api = new CourierApi();

    public CourierLoginWithEmptyFieldsParamTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"", getRandomPassword()},
                {getRandomLogin(), ""}
        };
    }

    @Test
    @DisplayName("Логин курьера, когда нет одного из полей")
    @Description("Не передаются обязательные поля для логина курьера")
    public void createCourierWithEmptyFieldsTest() {
        TypedResponse<CourierLoginResponse> response =
                api.loginCourier(new CourierLoginRequest(login, password));
        Assert.assertEquals(response.statusCode(), 400, "Статус-код");
        Assert.assertEquals(response.error().getMessage(), EMPTY_FIELDS_WHEN_LOGIN, "Ошибка при логине курьера.");
    }
}
