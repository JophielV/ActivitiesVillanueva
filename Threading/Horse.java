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
import java.util.concurrent.*;

public class Horse extends Thread {
	final private int barnToStart = 100;
	private Thread t; 
	private String horseName;
	private boolean healthy;
	private int trackLength;
	private int currentTrackBarnToStart = 0;
	private int currentTrackStartToFinish = 0;
	private int timeForBarnToStart;
	private boolean finished = false;
	private int place;
	private String healthStatus;

	public Horse(String n) {   
	   this.horseName = n;
	}

	public void setHorseName(String h) {
	   this.horseName = h;
	}

	public String getHorseName() {
	   return this.horseName;
	}

	public int getBarnToStart() {
	   return this.barnToStart;
	}

	public void setHealth(boolean h) { 
	   this.healthy = h;
	}

	public boolean getHealth() {
	   return this.healthy;
	}

	public String getHealthStatus() {
	   if(this.getHealth()) { return "Healthy"; }
	   else { return "Not Healthy"; }
	}

	public void setFinished(boolean f) {
	   this.finished = f;
	}

	public boolean getFinished() {
	   return this.finished;
	}

	public void setPlace(int p) {
	   this.place = p;
	}

	public int getPlace() {
	   return this.place;
	}

	public void setTrackLength(int t) {
	   this.trackLength = t;
	}

	public int getTrackLength() {
	   return this.trackLength;
	}

	public void setTimeBarnToStart(int t) {
	   this.timeForBarnToStart = t;
	}

	public int getTimeBarnToStart() {
	   return this.timeForBarnToStart;
	}
	
	public int getCurrentTrackBarnToStart() {
	   return this.currentTrackBarnToStart;
	}

	public void addCurrentTrackBarnToStart(int s) {
	   this.currentTrackBarnToStart += s;
	}

	
	public String outputCurrentTrackBarnToStart() {
	   if(this.getCurrentTrackBarnToStart() < this.barnToStart) {
	        return this.getHorseName() + " is now on " + this.getCurrentTrackBarnToStart() + " m" 
		+ " (current time: " + System.currentTimeMillis() + ")";
    	   }
	   else if(this.getCurrentTrackBarnToStart() == this.barnToStart) {
	        return this.getHorseName() + " has arrive the starting line! (" + this.getCurrentTrackBarnToStart() + "m), (current time: " + System.currentTimeMillis() + ")";
	   }
	   return "";
	}

	public int getCurrentTrackStartToFinish() {
	   return this.currentTrackStartToFinish;   
	}

	public void addCurrentTrackStartToFinish(int s) {
	   this.currentTrackStartToFinish += s;
	}

 	public String outputCurrentTrackStartToFinish() {
	   if(this.getCurrentTrackStartToFinish() < this.trackLength) {
	        return this.getHorseName() + " is now on " + this.getCurrentTrackStartToFinish() + " m" 
		+ " (current time: " + System.currentTimeMillis() + ")";
    	   }
	   else if(this.getCurrentTrackStartToFinish() == this.trackLength) {
		setFinished(true);
	        return this.getHorseName() + " has arrive on the finish line! (" + this.getCurrentTrackStartToFinish() + "m), (current time: " + System.currentTimeMillis() + ")";
	   }
	   return "";
	}	

	public void determineHealth() {
	   int min = 1, max = 10;
	   Random random = new Random();
	   int percent = random.nextInt(max - min + 1) + min;
	   if(percent >= 1 && percent <= 7) { setHealth(true); }
	   else { setHealth(false); }
	}
	
	static int localPlace = 1;
	public void run() {
	   int min = 1, max = 5;
	   Random random = new Random();
	   int delay = 1000;
	   int totalDelay = 0;
	   

	   
	      do {
	         if(this.getCurrentTrackBarnToStart() == 96) { min = 1; max = 4; }
	         else if(this.getCurrentTrackBarnToStart() == 97) { min = 1; max = 3; }
		 else if(this.getCurrentTrackBarnToStart() == 98) { min = 1; max = 2; }
		 else if(this.getCurrentTrackBarnToStart() == 99) { min = 1; max = 1; }
	   	 int steps = random.nextInt(max - min + 1) + min;
	         
                 addCurrentTrackBarnToStart(steps);		 
	    	 System.out.println(outputCurrentTrackBarnToStart());	
		 System.out.println("-------------------------------");
	      } while(this.getCurrentTrackBarnToStart() < this.barnToStart);
	      
	      synchronized(this) {
	         try { this.wait(); } catch(InterruptedException e) { }
	      }

	      min = 1; max = 5;	  
	      do {

	         if(this.getCurrentTrackStartToFinish() == (this.getTrackLength()-4)) { min = 1; max = 4; }
	         else if(this.getCurrentTrackStartToFinish() == (this.getTrackLength()-3)) { min = 1; max = 3; }
		 else if(this.getCurrentTrackStartToFinish() == (this.getTrackLength()-2)) { min = 1; max = 2; }
		 else if(this.getCurrentTrackStartToFinish() == (this.getTrackLength()-1)) { min = 1; max = 1; }
		 int steps = random.nextInt(max - min + 1) + min;
		 
		 addCurrentTrackStartToFinish(steps);
		 System.out.println(outputCurrentTrackStartToFinish());
		 if(this.getFinished()) { 
		    this.setPlace(localPlace);   
		    synchronized(this) {
		       try { this.wait(); } catch(InterruptedException e) { }
		    }
	         }	
		 System.out.println("-------------------------------");
		 localPlace++;
	      } while(this.getCurrentTrackStartToFinish() < this.getTrackLength());
 		
          
	}

}
