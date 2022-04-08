package StudentData;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBController {
	//테이블 모델객체 삽입 정적멤버함수(DB삽입기능) 
	public static int insertStudent(StudentData studentModel) {
		int returnValue=0;  	//데이터베이스 명령문 리턴값
		//데이터베이스 접속요청
		Connection con=DBUtility.getConnection();
		if(con==null) {
			System.out.println("mysql connection fail");
			return 0;
		}
		//삽입명령문 
		String insertQuery="insert into Studentproject.studenttbl values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		try {
			ps=(PreparedStatement) con.prepareStatement(insertQuery);
			ps.setString(1, studentModel.getName());
			ps.setString(2, studentModel.getId());
			ps.setString(3, studentModel.getHp());
			ps.setInt(4, studentModel.getKor());
			ps.setInt(5, studentModel.getEng());
			ps.setInt(6, studentModel.getMath());
			ps.setInt(7, studentModel.getTotal());
			ps.setDouble(8, studentModel.getAvr());
			ps.setString(9, studentModel.getGrade());
			
			returnValue=ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null && !ps.isClosed()) {
					ps.close();	
				}
				if(con!=null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//결과값 리턴
		return returnValue;		
	}
	//테이블 모델객체 출력 정적멤버함수(DB삽입기능) 
	public static List<StudentData> printstudent() {
		//테이블에있는 레코드 셋을 가져오기 위한 ArrayList<PhoneBookModel>
		List<StudentData> list=new ArrayList<StudentData>();
		//데이터베이스 접속요청
		Connection con=DBUtility.getConnection();
		if(con==null) {
			System.out.println("mysql connection fail");
			return null;
		}
		//DB명령문
		String selectQuery="select*from Studentproject.studenttbl";
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			ps=(PreparedStatement) con.prepareStatement(selectQuery);
			resultSet=ps.executeQuery(); 
			while(resultSet.next()) {
				String name=resultSet.getString(1);
				String id=resultSet.getString(2);
				String hp=resultSet.getString(3);
				int kor=resultSet.getInt(4);
				int eng=resultSet.getInt(5);
				int math=resultSet.getInt(6);
				int total=resultSet.getInt(7);
				Double avr=resultSet.getDouble(8);
				String grade=resultSet.getString(9);
				
				StudentData studentData =new StudentData(name, id, hp, kor, eng, math,total,avr,grade);
				list.add(studentData);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null && !ps.isClosed()) {
					ps.close();	
				}
				if(con!=null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//테이블 모델객체 검색 정적멤버함수(이름) 
	public static List<StudentData> searchstudent(String searchData, int number){
		final int S_NAME=1,S_ID = 2,S_EXIT=3;
		//레코드 셋을 저장을 List Collection
		List<StudentData> list=new ArrayList<StudentData>();
		//데이터베이스 접속요청
		Connection con=DBUtility.getConnection();
		if(con==null) {
			System.out.println("mysql connection fail");
			return null;
		}
		//DB명령문
		String searchQuary=null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		switch(number) {
		case S_NAME:
			searchQuary="select*from Studentproject.studenttbl where name like ?";
			
			break;
		case S_ID :
			searchQuary="select*from Studentproject.studenttbl where id like ?";
			break;
		}		
		try {
			ps=(PreparedStatement) con.prepareStatement(searchQuary);
			searchData="%"+searchData+"%";
			ps.setString(1, searchData);
			resultSet=ps.executeQuery();
			while(resultSet.next()) {
				String name=resultSet.getString(1);
				String id=resultSet.getString(2);
				String hp=resultSet.getString(3);
				int kor= resultSet.getInt(4);
				int eng=resultSet.getInt(5);
				int math=resultSet.getInt(6);
				int total=resultSet.getInt(7);
				Double avr=resultSet.getDouble(8);
				String grade=resultSet.getString(9);
				
				StudentData studentData =new StudentData(name, id, hp, kor, math, eng, total, avr, grade);
				list.add(studentData);			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null && !ps.isClosed()) {
					ps.close();	
				}
				if(con!=null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}
	//테이블 모델객체 삭제 정적멤버함수(DB삽입기능) 
	public static int deletestudent(String deleteData, int number) {
		final int S_NAME=1;
		//DB접속요청
		Connection con=DBUtility.getConnection();
		if(con==null) {
			System.out.println("mysql connection fail");
			return 0;
		}
		String deleteQuery=null;
		PreparedStatement ps=null;
		int resultNumber=0;
		//DB명령문
		switch(number) {
		case S_NAME:
			deleteQuery="delete from Studentproject.studenttbl where name like ?";
			break;
		}	
		try {
			ps=(PreparedStatement) con.prepareStatement(deleteQuery);
			deleteData="%"+deleteData+"%";
			ps.setString(1, deleteData);
			resultNumber=ps.executeUpdate();		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null && !ps.isClosed()) {
					ps.close();	
				}
				if(con!=null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultNumber;
	}

	//테이블 모델객체 수정 정적멤버함수(DB삽입기능) 
	public static int updatestudent(StudentData studentModel) {
		Connection con=DBUtility.getConnection();
		if(con==null) {
			System.out.println("mysql connection fail");
			return 0;
		}
		String updateQuery=null;
		PreparedStatement ps=null;
		int resultNumber=0;
		
		updateQuery="update Studentproject.studenttbl set kor=?,eng=?,math=?,total=?,avr=?,grade=? where hp=?";
			
		try {
			ps=(PreparedStatement) con.prepareStatement(updateQuery);	
			ps.setInt(1, studentModel.getKor());
			ps.setInt(2, studentModel.getEng());
			ps.setInt(3, studentModel.getMath());
			ps.setInt(4, studentModel.getTotal());
			ps.setDouble(5, studentModel.getAvr());
			ps.setString(6, studentModel.getGrade());
			ps.setString(7, studentModel.getHp());		
			resultNumber=ps.executeUpdate();		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null && !ps.isClosed()) {
					ps.close();	
				}
				if(con!=null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultNumber;
	}

	public static List<StudentData> sortstudent(int no) {
		final int ASC=1, DESC=2;
		//테이블에있는 레코드 셋을 가져오기 위한 ArrayList<PhoneBookModel>
		List<StudentData> list=new ArrayList<StudentData>();
		String sortQuery=null;
		//데이터베이스 접속요청
		Connection con=DBUtility.getConnection();
		if(con==null) {
			System.out.println("mysql connection fail");
			return null;
		}	
		//DB명령문
		switch(no) {
		case ASC:
			sortQuery="select*from Studentproject.studenttbl order by avr asc";
			break;
		case DESC:
			sortQuery="select*from Studentproject.studenttbl order by avr desc";
			break;
		}
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			ps=(PreparedStatement) con.prepareStatement(sortQuery);
			resultSet=ps.executeQuery(); 
			
			while(resultSet.next()) {
				String name=resultSet.getString(1);
				String id=resultSet.getString(2);
				String hp=resultSet.getString(3);
				int kor=resultSet.getInt(4);
				int eng=resultSet.getInt(5);
				int math=resultSet.getInt(6);
				int total=resultSet.getInt(7);
				Double avr=resultSet.getDouble(8);
				String grade=resultSet.getString(9);
				
				StudentData studentData  =new StudentData(name, id, hp, kor, eng, math, total, avr, grade);
				list.add(studentData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null && !ps.isClosed()) {
					ps.close();	
				}
				if(con!=null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	
}
