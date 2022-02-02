package contentsite;

import java.util.Objects;

public class User {
    private String userName;
    private int password;
    boolean premiumMember;
    boolean logIn;

    public User(String userName, String password) {
        this.userName = userName;
        String temp = userName + password;
        this.password = temp.hashCode();
    }


    public void upgradeForPremium() {
        premiumMember = true;
    }

    public void setLogIn(boolean value) {
        logIn = value;
    }


    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public boolean isLogIn() {
        return logIn;
    }
}
