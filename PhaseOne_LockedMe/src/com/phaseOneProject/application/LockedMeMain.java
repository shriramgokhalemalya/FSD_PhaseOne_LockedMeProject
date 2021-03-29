package com.phaseOneProject.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import com.phaseOneProject.model.*;

public class LockedMeMain {
	
	private static Scanner keyboardValues;
	private static Scanner inputValues;
	private static Scanner lockedMeInput;
	
	private static PrintWriter output;
	//private static PrintWriter lockedMeOutput;
	
	private static LockedMeUsers users;
	//private static LockedMeUserCredentials usercreds;
	
	public static void main(String[] args) {
		
		initialize();
		welcomeScreen();
		userInteractions();
		
		
	}
	
	public static void initialize() {

		File  dbFile = new File("files.txt");
		//File  lockerFile = new File("lockMeFiles.txt");
		
		try {
			//read data from db file
			inputValues = new Scanner(dbFile);
			
			//red data from locker file
			//lockedMeInput = new Scanner(lockerFile);
			
			//read data from keyboard
			keyboardValues = new Scanner(System.in);
			
			//out put 
			output = new PrintWriter( new FileWriter(dbFile,true));
			//lockedMeOutput = new PrintWriter( new FileWriter(lockerFile,true));
			
			users=new LockedMeUsers();
			//usercreds=new LockedMeUserCredentials();
			
			
			
		} catch (IOException e) {
			System.out.println("404 : File Not Found ");
		}
		
	}
	
	
	public static void welcomeScreen() {
		System.out.println("-----------------------------------------");
		System.out.println("#					#");
		System.out.println("*   Welcome To LockedMe.com		*");
		System.out.println("*					*");
		System.out.println("*   Developed By: Shriram Gokhale	*");
		System.out.println("#					#");
		System.out.println("------------------------------------------");
		
	}
	
	public static void userInteractions() {
		System.out.println("------------------------------------------");
		System.out.println("If you are new here, press 1");
		System.out.println("For SignIn Press 2");
		System.out.println("1 . Registration ");
		System.out.println("2 . Sign In ");
		System.out.println("------------------------------------------");
		int option = keyboardValues.nextInt();
		switch(option) {
			case 1 : 
				registerUser();
				break;
			case 2 :
				signInUser();
				break;
			default :
				System.out.println("Please select either  1 or 2");
				userInteractions();
				break;
		}
		keyboardValues.close();
		inputValues.close();
	}
	public static void displayOptions() {
		
		System.out.println("Select one of the options below");
		System.out.println("Click 1 to get all files");
		System.out.println("Click 2 for file options");
		
		int option = keyboardValues.nextInt();
		switch(option) {
			case 1: 
				getFiles();
				break;
			case 2:
				lockerOptions();
				break;
			default:
				System.out.println("Provide valid option");
				displayOptions();
				break;
		
		}
	}
	
	public static void lockerOptions() {
		System.out.println("Select one of the options below:");
		System.out.println("1 . GET ALL FILES");
		System.out.println("2 . ADD FILES ");
		System.out.println("3 . DELETE FILES ");
		System.out.println("4 . SEARCH FILES ");
		int option = keyboardValues.nextInt();
		switch(option) {
			case 1 : 
				getFiles();
				//fetchCredentials(inpUsername);
				break;
			case 2 :
				//storeCredentials(inpUsername);
				addUserSpecifiedFile();
				break;
			case 3 :
				deleteFile();
				//deleteCredentials(inpUsername);
				break;
			case 4 :
				searchFile();
				//searchCredentials(inpUsername);
				break;
			default :
				System.out.println("Please select one of the options");
				break;
		}
		lockedMeInput.close();
	}
	
	
	private static void searchFile() {
		
		System.out.println("Enter File name to search with extension Eg: root.txt ");
		String site = keyboardValues.next();
		
		File folder = new File("./Files");
		
		File[] listOfFiles = folder.listFiles();
		
		boolean found = false;

		for (File file : listOfFiles) {
		    if (file.getName().equals(site)) {
		        System.out.println("File "+site+" found in the directory");
		        found = true;
		    }
		}
		if (!found) {
			System.out.println("File Not found or invalid name");
			searchFile();
		}
		navigationMenu();
		
		
	}
	
