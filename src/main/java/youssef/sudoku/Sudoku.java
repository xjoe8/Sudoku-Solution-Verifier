package youssef.sudoku;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Sudoku {
    public static void main(String[] args) {
        String path = args[0];
        int mode = Integer.parseInt(args[1]);
        if(mode!=0 && mode !=3 && mode != 27){
            System.out.println("Error Please Choose a Valid Mode");
            System.exit(0);
        }
        int[][] board = new int[9][9];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            int r = 0;
            String line;
            for (int i=0; i<9;i++){
                line = reader.readLine();
                String[] arr = line.split(",");
                for (int c = 0; c < 9; c++)
                    board[r][c] = Integer.parseInt(arr[c]);
                r++;
            }
            SudokuVerifier verifier = VerifierFactory.create(mode);
            VerificationResult result = verifier.verify(board);
            if (result.isValid())
                System.out.println("VALID");
            else {
                System.out.println("INVALID");
                for (DuplicateEntry d : result.getDuplicates()) {
                    System.out.println(d.getType() + " " + d.getIndex() + ", #" + d.getDuplicatedValue() + ", " + d.getPositions());
                }
            }
        } catch (IOException e) {
            System.out.println("Error in Reading CSV File");
        }
    }
}