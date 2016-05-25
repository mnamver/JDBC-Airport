package csd;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Passenger {
	private int m_id;
	private String m_name, m_citizenId, m_phone, m_email;
	private LocalDate m_birthDate;
	
	public Passenger(int id, String name, String citizenId, String phone, String email, int day, int mon, int year)
	{
		//...
		m_id = id;
		m_name = name;
		m_citizenId = citizenId;
		m_phone = phone;
		m_email = email;
		m_birthDate = LocalDate.of(year, mon, day);
	}
	
	public int getId() {return m_id;}
	public String getName() {return m_name;}
	public String getCitizenId() {return m_citizenId;}
	public String getPhone() {return m_phone;}
	public String getEmail() {return m_email;}
	public LocalDate getBirthDate() {return m_birthDate;}
	public double getAge() {return ChronoUnit.DAYS.between(m_birthDate, LocalDate.now()) / 365;}
	
	public Passenger setName(String name)
	{
		//...
		m_name = name;
		
		return this;
	}
	
	public Passenger setCitizenId(String citizenId)
	{
		//...
		m_citizenId = citizenId;
		
		return this;
	}
	
	public Passenger setPhone(String phone)
	{
		//....
		m_phone = phone;
		
		return this;
	}
	
	public Passenger setEmail(String email)
	{
		//....
		m_email = email;
		
		return this;
	}
	
	public Passenger setBirthDate(int day, int mon, int year)
	{
		m_birthDate = LocalDate.of(year, mon, day);
		
		return this;		
	}
	//...
	@Override
	public String toString()
	{
		return String.format("%s-%s-%s-%s-%.01f", m_name, m_citizenId, m_phone, m_email, this.getAge());
	}
}