package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {

	public static void main(String args[])
	{
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString()
	{
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber()
	{
		assertEquals(1, Calculator.add("1"));
	}

    @Test
    public void testAnotherNumber()
    {
        assertEquals(3, Calculator.add("3"));
    }

	@Test
	public void testTwoNumbers()
	{
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
    public void testThreeNumbers()
    {
    	assertEquals(6, Calculator.add("1,2,3"));
    }

    @Test
    public void testFiveNumbers()
    {
		assertEquals(15, Calculator.add("1,2,3,4,5"));
    }

    @Test
    public void testSpaceDelimiter()
    {
        assertEquals(3, Calculator.add("1\n2"));
    }

    @Test
    public void testSpaceAndCommaDelimiter()
    {
        assertEquals(6, Calculator.add("1\n2,3"));
    }

    @Test
    public void testChooseDelimiter()
    {
        assertEquals(6, Calculator.add("//;\n1;2;3"));
    }

    @Test
    public void testChooseDelimiterAlt()
    {
        assertEquals(6, Calculator.add("//[;;]\n1;;2;;3"));
    }

    @Test
    public void testChooseDelimiterAlt2()
    {
        assertEquals(6, Calculator.add("//[ppp]\n1ppp2ppp3"));
    }

    @Test
    public void testNegativeNumber()
    {
        try {
            assertEquals(0, Calculator.add("-6,6"));
        }
        catch (Exception e) {
            assertEquals("Negatives not allowed: -6", e.getMessage());
        }
    }

    @Test
    public void testNegativeNumbers()
    {
        try {
            Calculator.add("-6,5,-4");
        }
        catch (Exception e) {
            assertEquals("Negatives not allowed: -6,-4", e.getMessage());
        }
    }

    @Test
    public void testBiggerThanThousand()
    {
        assertEquals(2, Calculator.add("1001,2"));
    }

    @Test
    public void testThousand()
    {
        assertEquals(1002, Calculator.add("1000,2"));
    }

    @Test
    public void testMultipleDelimiters()
    {
        assertEquals(6, Calculator.add("//[;][$]\n1;2$3"));
    }

    @Test
    public void testMultipleDelimiters2()
    {
        assertEquals(6, Calculator.add("//[;;][...]\n1;;2...3"));
    }
}