package bp.PAI_jwt.factory;

public class SuccessResponseBody<T> implements ResponseBody<T> {
    private T data;
    private String message;
    private String type = "success";
    private int statusCode;

    public SuccessResponseBody(T data, String message, int statusCode) {
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return "SuccessResponseBody{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}


