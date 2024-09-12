package test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import practicum.api.common.Assert;
import practicum.api.common.apis.OrderApi;
import practicum.api.common.wrapper.TypedResponse;
import practicum.api.pojo.order.response.OrdersGetListResponse;

public class OrderGetListTest {

    OrderApi api = new OrderApi();

    @Test
    @DisplayName("Получение списказаказов")
    @Description("В тело ответа возвращается список заказов")
    public void getListOrderTest() {
        TypedResponse<OrdersGetListResponse> response =
                api.getOrdersList();
        Assert.assertEquals(response
                .statusCode(), 200, "Статус-код.");
        Assert.assertTrue(response.contains("orders"), "Присутствует список заказов");
    }
}
