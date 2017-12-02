

import java.sql.*;
import java.util.*;

public class DataManager {

	Connection connection = null;
	
	public DataManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(Exception e) {
			System.err.println(e.toString());
		}
		String url = "jdbc:mysql://cs2043.cs.unb.ca:3306/cs2043team3";
		try {
			connection = DriverManager.getConnection(url, "cs2043team3", "ATyKKV90");
		}
		catch(SQLException e) {
			System.err.println("Database connection error.");
		}
	}
	
	public UserObject getUserObject(String name, String password) {
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "Select * from UserAccount where name = '" + name + "' and password = sha1('" + password + "');";
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				String uname = rs.getString(1);
				String upassword = rs.getString(2);
				String location = rs.getString(3);
				String gender = rs.getString(4);
				String age = rs.getString(5);
				return new UserObject(uname, upassword, location, gender, age);
			}
		}
		catch(SQLException e) {
			System.err.println("SQL error: getUserAccount");
		}
		return null;
	}
	
	public boolean getUserAccountByUserName(String name){
		
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "Select name from UserAccount where name = '" + name + "';";
			ResultSet rs = st.executeQuery(sqlQuery);
			return (rs.next());
		}
		catch(SQLException e) {
			System.err.println("SQL error: getUserAccountByUserName");
		}
		return false;
	}
	
	public boolean addUser(UserObject user){
		
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "insert into UserAccount values('" + user.getName() + "', sha1('" + user.getPassword() + "'), '"
					+ user.getLocation()[0] + "', '" + user.getGender() + "', '" + user.getAge() + "')";
			return st.execute(sqlQuery);
		}
		catch(SQLException e) {
			System.err.println("SQL error: addUser");
		}
		return false;
	}
	
	public boolean getCityByName(String name) {
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "select cityName from CityList where cityName = '" + name +"';";
			ResultSet rs = st.executeQuery(sqlQuery);
			if(rs.next()) {
				return true;
			}
			return false;
		}
		catch(SQLException e) {
			System.err.println("SQL error: getCityByName");
		}
		return false;
	}
	
	public boolean updateCity(UserObject user) {
		try {
			String new_location = user.getLocationString();
			Statement st = connection.createStatement();
			String sqlQuery = "update UserAccount set location = '" + new_location + "'" + " where name = '"+ user.getName() + "'";
			int r =  st.executeUpdate(sqlQuery);
			if(r>0) {
				return true;
			}else {
				return false;
			}
		}
		catch(SQLException e) {
			System.err.println("SQL error: updateCity");
			return false;
		}
	}
	
	public ArrayList<ChattingObject> getHistoryMessage(){
		ArrayList<ChattingObject> messageList = new ArrayList<ChattingObject>();
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "Select ts, name, message from Chat order by id desc limit 20;";
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				String ts = rs.getString(1);
				String name = rs.getString(2);
				String message = rs.getString(3);
				ChattingObject chat = new ChattingObject(ts, name, message);
				messageList.add(chat);
			}
		}
		catch(SQLException e) {
			System.err.println("SQL error: getHistoryMessage");
		}
		return messageList;
	}
	
	public ArrayList<ChattingObject> getNewMessage(String time){
		ArrayList<ChattingObject> messageList = new ArrayList<ChattingObject>();
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "Select ts, name, message from Chat where ts > timestamp('" + time + "');";
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				String ts = rs.getString(1);
				String name = rs.getString(2);
				String message = rs.getString(3);
				ChattingObject chat = new ChattingObject(ts, name, message);
				messageList.add(chat);
			}
		}
		catch(SQLException e) {
			System.err.println("SQL error: getNewMessage");
		}
		return messageList;
	}
	
	
	public boolean writeMessage(String name, String message) {
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "insert into Chat (name, message) values ('" + name + "', '" + message + "');";
			int r = st.executeUpdate(sqlQuery);
			if(r>0) {
				return true;
			}else{
				return false;
			}
		}
		catch(SQLException e) {
			System.err.println("SQL error: writeMessage");
			return false;
		}
	}
}
