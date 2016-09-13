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

public class AdvancedJava5 {
	static int choice = 0;
	static int n1,n2;	
	static Scanner sc = new Scanner(System.in);	
	static String key, value;
	static HashMap<String, Value> globalHashmap = new HashMap<String, Value>();
	static List<Integer> maxLengthsPerColumn = new ArrayList<Integer>();

	public static void main(String args[]) {		
	   System.out.println();
	   System.out.println("----------Advanced Java----------");
	   System.out.println();
	    
           if(!fileExists()) {
	      System.out.println("---No File found: Enter the dimensions of the matrix---");
              askCoordinates();  
	      generateMap();
	      System.out.println();
	      System.out.println("----------Printing in array form----------");
	      printMapLikeArray();
	   } 
	   else if(fileExists()) {
	      System.out.println("----File Found: Loading the contents----");
	      try { readFile(); } catch(Exception e) { }
	      printMapLikeArray();
	   }	
	   
	   while(choice!=8) {
	      pauseProgram();
               displayMenu();
	       switch(choice) {
		   case 1: 
			 searchMap();
		         break;
		   case 2:	
			int row = 0, column = 0;
			int getLength = 0;
			String update = "";
			String update2 = "";
			boolean rowFlag = true, colFlag = true;
			System.out.println();
			System.out.println("Enter the Coordinates of which cell to edit: ");
			while(rowFlag) {
				try {
					System.out.print("Coordinate for row: ");
					row = sc.nextInt();
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
			   //in.nextLine();
			   update = in.nextLine();
			   getLength = update.length();
		           if(globalHashmap.get(update) != null) { 
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
			   //in.nextLine();
			   update2 = in.nextLine();
			   getLength3 = update.length();
			   if(getLength3 <= 0 || getLength3 >= 11) { System.out.println("Please make sure that the length of the string is between 1 to 10"); }			
			}
			editMap(row,column,update,update2);
			maxLengthsPerColumn.clear();
			System.out.println();
	                System.out.println("----------Printing the updated map ----------");
	                printMapLikeArray();
                        break;
		   case 3:
			 System.out.println("----------Printing the array----------");
			 printMapLikeArray();
			 break;		
	       	   case 4:
			 System.out.println();
			 System.out.println("-----Resetting the map----"); 
			 askCoordinates();
			 globalHashmap.clear();
			 maxLengthsPerColumn.clear();
	                 generateMap();
	                 System.out.println();
	                 System.out.println("----------Printing in array form----------");
	                 printMapLikeArray();
			 break;

		   case 5:
			 System.out.println();
			 System.out.println("----------Add new Row----------");
			 addRow();
			 System.out.println();
			 maxLengthsPerColumn.clear();
			 printMapLikeArray();
			 break;
		   case 6:
			 boolean rowFlag2 = true;
			 int chosenRow = -1;
			 System.out.println();
			 System.out.println("---------------Sort--------------");
                         sortRow();
			 maxLengthsPerColumn.clear();
			 printMapLikeArray();
			 break;
                   case 7:  
                         System.out.println("Saving File...");
			 try {  saveFile(); }
           	         catch(Exception e) { }
			 System.out.println("File Saved!");
			 break;	  
		   case 8: System.out.println("Exiting the program..."); System.exit(0); break;
		   default: System.out.println("Please enter a valid option, taking you back to the menu..."); break;
	      }
	   }
	}

	public static boolean fileExists() {
	        File f = new File("/home/ecc/Documents/Output.txt");
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else { 
                   return false;
		}	
	}

	public static void readFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("/home/ecc/Documents/Output.txt"));
		int rowCount = 1;
		int maxColCount = 1;
		int currentRow = 0;
		String[] rowContent = new String[100];
		List<Integer> columnCounts = new ArrayList<Integer>();

		try {
                    String line = br.readLine();
                    while (line != null) {                            //this loop will read the lines and determine the amount of row and columns 
			String rowVal[] = line.split("\\s{2,100}");  // rowVal is for determining the number of columns per row
			columnCounts.add(rowVal.length); 
			rowContent[currentRow] = line;  // rowcContent is for getting the content of the row		
			rowCount++;
			currentRow++;
		        line = br.readLine();	
    		  }
		} finally {
    			br.close();
		}
		try {
                    Collections.sort(columnCounts);
	            maxColCount = columnCounts.get(columnCounts.size()-1);
		 }
		catch(ArrayIndexOutOfBoundsException e) {  }
					
		
		n1 = rowCount-1;
		n2 = maxColCount;	
		
		for(int r = 0; r < rowCount; r++) {
		   for(int c = 0; c < maxColCount; c++) {
		        String[] keyValueSet = rowContent[r].split("\\s{2,100}");
			try {
			   String[] separatedKeyValue = keyValueSet[c].split("\\s:\\s");
			   if(separatedKeyValue.length != 2) {   // if format of key value is not followed, throw illegal state exception
				try {  throw new IllegalStateException();  }
				catch(IllegalStateException e) { 
				   System.out.println("Error: One of the key : value pair on the file doesn't" +  
				   " follow the format. File is corrupted."); 
				   System.exit(0);
				}			
			   }
			   String cleanedKey = separatedKeyValue[0].replaceAll(" ", "");
			   Value val1 = new Value(separatedKeyValue[1], r, c);
			   globalHashmap.put(cleanedKey,val1);
			}
			catch(ArrayIndexOutOfBoundsException e) { break; } // this is for incompatible number of columns(when adding a new row)
		   }		   
		}     
        }

