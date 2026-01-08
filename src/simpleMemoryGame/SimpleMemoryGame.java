package simpleMemoryGame;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SimpleMemoryGame {
	final static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	final static char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		//int level = 0;
		while(true) {
			//level++;
			//int difficulty = 4*level;
			
			System.out.println("Welcome to the memory game!");
			System.out.println("In this game, you get a sequence of letters or numbers and you have to remember them.");
			System.out.println("You'll see it for a moment, then try your best to repeat it back!");
			System.out.println("We'll keep going until you get it right.");
			System.out.println("Difficulty represents how many characters are shown, so you can go as high as you want!");
			System.out.print("Choose your difficulty (at least 1): ");
			int difficulty = 4*Integer.parseInt(key.nextLine());
			System.out.println("Now pick your type");
			System.out.println("1. Numbers");
			System.out.println("2. Letters");
			System.out.println("3. Mix Of Numbers And Letters");
			System.out.println("4. Exit Game");
			System.out.print("Enter the number corresponding to your choice: ");
			int choice = Integer.parseInt(key.nextLine());
			
			String message = "";
			switch (choice) {
				case 1:
					message = generateMessage(difficulty, nums);
					break;
				case 2:
					message = generateMessage(difficulty, alphabet);
					break;
				case 3:
					char[] temp = add(alphabet, nums);
					message = generateMessage(difficulty, temp);
					break;
				case 4:
					System.out.println("Exiting...");
					System.out.print("Goodbye!");
					return;
				default:
					System.out.println("That is not valid input.");
					System.out.print("Exiting...");
					System.out.print("Goodbye!");
					return;
			}
			
			
			boolean gameOn = true;
			while(gameOn) {
				printMessage(message, difficulty+2);
				clearConsole();
				gameOn = memorise(message);
			}
			
			System.out.println("Well Done!\n");
		}
		
	}
	
	public static String generateMessage(int difficulty, char[] characters) {
		String message = "";
		Random r = new Random();
		for(int i = 0; i < difficulty; i++) {
			int num = r.nextInt(characters.length);
			message += characters[num];
		}
		return message;
	}
	
	public static boolean memorise(String message) {
		Scanner key = new Scanner(System.in);
		System.out.print("Repeat the message (or type 'exit to quit): ");
		String guess = key.nextLine();
		if(guess.toLowerCase().equals("exit")) {
			System.out.println("Exiting...");
			System.out.print("Goodbye!");
			System.exit(0);
		}
		if(guess.toUpperCase().equals(message)) { 
			System.out.println("Correct!");
			return false; 
		}
		System.out.println("Incorrect.");
		return true;
	}
	
	public static char[] add(char[] a1, char[] a2) {
		char[] temp = new char[a1.length + a2.length];
		for(int i = 0; i < a1.length; i++) {
			temp[i] = a1[i];
		}
		for(int i = a1.length; i < temp.length; i++) {
			temp[i] = a2[i-(a1.length)];
		}
		return temp;
	}
	
	public static void clearConsole() {
		for(int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
	
	public static void printMessage(String message, int time) {
		System.out.println("\n:: Memorise this! ::\n\n" + message + "\n");
		
		try{
			for(int i = time; i > 0; i--) {
				System.out.print(i + "...");
				TimeUnit.SECONDS.sleep(1);
			}
		}
		catch(InterruptedException e) {
			System.out.println("An error occured.");
		}
	}
}
