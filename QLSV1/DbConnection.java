package QLSV1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import QLSV.Student;

public class DbConnection {
	public static Connection getConnection() {// connection function
		Connection connection=null;
		String url="jdbc:sqlserver://DESKTOP-FCKCG6E\\SQLEXPRESS01:1433;databasename=QLSV2";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(url,"sa","123456");
			Statement stmt = connection.createStatement();
		}catch(Exception ex) {
		ex.printStackTrace();
	}
		return connection;
}
	public static List<SV>findAll(){
		List<SV>studentList= new ArrayList<>();
		String query="select*from SV";
		try {
			Connection connection =getConnection();
			Statement stmt= connection.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				SV st=new SV(rs.getString("MSSV"),rs.getString("NameSV"),rs.getInt("Gender"),
						rs.getDate("NS"),rs.getInt("ID_Lop"));
				studentList.add(st);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return studentList;
	}
	public static void insert(SV st) {
		String query="insert into SV values(?,?,?,?,?)";
		try {
			Connection connection =getConnection();
			PreparedStatement pstmt=connection.prepareStatement(query);
			pstmt.setString(1, st.getMSSV());
			pstmt.setString(2, st.getNameSV());
			pstmt.setInt(3, st.getGender());
			pstmt.setDate(4, st.getNS());
			pstmt.setInt(5, st.getID_Lop());
			pstmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void delete(SV st) {
		String query="delete from SV where MSSV='"+st.getMSSV()+"'";
		try {
			Connection connection =getConnection();
			PreparedStatement pstmt=connection.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void Update(SV st) {
		String query="Update SV set MSSV=?,NameSV=?,Gender=?,NS=?,ID_Lop=? where MSSV='"+st.getMSSV()+"'";
		try {
			Connection connection=getConnection();
			PreparedStatement pstmt=connection.prepareStatement(query);
			pstmt.setString(1, st.getMSSV());
			pstmt.setString(2, st.getNameSV());
			pstmt.setInt(3, st.getGender());
			pstmt.setDate(4, st.getNS());
			pstmt.setInt(5, st.getID_Lop());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static List<SV>findByMSSV(SV s) {
		List<SV>studentl= new ArrayList<>();
		String query="select*from SV where SV.MSSV='"+s.getMSSV()+"'";
		try {
			Connection connection =getConnection();
			Statement stmt= connection.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				SV st=new SV(rs.getString("MSSV"),rs.getString("NameSV"),rs.getInt("Gender"),
						rs.getDate("NS"),rs.getInt("ID_Lop"));
				studentl.add(st);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return studentl;
	}
}