        public static void generateMap() {
           for(int i = 0; i < n1; i++) {
	      for(int j = 0; j < n2; j++) {
                 randomizeKeyValue();
		 Value val = new Value(value, i, j);
		 globalHashmap.put(key,val);
              } 
           }
        }

	public static void printMapLikeArray() {
	   getMaxLengthPerColumn();
 	   for(int r = 0; r < n1; r++) {
	      for(int c = 0 ; c < n2; c++) {
	         for (String name: globalHashmap.keySet()) {
              	     String key = name.toString();
              	     Value val1 = globalHashmap.get(name);
	      	     if(val1.getRow() == r && val1.getColumn() == c) {
			int localSum = val1.getValue().length() + key.length();
		        if(localSum == maxLengthsPerColumn.get(c))
			{ System.out.print(" " + key + " : " +val1.getValue()  +  "    "); }
			else if(localSum < maxLengthsPerColumn.get(c))
			{ 
			  int val1Length = val1.getValue().length()+key.length();
			  int maxLen = maxLengthsPerColumn.get(c);
			  int spaceToAdd = maxLen - val1Length;
    		          String spaces = generateSpaces(spaceToAdd + 4);
                          System.out.print(" " + key + " : " + val1.getValue() + spaces  ); 
                        }
                     }
                  }
              }
	      System.out.println();
	   }
	}

	public static String generateSpaces(int num) {
	   StringBuilder sb = new StringBuilder();
	   for(int i = 0; i < num; i++) {
	      sb.append(" ");
           }
	   return sb.toString();	   
	}

	public static void getMaxLengthPerColumn() {
	   List<Integer> localMax = new ArrayList<Integer>();
	   for(int c = 0; c < n2; c++) {
	      int maxNum = 0;
	      for(int r = 0; r < n1; r++) { 
	     	 for (String name: globalHashmap.keySet()) {
              	    String key = name.toString();
              	    Value val1 = globalHashmap.get(name);
	      	    if(val1.getColumn() == c && val1.getRow() == r) {
		       localMax.add(val1.getValue().length() + key.length()); 
		    }
                 }
		 try {
                    Collections.sort(localMax);
	            maxNum = localMax.get(localMax.size() - 1);
		 }
		 catch(ArrayIndexOutOfBoundsException e) { }
              }
	      
              maxLengthsPerColumn.add(maxNum);
	      localMax.clear();
           }	
	}

	public static void printMapInfo() {
	    for (String name: globalHashmap.keySet()){
               String key = name.toString();
               Value val1 = globalHashmap.get(name);  
               System.out.println("Key: " + key + " " + "Value: " +  val1.getValue() + " R: " +  val1.getRow() + " C: " + val1.getColumn());  
            }   
        }

        public static void randomizeKeyValue() {
	   StringBuilder sb = new StringBuilder();
	   StringBuilder sb2 = new StringBuilder();
	   Random random = new Random();
	   int min = 33, max = 126, minLength = 1, maxLength = 10;
           
           int randomLength  = random.nextInt(maxLength - minLength + 1) + minLength;
 
  	   for(int i = 0; i < randomLength; i++) {
              int numHolder  = random.nextInt(max - min + 1) + min;
	      String charHolder = Character.toString((char) numHolder);
              sb.append(charHolder);
	   }
	   key = sb.toString();
	   sb.setLength(0);
	  
	   int randomLength2  = random.nextInt(maxLength - minLength + 1) + minLength;
 
  	   for(int i = 0; i < randomLength2; i++) {
              int numHolder  = random.nextInt(max - min + 1) + min;
	      String charHolder = Character.toString((char)numHolder);
              sb2.append(charHolder);
	   }
	   value = sb2.toString();
	   sb2.setLength(0);
	}

	public static void searchMap() {
	   boolean checker = false;
           String substr = "";
           int getLength = 0;

           System.out.println();
	   while(getLength <= 0 || getLength >= 11) {
		Scanner in2 = new Scanner(System.in);
		System.out.print("Enter the substring to be searched: ");
		substr = in2.nextLine();
		getLength = substr.length();
		if(getLength <= 0 || getLength >= 11) {
		   System.out.println("Please make sure that the length of the subtring" 
		   + " to be searched is from 1 to 10.");				
		}
	   }

	   System.out.println("Searching substring: " + substr);
	   for (String name: globalHashmap.keySet()){
               String key = name.toString();
               Value val1 = globalHashmap.get(name);
	       int keyCount = searchKeyValue(key,substr);
	       int valCount = searchKeyValue(val1.getValue(),substr);
	       if(keyCount > 0 || valCount > 0) {
		   int total = keyCount + valCount;
	           System.out.println(substr + " found at: (" + val1.getRow() + "," + val1.getColumn() + "). " +
                   "Key: " + keyCount + ", Value: " + valCount + ", Total: " + total);
		    checker = true;
	       } 
            }   
	}

