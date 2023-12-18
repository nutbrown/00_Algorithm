import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double creditSum = 0;
		double gradeSum = 0;
		
		for(int i = 0; i < 20; i++) {
			sc.next();
			double credit = Double.parseDouble(sc.next());
			String gradeStr = sc.next();
			double grade = 0;
			
			if(gradeStr.equals("A+")) grade = 4.5; 
			else if(gradeStr.equals("A0")) grade = 4.0; 
			else if(gradeStr.equals("B+")) grade = 3.5; 
			else if(gradeStr.equals("B0")) grade = 3.0; 
			else if(gradeStr.equals("C+")) grade = 2.5; 
			else if(gradeStr.equals("C0")) grade = 2.0; 
			else if(gradeStr.equals("D+")) grade = 1.5; 
			else if(gradeStr.equals("D0")) grade = 1.0; 
			else if(gradeStr.equals("F")) grade = 0.0;
			
			if(!gradeStr.equals("P")) {
				creditSum += credit;
				gradeSum += credit * grade;
			}
		}
		System.out.println(gradeSum / creditSum);
	}
}