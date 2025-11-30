package youssef.sudoku;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ThreadedVerifier27 implements SudokuVerifier {
    private List<DuplicateEntry> list = Collections.synchronizedList(new ArrayList<>());
    private int[][] board;
    public VerificationResult verify(int[][] board) {
        this.board = board;
        SequentialVerifier s = new SequentialVerifier();
        Thread[] arr = new Thread[27];
        for (int i = 0; i < 9; i++)
            arr[i] = threadCreator(s, 1, i);

        for (int i = 0; i < 9; i++)
            arr[i + 9] = threadCreator(s, 2, i);

        for (int i = 0; i < 9; i++)
            arr[i + 18] = threadCreator(s, 3, i);
        try {
            for (Thread t : arr)
                t.start();
            for (Thread t : arr)
                t.join();
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted");
        }
        return new VerificationResult(list.isEmpty(), list);
    }
    public Thread threadCreator(SequentialVerifier s, int check, int index) {
        Runnable task = () -> {
            switch (check) {
                case 1:
                    list.addAll(s.checkRow(board, index));
                    break;
                case 2:
                    list.addAll(s.checkColumn(board, index));
                    break;
                case 3:
                    list.addAll(s.checkBox(board, index));
                    break;
            }
        };
        Thread thread = new Thread(task);
        thread.setName(String.valueOf(index));
        return thread;
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
