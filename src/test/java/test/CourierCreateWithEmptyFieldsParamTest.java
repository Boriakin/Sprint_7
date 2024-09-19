package test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.api.common.AssertWithStep;
import practicum.api.common.OkResponse;
import practicum.api.common.apis.CourierApi;
import practicum.api.common.wrapper.TypedResponse;
import practicum.api.pojo.courier.request.CourierCreateRequest;

import static test.base.ConstantsResponceMessage.EMPTY_FIELDS_WHEN_CREATE;
import static test.base.FakeData.*;

@RunWith(Parameterized.class)
public class CourierCreateWithEmptyFieldsParamTest {

    private final String login;
    private final String password;
    private final String firstName;

    CourierApi api = new CourierApi();

    public CourierCreateWithEmptyFieldsParamTest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {getFakeLogin(), getFakePassword(), ""},
                {"", getFakePassword(), getFakeFirstName()},
                {getFakeLogin(), "", getFakeFirstName()}
        };
    }

    @Test
    @DisplayName("Создание курьера, когда нет одного из полей")
    @Description("Не передаются обязательные поля для создания учетной записи курьера")
    public void createCourierWithEmptyFieldsTest() {
        TypedResponse<OkResponse> response =
                api.createCourier(new CourierCreateRequest(login, password, firstName));
        AssertWithStep.assertEquals("Статус-код", 400, response.statusCode());
        AssertWithStep.assertEquals("Ошибка при создании курьера.", EMPTY_FIELDS_WHEN_CREATE, response.error().getMessage());
    }
}
