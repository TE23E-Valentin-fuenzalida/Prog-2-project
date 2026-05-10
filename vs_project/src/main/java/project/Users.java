package project;

public class Users implements Comparable<Users>{
    private String id;
    private String name;
    private String email;

    public Users(String id, String name, String email){
        this.id=id;
        this.name=name;
        this.email=email;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n"+"id: "+id+"\n"+"namn: "+name+"\n"+"email: "+email;
    }
    @Override
    public int compareTo(Users o) {
        return this.name.compareToIgnoreCase(o.name);
    }
}