	private static void deleteFile() {
		
		try {
			
			System.out.println("Mention the name of file for deletion");
			
			String site = keyboardValues.next();
			
			//File folder = new File("./Files");
			
			site = "./Files/" + site + ".txt";
			
			File file = new File(site);
			
			if (file.delete()) {
				
				System.out.println("File deleted successfully");
			}
			else {
				System.out.println("Failed to delete the fail. Please provide valid name and Try again please");
				deleteFile();
			}
			navigationMenu();
			
			
		}catch(Exception ex) {
			
			System.out.println(ex.getMessage());
		}
	}
	
	private static void getFiles() {
		
		try {
			
			File folder = new File("./Files");
			
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
			    if (file.isFile()) {
			        System.out.println(file.getName());
			    }
			}
			navigationMenu();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	private static void addUserSpecifiedFile() {
		
		try {
			System.out.println("Provide the name of the file");
			String site = keyboardValues.next();
			
			//File folder = new File("./Files");
			
			site = "./Files/" + site + ".txt";
			
			File file = new File(site);
		
			if (file.createNewFile()) {
				
				System.out.println("New File added");
			}
			else {
				System.out.println("Failed to add new file. Provide unique file name!! Try again please");
				addUserSpecifiedFile();
			}
			navigationMenu();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	private static void navigationMenu() {
		
		System.out.println("Click 1 to go back to Main Menu, Click 2 to close the application");
		
		int back = keyboardValues.nextInt();
		
		switch(back) {
		case 1 : 
			
			lockerOptions();
			break;
			
		case 2:
			
			System.exit(0);
			
		default :
			System.out.println("Please provide valid option");
			navigationMenu();
			break;
	}
		
	}
	
	
	public static void registerUser() {
		System.out.println("-----------------------------------------");
	//	System.out.println("*					*");
		System.out.println("*   WELCOME TO REGISTRATION PAGE	*");
	//	System.out.println("*					*");
		System.out.println("-----------------------------------------");
		
		try {
			
			System.out.println("Enter Username :");
			String username = keyboardValues.next();
			users.setUserName(username);
			
			System.out.println("Enter Password :");
			String password = keyboardValues.next();
			users.setPassword(password);
			
			output.println(users.getUserName());
			output.println(users.getPassword());
			
			System.out.println("Registration Successful !Your username is "+users.getUserName());
			
			System.out.println("Click 1 to Sign In");
			System.out.println("Click 2 to go back to main menu");
			
			int back = keyboardValues.nextInt();
			
			if(back == 1) {
				
				signInUser();
			}
			else if (back == 2){
				
				userInteractions();
			}
			
			output.close();
			
		}catch(Exception ex) {
			
			System.out.println(ex.getMessage());
		}
		
	}
	
	
	public static void signInUser() {
		System.out.println("------------------------------------------");
		//System.out.println("#					*");
		System.out.println("*   WELCOME TO SIGN IN PAGE	 *");
		//System.out.println("*					*");
		System.out.println("------------------------------------------");
		System.out.println("Enter Username :");
		String inpUsername = keyboardValues.next();
		boolean found = false;
		while(inputValues.hasNext() && !found) {
			if(inputValues.next().equals(inpUsername)) {
				System.out.println("Enter Password :");
				String inpPassword = keyboardValues.next();
				if(inputValues.next().equals(inpPassword)) {
					System.out.println("Sign In Successful");
					found = true;
					lockerOptions();
					break;
				}
			}
		}
		if(!found) {
			System.out.println("User Not Found : Sign In Failure");
			System.out.println("Click 1 to try again");
			System.out.println("Click 2 to go back to Main Menu");
			
			int back = keyboardValues.nextInt();
			
			if(back == 1) {
				
				signInUser();
			}
			else if (back == 2){
				
				userInteractions();
			}
		}
		
		
		
	}

}