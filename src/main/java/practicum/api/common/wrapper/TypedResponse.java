package practicum.api.common.wrapper;

import io.restassured.response.Response;
import practicum.api.common.BadResponse;

public class TypedResponse<T> {

    private final Response response;
    private Class<T> cls;

    public TypedResponse(final Response response, final Class<T> cls) {
        this.response = response;
        this.cls = cls;
    }

    public T body() {
        return response.as(cls);
    }

    public BadResponse error() {
        return response.as(BadResponse.class);
    }

    public int statusCode() {
        return response.statusCode();
    }

    public boolean contains(String text) {
        return response.asString().contains(text);
    }
}
