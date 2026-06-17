package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePostResponse {
    public int id;
    public String title;
    public String body;
    public int userId;
}
