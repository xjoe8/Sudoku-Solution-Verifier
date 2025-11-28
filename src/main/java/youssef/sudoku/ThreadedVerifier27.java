package youssef.sudoku;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ThreadedVerifier27 implements SudokuVerifier {
    private List<DuplicateEntry> list = Collections.synchronizedList(new ArrayList<>());
    private int[][] board;
    public VerificationResult verify(int[][] board) {
        this.board = board;
        Thread[] arr = new Thread[27];
        SequentialVerifier s = new SequentialVerifier();
        for (int i = 0; i < 9; i++)
            arr[i] = new Thread(() -> list.addAll(s.checkRow(this.board, Integer.parseInt(Thread.currentThread().getName()))));
        for (int i = 0; i < 9; i++)
            arr[i + 9] = new Thread(() -> list.addAll(s.checkColumn(this.board, Integer.parseInt(Thread.currentThread().getName()))));
        for (int i = 0; i < 9; i++)
            arr[i + 18] = new Thread(() -> list.addAll(s.checkBox(this.board, Integer.parseInt(Thread.currentThread().getName()))));
        for (int i = 0; i < 27; i++) {
            arr[i].setName(String.valueOf(i % 9));
            arr[i].start();
        }
        try {
            for (int i = 0; i < 27; i++)
                arr[i].join();
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted");
        }
        return new VerificationResult(list.isEmpty(), list);
    }
    public void setBoard(int[][] board) {
        this.board = board;
    }
    public int[][] getBoard() {
        return board;
    }
    public void setList(List<DuplicateEntry> list) {
        this.list = list;
    }
    public List<DuplicateEntry> getList() {
        return list;
    }
}
