package bp.PAI_jwt.factory;

public class ErrorResponseBody implements ResponseBody<Void> {

    private String message;
    private String type = "error";
    private int statusCode;

    public ErrorResponseBody(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public Void getData() {
        return null;
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

}

