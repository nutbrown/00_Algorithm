import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

// 메모리 268848 KB, 시간 2412 ms
public class Main {
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		// hashcode를 이용해서 해시코드가 같은지 확인한다
		public int hashCode() {
			return Objects.hash(x, y);
		}
		
		// equals를 통해서 같은 객체인지 확인한다
		public boolean equals(Object obj) {
			Point point = (Point)obj;
			return (this.x == point.x && this.y == point.y);
		}
	}
	
    public static void main(String[] args) throws IOException {
        // 스캐너 쓰면 메모리초과 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 점들의 개수 N
        int N = Integer.parseInt(br.readLine());

        // 만들고 싶은 직사각형의 가로길이 A, 세로길이 B
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        // 점들의 좌표 (-1,000,000,000이상 1,000,000,000이하)
        HashSet<Point> hs = new HashSet<>();
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());

        	hs.add(new Point(x, y));
        }
        
        int cnt = 0;
        
        // 점 1개 (x, y)를 골랐을 때 (한 방향으로만)
        // (x, y + B)가 있는지
        // (x + A, y)가 있는지
        // (x + A, y + B)가 있는지
        for(Point p : hs) {
        	int x = p.x;
        	int y = p.y;
        	
        	Point p1 = new Point(x, y + B);
        	Point p2 = new Point(x + A, y);
        	Point p3 = new Point(x + A, y + B);
        	
        	if(hs.contains(p1) && hs.contains(p2) && hs.contains(p3)) cnt++;
        }
        
        System.out.println(cnt);
    }
}
