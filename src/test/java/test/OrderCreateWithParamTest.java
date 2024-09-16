package test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.api.common.Assert;
import practicum.api.common.apis.OrderApi;
import practicum.api.common.wrapper.TypedResponse;
import practicum.api.pojo.order.request.OrderCancelRequest;
import practicum.api.pojo.order.request.OrderCreateRequest;
import practicum.api.pojo.order.response.OrderCreateResponse;

import static test.base.ConstantsColor.BLACK;
import static test.base.ConstantsColor.GREY;

@RunWith(Parameterized.class)
public class OrderCreateWithParamTest {

    private final String firstName, lastName, address, metroStation, phone, deliveryDate, comment;
    private final int rentTime;
    private final String[] color;
    int track;
    OrderApi api = new OrderApi();

    public OrderCreateWithParamTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.rentTime = rentTime;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Иван", "Иванов", "ул. Иванова 11", "1", "+79876543210", 1,
                        "01.01.2022", "Комментарий 1", new String[]{BLACK.name(), GREY.name()}},
                {"Иван", "Иванов", "ул. Иванова 11", "1", "+79876543210", 1,
                        "01.01.2022", "Комментарий 2", new String[]{BLACK.name()}},
                {"Иван", "Иванов", "ул. Иванова 11", "1", "+79876543210", 1,
                        "01.01.2022", "Комментарий 1", new String[]{BLACK.name()}},
                {"Иван", "Иванов", "ул. Иванова 11", "1", "+79876543210", 1,
                        "01.01.2022", "Комментарий 1", null}
        };
    }

    @Before
    public void setUp() {
        track = 0;
    }

    @Test
    @DisplayName("Проверка создания заказа с параметрами цвета")
    @Description("Проверка, что когда создаем заказ можно указать один из цветов," +
            " можно указать оба цвета," +
            " можно совсем не указывать цвет")
    public void orderCreateWithParamTest() {

        TypedResponse<OrderCreateResponse> response = api.createOrder(new OrderCreateRequest(
                firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                color));
        Assert.assertEquals("Статус-код.", 201, response
                .statusCode());
        track = response
                .body()
                .getTrack();
        Assert.assertTrue("Присутствует трек-номер заказа", response.contains("track"));
    }

    @After
    public void cleaningData() {
        api.orderCancel(new OrderCancelRequest(track));
    }

}

