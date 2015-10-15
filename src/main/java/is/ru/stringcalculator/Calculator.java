package is.ru.stringcalculator;

import java.util.ArrayList;

public class Calculator {

	public static int add(String text)
	{
		if(text.equals(""))
		{
			return 0;
		}
		else if(text.contains(",") || text.contains("\n"))
		{
            if (text.indexOf("//") == 0)
            {
                int delimiterEnds = text.indexOf("\n");
                String delimiter = text.substring(2, delimiterEnds);
                String numStr = text.substring(delimiterEnds+1);

                return sum(splitNumbers(numStr, delimiter));
            }

			return sum(splitNumbers(text));
		}
		
		return toInt(text);
	}

	private static int toInt(String number)
	{
		return Integer.parseInt(number);
	}

    private static String[] splitNumbers(String numbers, String delimiter)
    {
        return numbers.split(delimiter);
    }

	private static String[] splitNumbers(String numbers)
	{

        return numbers.split("[\n,]");
	}
      
    private static int sum(String[] numbers)
    {
 	    int total = 0;
        for (String number : numbers)
        {
            if (toInt(number) < 0)
            {
                throw new IndexOutOfBoundsException("Negatives not allowed: ");
            }
		    total += toInt(number);
		}
		return total;
    }



}