	public static int searchKeyValue(String str, String substr) {
	    int count = 0;
	    int lastIndex = 0;
            while(lastIndex != -1) {
		lastIndex = str.indexOf(substr, lastIndex);		
		if(lastIndex !=-1 ) {
			count++;
			lastIndex++;
		}
	    }
	    return count;	
	}

	public static void editMap(int r, int c, String u, String u2) {
	    Iterator<Map.Entry<String,Value>> iter = globalHashmap.entrySet().iterator();
	    while (iter.hasNext()) {
	 	Map.Entry<String,Value> entry = iter.next();
		String key = entry.getKey();
		Value val1 = entry.getValue();

		if(val1.getRow()==r && val1.getColumn()==c) {
		   iter.remove();			
		   break;
		}
	    }
	    Value valHolder = new Value(u2,r,c);
	    globalHashmap.put(u,valHolder);           
	}

	public static void saveFile() throws IOException {
	   if(!fileExists()) {
		System.out.println("No file exists");
	   	BufferedWriter output = null;
			try {
        		    File file = new File("/home/ecc/Documents/Output.txt");
        		} catch ( Exception e ) {
            			e.printStackTrace();
        		} finally {
          			
        		}
	         try { printFile(); } catch(Exception e) { }
	   }
	   else if(fileExists()) {
	      		try { printFile(); } catch(Exception e) { }	
	   }
	}

        public static void printFile() throws IOException {
	        String filename = "Output.txt"; 
		BufferedWriter outputWriter = null;
  		outputWriter = new BufferedWriter(new FileWriter(filename));
  		getMaxLengthPerColumn();
 	       
                for(int r = 0; r < n1; r++) {
	           for(int c = 0 ; c < n2; c++) {
	              for (String name: globalHashmap.keySet()) {
              	         String key = name.toString();
              	         Value val1 = globalHashmap.get(name);
	      	         if(val1.getRow() == r && val1.getColumn() == c) {
			    int localSum = val1.getValue().length() + key.length();
		            if(localSum == maxLengthsPerColumn.get(c))
			       { outputWriter.write(" " + key + " : " +val1.getValue() + "    "); }
			    else if(localSum < maxLengthsPerColumn.get(c))
			    { 
			       int val1Length = val1.getValue().length()+key.length();
			       int maxLen = maxLengthsPerColumn.get(c);
			       int spaceToAdd = maxLen - val1Length;
    		               String spaces = generateSpaces(spaceToAdd + 4);
                               outputWriter.write(" " + key + " : " + val1.getValue() + spaces  ); 
                            }
                        }
                      }
                   }
	          outputWriter.newLine();
	       }
	       outputWriter.flush();  
  	       outputWriter.close(); 
 	}


	public static void addRow() {
	   n1 += 1;
	   int counter = 0;
	   int displayCounter = 1;
	   
	   Scanner sc2 = new Scanner(System.in);
	
           while(counter != n2) {
	      String key;
	      String value;
	      System.out.print("Enter Key " + displayCounter + ": ");
	      key = sc2.nextLine();
	      if(globalHashmap.get(key) != null) {
  	         System.out.println("Error: The key already exists.Enter another key");
		 continue;	
	      }
	      System.out.print("Enter value " + displayCounter + ": ");
	      value = sc2.nextLine();
	      Value val1 = new Value(value,n1-1,counter);
	      globalHashmap.put(key,val1);
	      counter++;
	      displayCounter++;
	      System.out.println("----------------------------");
	   }	  
	   
	   //if(counter > n2) { n2 = counter; }
	   
	}

	public static void sortRow() {
	   boolean rowFlag = true;
	   int chosenRow = 0;
	   int colCounter = 0;
	   TreeMap<String,Value> sortedRow = new TreeMap<String,Value>();	
	
           while(rowFlag) {
		try {
		    System.out.print("Enter the row to sort: ");
		    chosenRow = sc.nextInt();
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
	     
	     // put first in treemap for sorting
	     for (String name: globalHashmap.keySet()){
                String key = name.toString();
                Value val1 = globalHashmap.get(name);
	        if(val1.getRow() == chosenRow) {
		   sortedRow.put(key,val1);
		}
	     }

	     // update column for each record and put it back on the global hashmap
	     for (String name: sortedRow.keySet()) {
		String key = name.toString();
                Value val1 = globalHashmap.get(name);		
		val1.setColumn(colCounter);
		colCounter++;
		globalHashmap.put(key,val1);
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
