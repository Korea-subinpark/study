import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1828_냉장고 {
	
	public static class C implements Comparable<C> {
		int min, max;
		public C (int min, int max) {
			this.min = min;
			this.max = max;
		}
		@Override
		public int compareTo(C o) {
			if(min != o.min)
				return min - o.min;
			else
				return max - o.max;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		C[] list = new C[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			list[i] = new C(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(list);//최저온도가 낮은 순으로, 같다면 범위가 좁은 순으로 정렬
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			boolean flag = false;
			int min = list[i].max;//최저온도를 업데이트
			for(int j = i + 1; j < N; j++) {
				if(min > list[j].max)
					min = list[j].max;
				if(min < list[j].min) {
					flag = true;
					i = j - 1;
					break;
				}
			}
			ans++;//냉장고 수 증가
			if(flag == false)//모든 화학물질을 다 넣었을 때
				break;
		}
		System.out.println(ans);
	}

}
