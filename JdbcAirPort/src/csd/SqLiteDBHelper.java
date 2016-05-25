package csd;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
public class SqLiteDBHelper {
	private static final String JDBC_DRIVER = "org.sqlite.JDBC";
	private static String DB_URL = "jdbc:sqlite:C:\\Users\\mnamver\\Desktop\\Database\\FlightDB";
	
	public SqLiteDBHelper(String dbName) throws ClassNotFoundException, SQLException 
	{
		
		Class.forName(JDBC_DRIVER);		
	}
	
	public int insertPassenger(Passenger p) throws Exception
	{
		String sql = "insert into passengers (name, citizenid, phone, email, birthDate) values (?, ?, ?, ?, ?)";
		int result = 0;
		
		try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, p.getName());
			stmt.setString(2, p.getCitizenId());
			stmt.setString(3, p.getPhone());
			stmt.setString(4, p.getEmail());
			//stmt.setDate(5, new Date(ChronoUnit.MILLIS.getDuration().
			stmt.setDate(5, new Date(p.getBirthDate().getDayOfYear(), p.getBirthDate().getMonthValue(), p.getBirthDate().getDayOfMonth()));
			result = stmt.executeUpdate();
		}
		catch (Exception ex) {
			throw ex;
		}
		
		return result;
	}
	public int insertFlight(Flights f) throws SQLException{
		String sql = "insert into flights(code , departure , destination ,deptime ,desttime ,numberOfPassengers) values (?,?,?,?,?,?)";
		int result = 0 ;
		try(Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, f.getCode());
			stmt.setString(2 , f.getDeparture());
			stmt.setString(3 ,f.getDestination());
			stmt.setString(4,f.getDepartureTime());
			stmt.setString(5 , f.getDestTime());
			stmt.setInt(6, f.getNumberOfPassagers());
			
			result = stmt.executeUpdate();
			
		}catch(Exception ex){
			throw ex ;
		}
		return result ;
	}
	
	public int updatePassenger(Passenger p) throws Exception
	{
		String sql = "update passengers set name=?, citizenid=?,phone=?,email=? where id=?";
		int result = 0;
		
		try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, p.getName());
			stmt.setString(2, p.getCitizenId());
			stmt.setString(3, p.getPhone());
			stmt.setString(4, p.getEmail());
			stmt.setInt(5, p.getId());
			result = stmt.executeUpdate();
		}
		catch (Exception ex) {
			throw ex;
		}
		
		return result;		
	}
	
	public int deletePassengerByCitizenId(Passenger p) throws Exception
	{
		String sql = "delete from passengers where citizenId=?";
		int result = 0;
		
		try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, p.getCitizenId());			
			result = stmt.executeUpdate();
		}
		catch (Exception ex) {
			throw ex;
		}
		
		return result;		
	}
	
	public int deletePassengerByCitizenId(String citizenId) throws Exception
	{
		String sql = "delete from passengers where citizenId=?";
		int result = 0;
		
		try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, citizenId);			
			result = stmt.executeUpdate();
		}
		catch (Exception ex) {
			throw ex;
		}
		
		return result;		
	}
	
	public int deletePassengerById(Passenger p) throws Exception
	{
		String sql = "delete from passengers where citizenId=?";
		int result = 0;
		
		try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, p.getId());			
			result = stmt.executeUpdate();
		}
		catch (Exception ex) {
			throw ex;
		}
		
		return result;		
	}
	
	public List<Passenger> getPassengers() throws Exception
	{
		String sql = "select * from passengers";
		List<Passenger> result = new ArrayList<>();
		
		try (Connection conn = DriverManager.getConnection(DB_URL); Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Date date = rs.getDate(6);
				result.add(new Passenger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), date.getDay(), date.getMonth(), date.getYear()));
			}
		}
		catch (Exception ex) {
			throw ex;
		}
		
		return result;		
	}
	
	public Passenger getPassengerByCitizenId(String citizenid) throws Exception
	{
		String sql = "select * from passengers where citizenid=" + citizenid;
		Passenger p = null;
		
		try (Connection conn = DriverManager.getConnection(DB_URL); Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			
//			if (rs.next())
//				p = new Passenger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));			
		}
		catch (Exception ex) {
			throw ex;
		}
		
		return p;		
	}
	
}
















