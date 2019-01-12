import java.util.Scanner;

public class Main {
	static int[][] arr;
	static int[][] ans;
	static boolean[] chk;
	static int N;
	
	static void dfs(int i) {
		for(int j=0; j<N; j++) {
			if(arr[i][j] == 1 && !chk[j]) {
				chk[j] = true;
				ans[i][j] = 1;
				for(int k=0; k<N; k++) {
					if(ans[k][i] == 1)
						ans[k][j] = 1;
				}
					dfs(j);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][N];
		ans = new int[N][N];
		chk = new boolean[N];
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				arr[i][j] = sc.nextInt();
		
		for(int i=0; i<N; i++) {
			dfs(i);
			chk = new boolean[N];
		}
			
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				System.out.print(ans[i][j] + " ");
			System.out.println();
		}
		sc.close();
	}

}
