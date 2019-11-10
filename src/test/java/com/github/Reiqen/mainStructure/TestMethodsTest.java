package com.github.Reiqen.mainStructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

class TestMethodsTest {

	TestMethods testMethods;
	
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
	void init() {
		testMethods = new TestMethods();
	}
	
	// Runs after each method
	@AfterEach
	void considered() {
		System.out.println("JUnit test considered");
	}
	
	@Test
	@DisplayName("Get author's name")
	void getAuthorsName_ShouldReturnRomanKuznetcov_WhenCalled() {
		String expected = "Roman Kuznetcov";
		String actual = testMethods.getAuthorsName();
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("Print author's name")
	void printAuthorsName_ShouldPrintRomanKuznetcov_WhenCalled() {
		// To be done soon
	}

}
