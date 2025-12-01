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
    public ArrayList<DuplicateEntry> checkRow(int[][] board, int row) {
        HashMap<Integer, ArrayList<Integer> > map = new HashMap<>();
        for (int col = 0; col < 9; col++) {
            int x = board[row][col];
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(col + 1);
        }
        ArrayList<DuplicateEntry> out = new ArrayList<>();
        for (int k : map.keySet()) {
            if (map.get(k).size() > 1)
                out.add(new DuplicateEntry("ROW", row + 1, k, map.get(k)));
        }
        return out;
    }
    public ArrayList<DuplicateEntry> checkColumn(int[][] board, int col) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int row = 0; row < 9; row++) {
            int x = board[row][col];
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(row + 1);
        }
        ArrayList<DuplicateEntry> out = new ArrayList<>();
        for (int k : map.keySet()) {
            if (map.get(k).size() > 1)
                out.add(new DuplicateEntry("COL", col + 1, k, map.get(k)));
        }
        return out;
    }
    public ArrayList<DuplicateEntry> checkBox(int[][] board, int box) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int srow = (box / 3) * 3;
        int scol = (box % 3) * 3;
        for (int row = srow; row < srow + 3; row++) {
            for (int col = scol; col < scol + 3; col++) {
                int x = board[row][col];
                map.putIfAbsent(x, new ArrayList<>());
                map.get(x).add((row - srow) * 3 + (col - scol) + 1);
            }
        }
        ArrayList<DuplicateEntry> out = new ArrayList<>();
        for (int k : map.keySet()) {
            if (map.get(k).size() > 1)
                out.add(new DuplicateEntry("BOX", box + 1, k, map.get(k)));
        }
        return out;
    }
}
