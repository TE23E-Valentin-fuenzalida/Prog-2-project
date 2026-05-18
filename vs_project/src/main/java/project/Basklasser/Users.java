package project.Basklasser;

public class Users implements Comparable<Users>{
    // variabler
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
    // Override för att skriva ut
    public String toString() {
        return "\n"+"id: "+id+"\n"+"namn: "+name+"\n"+"email: "+email;
    }
    @Override
    // Override för att sortera efter namn
    public int compareTo(Users o) {
        return this.name.compareToIgnoreCase(o.name);
    }
}
