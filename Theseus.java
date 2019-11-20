import java.io.*;
import java.util.*;

public class Theseus {

    public static void main(String[] args) {
        String myString;
        File file = new File("test.txt");
        try {
            Scanner scanner = new Scanner(file);
            myString = scanner.nextLine();
            while (scanner.hasNextLine()) {
                myString += scanner.nextLine();
            }
            myString = myString.replaceAll(" ", "");
            char[] myChars = myString.toCharArray();
            int[] myInfo = new int[4];
            for (int i = 0; i < 4; i++) {
                myInfo[i] = Character.getNumericValue(myChars[i]);
            }
            int n = myInfo[0];
            int m = myInfo[1];
            int startI = myInfo[2];
            int startJ = myInfo[3];
            if (myChars.length != m*n + 4) {
                System.out.println("Given matrix coordinates do not match with actual coordinates.");
                System.exit(1);
            }
            boolean contains = false;
            for (char c : myChars) {
                if (c == 'E') {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                System.out.println("Given matrix does not contain E.");
                System.exit(1);
            }
            char[][] newChars = new char[n][m];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    newChars[i][j] = myChars[sum + 4];
                    sum++;
                }
            }
            if (newChars[startI][startJ] != 'E') {
                System.out.println("Given starting point does not match with actual starting point.");
                System.exit(1);
            }
            if (hasExit(newChars, n, m, startI, startJ)) {
                System.out.println("Path Found!\n");
            }
            else {
                System.out.println("No Path Found!\n");
            }

        }

        catch (FileNotFoundException e) {
            System.out.println("The requested file could not be found.");
            System.exit(1);
        }

    }

    private static boolean hasExit(char[][] maze, int n, int m, int i, int j) {
        int d = -1;
        boolean[][] visited = new boolean[n][m];
        for (int a = 0; a < visited.length; a++) {
            for (int b = 0; b < (visited[a]).length; b++) {
                visited[a][b] = false;
            }
        }
        myStackImpl<int[]> s = new myStackImpl<>();
        s.push(new int[]{i, j});
        while (!(s.isEmpty())) {
            int[] temp = s.peek();
            d += 1;
            i = temp[0];
            j = temp[1];
            if (((i == 0) || (i == n-1) || (j == 0) || (j == m-1)) && (maze[i][j] == '0')) {
                System.out.println("Exit coordinates: " + "maze[" + i + "," + j + "]");
                return true;
            }
            if (d == 0) {
                if ((i-1 >= 0) && (maze[i-1][j] == '0') && (!(visited[i-1][j]))) {
                    visited[i-1][j] = true;
                    s.push(new int[]{i-1, j});
                    d = -1;
                }
            }
            else if (d == 1) {
                if ((j-1 >= 0) && (maze[i][j-1] == '0') && (!(visited[i][j-1]))) {
                    visited[i][j-1] = true;
                    s.push(new int[]{i, j-1});
                    d = -1;
                }
            }
            else if (d == 2) {
                if ((i+1 < n) && (maze[i+1][j] == '0') && (!(visited[i+1][j]))) {
                    visited[i+1][j] = true;
                    s.push(new int[]{i+1, j});
                    d = -1;
                }
            }
            else if (d == 3) {
                if ((j+1 < m) && (maze[i][j+1] == '0') && (!(visited[i][j+1]))) {
                    visited[i][j+1] = true;
                    s.push(new int[]{i, j+1});
                    d = -1;
                }
            }
            else {
                s.pop();
                d = -1;
            }
        }
        return false;
    }

}
