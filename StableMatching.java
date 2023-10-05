import java.util.*;

public class StableMatching {

    public static void main(String[] args) {
        List<Programmer> programmers = new ArrayList<>();
        List<Company> companies = new ArrayList<>();

        // We have 4 tests with different numbers of companies and programmers 
        // with different preferences. The algorithm should return satisfactory pairs
        // for all tests. To add new tests, you need to cerate new companies and 
        // programmers and then use programmers.addAll() or companies.addAll() as 
        // seen in the examples.    
         
        Programmer p1 = new Programmer("1", Arrays.asList("E", "A", "D", "B", "C"));
        Programmer p2 = new Programmer("2", Arrays.asList("D", "E", "B", "A", "C"));
        Programmer p3 = new Programmer("3", Arrays.asList("D", "B", "C", "E", "A"));
        Programmer p4 = new Programmer("4", Arrays.asList("C", "B", "D", "A", "E"));
        Programmer p5 = new Programmer("5", Arrays.asList("A", "D", "B", "C", "E"));

        Company c1 = new Company("A", Arrays.asList("2", "5", "1", "3", "4"));
        Company c2 = new Company("B", Arrays.asList("1", "2", "3", "4", "5"));
        Company c3 = new Company("C", Arrays.asList("5", "3", "2", "1", "4"));
        Company c4 = new Company("D", Arrays.asList("1", "3", "2", "4", "5"));
        Company c5 = new Company("E", Arrays.asList("2", "3", "5", "4", "1"));

        programmers.addAll(Arrays.asList(p1, p2, p3, p4, p5));
        companies.addAll(Arrays.asList(c1, c2, c3, c4, c5));

       /* Programmer p1 = new Programmer("1", Arrays.asList("D", "A", "B", "C"));
        Programmer p2 = new Programmer("2", Arrays.asList("B", "D", "A", "C"));
        Programmer p3 = new Programmer("3", Arrays.asList("B", "C", "A", "D"));
        Programmer p4 = new Programmer("4", Arrays.asList("A", "B", "C", "D"));

        Company c1 = new Company("A", Arrays.asList("2", "4", "1", "3"));
        Company c2 = new Company("B", Arrays.asList("2", "1", "3", "4"));
        Company c3 = new Company("C", Arrays.asList("1", "2", "3", "4"));
        Company c4 = new Company("D", Arrays.asList("3", "4", "2", "1"));

        programmers.addAll(Arrays.asList(p1, p2, p3, p4));
        companies.addAll(Arrays.asList(c1, c2, c3, c4));

        Programmer p1 = new Programmer("1", Arrays.asList("A", "F", "D", "B", "C", "E"));
        Programmer p2 = new Programmer("2", Arrays.asList("B", "E", "A", "D", "C", "F"));
        Programmer p3 = new Programmer("3", Arrays.asList("C", "D", "F", "E", "A", "B"));
        Programmer p4 = new Programmer("4", Arrays.asList("D", "C", "A", "B", "E", "F"));
        Programmer p5 = new Programmer("5", Arrays.asList("E", "B", "A", "C", "D", "F"));
        Programmer p6 = new Programmer("6", Arrays.asList("F", "A", "B", "C", "E", "D"));

        Company c1 = new Company("A", Arrays.asList("2", "5", "1", "3", "4", "6"));
        Company c2 = new Company("B", Arrays.asList("1", "2", "3", "6", "4", "5"));
        Company c3 = new Company("C", Arrays.asList("5", "6", "3", "2", "1", "4"));
        Company c4 = new Company("D", Arrays.asList("1", "3", "2", "4", "5", "6"));
        Company c5 = new Company("E", Arrays.asList("6", "2", "3", "5", "4", "1"));
        Company c6 = new Company("F", Arrays.asList("2", "3", "5", "4", "6", "1"));

        programmers.addAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
        companies.addAll(Arrays.asList(c1, c2, c3, c4, c5, c6));

        Programmer p1 = new Programmer("1", Arrays.asList("B", "D", "A", "C"));
        Programmer p2 = new Programmer("2", Arrays.asList("B", "D", "C", "A"));
        Programmer p3 = new Programmer("3", Arrays.asList("B", "A", "D", "C"));
        Programmer p4 = new Programmer("4", Arrays.asList("D", "C", "A", "B"));

        Company c1 = new Company("A", Arrays.asList("2", "3", "1", "4"));
        Company c2 = new Company("B", Arrays.asList("2", "4", "3", "1"));
        Company c3 = new Company("C", Arrays.asList("3", "2", "4", "1"));
        Company c4 = new Company("D", Arrays.asList("2", "3", "1", "4")); 

        programmers.addAll(Arrays.asList(p1, p2, p3, p4));
        companies.addAll(Arrays.asList(c1, c2, c3, c4)); */ 


        Map<Programmer, Company> matching = findSatisfactoryMatching(programmers, companies);

        // Print the matching results
        System.out.println("Satisfactory Programmer-Company Matches:");
        for (Map.Entry<Programmer, Company> entry : matching.entrySet()) {
            Programmer programmer = entry.getKey();
            Company company = entry.getValue();
            System.out.println(programmer.name + " - " + (company != null ? company.name : "Unmatched"));
        }
    }

