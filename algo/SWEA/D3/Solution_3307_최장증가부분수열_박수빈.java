import java.util.Scanner;
 /*
 현재 원소보다 작은 이전 원소들 중에서 가장 큰 dp값을 구하여 1을 더한다
 */
public class Solution {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[] arr = new int[N];
            int[] dp = new int[N];
            for(int i = 0; i < N; i++)
                arr[i] = sc.nextInt();
            dp[0] = 1;
            int max = 0;//현재 원소보다 작은 수 중 dp값의 최대값
            int ans = 0;//dp 배열의 최대값
            for(int i = 1; i < N; i++) {
                for(int j = i - 1; j >= 0; j--) {
                    if(arr[i] > arr[j]) {
                        if(dp[j] > max)
                            max = dp[j];
                    }
                }
                dp[i] = max + 1;
                max = 0;
                if(dp[i] > ans)
                    ans = dp[i];
            }
            System.out.println("#" + tc + " " + ans);
        }
         
        sc.close();
    }
 
}
