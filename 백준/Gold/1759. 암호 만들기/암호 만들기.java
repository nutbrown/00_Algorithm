import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int L;
	static int C;
	static char[] arr;
	static boolean[] isConsonant;
	static char[] chosen;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 서로 다른 L개의 알파벳 소문자로 구성되어 있으며
		// 문자의 종류 C
		L = sc.nextInt();
		C = sc.nextInt();
		
		arr = new char[C];
		for(int i = 0; i < C; i++) {
			arr[i] = sc.next().charAt(0);
		}
		// 알파벳 순서대로 정렬
		Arrays.sort(arr);
		
		// 모음인지 자음인지
		isConsonant = new boolean[C];
		for(int i = 0; i < C; i++) {
			if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i'
					|| arr[i] == 'o' || arr[i] == 'u') isConsonant[i] = true;
		}
		
		
		// 최소 1개의 모음과 2개의 자음으로 구성되어 있다
		// 알파벳이 증가하는 순서대로 배열 -> 중복 안 되니까 조합
		// L개 중에서 몇 개째를 넣고 있는지, 현재 보는 인덱스가 뭔지, 모음 개수, 자음 개수
		chosen = new char[L];
		sb = new StringBuilder();
		combination(0, 0, 0, 0);
		
		
		System.out.println(sb);
	}
	
	static void combination(int depth, int idx, int consonants, int vowels) {
		
		// L개가 다 채워졌다면
		if(depth == L) {
			// 모음이 1개, 자음이 2개라면
			if(consonants >= 1 && vowels >= 2) {
				for(int i = 0; i < L; i++) {
					sb.append(chosen[i]);
				}
				sb.append("\n");
			}
			return;
		}
		
		// idx가 N을 넘어갔으면 안 된다
		if(idx >= C) return;
		
		// 조합으로
		// 해당 인덱스를 방문하는 거 하나
		// 모음이면 모음증가, 자음이면 자음증가
		chosen[depth] = arr[idx];
		if(isConsonant[idx]) combination(depth + 1, idx + 1, consonants + 1, vowels);
		else combination(depth + 1, idx + 1, consonants, vowels + 1);
				
		// 방문 안 하는 거 하나 (**오타)
		combination(depth, idx + 1, consonants, vowels);
		
	}
}
