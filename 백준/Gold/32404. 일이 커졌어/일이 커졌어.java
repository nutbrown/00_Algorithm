import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		
		// 정수 1 ~ N로 순열을 만들고, 정우가 가지고 있는 정수 X를 키운다
		// i가 홀수면 X에 p를 곱한다
		// i가 짝수면 X에 p를 더한다
		// 1 ~ N을 어떻게 나열해야 정수 X를 최대로 키울 수 있는지
		
		// 1 2 3 4
		// 1 3 9 13
		// 1 3 12 15
		// (((1 + a) * b + c) * d) + e) * f
		// (b + ab + c) * d) + e ) * f
		// bd + abd + cd + e) * f
		// bdf + abdf + cdf + ef
		// 곱할 때는 앞에 나오는 모든 수에 다 곱하는 거여서
		// 수가 클 수록 뒤로 보내서 곱해야한다
		// 더하는 수는 한 번씩만 나오고, 뒤에 곱하는 수를 다 포함한다
		// 앞에 나올 수록 많은 수가 곱해지니까, 앞에 올수록 큰 수가 나와야한다
		
		// 홀 짝 홀 짝 홀 짝 홀 짝 - 홀이 곱하고, 짝이 더하기
		int N = Integer.parseInt(st.nextToken());
		int[] p = new int[N];
		
		// N이 6이라고 하면
		// 4 3 5 2 6 1
		// N이 7이라고 하면
		// 4 3 5 2 6 1 7
		
		// 홀짝 상관없이 나누기2해서 작은 수부터 채우고
		int x = N / 2;
		for(int i = 1; i < (N / 2) * 2; i += 2) {
			p[i] = x--;
		}

		// 큰 수부터 다시 채운다
		x = N / 2 + 1;
		for(int i = 0; i < (N / 2) * 2 - 1; i += 2) {
			p[i] = x++;
		}

		// 홀수면 맨 뒤에 제일 큰 수 채우기
		if(N % 2 == 1) p[N - 1] = N;

		// 출력
		for(int i = 0; i < N; i++) {
			System.out.print(p[i] + " ");
		}
		
		
	}
}