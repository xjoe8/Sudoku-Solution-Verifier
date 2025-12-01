package youssef.sudoku;
public class VerifierFactory {
    public static SudokuVerifier create(int mode) {
        if (mode == 0)
            return new SequentialVerifier();
        if (mode == 3)
            return new ThreadVerifier3();
        return new ThreadVerifier27();
    }
}
