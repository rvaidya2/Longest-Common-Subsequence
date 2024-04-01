// import java.util.Scanner;

public class Vaidya_Rujuta_pa3_lcs {

    public static void main(String[] args) {

        // Check if two input strings are provided
        if (args.length != 2) {
            System.out.println("Error : Please provide two input strings.");
            System.exit(-1);
//            return -1;
        }

        // Initialize input strings X and Y
        String X = args[0];
        String Y = args[1];

        // Get lengths of X and Y
        int m = X.length();
        int n = Y.length();

        //Check if length of 2 Strings is greater than 100
        if(m > 100 || n > 100){
            System.out.println("Error : String length greater than 100");
            System.exit(-2);
//            return -2;
        }

        // Initialize c and b arrays
        // Matrix c is used to store the length of the longest common subsequence of two strings
        int[][] c = new int[m+1][n+1];
       // Matrix b is used to store the optimal choice made for constructing the LCS
        char[][] b = new char[m+1][n+1];

        // Initializes the first row and column of matrix c with 0 to consider the empty string as a subsequence.
        for(int i=0; i<=m; i++) {
            c[i][0] = 0;
        }
        for(int j=0; j<=n; j++) {
            c[0][j] = 0;
        }

        // Computes the length of LCS for X and Y, and fills up the b matrix with directional arrows to trace back the LCS.
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(X.charAt(i-1) == Y.charAt(j-1)) {
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i][j] = 'D'; // Diagonal Arrow
                }
                else if(c[i-1][j] >= c[i][j-1]) {
                    c[i][j] = c[i-1][j];
                    b[i][j] = 'U'; // Up Arrow
                }
                else {
                    c[i][j] = c[i][j-1];
                    b[i][j] = 'L'; // Left Arrow
                }
            }
        }

        // extracts LCS by tracing back through matrix b, starting from the bottom-right corner

        StringBuilder lcs = new StringBuilder();
        int i = m;
        int j = n;
        while(i > 0 && j > 0) {
            if(b[i][j] == 'D') {
                lcs.append(X.charAt(i-1));
                i--;
                j--;
            }
            else if(b[i][j] == 'U') {
                i--;
            }
            else {
                j--;
            }
        }

        // Reverse the LCS string and print it
        String lcsOut = lcs.reverse().toString();
        System.out.println("Length of Longest Common Sequence " +lcsOut.length());
        System.out.println("Longest Common Sequence " + lcsOut);

//        return 0;
    }
}
