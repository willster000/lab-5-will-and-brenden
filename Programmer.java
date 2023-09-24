import java.util.*;

public class Programmer {
    String name;
    List<String> preferences;
    String matchedCompany;

    public Programmer(String name, List<String> preferences) {
        this.name = name;
        this.preferences = new ArrayList<>(preferences);
        this.matchedCompany = null;
    }
    
    // Getters and setters for name, preferences, and matchedCompany if needed
}
