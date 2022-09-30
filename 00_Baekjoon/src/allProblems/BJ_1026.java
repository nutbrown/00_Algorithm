package allProblems;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BJ_1026 {
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int N = sc.nextInt();
			
			
			int[] A = new int[N];
			int[] B = new int[N];
			for(int i = 0; i< N; i++) {
				A[i] = sc.nextInt();
			}
			for(int i = 0; i< N; i++) {
				B[i] = sc.nextInt();
			}
			
			Arrays.sort(A);
			Arrays.sort(B);
			
			for(int i = 0; i < N; i++) {
				System.out.print(B[i] + " ");
			}
			
			int S = 0;
			for(int i = 0; i < N; i++) {
				S += A[i] * B[N - 1 - i];
			}
			System.out.println(S);
			
			
			
//			int[][] A = new int[N][2];
//			int[][] B = new int[N][2];
//			for(int i = 0; i < N; i++) {
//				A[i][0] = sc.nextInt();
//			}
//			for(int i = 0; i < N; i++) {
//				B[i][0] = sc.nextInt();
//			}
//			
//			// A는 내림차순 B는 오름차순
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++) {
//					if(A[i][0] < A[j][0]) {
//						A[i][1]++;
//					}
//				}
//				for(int j = i + 1; j < N; j++) {
//					if(A[i][0] == A[j][0]) {
//						A[i][1]++;
//						}
//				}
//			}	
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++) {
//					if(B[i][0] >B[j][0]) {
//						B[i][1]++;
//					}
//				}
//				for(int j = i + 1; j < N; j++) {
//					if(B[i][0] == B[j][0]) {
//						B[i][1]++;
//					}
//				}
//			}		
//			
//			
//			// 확인 프린트
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < 2; j++) {
//					System.out.print(A[i][j] + " ");
//					}
//				System.out.print("/");
//			}
//			System.out.println();
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < 2; j++) {
//					System.out.print(B[i][j] + " ");
//					}
//				System.out.print("/");
//			}
//			System.out.println();
//
//			
//			
//			// 정렬
//			int[][] newA = new int[N][2];
//		    for(int i = 0; i < N; i++) {
//		    	for(int j = 0; j < N; j++) {
//		    		if(i != j) {
//			    		if(A[i][1] == B[j][1]) {
//			    			newA[j][0] = A[i][0];
//			    			newA[j][1] = A[i][1];
//			    		}
//		    		}
//		    	}
//		    }	
//			
//			// 확인 프린트
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < 2; j++) {
//					System.out.print(newA[i][j] + " ");
//					}
//				System.out.print("/");
//			}
//			System.out.println();
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < 2; j++) {
//					System.out.print(B[i][j] + " ");
//					}
//				System.out.print("/");
//			}
//			System.out.println();
//
//		    
//			int S = 0;
//			for(int i=0; i < N; i++) {
//				S += newA[i][0] * B[i][0];
//			}
//			System.out.println(S);
		}		
}


//반례
//4
//1 2 3 4
//1 0 0 0