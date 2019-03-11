import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_2247_도서관 {
	static ArrayList<Time> list;
	
	public static class Time implements Comparable<Time> {
		int s, e;
		public Time(int s, int e) {
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(Time o) {
			return s - o.s;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			list.add(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(list);
		int min = list.get(0).s;//학생이 없다가 들어온 시간
		int max = list.get(0).e;//학생이 있었던 마지막 시간
		int no = 0;//학생이 없었던 시간
		int yes = 0;//학생이 있었던 시간
		for(int i = 1; i < list.size(); i++) {
			Time cur = list.get(i);
			if(cur.s > max) {//i번 째로 온 학생이 아무도 없을 때 온 경우
				if(no < cur.s - max)//학생이 없었던 시간 계산하여 업데이트
					no = cur.s - max;
				min = cur.s;//들어온 시간과 나간 시간 업데이트
				max = cur.e;
				if(yes < max - min)//학생이 있었던 시간 업데이트
					yes = max - min;
			} else {//i번 째 학생이 이미 학생들이 있을 때 온 경우
				if(max < cur.e) {//집에 가는 시간 비교
					max = cur.e;
					if(yes < max - min)//학생이 있었던 시간 업데이트
						yes = max - min;
				}
			}
		}
		System.out.println(yes + " " + no);
	}

}
