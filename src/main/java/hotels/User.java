package hotels;

public class User {
    private final String nickName;
    private String name;
    private String password;
    private UserType type;

    public User(String nickName, String name, String password, UserType type) {
        this.nickName = nickName;
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public String getNickName() {
        return nickName;
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
