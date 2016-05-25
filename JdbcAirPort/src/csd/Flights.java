package csd;

public class Flights {
	private int  m_id , m_numberOfPassangers ;
	private String m_code , m_departure , m_destination , m_deptime , m_desttime ;
	
	public Flights(int id , int numberOfPassangers , String code , String departure , String destination ,String deptime,String desttime){
		m_id = id ;
		m_numberOfPassangers = numberOfPassangers ;
		m_code = code ;
		m_departure = departure ;
		m_destination = destination ;
		m_deptime = deptime ;
		m_desttime = destination ;
	}
	
	
	public int getId(){return m_id ;}
	public int getNumberOfPassagers(){ return m_numberOfPassangers; }
	public String getCode(){return m_code; }
	public String getDeparture(){return m_departure;}
	public String getDestination(){return m_destination;}
	public String getDepartureTime(){return m_deptime;}
	public String getDestTime(){return m_desttime;}
	
	public void setId(int id){
		m_id = id ;
	}
	public void setNumberOfPassangers(int numberOfPassangers){
	 m_numberOfPassangers = numberOfPassangers;
	}
	public void setCode(String code){
	m_code = code ;
		}
	public void setDeparture(String departure){
		m_departure = departure ;
				}
	public void setDestination(String destination){
		m_destination = destination;
		}
	public void setDepartureTime(String deptime){
		m_deptime = deptime ;
		}
	public void setDestTime(String desttime){
		m_desttime = desttime;
		}

	public String toString(){
		return String.format("%s - %s - %s -%s - %s - %d%n", m_code , m_departure , m_destination ,m_deptime , m_desttime , m_numberOfPassangers);
	}
	
}
