package com.revature;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.revature.exceptions.CalculatorException;

public class Calculator {

	/*
	 * Should be able to:
	 * 		- add
	 * 		- subtract
	 * 		- multiply
	 * 		- divide
	 * 			- throw a CalculatorException when attempting to divide by 0
	 * 		- isPrime: checks if a number is a prime number
	 * 		- compareThreeDecimals: returns true if the same up to 3 decimals
	 * 				- 3.123.compare...(3.1233445) should return true
	 */
	
	public double add(double x, double y) {
		return x+y;
	}
	
	public double subtract(double x, double y) {
		return x-y;
	}
	
	public double multiply(double x, double y) {
		return x*y;
	}
	
	public double divide(double x, double y) throws CalculatorException {
		if(y == 0)
		{
			throw new CalculatorException();
		}
		return x/y;
	}
	
	public double modulus(double x, double y)
	{
		return x%y;
	}
	
	public boolean isPrime(int x) throws CalculatorException {
		boolean res = false;
		
		if(modulus(x,2) == 1 && x > 1)
		{
			res=true;
		}
		return res;
	}
	
	public boolean compareThreeDecimals(double x, double y) {
		double f_X = Math.floor(x * 1000);
		double f_Y = Math.floor(y * 1000);
		if(f_X == f_Y)
		{
			return true;
		}
		return false;
	}
}
