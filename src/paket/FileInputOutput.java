package paket;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileInputOutput {

	/*
	 * unosi broj novcanica u svim apoenima iz fajla u program
	 */
	static void enterNumOfBillsFromFile() {
		BufferedReader outputMoney = null;
		try {
			outputMoney = new BufferedReader(new FileReader("money.txt"));
			String line = "";
			/*
			 * sve dok ne dodje do zadnje linije fajla, splituje liniju po zarazu
			 * i pravi niz ciji su clanovi vrijednosti koje su bile odvojene
			 * zarezom
			 */
			while ((line = outputMoney.readLine()) != null) {
			/* splituje liniju po zarazu i pravi niz ciji su clanovi
			* vrijednosti koje su bile odvojene zarezom
			* zatim setuje broj navcanica prosledjivanjem clanova niza
			 */
				String[] lineArray = line.split(",");
				ATM.setNumberOf100KMBills(Integer.parseInt(lineArray[0]));
				ATM.setNumberOf50KMBills(Integer.parseInt(lineArray[1]));
				ATM.setNumberOf20KMBills(Integer.parseInt(lineArray[2]));
				ATM.setNumberOf10KMBills(Integer.parseInt(lineArray[3]));
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try{
			outputMoney.close();
			}catch (IOException e){
				System.out.println("Nema fajla!");
				System.exit(0);
			}
		}

	}

	/*
	 * stampanje broja novcanica u fajl money.txt
	 */
	static void printNumOfBillsToFile() {

		PrintWriter writer = null;

		try {
			writer = new PrintWriter(new FileOutputStream("money.txt"));
			writer.write(ATM.getNumberOf100KMBills() + ","
					+ ATM.getNumberOf50KMBills() + ","
					+ ATM.getNumberOf20KMBills() + ","
					+ ATM.getNumberOf10KMBills());

		} catch (Exception el) {
			System.out.println(el);
		} finally {
			writer.close();
		}
	}

	/*
	 * Stampanje svih usera iz userLista u fajl
	 */
	static void printUsersToFile() {

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream("users.txt"));
			/*
			 * petlja prolazi sve User-e u userList-i i za svaki upisuje
			 * username, password i balance, odvojene zarezom u fajl
			 */
			for (User a2 : UserBase.getUserList()) {

				writer.write(a2.getUserName() + "," + a2.getPassword() + ","
						+ a2.getBalance() + "\n");
			}
		} catch (Exception el) {
			System.out.println(el);
		} finally {
			writer.close();
		}
	}

	/*
	 * unosenje usera iz fajla users.txt
	 */
	static void enterUsersFromFile() {
		ArrayList<User> tempUserList = new ArrayList<>();// privremena lista
															// korisnika
		BufferedReader output = null;
		try {
			output = new BufferedReader(new FileReader("users.txt"));
			String line = "";
			while ((line = output.readLine()) != null) {
				String[] lineArray = line.split(",");// niz sacinjen vrijednosti
														// iz linije
														// fajla(username,password,balance)
				User acc = new User(lineArray[0], Integer.parseInt(lineArray[1]),
						Double.parseDouble(lineArray[2]));// kreiranje novog
															// usera sa
															// prosledjenim
															// vrijednostima
															// string niza
				tempUserList.add(acc);// dodavanje novog usera u privremenu
										// listu
			}
			UserBase.setUserList(tempUserList);// setovanje prave liste usera
												// prosledjivanjem privremene
												// liste usera
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try{
			output.close();
			}catch(IOException exc){
				System.out.println("Nema fajla!");
				System.exit(0);
			}
		}
	}
}
