package contentsite;

import java.util.ArrayList;
import java.util.List;

public class Podcast implements Content {
    private String title;
    private List<String> speakers;
    private List<User> clickedBy = new ArrayList<>();


    public Podcast(String title, List<String> speakers) {
        this.title = title;
        this.speakers = speakers;
    }

    @Override
    public boolean isPremiumContent() {
        return false;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public List<User> clickedBy() {
        return new ArrayList<>(clickedBy);
    }

    @Override
    public void click(User user) {
        clickedBy.add(user);
    }

    public List<String> getSpeakers() {
        return new ArrayList<>(speakers);
    }
}
