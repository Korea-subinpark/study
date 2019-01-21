/*
사다리를 역으로 따라 올라가는 방식
좌우를 우선으로 살피고 좌우에 연결된 길이 있다면 따라간다
*/

import java.util.Scanner;
 
public class Solution {
    static int[][] arr;
    static int N = 100;
    static boolean flag = false;
 
    public static int climb(int x, int y) {
        for (int i = x; i >= 0; i--) {
            if (y + 1 < N && arr[i][y + 1] == 1) {
                flag = true;
                return i;
            }
            else if(y - 1 >= 0 && arr[i][y - 1] == 1) {
                flag = false;
                return i;
            }
        }
        return 0;
    }
 
    public static int right(int x, int y) {
        for (int i = y; i < N; i++) {
            if (arr[x - 1][i] == 1)
                return i;
        }
        return N-1;
    }
 
    public static int left(int x, int y) {
        for (int i = y; i >= 0; i--) {
            if (arr[x - 1][i] == 1)
                return i;
        }
        return 0;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int t = 1; t <= 10; t++) {
            arr = new int[N][N];
            int X = N-1, Y = 0;
            sc.nextInt();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    arr[i][j] = sc.nextInt();
            }
 
            for (int i = 0; i < N; i++) {
                if (arr[X][i] == 2) {
                    Y = i;// 도착점 찾기
                    break;
                }
            }
             
            while (true) {
                X = climb(X, Y);
                if (X == 0){
                    System.out.println("#" + t + " " + Y);
                    break;
                }
                else {
                    if (flag == true)
                        Y = right(X, Y + 1);
                    else
                        Y = left(X, Y - 1);
                }
                X--;
            }
 
        }
 
        sc.close();
    }
 
}
