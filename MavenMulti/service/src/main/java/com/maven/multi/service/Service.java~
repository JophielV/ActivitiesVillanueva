package com.maven.multi.service;

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
import com.maven.multi.model.Value;
import com.maven.multi.model.GeneralModel;

public class Service {
   private int n1,n2;
   private String key, value;
   private HashMap<String, Value> globalHashmap = new HashMap<String, Value>();
   private List<Integer> maxLengthsPerColumn = new ArrayList<Integer>();
   private String path;

        public Service()  { 
	  
	}
	
	public void setPath() throws IOException {
	   String line = "";
	   BufferedReader br = new BufferedReader(new FileReader("/usr/local/apache-maven-3.3.9/MavenMulti/App/target/classes/path.txt"));
	   try {
              line = br.readLine();
	      //System.out.println(line);            
	   }
	   catch(IOException e) { }

  	   finally {
    	      br.close();
           }
	   this.path = line;   
	}

        public void clearHashmap() { 
           globalHashmap.clear();
        }
   
        public void clearMaxLengthsPerColumn() {
           maxLengthsPerColumn.clear();
        }

        public boolean checkDuplicate(String update) {
	   if(globalHashmap.get(update) != null) { return true;  }
	   else  { return false; } 
        }

        public boolean fileExists() {
	   File f = new File(this.path);
           if(f.exists() && !f.isDirectory()) { 
              return true;
	   }
	   else { 
              return false;
           }	
        }

	public void readFile(GeneralModel gm) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(this.path));
		int rowCount = 1;
		int maxColCount = 1;
		int currentRow = 0;
		String[] rowContent = new String[100];
		List<Integer> columnCounts = new ArrayList<Integer>();

		try {
                    String line = br.readLine();
                    while (line != null) {                    //this loop will read the lines and determine the amount of row and columns 
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
					
		
		gm.setRow(rowCount-1);
		gm.setColumn(maxColCount);	
		
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

        public void generateMap(GeneralModel gm) {
           for(int i = 0; i < gm.getRow(); i++) {
	      for(int j = 0; j < gm.getColumn(); j++) {
                 randomizeKeyValue();
		 Value val = new Value(value, i, j);
		 globalHashmap.put(key,val);
              } 
           }
        }

	public void printMapLikeArray(GeneralModel gm) {
	   getMaxLengthPerColumn(gm);
 	   for(int r = 0; r < gm.getRow(); r++) {
	      for(int c = 0 ; c < gm.getColumn(); c++) {
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

	public String generateSpaces(int num) {
	   StringBuilder sb = new StringBuilder();
	   for(int i = 0; i < num; i++) {
	      sb.append(" ");
           }
	   return sb.toString();	   
	}

	public void getMaxLengthPerColumn(GeneralModel gm) {
	   List<Integer> localMax = new ArrayList<Integer>();
	   for(int c = 0; c < gm.getColumn(); c++) {
	      int maxNum = 0;
	      for(int r = 0; r < gm.getRow(); r++) { 
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

	public void printMapInfo() {
	    for (String name: globalHashmap.keySet()){
               String key = name.toString();
               Value val1 = globalHashmap.get(name);  
               System.out.println("Key: " + key + " " + "Value: " +  val1.getValue() + " R: " +  val1.getRow() + " C: " + val1.getColumn());  
            }   
        }

        public void randomizeKeyValue() {
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

	public void searchMap(GeneralModel gm) {
	   boolean checker = false;
	   System.out.println("Searching substring: " + gm.getSubStrSearch());
	   for (String name: globalHashmap.keySet()){
               String key = name.toString();
               Value val1 = globalHashmap.get(name);
	       int keyCount = searchKeyValue(key, gm.getSubStrSearch());
	       int valCount = searchKeyValue(val1.getValue(), gm.getSubStrSearch());
	       if(keyCount > 0 || valCount > 0) {
		   int total = keyCount + valCount;
	           System.out.println(gm.getSubStrSearch() + " found at: (" + val1.getRow() + "," + val1.getColumn() + "). " +
                   "Key: " + keyCount + ", Value: " + valCount + ", Total: " + total);
		    checker = true;
	       } 
            }   
	}

	public int searchKeyValue(String str, String substr) {
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

	public void editMap(GeneralModel gm) {
	    Iterator<Map.Entry<String,Value>> iter = globalHashmap.entrySet().iterator();
	    while (iter.hasNext()) {
	 	Map.Entry<String,Value> entry = iter.next();
		String key = entry.getKey();
		Value val1 = entry.getValue();

		if(val1.getRow()==gm.getEditRow() && val1.getColumn()==gm.getEditColumn()) {
		   iter.remove();			
		   break;
		}
	    }
	    Value valHolder = new Value(gm.getEditValue(),gm.getEditRow(),gm.getEditColumn());
	    globalHashmap.put(gm.getEditKey(),valHolder);           
	}

	public void saveFile(GeneralModel gm) throws IOException {
	   if(!fileExists()) {
		System.out.println("No file exists");
	   	BufferedWriter output = null;
			try {
        		    File file = new File(this.path);
        		} catch ( Exception e ) {
            			e.printStackTrace();
        		} finally {
          			
        		}
	         try { printFile(gm); } catch(Exception e) { }
	   }
	   else if(fileExists()) {
	      		try { printFile(gm); } catch(Exception e) { }	
	   }
	}

        public void printFile(GeneralModel gm) throws IOException {
	        String filename = this.path; 
		BufferedWriter outputWriter = null;
  		outputWriter = new BufferedWriter(new FileWriter(filename));
  		getMaxLengthPerColumn(gm);
 	       
                for(int r = 0; r < gm.getRow(); r++) {
	           for(int c = 0 ; c < gm.getColumn(); c++) {
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


	public void addRow(GeneralModel gm) {	   
	    Value val1 = new Value(gm.getAddValue(),gm.getAddedRow(),gm.getAddedColumn());
	    globalHashmap.put(gm.getAddKey(),val1);
	    	    	  	   	   
	}

	public void sortRow(GeneralModel gm) {
	   int colCounter = 0;
	   TreeMap<String,Value> sortedRow = new TreeMap<String,Value>();	
	   
	     // put first in treemap for sorting
	     for (String name: globalHashmap.keySet()){
                String key = name.toString();
                Value val1 = globalHashmap.get(name);
	        if(val1.getRow() == gm.getSortRow()) {
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
}
