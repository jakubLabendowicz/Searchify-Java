package bp.PAI_jwt.factory;

public class ResponseFactoryImpl implements ResponseFactory {
    @Override
    public <T> ResponseBody<T> createSuccessResponse(T data, String message, int statusCode) {
        return new SuccessResponseBody<T>(data, message, statusCode);
    }

    @Override
    public <T> ResponseBody<T> createErrorResponse(String message, int statusCode) {
        return (ResponseBody<T>) new ErrorResponseBody(message, statusCode);
    }
}
