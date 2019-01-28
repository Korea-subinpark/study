import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_1228_SW문제해결기본8일차_암호문1_박수빈 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			StringBuilder answer = new StringBuilder();
			answer.append("#" + tc + " ");
			
			LinkedList<Integer> list = new LinkedList<>();
			
			for(int i = 0; i < N; i++)
				list.add(Integer.parseInt(st.nextToken()));
			
			int L = Integer.parseInt(br.readLine().trim());
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < L; i++) {
				st.nextToken();//I
				int s = Integer.parseInt(st.nextToken());//삽입위치
				int num = Integer.parseInt(st.nextToken());//개수
				for(int j = s; j < s + num; j++) {
					list.add(j, Integer.parseInt(st.nextToken()));
				}
			}
			
			for(int i = 0; i < 10; i++) {
				answer.append(list.get(i) + " ");
			}
			System.out.println(answer);
		}
	}

}
