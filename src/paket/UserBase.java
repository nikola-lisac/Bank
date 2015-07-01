package paket;

import java.util.ArrayList;

public class UserBase {
	private static ArrayList<User> userList = new ArrayList<>();

	static ArrayList<User> getUserList() {
		return userList;
	}

	static void setUserList(ArrayList<User> userList) {
		UserBase.userList = userList;
	}

	/*
	 * metoda vraca objekat User na osnovu unijetog username
	 */
	public static User getUser(String username, String password) {
		User user = null;
		boolean status = false;
		// petlja prolazi sve user-e u user listi
		for (int i = 0; i < userList.size(); i++) {
			user = (User) userList.get(i);
			if ((username.equals(user.getUserName()))
					&& (password.equals(user.getPassword()))) {
				status = true;
				break;
			}
		}
		if (status) {// ukoliko postoji korisnik, vraca ga nazad
			return user;
		} else {
			return null;// ukoliko ne postoji, vraca null
		}
	}

	/*
	 * overload-ovana metoda getUser gdje joj prosledjujemo samo username
	 */
	public static User getUser(String username) {
		User user = null;
		boolean status = false;
		// petlja prolazi sve user-e u user listi
		for (int i = 0; i < userList.size(); i++) {
			user = (User) userList.get(i);
			if (username.equals(user.getUserName())) {
				status = true;
				break;
			}
		}
		if (status) {// ukoliko postoji korisnik, vraca ga nazad
			return user;
		} else {
			return null;// ukoliko ne postoji, vraca null
		}
	}
}