   // this is where the Gale-Shapley algorithm is implemented 
   public static Map<Programmer, Company> findSatisfactoryMatching(List<Programmer> programmers, List<Company> companies) {
    // Initialize a map to store the matching between programmers and companies
    Map<Programmer, Company> matching = new HashMap<>();
    
    // Continue until all programmers are matched
    while (!allProgrammersAreMatched(programmers)) {
        // Find a programmer who is not yet matched
        Programmer freeProgrammer = getFreeProgrammer(programmers);
        
        // Get the top preference of the free programmer (their preferred company)
        String preferredCompany = freeProgrammer.preferences.get(0);
        
        // Find the company object based on its name
        Company company = getCompanyByName(companies, preferredCompany);

        if (company.matchedProgrammer == null) {
            // The company is unassigned, assign the programmer to it
            company.matchedProgrammer = freeProgrammer.name;
            freeProgrammer.matchedCompany = company.name;
            
            // Add the matching pair to the result map
            matching.put(freeProgrammer, company);
        } else {
            // The company already has a matched programmer
            Programmer currentMatch = getProgrammerByName(programmers, company.matchedProgrammer);
            
            // Check if the free programmer prefers this company over their current match
            if (isCompanyPreferredOverCurrent(freeProgrammer, currentMatch, company)) {
                // Update the matching to reflect the new assignment
                matching.put(freeProgrammer, company);
                matching.put(currentMatch, null);
                currentMatch.matchedCompany = null;
                company.matchedProgrammer = freeProgrammer.name;
                freeProgrammer.matchedCompany = company.name;
            }
        }
        
        // Remove the top preference (company) from the free programmer's list
        freeProgrammer.preferences.remove(0);
    }
    
    // Return the final matching
    return matching;
}

    // returns true if all programmers are matched 
    public static boolean allProgrammersAreMatched(List<Programmer> programmers) {
        for (Programmer programmer : programmers) {
            if (programmer.matchedCompany == null) {
                return false;
            }
        }
        return true;
    }
    // method to return programmers without a company 
    public static Programmer getFreeProgrammer(List<Programmer> programmers) {
        for (Programmer programmer : programmers) {
            if (programmer.matchedCompany == null) {
                return programmer;
            }
        }
        return null;
    }
    // this method is called in a situation where we have am unpaired programmer whose top 
    // preference is a company which is already paired with another programmer. This method 
    // returns true if the company prefers the unpaired programmer over its current match.
    public static boolean isCompanyPreferredOverCurrent(Programmer programmer, Programmer currentMatch, Company company) {
        return company.preferences.indexOf(programmer.name) < company.preferences.indexOf(currentMatch.name);
    }
    // method to return the name of a company 
    public static Company getCompanyByName(List<Company> companies, String name) {
        for (Company company : companies) {
            if (company.name.equals(name)) {
                return company;
            }
        }
        return null;
    }

    // method to return the name of a programmer 
    public static Programmer getProgrammerByName(List<Programmer> programmers, String name) {
        for (Programmer programmer : programmers) {
            if (programmer.name.equals(name)) {
                return programmer;
            }
        }
        return null;
    }
}
