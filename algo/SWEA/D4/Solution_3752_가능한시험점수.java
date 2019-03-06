import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
HashSet에 있는 점수를 모두 꺼내서 현재 선택된 배점을 더해서 다시 넣는다.
마지막에 0점을 고려 안했으므로 HashSet의 사이즈에 1을 더해서 출력한다.
*/
public class Solution_3752_가능한시험점수 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int[] score = new int[N];
			for(int i = 0; i < N; i++)
				score[i] = Integer.parseInt(st.nextToken());
			
			HashSet<Integer> s = new HashSet<>();
			for(int i = 0; i < N; i++) {
				Object[] temp = s.toArray();
				for(int j = 0; j < temp.length; j++)
					s.add((int)temp[j] + score[i]);
				s.add(score[i]);
			}
			
			sb.append("#" + tc + " " + (s.size() + 1) + "\n");
		}
		System.out.print(sb);
	}

}
