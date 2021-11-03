package mt.spacewebapp.controllers.rest;

import lombok.Data;

@Data
public class SimpleResponseWrapper<T> {
     String message;
     T data;

    public SimpleResponseWrapper(String message) {
        this.message = message;
    }

    public SimpleResponseWrapper(T data) {
        this.data = data;
    }

    public SimpleResponseWrapper(T data, String message) {
        this.message = message;
        this.data = data;
    }
}
