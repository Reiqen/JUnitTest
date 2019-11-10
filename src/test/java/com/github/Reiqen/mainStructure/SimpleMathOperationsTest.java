package com.github.Reiqen.mainStructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;

/**
 * @TestInstance annotation with "PER_CLASS" attribute prevents
 * to use static BeforeAll in and AfterAll.
 * @Tag is used to specify methods and nested classes. Thus it is affordable
 * to run only "Operations" methods via Eclipse.
 * TestInfo and TestReporter are made for providing specific test information.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Making tests start")
class SimpleMathOperationsTest {
	
	SimpleMathOperations operations;
	TestInfo testInfo;
	TestReporter testReporter;
	
	// Runs at the beginning of testing
	@BeforeAll
	void start() {
		System.out.println("JUnit test started");
	}
	
	// Runs after all methods are done
	@AfterAll
	void end() {
		System.out.println("JUnit test ended");
	}
	
	// Runs before each method
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		operations = new SimpleMathOperations();
		this.testInfo = testInfo;
		this.testReporter = testReporter;
	}
	
	// Runs after each method
	@AfterEach
	void considered() {
		testReporter.publishEntry("The method [" + testInfo.getDisplayName() + "] is done. The tag is " + testInfo.getTags());
	}
	
	@Test
	@DisplayName("Perform addition test")
	@Tag("Operations")
	void add_ShouldMakeAddition_WhenTwoNumbersAreGiven() {
		int expected = 7;
		int actual = operations.add(2, 5);
		assertEquals(expected, actual, "Addition not made");
	}
	
	// Adding lambda to failure string makes code run faster
	@Test
	@DisplayName("Perform subtraction test")
	@Tag("Operations")
	void subtract_ShouldMakeSubtraction_WhenTwoNumbersAreGiven() {
		int expected = 12;
		int actual = operations.subtract(20, 8);
		assertEquals(expected, actual, () -> "Subtraction not made. Expected: " + expected + ". Actual: " + actual + ".");
	}
	
	// assertAll() asserts many assertions in one method
	@Test
	@DisplayName("Perform different multiplication tests")
	@Tag("Operations")
	void multiply_ShouldMakeMultiplication_WhenTwoNumbersAreGiven() {
		assertAll(
				() -> assertEquals(6, operations.multiply(2, 3)),
				() -> assertEquals(0, operations.multiply(0, 5)),
				() -> assertEquals(-10, operations.multiply(-2, 5))
				);
	}
	
	// Nested class lets create same way methods inside it
	@Nested
	@DisplayName("Division methods")
	@Tag("Operations")
	class DivisionClass {
	
	
		@Test
		@DisplayName("Perform division with positive and negative numbers test")
		void divide_ShouldMakeDivision_WhenNumbersArePositiveAndNegative() {
			int expected = -25;
			int actual = operations.divide(50, -2);
			assertEquals(expected, actual);
			assertThrows(ArithmeticException.class, () -> operations.divide(1, 0));
		}
	
		@Test
		@DisplayName("Perform division with both positive or negative numbers test")
		void divide_ShouldMakeDivision_WhenTwoNumbersAreBothPositiveOrNegative() {
			int expected = 25;
			int actual = operations.divide(-50, -2);
			assertEquals(expected, actual);
			assertThrows(ArithmeticException.class, () -> operations.divide(1, 0));
		}
	
	}
	
	@Test
	@DisplayName("TDD-method")
	@Disabled
	@Tag("TDD")
	void countCircle_ShouldCountCircleArea_WhenRadiusIsGiven() {
		fail("This method is TDD-method. Should not work now.");
	}
	
	// If assumption is false the method will be disabled
	@Test
	@DisplayName("Assume test")
	@Tag("Assume")
	void assumeResult_ShouldCheckIfResultIsTrue_WhenTwoNumbersAreGiven() {
		int expected = 6;
		int actual = operations.add(2, 5);
		assumeTrue(actual == expected);
	}
	
}
