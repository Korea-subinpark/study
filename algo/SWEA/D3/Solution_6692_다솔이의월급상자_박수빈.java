import java.util.Scanner;

public class Solution_6692_�ټ����ǿ��޻���_�ڼ��� {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			double ans = 0;
			for(int i=0; i<N; i++) {
				double prob = sc.nextDouble();
				double money = sc.nextDouble();
				ans += prob * money;
			}
			System.out.println("#" + tc + " " + ans);
		}
		sc.close();
	}
}
