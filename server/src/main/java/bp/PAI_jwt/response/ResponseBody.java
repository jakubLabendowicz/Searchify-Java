package bp.PAI_jwt.response;

public interface ResponseBody<T> {

    T getData();

    String getMessage();

    String getType();

    int getStatusCode();

}

