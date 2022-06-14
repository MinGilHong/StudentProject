package StudentData;

import java.io.Serializable;
import java.util.Objects;

public class StudentData implements Comparable<Object>, Serializable{
	//과목수
	public static final int SUBJECT_COUNT=3;
	//멤버변수
	private String name;
	private String id;
	private String hp;
	private int kor;
	private int math;
	private int eng;
	private int total;
	private double avr;
	private String grade;
	//생성자(디폴트생성자,매개변수생성자)
	public StudentData(String name,  String id, String hp, int kor, int math, int eng, int total, Double avr, String grade) {
		super();
		this.name = name;
		this.id = id;
		this.hp = hp;
		this.kor = kor;
		this.math = math;
		this.eng = eng;
		this.total = total;
		this.avr = avr;
		this.grade = grade;
	}
	//합계계산 함수
	public void totalSum(){
		this.total=this.kor+this.math+this.eng;
	}
	
	//평균계산 함수
	public void totalAvr(){
		this.avr=this.total/SUBJECT_COUNT;
	}
	//등급계산 함수
	public void calGrade() {
		if(this.avr>=90.0) {
			this.grade="A";
		}else if(this.avr>=80.0) {
			this.grade="B";
		}else if(this.avr>=70.0) {
			this.grade="C";
		}else if(this.avr>=60.0) {
			this.grade="D";
		}else {
			this.grade="F";
		}
	}
	//오버라이드 : hashcode(hp), equals(hp), compareTo(avr), toString
	@Override
	public int hashCode() {
		return Objects.hash(hp);
	}
	@Override
	public boolean equals(Object obj) {
		boolean flag=false;
		if(obj instanceof StudentData) {
			StudentData sd=(StudentData)obj;
			flag=this.hp.equals(sd.hp);
		}
		return flag;
	}
	@Override
	public int compareTo(Object o) {
		if(!(o instanceof StudentData)) {
			throw new IllegalArgumentException("비교가 안되는 객체를 보냈어요");
		}
		StudentData studentData=(StudentData)o;
		if(this.avr<studentData.avr) {
			return -1;
		}else if(this.avr>studentData.avr) {
			return 1;
		}else {
			return 0;
		}		
	}
	@Override
	public String toString() {
		return  name + "\t" +  id +  "\t" + hp
				+  "\t" + kor +  "\t" + math +  "\t" + eng +  "\t" + total +  "\t" + String.format("%6.2f", avr) +  "\t"
				+ grade ;
	}
	//getter,setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getAvr() {
		return avr;
	}
	public void setAvr(double avr) {
		this.avr = avr;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public static int getSubjectCount() {
		return SUBJECT_COUNT;
	}
	
}		
