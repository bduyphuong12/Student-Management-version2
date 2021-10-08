package QLSV1;

import java.sql.Date;

public class SV {
	private String MSSV, NameSV;
	private int Gender, ID_Lop;
	private Date NS;
	public SV() {
		
	}
	public SV(String MSSV, String NameSV,int  Gender, Date NS, int ID_Lop) {
		this.MSSV=MSSV;
		this.NameSV=NameSV;
		this.Gender=Gender;
		this.NS=NS;
		this.ID_Lop=ID_Lop;
	}
	public String getMSSV() {
		return MSSV;
	}
	public void setMSSV(String mSSV) {
		MSSV = mSSV;
	}
	public String getNameSV() {
		return NameSV;
	}
	public void setNameSV(String nameSV) {
		NameSV = nameSV;
	}
	public int getGender() {
		return Gender;
	}
	public void setGender(int gender) {
		Gender = gender;
	}
	public int getID_Lop() {
		return ID_Lop;
	}
	public void setID_Lop(int iD_Lop) {
		ID_Lop = iD_Lop;
	}
	public Date getNS() {
		return NS;
	}
	public void setNS(Date nS) {
		NS = nS;
	}
	
	
	
}
	
	
