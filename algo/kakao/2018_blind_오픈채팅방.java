import java.util.ArrayList;
import java.util.HashMap;
/*
입력되는 record에서 id를 추출하고 각 이벤트(입장, 퇴장, 닉네임변경)에 따라 자료구조에 저장한다
ArrayList에는 유저의 id와 입장과 퇴장 메세지를 저장한다
HashMap에는 key는 id, value에는 닉네임을 저장한다. HashMap의 특성상 같은 id로 입력하면 자동으로 새로운 닉네임이 저장된다

입장일 경우 두 가지 케이스가 존재한다
1. 새롭게 입장
2 기존 유저가 퇴장 후 새로운 닉네임으로 입장
id와 함께 입장했다는 메세지를 ArrayList에 저장하고 닉네임은 Map에 저장한다

퇴장의 경우 닉네임이 바뀌지 않으므로 ArrayList에만 저장한다

닉네임을 수정한 경우 Map에만 저장한다

ArrayList에 저장된 id로 Map에 저장된 닉네임을 불러오고 메세지를 덧붙여 answer 배열에 저장한다
*/
public class Kakao {
	

    public static String[] solution(String[] record) {
        String[] answer = {};
        ArrayList<String[]> events = new ArrayList<>();
        HashMap<String, String> nickname = new HashMap<>();
        
        for(int i = 0; i < record.length; i++) {
            String[] temp = record[i].split(" ");
            if(temp[0].equals("Enter")){
            	String[] str = {temp[1], "님이 들어왔습니다."};
            	events.add(str);
                nickname.put(temp[1], temp[2]);
            } else if(temp[0].equals("Leave")){
            	String[] str = {temp[1], "님이 나갔습니다."};
            	events.add(str);
            } else {
                nickname.put(temp[1], temp[2]);
            }
        }
        
        answer = new String[events.size()];
        for(int i = 0; i < answer.length; i++)
            answer[i] = nickname.get(events.get(i)[0]) + events.get(i)[1];
        return answer;
    }
	

	public static void main(String[] args) {
		String[] temp = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		String[] ans = solution(temp);
		
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}
}
