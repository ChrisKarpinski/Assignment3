/****************************************************************************
 *
 * Created by: Chris Karpinski
 * Created on: Oct 2016
 * This program uses the binary search algorithm to check for an entry in a
 *     sorted list of integers
 *
 ****************************************************************************/

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Assignment3BinarySearch {

	public static void main(String[] args) {

		// variables
		List <Integer> numbers = new ArrayList<Integer>();
		Random rand = new Random();
		Scanner read = new Scanner(System.in);
		int nextNumberAdded = 0;
		int keySearch = 0;
		String wantCont = "Y";
		boolean validInput;
		
		// outputs the original, random list of integers between
		// 1 and 1000.
		System.out.println("The original list of numbers is: ");
		
		for (int counter = 0;counter < 250;counter++) {
			
			numbers.add(rand.nextInt(1000-1) + 1);
			System.out.println(numbers.get(counter));
			
		}
		
		// Adds a line to clearly separate the unsorted list from 
		// the sorted one.
		System.out.println("*************************************************"
				+ "******************");
		
		// calls the sort function to sort the list
		numbers = sort(numbers);
		
		// print out the list
		System.out.println("The sorted list is: ");
		
		for (int counter = 0; counter < numbers.size(); counter++) {
			
			System.out.println(numbers.get(counter));
			
		}
		
		while (wantCont.equals("Y")) {
		
			// the while loop below makes sure the user enters an 
			// integer input to be searched for in the sorted list
			// This way of validating user input was retrieved from: 
			// https://www.youtube.com/watch?v=PWez5mVXACc
		do {
			
			System.out.println("Enter an integer to be searched for in the "
					+ "list: ");
			
			if (read.hasNextInt()) {
				keySearch = read.nextInt();
				validInput = true;
			}
			else {
				
				System.err.println("Invalid input - not integer");
				validInput = false;
				read.next();
			}
			
		} while (!validInput);
		
		// if the binary search function returns a -1, that means the
		// entered number could not be found
		if (binarySearch(numbers, keySearch) == -1) {
			
			System.out.println("The value you have entered could not be "
					+ "found");
			
		}
		
		// outputs the position of the entered key in the sorted list
		else {
			
		    System.out.println("The first occurance of the value is at "
		    		+ "position: " + binarySearch(numbers, keySearch));
		
		}
        
		
		// the while loop below once again verifies that the user enters a 
		// valid input for the number they want to input - 
		// it must be an integer
		do {
			
			System.out.println("Add another integer to the list: ");
			
			if (read.hasNextInt()) {
				
			    nextNumberAdded = read.nextInt();
			    validInput = true;
			    
			}

			else {
				
				System.err.println("Invalid input - not integer");
				validInput = false;
				read.next();
				
			}
			
		} while (!validInput);
		
		// calls the function that inserts a new number into the list in the 
		// correct position
		numbers = enterNewNumber(numbers, nextNumberAdded);
		
		// prints out the new sorted list with the user's inputed number
		// to show them that their number was inputed in the correct place
		System.out.println("The new list is now: ");
		
        for (int counter = 0; counter < numbers.size(); counter++) {
			
			System.out.println(numbers.get(counter));
			
		}
        
        // asks the user if they would like to continue with the program
        System.out.println("Would you like to continue?");
        wantCont = read.next();
        
        // makes sure they enter in valid input for continuation
        while (!(wantCont.equals("Y") || wantCont.equals("N"))) {
        	
        	System.err.println("Invalid input");
        	System.out.println("Would you like to continue?");
            wantCont = read.next();
        	
        }
        
        
        // closes the program if the user does not wish to continue
        if (wantCont.equals("N")) {
        	
        	System.out.println("");
        	System.out.println("Done");
        	
        }
        
		}
		
	}
	
	public static List<Integer> sort (List<Integer> listToSort) {
		// sort function that sorts an entered list
		
        for (int iterator1 = 0;iterator1 < listToSort.size(); iterator1++) {
			// this outer loop goes through and assembles the sorted list 
        	// by continually looping through smaller intervals of the list
        	// to find the minimum in each of those intervals
        	// and place it at the end of the list.
			int min = Collections.max(listToSort);
			
			for (int iterator2 = 0; iterator2 < listToSort.size() - iterator1; 
					iterator2++) {
				// this loop finds the minimum of the list up to the range 
				// based on how many elements have been sorted thus far
				
				if (listToSort.get(iterator2) < min) {
					
					min = listToSort.get(iterator2);
				}
				
			}
			
			// takes off the minimum from the current range in the list and 
			// puts it at the end of the list to build the sorted list
			listToSort.remove(listToSort.indexOf(min));
			listToSort.add(min);
			
		}
        
        return listToSort;
		
	}
	
	public static int binarySearch(List<Integer> numbersList, int key) {
		// this function executes the binary search algorithm to find the
		// indicated integer value in the list
		
		// the binary search algorithm below was retrieved from: 
		// https://en.wikipedia.org/wiki/Binary_search_algorithm
		int lowerBound = 0;
		int middleValuePos = 0;
		int higherBound = numbersList.size() - 1;
		
		while (higherBound >= lowerBound) {
			
		middleValuePos = (int) ((lowerBound + higherBound)/2);
		
		if (numbersList.get(middleValuePos) < key) {
			
			lowerBound = middleValuePos + 1; 
			
		}
		else if (numbersList.get(middleValuePos) > key) {
			
			higherBound = middleValuePos - 1;
			
		}
		
		else {
			// return the position if found
		    return middleValuePos;
		
		}
		
		}
		// return -1 (not found) if the given key is not found
		return -1;
	}
	
	public static List <Integer> enterNewNumber (List<Integer> numbersList, 
			int newValue) {
		// this function adds a new item in the correct place in the list
		
		numbersList.add(newValue);
		numbersList = sort(numbersList);
        return numbersList;
	}

}
