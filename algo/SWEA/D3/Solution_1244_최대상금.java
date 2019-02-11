import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*완전탐색 */
public class Solution_1244_최대상금 {
	
	static int max;
	static boolean[][] visited;
	
	public static void swap(StringBuilder sb, int i, int j) {
		char c = sb.charAt(i);
		sb.deleteCharAt(i);
		sb.insert(i, sb.charAt(j - 1));
		sb.deleteCharAt(j);
		sb.insert(j, c);
	}
	
	public static void dfs(StringBuilder sb, int chg) {
		if(visited[Integer.parseInt(sb.toString())][chg]) return;
		visited[Integer.parseInt(sb.toString())][chg] = true;
		if(chg == 0) {
			if(max < Integer.parseInt(sb.toString()))
				max = Integer.parseInt(sb.toString());
			return;
		}
		
		StringBuilder temp = new StringBuilder(sb);
		
		
		for(int i = 0; i < sb.length() - 1; i++) {
			for(int j = i + 1; j < sb.length(); j++) {
				swap(sb, i, j);
				dfs(sb, chg - 1);
				sb = new StringBuilder(temp);
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			StringBuilder sb = new StringBuilder(st.nextToken());
			int chg = Integer.parseInt(st.nextToken());
			visited = new boolean[1000000][chg + 1];
			
			dfs(sb, chg);
			
			System.out.println("#" + tc + " " + max);
			max = 0;
		}
	}

}
