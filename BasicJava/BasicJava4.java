import java.util.Scanner;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.InputMismatchException;
import java.lang.*;

public class BasicJava4 {
    static int choice=0;
    static Scanner sc = new Scanner(System.in);
    static int n, n1, n2;

    public static void main(String []args) {         
       String[][] array = new String[1][1];  //size to be changed later, for instantiation only

       System.out.println();
       System.out.println("------------First Basic Java Program-----------");
       System.out.println();	
	
       askCoordinates();
       array = new String[n1][n2];
       generateArray(array);	
       displayArray(array);
     

       while(choice!=5) {
	    pauseProgram();
            displayMenu();
	    switch(choice) {
 	    	case 1:	
			String subs="";
			int getLength2=0;
			System.out.println();
			while(getLength2<=0 || getLength2>=4) {
				Scanner in2 = new Scanner(System.in);
				System.out.print("Enter the substring to be searched: ");
				//in2.next();
				subs = in2.nextLine();
				getLength2 = subs.length();
				if(getLength2<=0 || getLength2>=4) {
					System.out.println("Please make sure that the length of the subtring to be searched is from 1 to 3.");				
				}
			}
			searchArray(array,subs);	
			break;
		case 2:
			int row=0, column=0;
			int getLength = 0;
			String update="";
			boolean rowFlag = true, colFlag = true;
			System.out.println();
			System.out.println("Enter the Coordinates of which cell to edit: ");
			while(rowFlag) {
				try {
					System.out.print("Coordinate for row: ");
					row = sc.nextInt();
					if(row<0 || row>n1) { throw new ArrayIndexOutOfBoundsException(); }
					rowFlag=false;
				}
				catch(InputMismatchException e) {
					System.out.println("Please make sure that you entered a valid integer");
					rowFlag=true;		
					sc.next();							
				}
				catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("The index you entered is either negative or greater than the size of the matrix.");
					rowFlag=true;		
					//sc.next();
				}
			}
			while(colFlag) {
				try {				
					System.out.print("Coordinate for column: ");
					column = sc.nextInt();
					if(column<0 || column>n2) { throw new ArrayIndexOutOfBoundsException(); }
					colFlag=false;
				}
				catch(InputMismatchException e) {
					System.out.println("Please make sure that you entered a valid integer");
					colFlag=true;	
					sc.next();								
				}
				catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("The index you entered is either negative or greater than the size of the matrix.");
					colFlag=true;		
					//sc.next();
				}
			}
			while(getLength!=3) {
			   Scanner in = new Scanner(System.in);
			   System.out.print("Enter the set of characters to update the cell: ");
			   //in.nextLine();
			   update = in.nextLine();
			   getLength = update.length();
			   if(getLength!=3) { System.out.println("Please make sure that the length of the string is 3"); }			
			}
			editArray(array,row,column,update);
			break;
		case 3:
			System.out.println();
			System.out.println("-----Printing the array----"); 
			displayArray(array); 
			break;
		case 4:
			boolean checker2=true;
			System.out.println();
			System.out.println("-----Resetting the array----"); 
			askCoordinates();
       			array = new String[n1][n2];
       			generateArray(array);	
       			displayArray(array);
  		        break;
		case 5: System.out.println("Exiting the program..."); System.exit(0); break;
		default: System.out.println("Please enter a valid option, taking you back to the menu..."); break;
	    }
       }
       
    }

    public static void generateArray(String a[][]) {
        StringBuilder sb = new StringBuilder();
	Random random = new Random();
        int min = 33;
        int max = 126;
       	
	for(int i=0; i<n1; i++) {
            for(int j=0; j<n2; j++) {
		for(int k=0; k<=2; k++) {
		  int numHolder  = random.nextInt(max - min + 1) + min;
		  String charHolder = Character.toString((char)numHolder);
                  sb.append(charHolder);
		}
		a[i][j] = sb.toString(); 
		sb.setLength(0); //clear stringbuilder		
            }
       }
    }
    
    public static void displayArray(String a[][]) {
	for(int k=0; k<n1; k++) {
            for(int l=0; l<n2; l++) {
		System.out.print(a[k][l] + "  " ); 		
            }
	    System.out.println("");
       }
    }

    public static void editArray(String a[][], int r, int c, String u) {
    	a[r][c] = u;
	System.out.println("Edit Done!");
	System.out.println();
    }

    public static void searchArray(String a[][], String s) {
	boolean flag = false;

	System.out.println();
	System.out.println("Searching for the substring: " + s);

	for(int i=0; i<n1; i++) {
	    for(int j=0; j<n2; j++) {
	    	int lastIndex=0;
		int count = 0;

		while(lastIndex!=-1) {
			lastIndex = a[i][j].indexOf(s, lastIndex);		
			if(lastIndex !=-1 ) {
				count++;
				lastIndex++;
			}
		}
		if(count>0) { 
		     System.out.println(s + " found at: " + " (" + i + "," + j + "). Occurence: " + count);
	             flag = true; 
		}
	    }	
	}
	if(flag==false) { System.out.println("No substring found for " + s); }
    }

    public static void displayMenu() {
       System.out.println();
       System.out.println("-------------Menu------------");
       System.out.println("Choose from the following options");
       System.out.println("1. Search");
       System.out.println("2. Edit");
       System.out.println("3. Print");
       System.out.println("4. Reset");
       System.out.println("5. Exit");

       System.out.print("Enter your choice: ");
       choice = sc.nextInt();
    }

    public static void pauseProgram() {
	System.out.println();
    	System.out.println("Press a key to continue...");
	Scanner pause = new Scanner(System.in);
	pause.nextLine();
    }

    public static void askCoordinates() {
	boolean checker = true;
	while(checker) {
		try {
       			System.out.print("Row: ");
       			n1 = sc.nextInt();
			if(n1<0) { throw new ArrayIndexOutOfBoundsException(); }
			checker=false;
		}
		catch(InputMismatchException e) {
			System.out.println("Error: Please enter a valid integer.");
			sc.next();
			checker=true;
			continue;
		}	
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Error: Negative number detected, please enter a positive integer");
			checker = true;
		}
       }
       checker=true;
       while(checker) {
		try {
       			System.out.print("Column: ");
       			n2 = sc.nextInt();
			if(n2<0) { throw new ArrayIndexOutOfBoundsException(); }
			checker=false;
		}
		catch(InputMismatchException e) {
			System.out.println("Error: Please enter a valid integer");
			sc.next();
			checker=true;
			continue;
		}	
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Error: Negative number detected, please enter a positive integer");
			checker = true;
		}
       }    	
    }

} 
