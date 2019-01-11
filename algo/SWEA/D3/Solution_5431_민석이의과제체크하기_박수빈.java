import java.util.Scanner;

public class Solution_5431_민석이의과제체크하기_박수빈 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			int subN = sc.nextInt();
			int[] arr = new int[N];
			
			for(int i=0; i<subN; i++)
				arr[sc.nextInt()-1] = 1;//제출한 사람은 1
			
			String ans ="#" + tc;
			
			for(int i=0; i<N; i++) {
				if(arr[i] == 0)
					ans += " " + (i+1);
			}
			System.out.println(ans);
		}
		sc.close();
	}

}
