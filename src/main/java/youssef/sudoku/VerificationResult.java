package youssef.sudoku;
import java.util.ArrayList;
import java.util.List;
public class VerificationResult {
    private boolean valid;
    private List<DuplicateEntry> list;
    public VerificationResult(boolean v, List<DuplicateEntry> l) {
        this.valid = v;
        this.list = l;
    }
    public boolean isValid() {
        return valid;
    }
    public List<DuplicateEntry> getDuplicates() {
        return list;
    }
    public void setList(ArrayList<DuplicateEntry> list) {
        this.list = list;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
