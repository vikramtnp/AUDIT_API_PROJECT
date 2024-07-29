package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogoutResponse {
    @JsonProperty("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
