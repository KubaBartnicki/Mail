package MyMail.MyMailClient;

import java.util.Scanner;

public class Msg {
	Scanner scanner = new Scanner(System.in);
	String message;
	String title;
	String reciver;

	public Msg writeMail() {
		System.out.println("Adres odbiorcy: ");
		reciver = scanner.nextLine();
		System.out.println("Temat: ");
		title = scanner.nextLine();
		System.out.println("Tresc: ");
		this.message = scanner.nextLine();
		return this;
	}
	public String getTitle() {
		return title;
	}
	
	public String getMessage() {
		return message;
	}

	public String getReciver() {
		return reciver;
	}

}
