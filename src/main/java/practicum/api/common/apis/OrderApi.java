package practicum.api.common.apis;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import practicum.api.common.OkResponse;
import practicum.api.common.RequestSpec;
import practicum.api.common.wrapper.TypedResponse;
import practicum.api.pojo.order.request.OrderCancelRequest;
import practicum.api.pojo.order.request.OrderCreateRequest;
import practicum.api.pojo.order.response.OrderCreateResponse;
import practicum.api.pojo.order.response.OrdersGetListResponse;

import static io.restassured.RestAssured.given;
import static practicum.api.constants.UrlsRequest.CANCEL_ORDER_URL;
import static practicum.api.constants.UrlsRequest.ORDERS_URL;


public class OrderApi {

    RequestSpec spec = new RequestSpec();

    @Step("Создание заказа")
    public TypedResponse<OrderCreateResponse> createOrder(OrderCreateRequest orderCreateRequest) {
        RequestSpecification request = given(spec.baseRequest());
        request.body(orderCreateRequest);
        Response response = request.basePath(ORDERS_URL)
                .body(orderCreateRequest)
                .post();
        return new TypedResponse<>(response, OrderCreateResponse.class);
    }

    @Step("Получение списка заказов")
    public TypedResponse<OrdersGetListResponse> getOrdersList() {
        RequestSpecification request = given(spec.baseRequest());
        Response response = request.basePath(ORDERS_URL)
                .get();
        return new TypedResponse<>(response, OrdersGetListResponse.class);
    }

    @Step("Отменить заказ")
    public TypedResponse<OkResponse> orderCancel(OrderCancelRequest cancelRequest) {
        RequestSpecification request = given(spec.baseRequest());
        Response response = request.basePath(CANCEL_ORDER_URL)
                .queryParam("track", cancelRequest.getTrack())
                .put();
        return new TypedResponse<>(response, OkResponse.class);
    }

}
