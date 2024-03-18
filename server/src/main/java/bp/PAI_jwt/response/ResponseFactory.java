package bp.PAI_jwt.response;

public interface ResponseFactory {

    <T> ResponseBody<T> createSuccessResponse(T data, String message, int statusCode);

    <T> ResponseBody<T> createErrorResponse(String message, int statusCode);

}



