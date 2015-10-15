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
            if (text.contains("//") && text.indexOf("//") == 0)
            {
                int delimiterEnds = 3;
                int delimiterStarts = 2;

                if(text.contains("[") && text.indexOf("[") == 2)
                {
                    delimiterStarts += 1;
                    delimiterEnds = text.indexOf("\n") - 1;
                }

                String delimiter = text.substring(delimiterStarts, delimiterEnds);
                
                String numStr = text.substring(text.indexOf("\n") + 1);

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
        int pos = 0;
        for (String num : numbers)
        {
            int number = toInt(num);

            if (number < 0)
            {
                String msg = "Negatives not allowed: ";
                for (int n = pos; n < numbers.length; n++)
                {
                    if (toInt(numbers[n]) < 0)
                    {
                        msg = msg + numbers[n] + ",";
                    }
                }
                throw new IllegalArgumentException(msg);
            }

            if (number <= 1000)
            {
                total += number;
            }
            pos++;
		}
		return total;
    }



}