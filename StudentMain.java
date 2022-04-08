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
			int no=selectMenu();  //¸Þ´º¼±ÅÃ ÇÔ¼ö	
			switch(no){
			case INSERT:insertStudent(); break; //ÀÔ·Â
			case SEARCH:searchstudent(); break; //°Ë»ö
			case DELETE:deletestudent(); break;	//»èÁ¦
			case UPDATE:updatestudent(); break;	//¼öÁ¤
			case PRINT:printstudent(); break; 	//Ãâ·Â
			case SORT:sortstudent(); break;		//Á¤·Ä
			case EXIT:flag=true; 				//Á¾·á
				System.out.println("¼ºÀûÇÁ·Î±×·¥ÀÌ Á¾·áµÊ"); break; 
			default: System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä"); break;
			}
		}
	}
	//ÇÐ»ý¼ºÀû Á¤·Ä(¿À¸§Â÷¼ø,³»¸²Â÷¼ø)
	private static void sortstudent() {
		List<StudentData> list=new ArrayList<StudentData>();
		int no=0;
		boolean flag=false;
		
		while(!flag) {
			//Á¤·Ä¹æ½Ä(¿À¸§Â÷¼ø,³»¸²Â÷¼ø)
			System.out.println("1.¿À¸§Â÷¼ø(Æò±Õ)  2.³»¸²Â÷¼ø(Æò±Õ)");
			System.out.print("Á¤·Ä¹æ½Ä ¼±ÅÃ >> ");
			try {
				no=Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("1~2±îÁö ¼ýÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä!");
				continue;
			}catch(Exception e) {
				System.out.println("1~2±îÁö ¼ýÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä!");
				continue;
			}
			if(no>=1 && no<=2) {
				flag=true;
			}else {
				System.out.println("1~2±îÁö ¼ýÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä!");
				}
			}//end of while
		
			list=DBController.sortstudent(no);
			
			if(list.size()<=0) {
				System.out.println("Á¤·Ä ÇÒ ³»¿ëÀÌ ¾ø½À´Ï´Ù");
				return;
			}
			for(StudentData data:list) {
				System.out.println(data);
			}
			return;					
	}
	//ÇÐ»ý¼ºÀû ¼öÁ¤
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
			System.out.print("¼öÁ¤ ÇÒ ÇÐ»ýÀÌ¸§ ÀÔ·Â >> ");
			name=scan.nextLine();
			
			if(patternCheck(name, NAME)) {
				break;
			}else {
				System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä");
				}		   
			}
			searchData=name;
			number=S_NAME;
			list=DBController.searchstudent(searchData, number);
			if(list.size()<=0) {
				System.out.println(searchData+"Ã£À¸½Ã´Â µ¥ÀÌÅÍ°¡ ¾ø½À´Ï´Ù");
				return;
			}
			StudentData data_buffer=null;
			for(StudentData data:list) {
				System.out.println(data);
				data_buffer=data;
			}
			while(true) {
				System.out.print("["+data_buffer.getKor()+"] ¼öÁ¤ ÇÒ ±¹¾îÁ¡¼öÀÔ·Â >> ");
				kor=scan.nextInt();
				
				 if (kor<=100) {
			            break;
				 }else {
		        	System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä"); 
		        }
				
			}
			while(true) {
				System.out.print("["+data_buffer.getEng()+"] ¼öÁ¤ ÇÒ ¿µ¾îÁ¡¼öÀÔ·Â >> ");
				eng=scan.nextInt();
				
				 if (eng<=100) {
			            break;
				 }else {
		        	System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä"); 
		        }
				
			}
			while(true) {
				System.out.print("["+data_buffer.getMath()+"] ¼öÁ¤ ÇÒ ¼öÇÐÁ¡¼öÀÔ·Â >> ");
				math=scan.nextInt();
				
				 if (math<=100) {
			            break;
				 }else {
		        	System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä"); 
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
				System.out.println(name+"¹øÈ£ ¼öÁ¤¿Ï·á");
			}else {
				System.out.println(name+"¹øÈ£ ¼öÁ¤½ÇÆÐ");		
			}
			return;	
	}
	//ÇÐ»ýÁ¤º¸ »èÁ¦
	private static void deletestudent() {
		final int S_NAME=1;
		String name=null;
		String deleteData=null;
		int number=0;
		int resultNumber=0;
		while(true) {
			System.out.print("»èÁ¦ÇÒ ÇÐ»ýÀÌ¸§ ÀÔ·Â >> ");
			name=scan.nextLine();
			
			if(patternCheck(name, NAME)) {
				break;
			}else {
				System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä");
			}		   
		}
		deleteData=name;
		number=S_NAME;	
		resultNumber=DBController.deletestudent(deleteData, number);
		if(resultNumber!=0) {
			System.out.println(name+"ÇÐ»ý »èÁ¦¿Ï·á");
		}else {
			System.out.println(name+"ÇÐ»ý »èÁ¦½ÇÆÐ");
		}
		return;
		
	}

	//ÇÐ»ýÁ¤º¸ °Ë»ö
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
				System.out.print("°Ë»öÇÒ ÀÌ¸§ ÀÔ·Â >> ");
				name=scan.nextLine();
				
				if(patternCheck(name, NAME)) {
					break;
				}else {
					System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä");
				}		   	
			}
			searchData=name;
			number=S_NAME;
			break;
		case S_EXIT:
			System.out.println("°Ë»öÇÏ±â°¡ Á¾·áµÇ¾ú½À´Ï´Ù");
			flag=true;
			break;
		}
		if(flag) {			
			return;
		}
		list=DBController.searchstudent(searchData, number);
		if(list.size()<=0) {
			System.out.println(searchData+"Ã£À¸½Ã´Â ÇÐ»ýÁ¤º¸°¡ ¾ø½À´Ï´Ù");
			return;
		}
		for(StudentData data:list) {
			System.out.println(data);
		}
	}
	
	//ÇÐ»ýÁ¤º¸°Ë»ö ¼±ÅÃÃ¢
	private static int searchMenu() {
		boolean flag=false;
		int no=0;
		while(!flag) {
			System.out.println("***********");
			System.out.println("1.ÀÌ¸§  2.Á¾·á");
			System.out.println("***********");
			System.out.print("¹øÈ£¼±ÅÃ >> ");
			try {
				no=Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("1~2±îÁö ¼ýÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä!");
				continue;
			}catch(Exception e) {
				System.out.println("1~2±îÁö ¼ýÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä!");
				continue;
			}
			if(no>=1 && no<=2) {
				flag=true;
			}else {
				System.out.println("1~2±îÁö ¼ýÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä!");
			}
		}//end of while
		return no;	
	}

	//ÇÐ»ýÁ¤º¸ Ãâ·Â
	private static void printstudent() {
		List<StudentData> list=new ArrayList<StudentData>();
		
		list=DBController.printstudent();
		System.out.println("ÀÌ¸§      ÇÐ¹ø         ÀüÈ­¹øÈ£       ±¹¾î     ¿µ¾î     ¼öÇÐ     ÃÑÁ¡       Æò±Õ   ÇÐÁ¡  ");
		if(list.size()<=0) {
			System.out.println("Ãâ·ÂÇÒ ÇÐ»ýÁ¤º¸°¡ ¾ø½À´Ï´Ù");
			return;
		}
		for(StudentData data:list) {
			System.out.println(data);
		}
		
	}
	
	//ÇÐ»ýÁ¤º¸ ÀÔ·Â
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
		
		//ÀÌ¸§
		while(true) {
			System.out.print("ÇÐ»ýÀÌ¸§(È«±æµ¿) ÀÔ·Â >> ");
			name=scan.nextLine();
			
			if(patternCheck(name, NAME)) {
				break;
			}else {
				System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä");
			}		   	
		}
		
		//id
		while(true) {
			List<StudentData> list = new ArrayList<StudentData>();
			System.out.print("ÇÐ»ý id ÀÔ·Â(10±ÛÀÚÀÌ³») >> ");
			id = scan.nextLine();
			
		    if (id.length()>=1 && id.length()<=4) {
		    	list = DBController.searchstudent(id, 2);
		    	if(list.size() >= 1 ) {
		    		System.out.println("Áßº¹µÈ ¹øÈ£ÀÔ´Ï´Ù.");
		    		continue;
		    	}
		    	break;
	        }else {
	        	System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä");
	        	continue;
	        }
		   
		}
		
		//ÀüÈ­¹øÈ£ÀÔ·Â
		while(true) {
			System.out.print("000-0000-0000 ÀÔ·Â >> ");
			hp=scan.nextLine();
			
			if(patternCheck(hp, PHONE)) {
				break;
			}else {
				System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä");
			}		   
		}
		//±¹¾îÁ¡¼ö
		while(true) {
			System.out.print("±¹¾îÁ¡¼ö ÀÔ·Â(0~100) >> ");
			kor=scan.nextInt();
			
		    if (kor<=100) {
	            break;
	        }else {
	        	System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä"); 
	        }
		}
		//¼öÇÐÁ¡¼ö
		while(true) {
			System.out.print("¼öÇÐÁ¡¼ö ÀÔ·Â(0~100) >> ");
			math=scan.nextInt();
			
		    if (math<=100) {
	            break;
	        }else {
	        	System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä"); 
	        }
		}
	   //¿µ¾îÁ¡¼ö
		while(true) {
			System.out.print("¿µ¾îÁ¡¼ö ÀÔ·Â(0~100) >> ");
			eng=scan.nextInt();
			
		    if (eng<=100) {
	            break;
	        }else {
	        	System.out.println("´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä"); 
	        }		    
		}
		//»ðÀÔÇÒ ¸ðµ¨»ý¼º
		StudentData studentModel=new StudentData(name, id, hp, kor, math, eng, total, avr, grade);
		studentModel.totalSum();
		studentModel.totalAvr();
		studentModel.calGrade();
		int returnValue=DBController.insertStudent(studentModel);
		if(returnValue!=0) {
			System.out.println(studentModel.getName()+"´Ô ÀÔ·Â ¼º°øÇÏ¼Ì½À´Ï´Ù");
		}else {
			System.out.println(studentModel.getName()+"´Ô ÀÔ·Â ½ÇÆÐÇÏ¼Ì½À´Ï´Ù");		
		}
		
	}
	
	//ÆÐÅÏÃ¼Å©
	private static boolean patternCheck(String patternData, int patternType) {
		String filter=null;
		switch(patternType) {
			case PHONE:filter="\\d{3}-\\d{4}-\\d{4}"; break;
			case NAME:filter="^[°¡-ÆR]{2,5}$"; break;
		}
		Pattern pattern = Pattern.compile(filter);
        Matcher matcher = pattern.matcher(patternData);
		return matcher.matches();
	}
	
	//¸Þ´º¼±ÅÃÃ¢
	private static int selectMenu() {
		boolean flag=false;
		int no=0;
		while(!flag) {
			System.out.println("***************************************");
			System.out.println("1.ÀÔ·Â 2.°Ë»ö 3.»èÁ¦ 4.¼öÁ¤ 5.Ãâ·Â 6.Á¤·Ä 7.Á¾·á");
			System.out.println("***************************************");
			System.out.print("¹øÈ£¼±ÅÃ >> ");
			try {
				no=Integer.parseInt(scan.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("1~7±îÁö ¼ýÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä!");
				continue;
			}catch(Exception e) {
				System.out.println("1~7±îÁö ¼ýÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä!");
				continue;
			}
			if(no>=1 && no<=7) {
				flag=true;
			}else {
				System.out.println("1~7±îÁö ¼ýÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä!");
			}
		}//end of while
		return no;
	}
}
