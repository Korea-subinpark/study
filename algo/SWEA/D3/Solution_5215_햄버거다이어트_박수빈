import java.util.Scanner;
 
public class Solution {
    static int N, maxKal, maxScore;
    static int[] score;
    static int[] kal;
     
    static void dfs(int idx, int scoreVal, int kalVal) {
        if(kalVal > maxKal)
            return;
        if(idx == N) {
            if(scoreVal > maxScore)
                maxScore = scoreVal;
            return;
        }
        dfs(idx+1, scoreVal+score[idx], kalVal+kal[idx]);
        dfs(idx+1, scoreVal, kalVal);
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int t=1; t<=tc; t++) {
            N = sc.nextInt();
            score = new int[N];
            kal = new int[N];
            maxKal = sc.nextInt();
            maxScore = 0;
            for(int i=0; i<N; i++) {
                score[i] = sc.nextInt();
                kal[i] = sc.nextInt();
            }
            dfs(0,0,0);
            System.out.println("#" + t + " " + maxScore);
        }
        sc.close();
    }
 
}
