package StudentData;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class StudentMain {
	public static Scanner scan=new Scanner(System.in);
	public static final int INSERT=1,SEARCH=2, DELETE=3, UPDATE=4, PRINT=5,SORT=6,EXIT=7;
	public static final int NAME=1, PHONE=2;

	public static void main(String[] args) {
		boolean flag=false;
		while(!flag) {
			int no=selectMenu();  //메뉴선택 함수	
			switch(no){
			case INSERT:insertStudent(); break; //입력
			case SEARCH:searchstudent(); break; //검색
			case DELETE:deletestudent(); break;	//삭제
			case UPDATE:updatestudent(); break;	//수정
			case PRINT:printstudent(); break; 	//출력
			case SORT:sortstudent(); break;		//정렬
			case EXIT:flag=true; 				//종료
				System.out.println("성적프로그램이 종료됨"); break; 
			default: System.out.println("다시 입력해 주세요"); break;
			}
		}
	}
	//학생성적 정렬(오름차순,내림차순)
	private static void sortstudent() {
		List<StudentData> list=new ArrayList<StudentData>();
		int no=0;
		boolean flag=false;
		
		while(!flag) {
			//정렬방식(오름차순,내림차순)
			System.out.println("1.오름차순(평균)  2.내림차순(평균)");
			System.out.print("정렬방식 선택 >> ");
			try {
				no=Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("1~2까지 숫자만 입력해주세요!");
				continue;
			}catch(Exception e) {
				System.out.println("1~2까지 숫자만 입력해주세요!");
				continue;
			}
			if(no>=1 && no<=2) {
				flag=true;
			}else {
				System.out.println("1~2까지 숫자만 입력해주세요!");
				}
			}//end of while
		
			list=DBController.sortstudent(no);
			
			if(list.size()<=0) {
				System.out.println("정렬 할 내용이 없습니다");
				return;
			}
			for(StudentData data:list) {
				System.out.println(data);
			}
			return;					
	}
	//학생성적 수정
	private static void updatestudent() {
		final int S_NAME=1;
		List<StudentData> list=new ArrayList<StudentData>();
		String name=null;
		int kor=0;
		int eng=0;
		int math=0;
		String searchData=null;
		int number=0;
		int resultNumber=0;
		while(true) {
			System.out.print("수정 할 학생이름 입력 >> ");
			name=scan.nextLine();
			
			if(patternCheck(name, NAME)) {
				break;
			}else {
				System.out.println("다시 입력해 주세요");
				}		   
			}
			searchData=name;
			number=S_NAME;
			list=DBController.searchstudent(searchData, number);
			if(list.size()<=0) {
				System.out.println(searchData+"찾으시는 데이터가 없습니다");
				return;
			}
			StudentData data_buffer=null;
			for(StudentData data:list) {
				System.out.println(data);
				data_buffer=data;
			}
			while(true) {
				System.out.print("["+data_buffer.getKor()+"] 수정 할 국어점수입력 >> ");
				kor=scan.nextInt();
				
				 if (kor<=100) {
			            break;
				 }else {
		        	System.out.println("다시 입력해 주세요"); 
		        }
				
			}
			while(true) {
				System.out.print("["+data_buffer.getEng()+"] 수정 할 영어점수입력 >> ");
				eng=scan.nextInt();
				
				 if (eng<=100) {
			            break;
				 }else {
		        	System.out.println("다시 입력해 주세요"); 
		        }
				
			}
			while(true) {
				System.out.print("["+data_buffer.getMath()+"] 수정 할 영어점수입력 >> ");
				math=scan.nextInt();
				
				 if (math<=100) {
			            break;
				 }else {
		        	System.out.println("다시 입력해 주세요"); 
		        }
				
			}
			data_buffer.setKor(kor);
			data_buffer.setEng(eng);
			data_buffer.setMath(math);
			data_buffer.totalSum();
			data_buffer.totalAvr();
			data_buffer.calGrade();
			
			resultNumber=DBController.updatestudent(data_buffer);
			if(resultNumber!=0) {
				System.out.println(name+"번호 수정완료");
			}else {
				System.out.println(name+"번호 수정실패");		
			}
			return;	
	}
	//학생정보 삭제
	private static void deletestudent() {
		final int S_NAME=1;
		String name=null;
		String deleteData=null;
		int number=0;
		int resultNumber=0;
		while(true) {
			System.out.print("삭제할 학생이름 입력 >> ");
			name=scan.nextLine();
			
			if(patternCheck(name, NAME)) {
				break;
			}else {
				System.out.println("다시 입력해 주세요");
			}		   
		}
		deleteData=name;
		number=S_NAME;	
		resultNumber=DBController.deletestudent(deleteData, number);
		if(resultNumber!=0) {
			System.out.println(name+"학생 삭제완료");
		}else {
			System.out.println(name+"학생 삭제실패");
		}
		return;
		
	}

	//학생정보 검색
	private static void searchstudent() {

		final int S_NAME=1,S_EXIT=2;
		List<StudentData> list=new ArrayList<StudentData>();
		String name=null;
		String searchData=null;
		int number=0;
		int no=0;
		boolean flag=false;
		no=searchMenu();
		
		switch(no) {
		case S_NAME:
			while(true) {
				System.out.print("검색할 이름 입력 >> ");
				name=scan.nextLine();
				
				if(patternCheck(name, NAME)) {
					break;
				}else {
					System.out.println("다시 입력해 주세요");
				}		   	
			}
			searchData=name;
			number=S_NAME;
			break;
		case S_EXIT:
			System.out.println("검색하기가 종료되었습니다");
			flag=true;
			break;
		}
		if(flag) {			
			return;
		}
		list=DBController.searchstudent(searchData, number);
		if(list.size()<=0) {
			System.out.println(searchData+"찾으시는 학생정보가 없습니다");
			return;
		}
		for(StudentData data:list) {
			System.out.println(data);
		}
	}
	
	//학생정보검색 선택창
	private static int searchMenu() {
		boolean flag=false;
		int no=0;
		while(!flag) {
			System.out.println("***********");
			System.out.println("1.이름  2.종료");
			System.out.println("***********");
			System.out.print("번호선택 >> ");
			try {
				no=Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("1~2까지 숫자만 입력해주세요!");
				continue;
			}catch(Exception e) {
				System.out.println("1~2까지 숫자만 입력해주세요!");
				continue;
			}
			if(no>=1 && no<=2) {
				flag=true;
			}else {
				System.out.println("1~2까지 숫자만 입력해주세요!");
			}
		}//end of while
		return no;	
	}

	//학생정보 출력
	private static void printstudent() {
		List<StudentData> list=new ArrayList<StudentData>();
		
		list=DBController.printstudent();
		
		if(list.size()<=0) {
			System.out.println("출력할 학생정보가 없습니다");
			return;
		}
		for(StudentData data:list) {
			System.out.println(data);
		}
		
	}
	
	//학생정보 입력
	private static void insertStudent() {
		String name=null;		
		String id=null;
		String hp=null;
		int kor=0;
		int math=0;
		int eng=0;
		int total=0;
		double avr=0.0;
		String grade=null;
		
		//이름
		while(true) {
			System.out.print("학생이름(홍길동) 입력 >> ");
			name=scan.nextLine();
			
			if(patternCheck(name, NAME)) {
				break;
			}else {
				System.out.println("다시 입력해 주세요");
			}		   	
		}
		//id
		while(true) {
			System.out.print("학생 id 입력(10글자이내) >> ");
			id=scan.nextLine();
			
		    if (id.length()>=1 && id.length()<=10) {
	            break;
	        }else {
	        	System.out.println("다시 입력해 주세요"); 
	        }
		}
		//전화번호입력
		while(true) {
			System.out.print("000-0000-0000 입력 >> ");
			hp=scan.nextLine();
			
			if(patternCheck(hp, PHONE)) {
				break;
			}else {
				System.out.println("다시 입력해 주세요");
			}		   
		}
		//국어점수
		while(true) {
			System.out.print("국어점수 입력(0~100) >> ");
			kor=scan.nextInt();
			
		    if (kor<=100) {
	            break;
	        }else {
	        	System.out.println("다시 입력해 주세요"); 
	        }
		}
		//수학점수
		while(true) {
			System.out.print("수학점수 입력(0~100) >> ");
			math=scan.nextInt();
			
		    if (math<=100) {
	            break;
	        }else {
	        	System.out.println("다시 입력해 주세요"); 
	        }
		}
	   //영어점수
		while(true) {
			System.out.print("영어점수 입력(0~100) >> ");
			eng=scan.nextInt();
			
		    if (eng<=100) {
	            break;
	        }else {
	        	System.out.println("다시 입력해 주세요"); 
	        }		    
		}
		//삽입할 모델생성
		StudentData studentModel=new StudentData(name, id, hp, kor, math, eng, total, avr, grade);
		studentModel.totalSum();
		studentModel.totalAvr();
		studentModel.calGrade();
		int returnValue=DBController.insertStudent(studentModel);
		if(returnValue!=0) {
			System.out.println(studentModel.getName()+"님 입력 성공하셨습니다");
		}else {
			System.out.println(studentModel.getName()+"님 입력 실패하셨습니다");		
		}
		
	}
	
	//패턴체크
	private static boolean patternCheck(String patternData, int patternType) {
		String filter=null;
		switch(patternType) {
			case PHONE:filter="\\d{3}-\\d{4}-\\d{4}"; break;
			case NAME:filter="^[가-힣]{2,5}$"; break;
		}
		Pattern pattern = Pattern.compile(filter);
        Matcher matcher = pattern.matcher(patternData);
		return matcher.matches();
	}
	
	//메뉴선택창
	private static int selectMenu() {
		boolean flag=false;
		int no=0;
		while(!flag) {
			System.out.println("***************************************");
			System.out.println("1.입력 2.검색 3.삭제 4.수정 5.출력 6.정렬 7.종료");
			System.out.println("***************************************");
			System.out.print("번호선택 >> ");
			try {
				no=Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("1~7까지 숫자만 입력해주세요!");
				continue;
			}catch(Exception e) {
				System.out.println("1~7까지 숫자만 입력해주세요!");
				continue;
			}
			if(no>=1 && no<=7) {
				flag=true;
			}else {
				System.out.println("1~7까지 숫자만 입력해주세요!");
			}
		}//end of while
		return no;
	}
}
