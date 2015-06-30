package paket;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class User {
	private String userName;
	private int password;
	private double balance;
	private Date dateCreated;

	User() {

	}

	User(String userName, int password, double balance) {
		this.userName = userName;
		this.password = password;
		this.balance = balance;
		this.dateCreated = new Date();
	}

	String getUserName() {
		return userName;
	}

	void setUserName(String userName) {
		this.userName = userName;
	}

	int getPassword() {
		return password;
	}

	void setPassword(int password) {
		this.password = password;
	}

	double getBalance() {
		return balance;
	}

	void setBalance(double balance) {
		this.balance = balance;
	}

	Date getDateCreated() {
		return dateCreated;
	}

	void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	// prikaz stanja na racunu i datum kreiranja user-a
	void printInfo() {
		System.out
				.println("########################################################");
		System.out.println("\nIznos na vasem racunu je: " + this.getBalance()
				+ " KM");
		System.out.println("Vas racun je kreiran: " + this.getDateCreated()
				+ "\n");
		System.out
				.println("########################################################");
	}

	public static void userMenu(User currentUser) {

		System.out.println("\n******* Dobrodosli *******\n");
		System.out.println("Izaberite zeljenu opciju unosenjem broja opcije\n");
		System.out.println("Opcija 0:  Izlogujte se.");
		System.out.println("Opcija 1:  Podignite novac.");
		System.out.println("Opcija 2:  Provjerite stanje racuna.");
		
		Scanner userInput = new Scanner(System.in);
		int userChoice = 0;
		try {
			userChoice = userInput.nextInt();
		} catch (Exception e) {
			System.out
					.println("Unijeli ste pogresnu vrijednost, unesite broj opcije koju zelite izabrati.");
			userMenu(currentUser);
		}
		switch (userChoice) {
		case 0: {// log out
			System.out.println("Hvala na posjeti, zelimo Vam prijatan dan.");
			FileInputOutput.printUsersToFile();
			ATM.loginMenu();

		}
		case 1: { // podizanje novca(withdrow)
			Scanner withInput = new Scanner(System.in);
			int sum = 0;
			System.out.println("Unesite sumu koju zelite podignuti: ");
			try {
				sum = withInput.nextInt();
			} catch (Exception ex) {
				System.out
						.println("Unijeli ste pogresnu vrijednost, pokusajte ponovo!");
				userMenu(currentUser);
			}
			ATM.withdraw(currentUser, sum);
			FileInputOutput.printNumOfBillsToFile();
			userMenu(currentUser);
			break;

		}
		case 2: {//provjera stanja racuna
			currentUser.printInfo();
			userMenu(currentUser);

			break;
		}
		default: {
			System.out.println("Unijeli ste pogresnu vrijednost!");
			userMenu(currentUser);
		}
		}
	}
}
