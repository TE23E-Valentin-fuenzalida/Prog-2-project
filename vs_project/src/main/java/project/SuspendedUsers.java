package project;
/* 
Valentin fuenzalida ribbing
SuspendedUsers innehåller variabler med en konstuktor, getters, setters och
en override en för att skriva ut.
*/
public class SuspendedUsers {
    private String id;
    private String userId;

    private String reason;

    public SuspendedUsers(String id, String userId, String reason) {
        this.id = id;
        this.userId = userId;
        this.reason=reason;
    }
    public String getId() {
        return id;
    }
    public String getUserId() {
        return userId;
    }
    public String getReason() {
        return reason;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    @Override
    public String toString() {
        return "\n"+"id: "+id+"\n"+"userId: "+userId+"\n"+"Reason: "+reason;
    }
}
