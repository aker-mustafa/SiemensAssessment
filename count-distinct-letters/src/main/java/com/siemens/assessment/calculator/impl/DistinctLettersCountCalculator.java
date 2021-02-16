package com.siemens.assessment.calculator.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.siemens.assessment.Main;
import com.siemens.assessment.calculator.Calculator;

public class DistinctLettersCountCalculator implements Calculator {

	/**
	 * According to the problem definition, we want to get rid of each letter which
	 * brings equality to the number of occurrence of each unique letters. Within
	 * this perspective, specification of character that thrown away from input
	 * string becomes unimportant (E.g. For "aaabbb" case it is not important
	 * whether one 'a' or one 'b' is removed.). As an abstraction to the problem, we
	 * only need to process on the array that contains number of occurrences for
	 * each unique character. According to the problem definition, our win condition
	 * is to having unique numbers at each index of the array. If number at
	 * specified index is not unique among array, subtract '1', until, it becomes
	 * unique (stop if its value reaches to '0'). For each subtraction operation
	 * operation cost (which is initially '0') increased by '1' representing how
	 * many letters actually thrown away from initial string.
	 *
	 *
	 * @author Mustafa Aker
	 * @version 1.0
	 * @since 15-02-2021
	 */

	// This part can also be defined in 'application.properties' file and can be
	// read from there.
	//
	private final int MIN_STRING_LENGTH = 1;
	private final int MAX_STRING_LENGTH = 300000;

	// Logger variable definition.
	//
	static Logger logger = Logger.getLogger(DistinctLettersCountCalculator.class.getName());

	// Regex pattern for allowing only lowercase alphabet letters.
	//
	String regex = "^[a-z]+$";

	Pattern pattern = Pattern.compile(regex);

	@Override
	public Integer calculate(String inputString) throws Exception {

		// Pre-defined regex pattern execution and character validation
		// according to problem constraints.
		//
		Matcher matcher = pattern.matcher(inputString);

		if (!matcher.matches()) {
			logger.log(Level.SEVERE, "Invalid characters on line : " + inputString);
			throw new Exception("Invalid characters on line : " + inputString);
		}

		// Character length validation for input string
		//
		if (inputString.length() < MIN_STRING_LENGTH || inputString.length() > MAX_STRING_LENGTH) {
			logger.log(Level.SEVERE, "Invalid number of characters.");
			throw new Exception("Invalid number of characters.");
		}

		// First of all, for each unique character within the input stream, its
		// repetition is calculated and stored in a map.
		// Note : Size of the map corresponds to unique number of characters (This
		// information is not required for this problem).
		//
		Map<Character, Integer> charMap = new HashMap<>();
		int characterCount;

		for (char charAt : inputString.toCharArray()) {

			if (!charMap.containsKey(charAt)) {
				charMap.put(charAt, 1);
			} else {
				characterCount = charMap.get(charAt);
				charMap.put(charAt, ++characterCount);

			}

		}

		// Ordering the values in descending manner, allows us to get rid of inner loops
		// (backward control) required to check conditions for values lower than current
		// value.
		//
		List<Integer> sortedCharacterCount = new ArrayList<>(charMap.values());
		Collections.sort(sortedCharacterCount, Collections.reverseOrder());

		int operationCost = 0;

		// If currentValue equals leftValue inside the loop, it means that we have same
		// number of different character exists within the input string.
		// According to problem definition, we need to decrease by one (If left value
		// already reached '0', decrease it by currentValue and increase the
		// operationCost by the same amount of this decrease.) and proceed to the next
		// value until it reaches to the last index of the array. After all items on the
		// count array is traversed, operationCost variable is returned as solution of
		// this problem.
		//
		for (int i = 1; i < sortedCharacterCount.size(); i++) {

			Integer leftValue = sortedCharacterCount.get(i - 1);
			Integer currentValue = sortedCharacterCount.get(i);

			if (leftValue <= currentValue && leftValue > 0) {
				operationCost = operationCost + currentValue - (leftValue - 1);
				sortedCharacterCount.set(i, leftValue - 1);
			}

			if (leftValue == 0) {
				operationCost = operationCost + currentValue;
				sortedCharacterCount.set(i, 0);
			}

		}
		return operationCost;
	}

}
