import java.util.ArrayList;
import java.util.List;

public class Queen {
    private String id;
    private List<String> prefixes;

    public Queen(String id) {
        this.id = id;
        this.prefixes = prefix(id, id.length());
    }

    public List<String> getPrefixes() {
        return prefixes;
    }

    private List<String> prefix(String str, int n) {
        List<String> result = new ArrayList<>();
        String prestr;
        boolean check = false;

        for (int i = 0; i < n; i++) {
            prestr = "";

            for (int j = i; j < n; j++) {
                prestr += str.charAt(j);
                if (prestr.length() == n) {
                    check = true;
                    break;
                }
                result.add(prestr);
            }

            if (check)
                break;
        }

        return result;
    }
}
