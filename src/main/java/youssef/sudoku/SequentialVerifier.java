package youssef.sudoku;
import java.util.ArrayList;
import java.util.HashMap;
public class SequentialVerifier implements SudokuVerifier {
    public VerificationResult verify(int[][] board) {
        ArrayList<DuplicateEntry> list = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            list.addAll(checkRow(board, i));
        for (int i = 0; i < 9; i++)
            list.addAll(checkColumn(board, i));
        for (int i = 0; i < 9; i++)
            list.addAll(checkBox(board, i));
        return new VerificationResult(list.isEmpty(), list);
    }
    public ArrayList<DuplicateEntry> checkRow(int[][] board, int r) {
        HashMap<Integer, ArrayList<Integer> > map = new HashMap<>();
        for (int c = 0; c < 9; c++) {
            int v = board[r][c];
            map.putIfAbsent(v, new ArrayList<>());
            map.get(v).add(c + 1);
        }
        ArrayList<DuplicateEntry> out = new ArrayList<>();
        for (int k : map.keySet()) {
            if (map.get(k).size() > 1)
                out.add(new DuplicateEntry("ROW", r + 1, k, map.get(k)));
        }
        return out;
    }
    public ArrayList<DuplicateEntry> checkColumn(int[][] board, int c) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int r = 0; r < 9; r++) {
            int v = board[r][c];
            map.putIfAbsent(v, new ArrayList<>());
            map.get(v).add(r + 1);
        }
        ArrayList<DuplicateEntry> out = new ArrayList<>();
        for (int k : map.keySet()) {
            if (map.get(k).size() > 1)
                out.add(new DuplicateEntry("COL", c + 1, k, map.get(k)));
        }
        return out;
    }
    public ArrayList<DuplicateEntry> checkBox(int[][] board, int b) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int sr = (b / 3) * 3;
        int sc = (b % 3) * 3;
        for (int r = sr; r < sr + 3; r++) {
            for (int c = sc; c < sc + 3; c++) {
                int v = board[r][c];
                map.putIfAbsent(v, new ArrayList<>());
                map.get(v).add((r - sr) * 3 + (c - sc) + 1);
            }
        }
        ArrayList<DuplicateEntry> out = new ArrayList<>();
        for (int k : map.keySet()) {
            if (map.get(k).size() > 1)
                out.add(new DuplicateEntry("BOX", b + 1, k, map.get(k)));
        }
        return out;
    }
}
