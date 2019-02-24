import java.util.Scanner;

/*
 * 하나의 행에 하나의 퀸을 배치하는 방법으로
 * 만약 이전 행들 중에서 자신과 같은 열에 있거나 왼쪽 대각선 , 오른쪽 대각선에 있는지 확인하여 백트래킹한다
 * 왼쪽 대각선은 행과 열을 뺀 값이 같고 오른쪽 대각선은 행과 열을 더한 값이 같다
 */

public class Main_9663_NQueen {
	static int[] arr;
	static int n, cnt;
	
	public static void dfs(int idx) {
		if(idx == n) {
			cnt++;
			return;
		}
		
		for(int i = 0; i < n; i++) {
			arr[idx] = i;
			boolean flag = false;
			for(int j = 0; j < idx; j++) {
				if(arr[j] == i || j - arr[j] == idx - arr[idx] || j + arr[j] == idx + arr[idx]) {//같은 열과 대각선 체크
					flag = true;
					break;
				}
			}
			if(flag == false) {//같은 열 또는 대각선에 퀸이 없는 경우
				dfs(idx + 1);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[0] = i;
			dfs(1);
		}
		
		System.out.println(cnt);
		sc.close();
	}

}
