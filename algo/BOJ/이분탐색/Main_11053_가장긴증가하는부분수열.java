import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * DP를 이용하면 O[n^2]
 * 이지만 이분 탐색을 이용하면 O[nlogn]에 해결할 수 있다
 * */

class Main_11053_가장긴증가하는부분수열 {
	
	static ArrayList<Integer> C;
	
	public static int binarySearch(int key, int begin, int end) {
		int mid = 0;
		
		while(end > begin) {
			mid = (begin + end) / 2;
			if(C.get(mid) < key)
				begin = mid + 1;
			else end = mid;
		}
		return end;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i++)
        	arr[i] = Integer.parseInt(st.nextToken());
        
        C = new ArrayList<>();
        
        for(int i = 0; i < N; i++) {
        	if(C.isEmpty())
        		C.add(arr[i + 1]);
        	else {
        		if(arr[i + 1] <= C.get(C.size() - 1)) {
        			int idx = binarySearch(arr[i + 1], 0, C.size());
        			C.remove(idx);
            		C.add(idx, arr[i + 1]);
        		} else {
        			C.add(arr[i + 1]);
        		}
        	}
        }
        
        System.out.println(C.size());
    }
}
