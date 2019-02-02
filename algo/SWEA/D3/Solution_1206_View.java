import java.util.Scanner;

public class Solution_1206_SW문제해결기본1일차View_박수빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int t=1; t<=10; t++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			int cnt = 0, temp = 0;
			for(int i=0; i<N; i++)
				arr[i] = sc.nextInt();
			
			for(int i=2; i<N-2; i++) {
				if(arr[i]>arr[i-2] && arr[i]>arr[i-1] && arr[i]>arr[i+1] && arr[i]>arr[i+2]) {
					temp = Math.max(arr[i-2], arr[i-1]);
					temp = Math.max(temp, arr[i+1]);
					temp = Math.max(temp, arr[i+2]);
					cnt += arr[i] - temp;
				}
			}
			
			System.out.println("#" + t + " " + cnt);
		}
		
		sc.close();
	}

}
