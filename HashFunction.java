import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
    Adam Tigar
    CS 201
    May 18, 2015
**/

public class HashFunction
{
    public static void main(String[] args) throws FileNotFoundException
    {    
        String key = "";
        String value = "";
        int counter = 0;
            
        HashMapForStrings hm = new HashMapForStrings();
        Scanner inp = new Scanner(new File(args[0]), "ISO-8859-1");
        
        System.gc();
        long startMem = getMemory();
        long starttime = System.currentTimeMillis();

        while(inp.hasNextLine() && counter <= 4000000)        // Taken from Search Engine assignment, modified slightly
                                        // Worked with Holt Maki
		{
			String line = inp.nextLine();
			
			if(line.length() >= 3)
			{
				if(line.substring(0, 3).equals("MV:")) // Proper formatting for file
					key = line.substring(4);
				else if(line.substring(0, 3).equals("PL:")) 
					value+=(line.substring(4)+"\n");
				else if(line.substring(0, 3).equals("BY:")) // The end of every movie contains this line
				{
					hm.put(key, value);
					key = "";
					value = "";
				}
			}
            counter++;
            if (counter % 50000 == 0) // 1000 movies updated too frequently
                System.out.println("Inputted " + counter + " movies so far.");
        }

        System.gc();
        long endMem = getMemory();
        long endtime = System.currentTimeMillis();

        System.out.println ("Size for inputting: " + (endMem-startMem));
        System.out.println ("Average size: " + ((endMem-startMem) / ((double)counter)));
        System.out.println ("Time taken for inputting: " + (endtime-starttime));
        
        boolean running = true;
		Scanner s = new Scanner(System.in); 	// Text input scanner
		while(running)
		{
			System.out.println("What is your query?");
			
			String textinput = s.nextLine();
			if(textinput.equals("####"))
				running = false;
			else
			{
				System.out.println("Your results are: ");
                System.out.println(hm.get(textinput));
                System.out.println();

			}
			
		}

    }
    private static long getMemory()
    {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
    