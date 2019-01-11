import java.util.Scanner;
public class Solution {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int tc = sc.nextInt();
        for(int t=1; t<=tc; t++) {
            int N = sc.nextInt();
            int[] arr = new int[N];
            int[] dp = new int[N];
            for(int i=0; i<N; i++)
                arr[i] = sc.nextInt();
            int maxCnt = 0, answer = 0;
            for(int i=0; i<N; i++) {
                for(int j=i-1; j>=0; j--) {
                    if(arr[i] > arr[j]) {
                        if(dp[j] > maxCnt)
                            maxCnt = dp[j];
                    }
                }
                if(maxCnt == 0)
                    dp[i] = 1;
                else
                    dp[i] = maxCnt + 1;
                maxCnt = 0;
                if(dp[i] > answer)
                    answer = dp[i];
            }
             
            System.out.println("#" + t + " " + answer);
             
        }
        sc.close();
    }
 
}
