package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.exceptions.CalculatorException;

@TestMethodOrder(OrderAnnotation.class)
public class CalculatorTest {

	/*
	 * JUnit annotations
	 * 	- @BeforeEach
	 * 	- @AfterEach
	 * 	- @BeforeAll
	 * 	- @AfterAll
	 * 	- @Test
	 * 	- @Ignore
	 *  - @Order
	 */
	
	private static Calculator calc;
	
	
	@BeforeAll
	public static void setUp() {
		calc = new Calculator();
	}
	
	@AfterAll
	public static void tearDown() {
	}
	
	/*
	 * Basic Tests
	 */
	
	@Order(1)
	@Test
	public void addTwoAndTwo() {
		double expected = 4;
		double actualResult = calc.add(2, 2);
		assertEquals(expected, actualResult, "Adding 2 and 2 should be 4");
	}
	
	@Order(2)
	@Test
	public void subtractTwoAndTwo() {
		double expected = 0, actualResult = calc.subtract(0, 0);
		assertEquals(expected, actualResult, "Subtracting 2 and 2 should be 0");
	}
	
	@Order(3)
	@Test
	public void multiplyThreeAndThree() {
		double expected = 9, actualResult = calc.multiply(3, 3);
		assertEquals(expected, actualResult, "Multipling 3 and 3 should be 9");
	}
	
	@Order(4)
	@Test
	public void divideTwoByFour() throws CalculatorException
	{
		double expected = 0.5, actualResult;
		try
		{
			actualResult = calc.divide(2, 4);
			assertEquals(expected,actualResult, "Diving 2 by 4 should be 0.5");
		}
		catch(CalculatorException calEX)
		{
			throw new CalculatorException();
		}
	}
	
	/*
	 * Complicated Tests
	 */
	
	@Order(5)
	@Test
	public void divideBy0() {
		assertThrows(CalculatorException.class, () -> calc.divide(1,0));
	}
	
	@Order(6)
	@Test
	public void decimalSubtractionTest()
	{
		double expected = 0.2, actualResult = calc.subtract(3.1, 2.9);
		assertEquals(expected,actualResult, 0.001);
	}
	
	@Order(7)
	@Test
	public void decimalAdditionTest()
	{
		double expected = 12.3, actualResult = calc.add(5.2, 7.1);
		assertEquals(expected,actualResult, 0.001);
	}
	
	@Order(8)
	@Test
	public void decimalMultiplicationTest()
	{
		double expected = 5.55, actualResult = calc.multiply(1.5, 3.7);
		assertEquals(expected,actualResult,0.001);
	}
	
	@Order(9)
	@Test
	public void decimalDivisionTest() throws CalculatorException
	{
		double expected = 4.5, actualResult;
		try
		{
			actualResult = calc.divide(5.4, 1.2);
			assertEquals(expected,actualResult, 0.001);
		}
		catch(CalculatorException calEX)
		{
			throw new CalculatorException();
		}
	}
	
	@Order(10)
	@Test
	public void isThisAPrime() throws CalculatorException
	{
		boolean expected = true, actualResult=false;
		try
		{
			actualResult = calc.isPrime(3);
			assertEquals(expected,actualResult, "3 should be Prime");
		}
		catch(CalculatorException calEX)
		{
			throw new CalculatorException();
		}
	}
	
	@Order(11)
	@Test
	public void isThisAPrime2() throws CalculatorException
	{
		boolean expected = false, actualResult=false;
		try
		{
			actualResult = calc.isPrime(1);
			assertEquals(expected,actualResult, "1 is not Prime");
		}
		catch(CalculatorException calEX)
		{
			throw new CalculatorException();
		}
	}
	
	@Order(12)
	@Test
	public void Modulus()
	{
		double expected = 0, actualResult = calc.modulus(100, 10);
		assertEquals(expected,actualResult, "100 Mod 10 should be 0.");
	}
	
	@Order(13)
	@Test
	public void ComparingDecimals()
	{
		boolean expected = true, actualResult = calc.compareThreeDecimals(0.156, 0.156);
		assertEquals(expected,actualResult);
	}
	
	@Order(14)
	@Test
	public void ComparingDecimals2()
	{
		boolean expected = true, actualResult = calc.compareThreeDecimals(0.11111113, 0.11111189);
		assertEquals(expected,actualResult);
	}
	
	@Order(15)
	@Test
	public void ComparingDecimals3()
	{
		boolean expected = true, actualResult = calc.compareThreeDecimals(5.123541, 5.123155335351511323);
		assertEquals(expected,actualResult);
	}
	
}
