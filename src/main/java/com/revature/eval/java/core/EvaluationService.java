package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String[] splitted = phrase.split(" ");
		String acro = "";
		
		for(int i=0; i<splitted.length; i++) {
			acro += Character.toUpperCase(splitted[i].charAt(0));
			if(splitted[i].contains("-")) {
				int x = splitted[i].indexOf("-");
				acro += Character.toUpperCase(splitted[i].charAt(x+1));
			}
		}
		
		return acro;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			
			//is equilateral if all sides are equal, so checking all sides
			// against each other
			if(this.getSideOne() == this.getSideTwo() && this.getSideOne() == this.getSideThree()) {
				return true;
			}
			return false;
		}

		public boolean isIsosceles() {
			//x is amount of sets of equal sides
			int x = 0;
			
			//if side one equals side two, one pair of sides is equal (and
			//so on)
			if(this.getSideOne() == this.getSideTwo()) {
				x++;
			}
			if(this.getSideOne() == this.getSideThree()) {
				x++;
			}
			if(this.getSideTwo() == this.getSideThree()) {
				x++;
			}
			
			//one pair of sides equal
			if(x>=1) {
				return true;
			}
			return false;
		}

		public boolean isScalene() {
			//x is amount of sets of equal sides
			int x = 0;
			
			//as long as no sides are equal, it is scalene
			if(this.getSideOne() == this.getSideTwo()) {
				x++;
			}
			if(this.getSideOne() == this.getSideThree()) {
				x++;
			}
			if(this.getSideTwo() == this.getSideThree()) {
				x++;
			}
			
			
			if(x>=1) {
				return false;
			}
			return true;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		int score = 0;
		
		//string of letters for each point value
		String pt1 = "AEIOULNRST";
		String pt2 = "DG";
		String pt3 = "BCMP";
		String pt4 = "FHVWY";
		String pt5 = "K";
		String pt8 = "JX";
		String pt10 = "QZ";
		
		//loop through each letter in the word, assigning points
		for(int i=0; i<string.length(); i++) {
			//getting current letter
			String let = String.valueOf(string.charAt(i)).toUpperCase();
			
			//checking each string to see if it contains current letter;
			//if so, adds that many point to score
			if(pt1.contains(let)){score+=1;}
			else if(pt2.contains(let)) {score+=2;}
			else if(pt3.contains(let)) {score+=3;}
			else if(pt4.contains(let)) {score+=4;}
			else if(pt5.contains(let)) {score+=5;}
			else if(pt8.contains(let)) {score+=8;}
			else if(pt10.contains(let)) {score+=10;}
		}
		
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		//PN = phone number, nums is string containing all possible digits
		String PN = "";
		String nums = "1234567890";
		
		//count
		int ct = 0;
		
		//iterating through input string
		for(int i = 0; i<string.length(); i++) {
			String num = String.valueOf(string.charAt(i));
			
			//if current character is a number, add it to the phone number
			if(nums.contains(num)) {
				PN += num;
				ct++;
			}
		}
		
		//if there was the right amount of characters in PN, return
		if(ct == 10) {
			return PN;
		}
		//wrong amount of digits = Exception
		else {
			throw new IllegalArgumentException("Too many digits");
		}
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		Map<String, Integer> occMap= new HashMap<>();
		
		//replace commas and newline characters with spaces
		string = string.replace(",", " ");
		string = string.replace("\n", " ");
		
		//replace awkward spaces with normal spaces
		string = string.replace("  ", " ");
		
		//split input into words on spaces
		String[] words = string.split(" ");
		
		for(String word : words) {
			//if map doesn't contain a word, put it in and count at 1
			if(!occMap.containsKey(word)) {
				occMap.put(word, 1);
			}
			//else, increment count
			else {
				occMap.put(word, occMap.get(word)+1);
			}
		}
		
		return occMap;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			//make string and int array
			String[] arr = new String[this.sortedList.size()];
			int[] arr2 = new int[arr.length];
			
			//convert T's to strings and put in array, and
			//convert strings to ints, and put in the other array
			for(int i=0; i<this.sortedList.size(); i++) {
				arr[i]=String.valueOf(this.sortedList.get(i));
				arr2[i] = Integer.valueOf(arr[i]);
			}
			
			//normal binary search stuff
			
			int hi = this.sortedList.size()-1;
			int lo = 0;
			
			while(lo<=hi) {
				int m = lo + (hi-lo)/2;
				int mid = arr2[m];
			
				if(mid == (Integer.valueOf(String.valueOf(t)))) {
					return m;
				}
				else if(mid>(Integer.valueOf(String.valueOf(t)))) {
					hi = m - 1;
				}
				else {
					lo = m + 1;
				}
			
			}
			
			
			return -1;
		}
		

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	
	public String toPigLatin(String string) {
		String vowels = "aeiou";
		String[] spl = string.split(" ");
		String finalRes = "";
		
		//iterate through words in the input string (split)
		for(String word : spl) {
			String let = String.valueOf(word.charAt(0));
			
			if(vowels.contains(let)) {
				finalRes += word + "ay ";
			}
			else if(word.contains("sch")) {
				finalRes += word.substring(3, word.length()) + word.substring(0, 3) + "ay ";
			}
			else if(word.contains("th") || word.contains("qu")) {
				finalRes += word.substring(2, word.length()) + word.substring(0, 2) + "ay ";
			}
			else {
				finalRes += word.substring(1, word.length()) + word.substring(0, 1) + "ay ";
			}
		}
		
		return finalRes.trim();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		String in = String.valueOf(input);
		int sum = 0;
		
		//iterate through the characters
		for(int i=0; i<in.length(); i++) {
			
			//convert each character into a string into an integer
			int x = Integer.valueOf(String.valueOf(in.charAt(i)));
			
			//add each digit times its position to the sum
			sum += Math.pow(x, in.length());//times itself the num of digits times;
		}
		
		if(sum == input) {
			return true;
		}
		
		return false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	
	public List<Long> calculatePrimeFactorsOf(long l) {
//		List<Long> li = new ArrayList<>();
//		boolean prime = true;
//		
//		for(long i = 2; i<l+1; i++) {
//			prime = true;
//			//check if factor
//			if(l%i==0) {
//				//check if prime
//				for(long j = 2; j<i; j++) {
//					if(i%j==0) {
//						prime = false;
//						break;
//					}
//				}
//			}
//			
//			if(prime) {
//				li.add(i);
//			}
//		}
//		
//		return li;
		return new ArrayList<Long>();
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();//65 90 97 122
			this.key = key;
		}

		public String rotate(String string) {
			char let;
			String result = "";
			
			//loop through characters in string
			for(int i=0; i<string.length(); i++) {
				let = string.charAt(i);
				int newLet = let;
				
				//if lowercase character
				if(let>=65 && let<=90) {
					newLet = let + this.key;
					
					if(newLet > 90) {
						newLet = (newLet - 90)+65;
					}
				}
				//if uppercase character
				else if(let>=97 && let<=122) {
					newLet = let + this.key;
					
					if(newLet > 122) {
						newLet = (newLet - 122)+97-1;
					}
				}
				
				result+=(char)newLet;
			}
			
			return result;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	
	public int calculateNthPrime(int i) {
		int testNum = 3;
		int iter = 1;
		boolean isPrime = true;
		
		if(i<1) {
			throw new IllegalArgumentException("Don't do that!");
		}
		
		while(iter<i) {
			
			//special case for 2
			if(i==1) {
				return 2;
			}
			
			for(int j=2; j<testNum; j++) {
				if(testNum%j==0) {
					isPrime=false;
					break;
				}
			}
			
			if(isPrime) {
				iter++;
			}
			else {
				isPrime = true;
			}
			
			testNum++;
		}
		
		return testNum-1;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			String alpha = "abcdefghijklmnopqrstuvwxyz";
			int z = alpha.length()-1;
			String encoded = "";
			int ct = 0;
			
			for(int i = 0; i<string.length(); i++) {
				String let = String.valueOf(string.charAt(i));
				
				if("0123456789".contains(let)) {
					encoded += let;
					ct++;
				}
				else if(alpha.contains(let)) {
					int in = alpha.indexOf(let);
					encoded += String.valueOf(alpha.charAt(z-in));
					ct++;
				}
				else if(alpha.toUpperCase().contains(let)) {
					int in = alpha.toUpperCase().indexOf(let);
					encoded += String.valueOf(alpha.charAt(z-in));
					ct++;
				}
				
				if(ct==5) {
					ct=0;
					encoded+=" ";
				}
			}
			
			String result = "";
			if(String.valueOf(encoded.charAt(encoded.length()-1)).equals(" ")) {
				for(int i = 0; i<encoded.length()-1; i++) {
					result += String.valueOf(encoded.charAt(i));
				}
			}
			else {
				result = encoded;
			}
			
			return result;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			String alpha = "zyxwvutsrqponmlkjihgfedcba";
			int z = alpha.length()-1;
			String decoded = "";
			
			for(int i = 0; i<string.length(); i++) {
				String let = String.valueOf(string.charAt(i));
				if(alpha.contains(let)) {
					int in = alpha.indexOf(let);
					decoded += String.valueOf(alpha.charAt(z-in));
				}
				else if("0123456789".contains(let)) {
					decoded += let;
				}
			}
			
			return decoded;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String str) {
		int result = -1;
		int sum = 0;
		String string = str.replace("-", "");
		int[] arr = new int[string.length()];
		for(int i=0; i<arr.length; i++) {
			
			if("0123456789".contains(String.valueOf(string.charAt(i)))) {
				arr[i] = Integer.valueOf(String.valueOf(string.charAt(i)));
			}
			else if(string.charAt(i)=='X'){
				arr[i]=10;
			}
			else {
				return false;
			}
		}
		
		int j = 0;
		for(int i = 10; i>0; i--) {
			sum += arr[j]*i;
			j++;
		}
		
		result = sum%11;
		
		return result == 0;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		Map<Character, Boolean> alpha = new HashMap<>();
		String a = "abcdefghijklmnopqrstuvwxyz";
		for(int i=0; i<a.length(); i++) {
			alpha.put(a.charAt(i), false);
		}
		
		for(int i=0; i<string.length(); i++) {
			if(a.contains(String.valueOf(string.charAt(i)))) {
				alpha.put(string.charAt(i), true);
			}
		}
		
		for(int i=0; i<a.length(); i++) {
			if(!alpha.get(a.charAt(i))) {
				return false;
			}
		}
		
		
		return true;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		LocalDateTime date;
		
		try {
			date = LocalDateTime.of((LocalDate)given, LocalTime.MIDNIGHT);
		}
		catch(ClassCastException ex){
			date = (LocalDateTime) given;
		}
		date = date.plus((int)Math.pow(10, 9), ChronoUnit.SECONDS);
		
		return date;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		int sum = 0;
		Set<Integer> uniqSet = new HashSet<>();
		
		for(int j=0; j<set.length; j++) {
			int k=1;
			int num = set[j];
			
			while((num*k)<i) {
				uniqSet.add(num*k);
				k++;
			}
		}
		
		Iterator<Integer> it = uniqSet.iterator();
		while(it.hasNext()) {
			sum += (int)it.next();
		}
		
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		int sum = 0;
		int newVal = -1;
		//string to hold reversed string
		StringBuilder rev = new StringBuilder(string);
		rev.reverse();
		
		//z is amount of number iterations
		int z = 1;
		for(int i=0; i<rev.length(); i++) {
			String let = String.valueOf(rev.charAt(i));
			
			//spaces are valid, continue
			if(let.equals(" ")) {
				continue;
			}
			//we need numbers for calculation
			else if("0123456789".contains(String.valueOf(let))) {
				//double in place, check if greater than 9 (-9 if so), sum and mod by 10
				if(z%2==0) {
					newVal = Integer.parseInt(let)*2;
					newVal = (newVal>9) ? newVal-9 : newVal;
					rev.setCharAt(i, (char)('0'+newVal));
				}
				
				//add to sum
				if(newVal!=-1) {
					sum += newVal;
				}
				else {
					sum += Integer.valueOf(let);
				}
				
				newVal = -1;
				z++;
			}
			//illegal character, invalid
			else {
				return false;
			}
		}
		
		return sum % 10 == 0;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		string = string.replace("?", " ");
		//str is the string split into words
		String[] str = string.split(" ");
		
		int num1 = 0;
		boolean num1Done = false;
		int num2 = 0;
		
		//holds the operator to be used
		int op = -1;
		
		for(String word : str) {
			if(word.contains("0") || word.contains("1") || word.contains("2") || word.contains("3") || word.contains("4") || word.contains("5")
					|| word.contains("6") || word.contains("7") || word.contains("8") || word.contains("9")) {
				if(!num1Done) {
					num1 = Integer.parseInt(word);
					num1Done = true;
				}
				else {
					num2 = Integer.parseInt(word);
				}
			}
			else if(word.equals("divided")) {
				op = 1;
			}
			else if(word.equals("minus")) {
				op = 2;
			}
			else if(word.equals("multiplied")) {
				op = 3;
			}
			else if(word.equals("plus")) {
				op = 4;
			}
		}
		
		//result stored in res
		int res = 0;
		
		switch (op) {
		case 1:
			res = num1 / num2;
			break;

		case 2:
			res = num1 - num2;
			break;
			
		case 3:
			res = num1 * num2;
			break;
			
		case 4:
			res = num1 + num2;
			break;
		}
		
		return res;
	}

}
