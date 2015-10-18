package is.ru.stringcalculator;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class Calculator {

	public static int add(String text)
	{
		if(text.equals(""))
		{
			return 0;
		}
		else if(text.contains(",") || text.contains("\n"))
		{
            // Choosing delimiters
            if (text.contains("//") && text.indexOf("//") == 0)
            {
                if (text.contains("//["))
                {
                    String delimiter = getDelimiters(text);
                    String numStr = text.substring(text.indexOf("]\n") + 2);
                    return sum(splitNumbers(numStr, delimiter));
                }
                else
                {
                    String delimiter = getDelimiter(text);
                    String numStr = text.substring(text.indexOf("\n") + 1);
                    return sum(splitNumbers(numStr, delimiter));
                } 
            }
			return sum(splitNumbers(text));
		}
		return toInt(text);
	}

    private static String getDelimiters(String input)
    {
        // Get string with delimiters
        String delimiterString = input.substring(2, input.indexOf("]\n") + 1);

        // Run through the string and build up the regex string
        StringBuilder builder = new StringBuilder();
        boolean open = false;

        builder.append("[");
        
        for (int i = 0; i < delimiterString.length(); i++) {
            if (delimiterString.charAt(i) == '[' && open == false) {
                builder.append("(");
                open = true;
            }
            else if (delimiterString.charAt(i) == ']') {
                if (i < delimiterString.length() - 1)
                    builder.append(")|");
                else
                    builder.append(")");
                open = false;
            }
            else {
                builder.append(delimiterString.charAt(i));
            }
        }

        builder.append("]");

        return builder.toString();
    }

    private static String getDelimiter(String input)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(input.charAt(2));
        
        return builder.toString();
    }

	private static int toInt(String number)
	{
        if (number.equals(""))
            return 0;

		return Integer.parseInt(number);
	}

    private static String[] splitNumbers(String numbers, String delimiter)
    {
        return numbers.split(delimiter);
    }

	private static String[] splitNumbers(String numbers)
	{

        return numbers.split("[(\n)|(,)]");
	}

    public static boolean isNumeric(String str)  
    {  
        try {  
            double d = Double.parseDouble(str);  
        }
        catch(NumberFormatException nfe) {  
            return false;  
        }
        
        return true;  
    }
      
    private static int sum(String[] numbers)
    {
 	    int total = 0;
        int pos = 0;

        for (String num : numbers)
        {
            int number;

            if (isNumeric(num))
                number = toInt(num);
            else
                number = 0;

            if (number < 0)
            {
                StringBuilder msg = new StringBuilder("Negatives not allowed: " + number);

                for (int n = pos+1; n < numbers.length; n++)
                {
                    if (toInt(numbers[n]) < 0)
                    {
                        msg.append("," + numbers[n]);
                    }
                }
                throw new IllegalArgumentException(msg.toString());
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