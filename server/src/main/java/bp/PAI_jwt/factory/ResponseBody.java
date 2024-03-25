package bp.PAI_jwt.factory;

public interface ResponseBody<T> {

    T getData();

    String getMessage();

    String getType();

    int getStatusCode();

}

