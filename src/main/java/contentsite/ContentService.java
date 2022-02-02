package contentsite;

import java.util.ArrayList;
import java.util.List;

public class ContentService {
    private List<User> allUsers = new ArrayList<>();
    private List<Content> allContent = new ArrayList<>();

    void registerUser(String name, String password) {
        if (allUsers.stream().anyMatch(user -> user.getUserName().equals(name))) {
            throw new IllegalArgumentException("Username is already taken: " + name);
        }
        allUsers.add(new User(name, password));
    }

    public void addContent(Content content) {
        if (allContent.stream().anyMatch(c -> c.getTitle().equals(content.getTitle()))) {
            throw new IllegalArgumentException("Content name is already taken: " + content.getTitle());
        }
        allContent.add(content);
    }


    public void logIn(String username, String password) {
        User user = allUsers.stream().filter(u -> u.getUserName().equals(username))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Username is wrong!"));

        int hash = (username + password).hashCode();
        if (user.getPassword() != hash) {
            throw new IllegalArgumentException("Password is Invalid!");
        }
        user.setLogIn(true);
    }


    public void clickOnContent(User user, Content content) {

        if (!user.isLogIn()) {
            throw new IllegalStateException("Log in to watch this content!");
        }

        if (content.isPremiumContent() && !user.isPremiumMember()) {
            throw new IllegalStateException("Upgrade for Premium to watch this content!");
        }
        content.click(user);
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public List<Content> getAllContent() {
        return allContent;
    }


}
