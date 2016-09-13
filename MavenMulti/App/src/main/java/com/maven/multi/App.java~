package com.maven.multi;

import java.util.Scanner;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.InputMismatchException;
import java.lang.*;
import java.io.*;
import java.io.File;
import java.nio.file.Path; 
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.ArrayList;
import java.nio.file.Files;
import com.maven.multi.model.GeneralModel;
import com.maven.multi.model.Value;
import com.maven.multi.service.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import org.apache.commons.collections.*;
import org.apache.commons.collections.map.*;
import org.apache.commons.collections.map.MultiValueMap;

public class App {
    static int n1,n2;
    static int choice = 0;
    static GeneralModel gm = new GeneralModel();
    static Scanner sc = new Scanner(System.in);	
    private Log log = LogFactory.getLog(App.class);	
    public static void main(String args[]) {
           Service s = new Service();
	   try { s.setPath(); } catch(IOException e) { }
           MultiValueMap groupMap = new MultiValueMap();     

           System.out.println();
	   System.out.println("----------Advanced Java----------");
	   System.out.println();
	    
           if(!s.fileExists()) {
	      System.out.println("---No File found: Enter the dimensions of the matrix---");
              askCoordinates();  
	      s.generateMap(gm);
	      System.out.println();
	      System.out.println("----------Printing in array form----------");
	      s.printMapLikeArray(gm);
	   } 
	   else if(s.fileExists()) {
	      System.out.println("----File Found: Loading the contents----");
	      try { s.readFile(gm); } catch(Exception e) { }
	      s.printMapLikeArray(gm);
	   }	
	  
	   while(choice != 8) {
	       pauseProgram();
               displayMenu();
	       switch(choice) {
		   case 1: 
		 	 boolean checker = false;
           		 int getLength = 0;
			 String substr = "";
			 System.out.println();
	                 while(getLength <= 0 || getLength >= 11) {
		            Scanner in2 = new Scanner(System.in);
			    System.out.print("Enter the substring to be searched: ");
			    substr = in2.nextLine();
			    gm.setSubStrSearch(substr);
			    getLength = substr.length();
			    if(getLength <= 0 || getLength >= 11) {
		   	       System.out.println("Please make sure that the length of the subtring" 
		   	       + " to be searched is from 1 to 10.");				
			    }
	   		 }
			 s.searchMap(gm);
		         break;
		
		   case 2:	
			int row = 0, column = 0;
		        getLength = 0;
			String update = "";
			String update2 = "";
			boolean rowFlag = true, colFlag = true;
			System.out.println();
			System.out.println("Enter the Coordinates of which cell to edit: ");
			while(rowFlag) {
				try {
					System.out.print("Coordinate for row: ");
					row = sc.nextInt();
					gm.setEditRow(row);
					if(row < 0 || row > n1) { throw new ArrayIndexOutOfBoundsException(); }
					rowFlag=false;
				}
				catch(InputMismatchException e) {
					System.out.println("Please make sure that you entered a valid integer");
					rowFlag = true;		
					sc.next();							
				}
				catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("The index you entered is either negative or greater than the size of the matrix.");
					rowFlag = true;		
					//sc.next();
				}
			}
			while(colFlag) {
				try {				
					System.out.print("Coordinate for column: ");
					column = sc.nextInt();
					gm.setEditColumn(column);
					if(column < 0 || column > n2) { throw new ArrayIndexOutOfBoundsException(); }
					colFlag = false;
				}
				catch(InputMismatchException e) {
					System.out.println("Please make sure that you entered a valid integer");
					colFlag = true;	
					sc.next();								
				}
				catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("The index you entered is either negative or greater than the size of the matrix.");
					colFlag = true;		
					//sc.next();
				}
			}
			while(getLength <= 0 || getLength >= 11) {
			   Scanner in = new Scanner(System.in);
			   System.out.print("Update for Key: ");
			   update = in.nextLine();
			   gm.setEditKey(update);
			   getLength = update.length();
		           if(s.checkDuplicate(update)) { 
				getLength = 0; 
				System.out.println("Error: The key already exist, please enter another key.");		
				continue;
			   } //if there exist a key, ask for another key
			   if(getLength <= 0 || getLength >= 11) { 
				System.out.println("Please make sure that the length of the string is between 1 to 10"); 
			   }			
			}
		        int getLength3 = -1;
			while(getLength3 <= 0 || getLength3 >= 11) {
			   Scanner in = new Scanner(System.in);
			   System.out.print("Update for Value: ");
			   update2 = in.nextLine();
			   gm.setEditValue(update2);
			   getLength3 = update2.length();
			   if(getLength3 <= 0 || getLength3 >= 11) { 
				System.out.println("Please make sure that the length of the" +  
				"string is between 1 to 10"); 
			         
			   }			
			}
			s.editMap(gm);
			s.clearMaxLengthsPerColumn();
			System.out.println();
	                System.out.println("----------Printing the updated map ----------");
	                s.printMapLikeArray(gm);
                        break;
		   case 3:
			 System.out.println("----------Printing the array----------");
			 s.printMapLikeArray(gm);
			 break;		
	       	   case 4:
			 System.out.println();
			 System.out.println("-----Resetting the map----"); 
			 askCoordinates();
			 s.clearHashmap();
			 s.clearMaxLengthsPerColumn();
	                 s.generateMap(gm);
	                 System.out.println();
	                 System.out.println("----------Printing in array form----------");
	                 s.printMapLikeArray(gm);
			 break;
		   case 5:
			 System.out.println();
			 System.out.println("----------Add new Row----------");
			 
			 gm.setRow(gm.getRow()+1);
	   		 int counter = 0;
	   		 int displayCounter = 1;
	   		 Scanner sc2 = new Scanner(System.in);
	
           	         while(counter != gm.getColumn()) {
	      		    String key;
	      		    String value;
	      		    System.out.print("Enter Key " + displayCounter + ": ");
	      		    key = sc2.nextLine();
	      		    if(s.checkDuplicate(key)) {
  	         	       System.out.println("Error: The key already exists.Enter another key");
		 	       continue;	
	      		    }
	      		    System.out.print("Enter value " + displayCounter + ": ");
	      		    value = sc2.nextLine();
			    gm.setAddKey(key);
			    gm.setAddValue(value);
			    gm.setAddedRow(gm.getRow()-1);
			    gm.setAddedColumn(counter);
			    s.addRow(gm);
	      		    counter++;
	      		    displayCounter++;
	      		    System.out.println("----------------------------");			 
			 }
			 System.out.println();
			 s.clearMaxLengthsPerColumn();
			 s.printMapLikeArray(gm);
			 break;
		  
		   case 6:
			 rowFlag = true;
	   		 int chosenRow = 0;
	   		 
           		 while(rowFlag) {
			    try {
		    	       System.out.print("Enter the row to sort: ");
		    	       chosenRow = sc.nextInt();
			       gm.setSortRow(chosenRow);
		    	       if(chosenRow < 0 || chosenRow > n1) { throw new ArrayIndexOutOfBoundsException(); }
			       rowFlag = false;
		     	    }
		    	    catch(InputMismatchException e) {
			    System.out.println("Please make sure that you entered a valid integer");
			    rowFlag = true;		
			    sc.next();							
		            }
		            catch(ArrayIndexOutOfBoundsException e) {
			    System.out.println("The index you entered is either negative or greater than the size of the matrix.");
			    rowFlag = true;		
		            }
	    	        }
			System.out.println("---------------Sort--------------");
                        s.sortRow(gm);
			s.clearMaxLengthsPerColumn();
			s.printMapLikeArray(gm);
		  
                   case 7:  
                         System.out.println("Saving File...");
			 try {  s.saveFile(gm); }
           	         catch(Exception e) { }
			 System.out.println("File Saved!");
			 break;	  
		   case 8: System.out.println("Exiting the program..."); System.exit(0); break;
		   default: System.out.println("Please enter a valid option, taking you back to the menu..."); break; 
	      } 
	   }
	   
    }

    public static void displayMenu() {
           System.out.println();
           System.out.println("-------------Menu------------");
           System.out.println("Choose from the following options");
           System.out.println("1. Search");
           System.out.println("2. Edit");
           System.out.println("3. Print");
           System.out.println("4. Reset");
	   System.out.println("5. Add new row");
	   System.out.println("6. Sort");
	   System.out.println("7. Save");
           System.out.println("8. Exit");

           System.out.print("Enter your choice: ");
           choice = sc.nextInt();
    }

    public static void askCoordinates() {
	   boolean checker = true;
	   while(checker) {
		try {
       			System.out.print("Row: ");
       			n1 = sc.nextInt();
			gm.setRow(n1);
			if(n1 < 0) { throw new ArrayIndexOutOfBoundsException(); }
			checker = false;
		}
		catch(InputMismatchException e) {
			System.out.println("Error: Please enter a valid integer.");
			sc.next();
			checker = true;
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
			gm.setColumn(n2);
			if(n2 < 0) { throw new ArrayIndexOutOfBoundsException(); }
			checker = false;
		}
		catch(InputMismatchException e) {
			System.out.println("Error: Please enter a valid integer");
			sc.next();
			checker = true;
			continue;
		}	
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Error: Negative number detected, please enter a positive integer");
			checker = true;
		}
          }    	
       }

     public static void pauseProgram() {
	   System.out.println();
    	   System.out.println("Press a key to continue...");
	   Scanner pause = new Scanner(System.in);
	   pause.nextLine();
    	}


}
