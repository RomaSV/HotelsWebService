package hotels;

public class User {
    private final String userName;
    private String name;
    private UserType type;

    public User(String userName, String name, UserType type) {
        this.userName = userName;
        this.name = name;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
