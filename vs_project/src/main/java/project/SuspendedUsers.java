package project;

public class SuspendedUsers {
    private String id;
    private String userId;

    public SuspendedUsers(String id, String userId) {
        this.id = id;
        this.userId = userId;
    }
    public String getId() {
        return id;
    }
    public String getUserId() {
        return userId;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
}
