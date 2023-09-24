import java.util.*;

public class StableMatching {

    public static void main(String[] args) {
        List<Programmer> programmers = new ArrayList<>();
        List<Company> companies = new ArrayList<>();

        // Initialize programmers and companies with their preferences
        Programmer p1 = new Programmer("P1", Arrays.asList("C1", "C2", "C3"));
        Programmer p2 = new Programmer("P2", Arrays.asList("C2", "C1", "C3"));
        Programmer p3 = new Programmer("P3", Arrays.asList("C2", "C3", "C1"));

        Company c1 = new Company("C1", Arrays.asList("P1", "P2", "P3"));
        Company c2 = new Company("C2", Arrays.asList("P2", "P1", "P3"));
        Company c3 = new Company("C3", Arrays.asList("P3", "P1", "P2"));

        programmers.addAll(Arrays.asList(p1, p2, p3));
        companies.addAll(Arrays.asList(c1, c2, c3));

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
        Map<Programmer, Company> matching = new HashMap<>();
        while (!allProgrammersAreMatched(programmers)) {
            Programmer freeProgrammer = getFreeProgrammer(programmers);
            String preferredCompany = freeProgrammer.preferences.get(0);
            Company company = getCompanyByName(companies, preferredCompany);

            if (company.matchedProgrammer == null) {
                // The company is unassigned, assign the programmer to it
                company.matchedProgrammer = freeProgrammer.name;
                freeProgrammer.matchedCompany = company.name;
                matching.put(freeProgrammer, company);
            } else {
                Programmer currentMatch = getProgrammerByName(programmers, company.matchedProgrammer);
                if (isCompanyPreferredOverCurrent(freeProgrammer, currentMatch, company)) {
                    // The programmer prefers this company over their current match
                    matching.put(freeProgrammer, company);
                    matching.put(currentMatch, null);
                    currentMatch.matchedCompany = null;
                    company.matchedProgrammer = freeProgrammer.name;
                    freeProgrammer.matchedCompany = company.name;
                }
            }
            freeProgrammer.preferences.remove(0);
        }
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
    // returns true if another company is preferred over current, which is 1/2 the case for an unsatisfactory pairing
    public static boolean isCompanyPreferredOverCurrent(Programmer programmer, Programmer currentMatch, Company company) {
        return programmer.preferences.indexOf(company.name) < programmer.preferences.indexOf(currentMatch.matchedCompany);
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
