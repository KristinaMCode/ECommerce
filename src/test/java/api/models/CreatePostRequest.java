package api.models;

public class CreatePostRequest {
    public String title;
    public String body;
    public int userId;

    public CreatePostRequest(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
