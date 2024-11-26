import java.util.ArrayList;
import java.util.List;

public class Worker {
    private String id;
    private List<String> substrings;

    public Worker(String id) {
        this.id = id;
        this.substrings = substring(id, id.length());
    }

    public List<String> getSubstrings() {
        return substrings;
    }

    private List<String> substring(String str, int n) {
        List<String> result = new ArrayList<>();
        String substr;

        for (int i = 0; i < n; i++) {
            substr = "";
            for (int j = i; j < n; j++) {
                substr += str.charAt(j);
                result.add(substr);
            }
        }

        return result;
    }
}
