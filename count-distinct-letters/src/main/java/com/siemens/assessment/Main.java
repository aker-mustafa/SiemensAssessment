package com.siemens.assessment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.siemens.assessment.calculator.Calculator;
import com.siemens.assessment.calculator.impl.DistinctLettersCountCalculator;

/**
 * CountDistictLetter program calculates the amount of character needs to be
 * deleted from input string in order to maintain unique number of characters
 * for each specific character observed within the input.
 *
 *
 * @author Mustafa Aker
 * @version 1.0
 * @since 15-02-2021
 */

public class Main {

	// Logger variable definition.
	//
	static Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws Exception {

		// Instead of using concrete DistinctLettersCountCalculator directly, it is
		// defined as implementation of more generic Calculator interface.
		// Within this perspective, the main goal is to leave an open door for future
		// extention.
		//
		Calculator distinctLettersCountCalculator = new DistinctLettersCountCalculator();

		try (Scanner scanner = new Scanner(new File(args[0]))) {

			int lineCount = 1;

			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();

				// Function call for calculating the count value for given input string.
				//
				System.out.println(lineCount + "-" + distinctLettersCountCalculator.calculate(line));
				lineCount++;

			}
		} catch (FileNotFoundException e) {

			logger.log(Level.SEVERE, e.getMessage());
			throw new Exception("File not Found!");

		}

	}

}
