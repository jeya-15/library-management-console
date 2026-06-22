package Entity;

public class UserEntity {


    private String id;

    private String name;

    private String password;

    private String role;

    private int limit = 5;

    public UserEntity(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
