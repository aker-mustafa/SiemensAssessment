package com.siemens.assessment.calculator.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.siemens.assessment.calculator.Calculator;

public class DistinctLettersCountCalculatorTest {

	private Calculator distinctLettersCountCalculator;

	@BeforeEach
	public void environmentSetUp() {
		distinctLettersCountCalculator = new DistinctLettersCountCalculator();
	}

	@Test()
	public void testSuccessCases() throws Exception {

		assertEquals(1, distinctLettersCountCalculator.calculate("aaaabbbb"));
		assertEquals(6, distinctLettersCountCalculator.calculate("ccaaffddecee"));
		assertEquals(0, distinctLettersCountCalculator.calculate("eeee"));
		assertEquals(4, distinctLettersCountCalculator.calculate("example"));

	}

	@Test
	public void testMinLengthCase() throws Exception {
		String testString = "";
		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testString);
		});

	}

	@Test
	public void testMaxLengthCase() throws Exception {
		String testString = StringUtils.repeat('a', 300001);
		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testString);
		});
	}

	@Test
	public void testInvalidCharacterCase() throws Exception {

		String testStringWithUpperCaseLetterAtFirst = "Aaaaabbb";
		String testStringWithUpperCaseLetterInMiddle = "aaaAabbb";
		String testStringWithUpperCaseLetterAtLast = "aaaabbB";

		String testStringWithNumberAtFirst = "1aaaabbbb";
		String testStringWithNumberInMiddle = "aaaab1bbb";
		String testStringWithNumberAtLast = "aaaabbbb1";

		String testStringWithSpecialCharacterAtFirst = ".aaaabbb";
		String testStringWithSpecialCharacterInMiddle = "aaaabb.b";
		String testStringWithSpecialCharacterAtLast = "aaaaBbbb.";

		String testStringWithNonEnglishSpecialCharacterAtFirst = "çaaaabbb";
		String testStringWithNonEnglishSpecialCharacterInMiddle = "aaaaçbbb";
		String testStringWithNonEnglishSpecialCharacterAtLast = "aaaaBbbb.";

		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testStringWithUpperCaseLetterAtFirst);
		});

		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testStringWithUpperCaseLetterInMiddle);
		});

		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testStringWithUpperCaseLetterAtLast);
		});

		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testStringWithNumberAtFirst);
		});

		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testStringWithNumberInMiddle);
		});

		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testStringWithNumberAtLast);
		});

		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testStringWithSpecialCharacterAtFirst);
		});

		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testStringWithSpecialCharacterInMiddle);
		});

		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testStringWithSpecialCharacterAtLast);
		});

		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testStringWithNonEnglishSpecialCharacterAtFirst);
		});

		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testStringWithNonEnglishSpecialCharacterInMiddle);
		});

		assertThrows(Exception.class, () -> {
			distinctLettersCountCalculator.calculate(testStringWithNonEnglishSpecialCharacterAtLast);
		});
	}
}
