/*
재료에 대한 점수와 칼로리를 배열에 저장하고
현재 재료를 포함하는 dfs와 포함하지 않는 dfs를 모두 호출하여 최대 점수를 계산한다
*/
import java.util.Scanner;

public class Solution_5215_햄버거다이어트_박수빈 {
	static int lim, maxScore;//칼로리 제한, 최대점수
	static int[][] food;//재료의 점수와 칼로리
	static int N;//재료 수
	
	public static void dfs(int i, int totalKal, int totalScore) {
		if(totalKal <= lim && maxScore < totalScore)
			maxScore = totalScore;
		if(i + 1 >= N)
			return;
		dfs(i + 1, totalKal, totalScore);
		dfs(i + 1, totalKal + food[i + 1][1], totalScore + food[i + 1][0]);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			lim = sc.nextInt();
			food = new int[N][2];
			for(int i = 0; i < N; i++) {
				food[i][0] = sc.nextInt();//점수
				food[i][1] = sc.nextInt();//칼로리
			}
			
			for(int i = 0; i < N; i++) 
				dfs(i, food[i][1], food[i][0]);
			
			System.out.println("#" + tc + " " + maxScore);
			maxScore = 0;
		}
		
		sc.close();
	}

}
