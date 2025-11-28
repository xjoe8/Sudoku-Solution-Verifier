package youssef.sudoku;
public class VerifierFactory {
    public static SudokuVerifier create(int mode) {
        if (mode == 0)
            return new SequentialVerifier();
        if (mode == 3)
            return new ThreadedVerifier3();
        return new ThreadedVerifier27();
    }
}
