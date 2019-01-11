import java.util.Scanner;

public class Solution_6719_성수의프로그래밍강좌시청_박수빈 {
	static int[] arr;
	
	static int maxVal() {
		int max = arr[0];
		int maxidx = 0;
		for(int i=1; i<arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
				maxidx = i;
			}
		}
		arr[maxidx] = 0;
		return max;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			arr = new int[N];
			int[] M = new int[K];
			double A = 0;
			
			for(int i=0; i<N; i++)
				arr[i] = sc.nextInt();
			
			for(int i=0; i<K; i++)
				M[i] = maxVal();
			
			for(int i=K-1; i>=0; i--)
				A = (A+M[i]) / 2;
			
			System.out.println("#" + tc + " " + A);
		}
		sc.close();
	}
}
