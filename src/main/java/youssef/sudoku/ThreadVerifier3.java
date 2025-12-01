package youssef.sudoku;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ThreadVerifier3 implements SudokuVerifier {
    private List<DuplicateEntry> list = Collections.synchronizedList(new ArrayList<>());
    private int[][] board;
    public VerificationResult verify(int[][] board) {
        this.board = board;
        Thread t1 = new Thread(new RowChecker());
        Thread t2 = new Thread(new ColChecker());
        Thread t3 = new Thread(new BoxChecker());
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted");
        }
        return new VerificationResult(list.isEmpty(), list);
    }
    class RowChecker implements Runnable {
        public void run() {
            SequentialVerifier s = new SequentialVerifier();
            for (int i = 0; i < 9; i++)
                list.addAll(s.checkRow(board, i));
        }
    }
    class ColChecker implements Runnable {
        public void run() {
            SequentialVerifier s = new SequentialVerifier();
            for (int i = 0; i < 9; i++)
                list.addAll(s.checkColumn(board, i));
        }
    }
    class BoxChecker implements Runnable {
        public void run() {
            SequentialVerifier s = new SequentialVerifier();
            for (int i = 0; i < 9; i++)
                list.addAll(s.checkBox(board, i));
        }
    }
    public void setList(List<DuplicateEntry> list) {
        this.list = list;
    }
    public List<DuplicateEntry> getList() {
        return list;
    }
    public void setBoard(int[][] board) {
        this.board = board;
    }
    public int[][] getBoard() {
        return board;
    }
}
