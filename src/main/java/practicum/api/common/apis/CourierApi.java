package practicum.api.common.apis;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import practicum.api.common.OkResponse;
import practicum.api.common.RequestSpec;
import practicum.api.common.wrapper.TypedResponse;
import practicum.api.pojo.courier.request.CourierCreateRequest;
import practicum.api.pojo.courier.request.CourierDeleteRequest;
import practicum.api.pojo.courier.request.CourierLoginRequest;
import practicum.api.pojo.courier.response.CourierLoginResponse;

import static io.restassured.RestAssured.given;
import static practicum.api.constants.UrlsRequest.*;

public class CourierApi {

    RequestSpec spec = new RequestSpec();

    @Step("Создание курьера")
    public TypedResponse<OkResponse> createCourier(CourierCreateRequest courierRequest) {
        RequestSpecification request = given(spec.baseRequest());
        request.body(courierRequest);
        Response response = request.basePath(COURIER_URL)
                .body(courierRequest)
                .post();
        return new TypedResponse<>(response, OkResponse.class);
    }

    @Step("Удаление курьера")
    public TypedResponse<OkResponse> deleteCourier(CourierDeleteRequest deleteRequest) {
        RequestSpecification request = given(spec.baseRequest());
        request.body(deleteRequest);
        Response response = request.basePath(DELETE_COURIER_URL)
                .pathParam("id", deleteRequest.getId())
                .and()
                .body(deleteRequest)
                .delete();
        return new TypedResponse<>(response, OkResponse.class);
    }

    @Step("Создание двух курьеров")
    public TypedResponse<OkResponse> createTwoCouriers(CourierCreateRequest courierRequest) {
        RequestSpecification request = given(spec.baseRequest());
        request.body(courierRequest);
        request.basePath(COURIER_URL)
                .body(courierRequest)
                .post();
        Response response = request.basePath(COURIER_URL)
                .body(courierRequest)
                .post();
        return new TypedResponse<>(response, OkResponse.class);
    }

    @Step("Логин курьера")
    public TypedResponse<CourierLoginResponse> loginCourier(CourierLoginRequest loginRequest) {
        RequestSpecification request = given(spec.baseRequest());
        request.body(loginRequest);
        Response response = request.basePath(LOGIN_COURIER_URL).post();
        return new TypedResponse<>(response, CourierLoginResponse.class);
    }

}
