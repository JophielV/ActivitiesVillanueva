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

   public class Threading {
	public static void main(String args[]) {
	   Scanner in = new Scanner(System.in);
	   int numHorse = 0;
	   int trackLength = 0;
	   List<Horse> horseList = new ArrayList<Horse>();	
	   char horseMark = 'A';	
	   boolean flag1 = true, flag2 = true;;	   

   	   System.out.println();
	   System.out.println("--------- Thread Acitivity: Horse Race ----------");

	   while(flag1) {	
	      try {   
	         System.out.print("Enter the amount of horses that will participate: ");
	         numHorse = in.nextInt();
		 if(numHorse <= 0 ) { throw new IllegalStateException(); }
		 flag1 = false;		 
	      }
	      catch(InputMismatchException e) {
		 System.out.println("Please make sure that you entered a valid integer");
		 flag1 = true;
		 in.next();
	      }
	      catch(IllegalStateException e) {
		 System.out.println("Please make sure that you enter a positive integer");
		 flag1 = true;
	      }
	   }

	   while(flag2) {
	      try {
	         System.out.print("Enter the length of track: ");
	         trackLength = in.nextInt();
		 if(trackLength <= 0 ) { throw new IllegalStateException(); }
	         flag2 = false;
	      }
	      catch(InputMismatchException e) {
		 System.out.println("Please make sure that you entered a valid integer");
		 flag2 = true;
		 in.next();
	      }	     
	      catch(IllegalStateException e) {
		 System.out.println("Please make sure that you enter a positive integer");
		 flag2 = true;
	      } 
 	   }
	
	   System.out.println();

	   for(int i = 0; i < numHorse; i++) {
	      Horse h = new Horse("Horse " + Character.toString(horseMark));
	      h.determineHealth();
	      h.setTrackLength(trackLength);
	      horseList.add(h);
	      horseMark++;
	   }

	   System.out.println("----------- Printing the generated horses -----------");
	   horseList.forEach(horses -> System.out.println(horses.getHorseName() + ", Status: " + horses.getHealthStatus()));

	   System.out.println();
	   System.out.println("----------  Clearing up Unhealthy horses --------------");	   
	   Iterator<Horse> it = horseList.iterator();
	   while (it.hasNext()) {
  	      Horse h = it.next();
  	      if (!h.getHealth()) {
    	         it.remove();
		 continue;
  	      }
	      System.out.println(h.getHorseName() + " is participating!");
	   }
	   
	   pauseProgram();	
	   System.out.println("-------------- Going to the start line ------------------");
	   
	   Iterator<Horse> it2 = horseList.iterator();   
	   while(it2.hasNext()) {
	       Horse h = it2.next();
	       h.start();
	   }  

	   // this loop will check if the threads are still executing
	   for(int i = 0; i < horseList.size(); i++ ) {
	      Horse h = horseList.get(i);
	      if(h.getState() != Thread.State.WAITING) {
	         i = 0;
	      } 
	   }

	   try { Thread.sleep(2500); } catch(InterruptedException e) { }
	   System.out.println("----------All Horses arrived at the starting line! Next is the race!-----------");	   
           try { Thread.sleep(2500); } catch(InterruptedException e) { }

	   Iterator<Horse> it3 = horseList.iterator();   
	   while(it3.hasNext()) {
	       Horse h = it3.next();
	       synchronized(h) {
      	          h.notify();
 	       }
	   }

	   // this loop will check if the threads are still executing
	   for(int i = 0; i < horseList.size(); i++ ) {
	      Horse h = horseList.get(i);
	      if(h.getState() == Thread.State.RUNNABLE) {
	         i = 0;
	      } 
	   }

	   try { Thread.sleep(2500); } catch(InterruptedException e) { }
	   System.out.println("-------Race Finished. The result is next!---------------");	   
           try { Thread.sleep(2500); } catch(InterruptedException e) { }


	   Collections.sort(horseList, 
                        (o1, o2) -> new Integer(o1.getPlace()).compareTo(new Integer(o2.getPlace())));
	   
	   
	   Iterator<Horse> it4 = horseList.iterator(); 
	   Stack<Integer> st = new Stack<Integer>();
	   int place = 1;  
	   while(it4.hasNext()) {
	       Horse h = it4.next();
	       if(place != 1 && st.peek() != h.getPlace()) { place++; }
	       else if(place == 1 && !st.empty() && st.peek() != h.getPlace()) { place++; }
	       st.push(h.getPlace());
	       h.setPlace(place);
	       System.out.println(" " + h.getHorseName() + ", Place: " + h.getPlace());
	   }
	   System.exit(0);	   
	}



	public static void pauseProgram() {
	    System.out.println();
    	    System.out.println("Press a key to continue...");
	    Scanner pause = new Scanner(System.in);
	    pause.nextLine();
     	}

	public static void startRaceHold(int t) {
	    System.out.println("Notice: The horses will race for " + t + " m.");
    	    System.out.println("---------------- Press enter to start the race! -----------------");
	    Scanner pause = new Scanner(System.in);
	    pause.nextLine();
    	}

        public static void pauseProgramWithoutText() {
	     Scanner pause = new Scanner(System.in);
	     pause.nextLine();
	}

   }
