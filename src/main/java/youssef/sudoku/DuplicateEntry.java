package youssef.sudoku;
import java.util.ArrayList;
public class DuplicateEntry {
    private String type;
    private int index;
    private int duplicatedValue;
    private ArrayList<Integer> positions;
    public DuplicateEntry(String t, int i, int v, ArrayList<Integer> p) {
        this.type = t;
        this.index = i;
        this.duplicatedValue = v;
        this.positions = p;
    }
    public ArrayList<Integer> getPositions() {
        return positions;
    }
    public int getIndex() {
        return index;
    }
    public int getDuplicatedValue() {
        return duplicatedValue;
    }
    public String getType() {
        return type;
    }
    public void setDuplicatedValue(int duplicatedValue) {
        this.duplicatedValue = duplicatedValue;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setPositions(ArrayList<Integer> positions) {
        this.positions = positions;
    }
}