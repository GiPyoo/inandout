package com.mappractice.demo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class RestResponse<T> {

    private T data;
    private String message;
    private List<Error> errors;

    private RestResponse(List<Error> errors) {
        this.errors = errors;
    }

    private RestResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public static <T> RestResponse<T> success(T data) {
        return success(data, null);
    }

    public static <T> RestResponse<T> success(T data, String message) {
        return new RestResponse<T>(data, message);
    }

    public static ErrorResponseBuilder errorResponseBuilder() {
        return new ErrorResponseBuilder();
    }


    @NoArgsConstructor
    public static class ErrorResponseBuilder {

        private List<Error> errors = new ArrayList<>();

        public ErrorResponseBuilder appendError(String field, String errorMessage) {
            Error error = new Error(field, errorMessage);
            errors.add(error);
            return this;
        }

        public ErrorResponseBuilder appendError(String errorMessage) {
            return appendError(null, errorMessage);
        }

        public RestResponse<?> build() {
            return new RestResponse(this.errors);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Error {
        private String field;
        private String message;
    }
}