package paket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
	private static int numberOf10KMBills;// broj novcanica od 10 KM
	private static int numberOf20KMBills;// broj novcanica od 20 KM
	private static int numberOf50KMBills;// broj novcanica od 50 KM
	private static int numberOf100KMBills;// broj novcanica od 100 KM
	public static final int billsLimit=100;

	/*
	 * Osnovni meni u kome korisnik upisuje svoj username i password kako bi se
	 * potvrdio njegov identitet
	 */
	public static void loginMenu() {
		FileInputOutput.enterNumOfBillsFromFile();
		FileInputOutput.enterUsersFromFile();
		Scanner input = new Scanner(System.in);
		System.out.println("*******************************************");
		System.out.println("Unesite korisnicko ime i lozinku: ");
		String name = input.next();
		int password=0;
		try{
		password = input.nextInt();
		}catch(Exception err){
			System.out.println("Password je cetverocifren cijeli broj!");
			loginMenu();
		}
		validate(name, password);
	}

	/*
	 * Metoda poredi prosledjene parametre name i password iz loginMenu() sa
	 * imenom i lozinkom admina i ako se podudaraju poziva metodu adminMenu iz
	 * klase Admin. Ako se ne podudaraju, provjera da li postoji User ciji
	 * username i password odgovaraju prosledjenim argumentima i ako postoji
	 * poziva userMenu metodu klase user. Ako ni to nije uspjesno izvrseno,
	 * obavjestava korisnika da je unio pogresan username ili password i vraca
	 * ga na loginMenu.
	 */
	public static void validate(String name, int password) {
		if ((name.equals(Admin.getAdminName()) && password==Admin
				.getAdminPassword())) {

			System.out.println("\nUspjesno ste se ulogovali kao administrator.");
			System.out
					.println("###########################################################");

			Admin.adminMenu();

		} else {

			User user = UserBase.getUser(name, password);
			if (user != null) {
				System.out.println("\nUspjesno ste se ulogovali kao korisnik.");
				System.out
						.println("###########################################################");
				User.userMenu(user);

			} else {

				System.out
						.println("Unijeli ste pogresan username ili password!\nMolimo Vas da pokusate ponovo.");
				loginMenu();

			}
		}
	}

	public static double getATMBalance() {
		return numberOf100KMBills * 100 + numberOf50KMBills * 50
				+ numberOf20KMBills * 20 + numberOf10KMBills * 10;
	}

	static int getNumberOf10KMBills() {
		return numberOf10KMBills;
	}

	static void setNumberOf10KMBills(int numberOf10KMBills) {
		ATM.numberOf10KMBills = numberOf10KMBills;
	}

	static int getNumberOf20KMBills() {
		return numberOf20KMBills;
	}

	static void setNumberOf20KMBills(int numberOf20KMBills) {
		ATM.numberOf20KMBills = numberOf20KMBills;
	}

	static int getNumberOf50KMBills() {
		return numberOf50KMBills;
	}

	static void setNumberOf50KMBills(int numberOf50KMBills) {
		ATM.numberOf50KMBills = numberOf50KMBills;
	}

	static int getNumberOf100KMBills() {
		return numberOf100KMBills;
	}

	static void setNumberOf100KMBills(int numberOf100KMBills) {
		ATM.numberOf100KMBills = numberOf100KMBills;
	}

	/** Proslijedjujemo korisnika i sumu koju zelimo isplatiti */
	public static void withdraw(User user, int amount) {
		// kreiramo objekat user i dodjeljujemo mu vrijednosti objekta cije smo
		// korisnicko ime proslijedili u metodu
		// ja sam korisnike smjestio u arraylist u klasi UserBase

		// User user objekat "pokazuje" na isti objekat iz arraylista
		// zato ce sve promjene koje su se desile u metodi biti izvršene na
		// originalnom useru cije smo
		// korisnicko ime proslijedili metodi
		if (amount > getATMBalance()) {
			System.out.println("U bankomatu nema dovoljno novca za isplatu.");
		} else if (amount > user.getBalance()) {
			System.out.println("Nemate toliko novaca na racunu.");
		} else {
			int count100 = 0, count50 = 0, count20 = 0, count10 = 0;// brojaci
																	// novcanica
			int paidSum = 0;// isplacen iznos

			while (amount >= 100 && count100 < getNumberOf100KMBills()) {

				amount -= 100;
				count100++;
				paidSum += 100;
			}
			while (amount >= 50 && count50 < getNumberOf50KMBills()) {

				amount -= 50;
				count50++;
				paidSum += 50;
			}

			while (amount >= 20 && count20 < getNumberOf20KMBills()) {

				amount -= 20;
				count20++;
				paidSum += 20;
			}
			while (amount >= 10 && count10 < getNumberOf10KMBills()) {

				amount -= 10;
				count10++;
				paidSum += 10;
			}
			if (amount == 0) {
				setNumberOf100KMBills(getNumberOf100KMBills() - count100);
				setNumberOf50KMBills(getNumberOf50KMBills() - count50);
				setNumberOf20KMBills(getNumberOf20KMBills() - count20);
				setNumberOf10KMBills(getNumberOf10KMBills() - count10);
				user.setBalance(user.getBalance() - paidSum);
				System.out.println("Uspjeli smo isplatiti    " + paidSum);
				System.out
						.println("Bankomat ce Vam izbaciti sledece novcanice: ");
				if (count100 > 0) {
					System.out.println(count100 + " x 100 KM");
				}
				if (count50 > 0) {
					System.out.println(count50 + " x 50 KM");
				}
				if (count20 > 0) {
					System.out.println(count20 + " x 20 KM");
				}
				if (count10 > 0) {
					System.out.println(count10 + " x 10 KM");
				}
			} else {
				System.out.println("Iznos nije moguce isplatiti!");
			}

		}
	}

	public static void checkATM() {
		System.out.println("U bankomatu ima: " + getATMBalance() + " KM");
		System.out.println("Broj novcanica od 100 KM: "
				+ getNumberOf100KMBills());
		System.out
				.println("Broj novcanica od 50 KM: " + getNumberOf50KMBills());
		System.out
				.println("Broj novcanica od 20 KM: " + getNumberOf20KMBills());
		System.out
				.println("Broj novcanica od 10 KM: " + getNumberOf10KMBills());
	}

	
}
