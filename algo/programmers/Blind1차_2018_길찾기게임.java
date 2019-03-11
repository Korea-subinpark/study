import java.util.*;
/*
먼저 노드를 y축을 기준으로 내림차순, y축이 같을 경우 x축을 기준으로 오름차순 정렬한다.
노드를 모두 트리에 삽입한 뒤에 전위순회, 후위순회 결과를 반환한다.
*/
class Solution {
    ArrayList<Node> list;
    int[][] answer;
    int idx;
    
    class Node implements Comparable<Node> {
        int x, h, num;
        Node left, right;
        public Node(int x, int h, int num) {
            this.x = x;
            this.h = h;
            this.num = num;
        }
        @Override
        public int compareTo(Node n) {
            if(h == n.h)
                return x - n.x;
            return n.h - h;
        }
    }
    
    class Tree {
        Node root;
        
        public void insertChild(Node p, Node c) {
            if(p.x > c.x) {//부모의 숫자가 더 큰 경우 - leftChild
                if(p.left == null) {//left가 비어 있을 경우
                    p.left = c;
                    return;
                } else {//left가 존재하는 경우 재귀 호출
                    insertChild(p.left, c);
                }
            } else {//부모의 숫자가 더 작은 경우 - rightChild
                if(p.right == null) {//right가 비어 있을 경우
                    p.right = c;
                    return;
                } else {//right가 존재하는 경우 재귀 호출
                    insertChild(p.right, c);
                }
            }
        }
        
        public void insert(Node n) {
            Node parent = root;
            if(parent == null) {//root가 없을 경우
                root = n;
                return;
            } else {//root가 있는 경우
                insertChild(parent, n);
            }
        }
        
        public void preOrder(Node n) {
            if(n != null) {
                answer[0][idx++] = n.num;//현재 노드 1
                preOrder(n.left);//leftChild 2
                preOrder(n.right);//rightChild 3
            }
        }
        
        public void postOrder(Node n) {
            if(n != null) {
                postOrder(n.left);//leftChild 1
                postOrder(n.right);//rightChild 2
                answer[1][idx++] = n.num;//현재 노드 3
            }
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        list = new ArrayList<>();
        
        for(int i = 0; i < nodeinfo.length; i++)//node 정보 저장
            list.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        
        Collections.sort(list);//y축 좌표가 높은 순, 같다면 x축 좌표가 작은 순으로 정렬
        
        Tree t = new Tree();
        for(int i = 0; i < list.size(); i++)
            t.insert(list.get(i));//node를 tree에 하나씩 삽입
        
        answer = new int[2][list.size()];
        t.preOrder(t.root);//전위 순회
        idx = 0;
        t.postOrder(t.root);//후위 순회
        
        return answer;
    }
}
