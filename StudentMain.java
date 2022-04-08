package StudentData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
			int no=selectMenu();  //�޴����� �Լ�	
			switch(no){
			case INSERT:insertStudent(); break; //�Է�
			case SEARCH:searchstudent(); break; //�˻�
			case DELETE:deletestudent(); break;	//����
			case UPDATE:updatestudent(); break;	//����
			case PRINT:printstudent(); break; 	//���
			case SORT:sortstudent(); break;		//����
			case EXIT:flag=true; 				//����
				System.out.println("�������α׷��� �����"); break; 
			default: System.out.println("�ٽ� �Է��� �ּ���"); break;
			}
		}
	}
	//�л����� ����(��������,��������)
	private static void sortstudent() {
		List<StudentData> list=new ArrayList<StudentData>();
		int no=0;
		boolean flag=false;
		
		while(!flag) {
			//���Ĺ��(��������,��������)
			System.out.println("1.��������(���)  2.��������(���)");
			System.out.print("���Ĺ�� ���� >> ");
			try {
				no=Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("1~2���� ���ڸ� �Է����ּ���!");
				continue;
			}catch(Exception e) {
				System.out.println("1~2���� ���ڸ� �Է����ּ���!");
				continue;
			}
			if(no>=1 && no<=2) {
				flag=true;
			}else {
				System.out.println("1~2���� ���ڸ� �Է����ּ���!");
				}
			}//end of while
		
			list=DBController.sortstudent(no);
			
			if(list.size()<=0) {
				System.out.println("���� �� ������ �����ϴ�");
				return;
			}
			for(StudentData data:list) {
				System.out.println(data);
			}
			return;					
	}
	//�л����� ����
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
			System.out.print("���� �� �л��̸� �Է� >> ");
			name=scan.nextLine();
			
			if(patternCheck(name, NAME)) {
				break;
			}else {
				System.out.println("�ٽ� �Է��� �ּ���");
				}		   
			}
			searchData=name;
			number=S_NAME;
			list=DBController.searchstudent(searchData, number);
			if(list.size()<=0) {
				System.out.println(searchData+"ã���ô� �����Ͱ� �����ϴ�");
				return;
			}
			StudentData data_buffer=null;
			for(StudentData data:list) {
				System.out.println(data);
				data_buffer=data;
			}
			while(true) {
				System.out.print("["+data_buffer.getKor()+"] ���� �� ���������Է� >> ");
				kor=scan.nextInt();
				
				 if (kor<=100) {
			            break;
				 }else {
		        	System.out.println("�ٽ� �Է��� �ּ���"); 
		        }
				
			}
			while(true) {
				System.out.print("["+data_buffer.getEng()+"] ���� �� ���������Է� >> ");
				eng=scan.nextInt();
				
				 if (eng<=100) {
			            break;
				 }else {
		        	System.out.println("�ٽ� �Է��� �ּ���"); 
		        }
				
			}
			while(true) {
				System.out.print("["+data_buffer.getMath()+"] ���� �� ���������Է� >> ");
				math=scan.nextInt();
				
				 if (math<=100) {
			            break;
				 }else {
		        	System.out.println("�ٽ� �Է��� �ּ���"); 
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
				System.out.println(name+"��ȣ �����Ϸ�");
			}else {
				System.out.println(name+"��ȣ ��������");		
			}
			return;	
	}
	//�л����� ����
	private static void deletestudent() {
		final int S_NAME=1;
		String name=null;
		String deleteData=null;
		int number=0;
		int resultNumber=0;
		while(true) {
			System.out.print("������ �л��̸� �Է� >> ");
			name=scan.nextLine();
			
			if(patternCheck(name, NAME)) {
				break;
			}else {
				System.out.println("�ٽ� �Է��� �ּ���");
			}		   
		}
		deleteData=name;
		number=S_NAME;	
		resultNumber=DBController.deletestudent(deleteData, number);
		if(resultNumber!=0) {
			System.out.println(name+"�л� �����Ϸ�");
		}else {
			System.out.println(name+"�л� ��������");
		}
		return;
		
	}

	//�л����� �˻�
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
				System.out.print("�˻��� �̸� �Է� >> ");
				name=scan.nextLine();
				
				if(patternCheck(name, NAME)) {
					break;
				}else {
					System.out.println("�ٽ� �Է��� �ּ���");
				}		   	
			}
			searchData=name;
			number=S_NAME;
			break;
		case S_EXIT:
			System.out.println("�˻��ϱⰡ ����Ǿ����ϴ�");
			flag=true;
			break;
		}
		if(flag) {			
			return;
		}
		list=DBController.searchstudent(searchData, number);
		if(list.size()<=0) {
			System.out.println(searchData+"ã���ô� �л������� �����ϴ�");
			return;
		}
		for(StudentData data:list) {
			System.out.println(data);
		}
	}
	
	//�л������˻� ����â
	private static int searchMenu() {
		boolean flag=false;
		int no=0;
		while(!flag) {
			System.out.println("***********");
			System.out.println("1.�̸�  2.����");
			System.out.println("***********");
			System.out.print("��ȣ���� >> ");
			try {
				no=Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("1~2���� ���ڸ� �Է����ּ���!");
				continue;
			}catch(Exception e) {
				System.out.println("1~2���� ���ڸ� �Է����ּ���!");
				continue;
			}
			if(no>=1 && no<=2) {
				flag=true;
			}else {
				System.out.println("1~2���� ���ڸ� �Է����ּ���!");
			}
		}//end of while
		return no;	
	}

	//�л����� ���
	private static void printstudent() {
		List<StudentData> list=new ArrayList<StudentData>();
		
		list=DBController.printstudent();
		System.out.println("�̸�      �й�         ��ȭ��ȣ       ����     ����     ����     ����       ���   ����  ");
		if(list.size()<=0) {
			System.out.println("����� �л������� �����ϴ�");
			return;
		}
		for(StudentData data:list) {
			System.out.println(data);
		}
		
	}
	
	//�л����� �Է�
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
		
		//�̸�
		while(true) {
			System.out.print("�л��̸�(ȫ�浿) �Է� >> ");
			name=scan.nextLine();
			
			if(patternCheck(name, NAME)) {
				break;
			}else {
				System.out.println("�ٽ� �Է��� �ּ���");
			}		   	
		}
		
		//id
		while(true) {
			List<StudentData> list = new ArrayList<StudentData>();
			System.out.print("�л� id �Է�(10�����̳�) >> ");
			id = scan.nextLine();
			
		    if (id.length()>=1 && id.length()<=4) {
		    	list = DBController.searchstudent(id, 2);
		    	if(list.size() >= 1 ) {
		    		System.out.println("�ߺ��� ��ȣ�Դϴ�.");
		    		continue;
		    	}
		    	break;
	        }else {
	        	System.out.println("�ٽ� �Է��� �ּ���");
	        	continue;
	        }
		   
		}
		
		//��ȭ��ȣ�Է�
		while(true) {
			System.out.print("000-0000-0000 �Է� >> ");
			hp=scan.nextLine();
			
			if(patternCheck(hp, PHONE)) {
				break;
			}else {
				System.out.println("�ٽ� �Է��� �ּ���");
			}		   
		}
		//��������
		while(true) {
			System.out.print("�������� �Է�(0~100) >> ");
			kor=scan.nextInt();
			
		    if (kor<=100) {
	            break;
	        }else {
	        	System.out.println("�ٽ� �Է��� �ּ���"); 
	        }
		}
		//��������
		while(true) {
			System.out.print("�������� �Է�(0~100) >> ");
			math=scan.nextInt();
			
		    if (math<=100) {
	            break;
	        }else {
	        	System.out.println("�ٽ� �Է��� �ּ���"); 
	        }
		}
	   //��������
		while(true) {
			System.out.print("�������� �Է�(0~100) >> ");
			eng=scan.nextInt();
			
		    if (eng<=100) {
	            break;
	        }else {
	        	System.out.println("�ٽ� �Է��� �ּ���"); 
	        }		    
		}
		//������ �𵨻���
		StudentData studentModel=new StudentData(name, id, hp, kor, math, eng, total, avr, grade);
		studentModel.totalSum();
		studentModel.totalAvr();
		studentModel.calGrade();
		int returnValue=DBController.insertStudent(studentModel);
		if(returnValue!=0) {
			System.out.println(studentModel.getName()+"�� �Է� �����ϼ̽��ϴ�");
		}else {
			System.out.println(studentModel.getName()+"�� �Է� �����ϼ̽��ϴ�");		
		}
		
	}
	
	//����üũ
	private static boolean patternCheck(String patternData, int patternType) {
		String filter=null;
		switch(patternType) {
			case PHONE:filter="\\d{3}-\\d{4}-\\d{4}"; break;
			case NAME:filter="^[��-�R]{2,5}$"; break;
		}
		Pattern pattern = Pattern.compile(filter);
        Matcher matcher = pattern.matcher(patternData);
		return matcher.matches();
	}
	
	//�޴�����â
	private static int selectMenu() {
		boolean flag=false;
		int no=0;
		while(!flag) {
			System.out.println("***************************************");
			System.out.println("1.�Է� 2.�˻� 3.���� 4.���� 5.��� 6.���� 7.����");
			System.out.println("***************************************");
			System.out.print("��ȣ���� >> ");
			try {
				no=Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("1~7���� ���ڸ� �Է����ּ���!");
				continue;
			}catch(Exception e) {
				System.out.println("1~7���� ���ڸ� �Է����ּ���!");
				continue;
			}
			if(no>=1 && no<=7) {
				flag=true;
			}else {
				System.out.println("1~7���� ���ڸ� �Է����ּ���!");
			}
		}//end of while
		return no;
	}
}
