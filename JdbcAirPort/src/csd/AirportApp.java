package csd;

import java.sql.SQLException;
import java.util.List;

public class AirportApp {
	private Keyboard m_kb;
	private SqLiteDBHelper m_helper;

	private void displayMainMenu() {
		System.out.println("1.Yolcu");
		System.out.println("2.U�u�");
		System.out.println("3.Yolcu U�u�");
		System.out.println("4.��k��");
		System.out.print("Se�enek:");
	}

	private void displayPassengerMenu() {
		System.out.println("1.Yolcu Ekle");
		System.out.println("2.Yolcu Sil");
		System.out.println("3.Yolcu G�ncelle");
		System.out.println("4.Yolcular� Listele");
		System.out.println("5.��k��");
		System.out.print("Se�enek:");
	}

	private void displayFlightsMenu() {
		System.out.println("1.UcakSeferi Ekle");
		System.out.println("2.UcakSeferi Sil");
		System.out.println("3.UcakSeferini G�ncelle");
		System.out.println("4.UcakSeferlerini Listele");
		System.out.println("5.C�k��");
		System.out.println("Secenek");
	}

	private void flightMenuProc() throws SQLException {
		int option;

		for (;;) {
			this.displayFlightsMenu();
			option = m_kb.getInt();
			if (option < 1 || option > 5) {
				System.err.println("Gecersiz Secenek");
				continue;
			}
			switch (option) {
			case 1:
				this.insertFlightProc();
				break;
			case 2:
		//		this.deleteFlight();
			//	break;
			case 3:
		//		this.updateFlight();
				break;
			case 4:
	//			this.listFlights();
				break;
			case 5:
				break;
			}

		}
	}

	private void insertFlightProc()  {
		String code = m_kb.getLine("ucag�n code numaras�n� giriniz");
		String departure = m_kb.getLine("ucak nereden kalk�yor");
		String destination = m_kb.getLine("ucak nerede inecek");
		String deptime = m_kb.getLine("ucak saat kacda kalkacak");
		String desttime = m_kb.getLine("ucak saat kacda inecek");
		int numberOfPassangers = m_kb.getInt("ucakda kac yolcu var");
		Flights f = new Flights(10, numberOfPassangers, code, departure, destination, deptime, desttime);

		try {
			m_helper.insertFlight(f);
			System.out.println("Kay�t Ba�ar�yla eklendi");
		} catch (SQLException e) {
			System.err.println("kay�t eklenemdi");
			e.printStackTrace();
		}
		
	}

	private void passengerMenuProc() throws Exception {
		int option;

		EXIT_PASSENGER_MENU: for (;;) {
			this.displayPassengerMenu();
			option = m_kb.getInt();

			if (option < 1 || option > 5) {
				System.err.println("Gecersiz secenek");
				continue;
			}
			switch (option) {
			case 1:
				this.insertPassengerProc();
				break;
			case 2:
				this.deletePassenger();
				break;
			case 3:
				this.updatePassenger();
				break;
			case 4:
				this.listPassengers();
				break;
			case 5:
				break EXIT_PASSENGER_MENU;
			}
		}
	}

	private void insertPassengerProc() throws Exception {
		String name = m_kb.getLine("�sim giriniz");
		String citizenId = m_kb.getLine("Vatanda�l�k numaras� giriniz");
		String phone = m_kb.getLine("Telefon giriniz");
		String email = m_kb.getLine("Email giriniz");
		System.out.println("Do�um tarihi bilgileri:");
		int day = m_kb.getInt("G�n?");
		int mon = m_kb.getInt("Ay?");
		int year = m_kb.getInt("Y�l?");

		Passenger p = new Passenger(0, name, citizenId, phone, email, day, mon, year);

		try {
			m_helper.insertPassenger(p);
			System.out.println("Kay�t Ba�ar�yla eklendi");
		} catch (SQLException ex) {
			System.err.println("Kay�t eklenemedi");
		}
	}

	private void listPassengers() throws Exception {
		List<Passenger> list = m_helper.getPassengers();

		if (list.size() == 0) {
			System.out.println("Hi� bir yolcu bulunamad�");
			return;
		}

		for (Passenger p : list)
			System.out.println(p);
	}

	private void updatePassenger() throws Exception {
		String citizenId = m_kb.getLine("Vatanda�l�k numarass�n� giriniz");
		Passenger p = m_helper.getPassengerByCitizenId(citizenId);

		if (p == null) {
			System.out.printf("%s vatanda�l�k numas� olarak kay�tl� yolcu yok%n", citizenId);
			return;
		}

		System.out.println(p);
		int option = m_kb.getInt("�smi g�ncellemek ister misiniz? Evet i�in 1 giriniz");

		if (option == 1)
			p.setName(m_kb.getLine("�sim giriniz"));

		option = m_kb.getInt("Telefonu g�ncellemek ister misiniz? Evet i�in 1 giriniz");

		if (option == 1)
			p.setPhone(m_kb.getLine("Telefon numaras�n� giriniz"));

		option = m_kb.getInt("Email bilgisini g�ncellemek ister misiniz? Evet i�in 1 giriniz");

		if (option == 1)
			p.setEmail(m_kb.getLine("Email adresinizi giriniz"));

		m_helper.updatePassenger(p);
		System.out.println("Bilgiler g�ncellendi");
	}

	private void deletePassenger() throws Exception {
		String citizenId = m_kb.getLine("Silinecek yolcunun Vatanda�l�k numaras�n� giriniz");

		int n = m_helper.deletePassengerByCitizenId(citizenId);

		if (n == 0)
			System.err.println("Silinecek yolcu bulunamad�");
		else
			System.out.println("Yolcu ba�ar�yla silindi");
	}

	public AirportApp() {
		m_kb = new Keyboard();
		try {
			m_helper = new SqLiteDBHelper("flightdb");
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void run() throws Exception {
		int option;

		EXIT_MAIN_MENU: for (;;) {
			this.displayMainMenu();
			option = m_kb.getInt();
			if (option < 1 || option > 4) {
				System.err.println("Ge�ersiz Se�enek");
				continue;
			}

			switch (option) {
			case 1:
				this.passengerMenuProc();
				break;
			case 2:
				this.flightMenuProc();
				break;
			case 3:
				// ...
				break;
			case 4:
				break EXIT_MAIN_MENU;
			}
		}
		System.out.println("te�ekk�r ederiz");

	}
}