import java.util.*;

public class Company {
    String name;
    List<String> preferences;
    String matchedProgrammer;

    public Company(String name, List<String> preferences) {
        this.name = name;
        this.preferences = new ArrayList<>(preferences);
        this.matchedProgrammer = null;
    }
    
    // Getters and setters for name, preferences, and matchedProgrammer if needed
}
