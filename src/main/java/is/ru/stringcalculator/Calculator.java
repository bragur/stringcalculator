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

                return sum(splitNumbersBy(numStr, delimiter));
            }

			return sum(splitNumbers(text));
		}
		
		return toInt(text);
	}

	private static int toInt(String number)
	{
		return Integer.parseInt(number);
	}

    private static String[] splitNumbersBy(String numbers, String delimiter)
    {
        return numbers.split(delimiter);
    }

	private static String[] splitNumbers(String numbers)
	{
        ArrayList<String> allNumbers = new ArrayList<String>();

        String[] splitByCommas = numbers.split(",");

        for (String chunks : splitByCommas)
        {
            String[] splitBySpacesAndCommas = chunks.split("\n");

            for (String number : splitBySpacesAndCommas)
            {
                allNumbers.add(number);
            }
        }

        String[] result = allNumbers.toArray(new String[allNumbers.size()]);

	    return result;
	}
      
    private static int sum(String[] numbers)
    {
 	    int total = 0;
        for (String number : numbers)
        {
		    total += toInt(number);
		}
		return total;
    }



}