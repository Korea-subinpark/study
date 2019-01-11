import java.util.Scanner;

public class Solution_4796_의석이의우뚝선산_박수빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] arr = new int[N + 1];
			for (int i = 0; i < N; i++)
				arr[i] = sc.nextInt();
			int smallCnt = 0, bigCnt = 0, ans = 0;
			boolean flag = false;
			for (int i = 0; i < N - 1; i++) {
				if (!flag && arr[i] < arr[i + 1]) {
					smallCnt++;
				} else if (!flag && arr[i] > arr[i + 1]) {
					flag = true;
					bigCnt++;
				} else if (flag && arr[i] > arr[i + 1]) {
					bigCnt++;
				} else if (flag && arr[i] < arr[i + 1]) {
					flag = false;
					ans += smallCnt * bigCnt;
					smallCnt = 1;
					bigCnt = 0;
					continue;
				}
			}
			ans += smallCnt * bigCnt;
			System.out.println("#" + tc + " " + ans);
		}
		sc.close();
	}

}
