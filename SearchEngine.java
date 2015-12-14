import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/*
 * Adam Tigar and Holt Maki
 * CS 201 Data Structures
 * May 11, 2015
 */


public class SearchEngine {

	
	public static void main(String[] args) throws FileNotFoundException
	{
		TreeMapForStrings t = new TreeMapForStrings();
		Scanner inp = new Scanner(new File(args[0]), "ISO-8859-1");
		
		String key = "";
		String value = "";
		int counter = 0;

		System.gc();
        long startMem = getMemory();
        long starttime = System.currentTimeMillis();
		
		while(inp.hasNextLine() && counter <= 4000000)
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
					t.put(key, value);
					key = "";
					value = "";
				}
			}
			counter++;		
		}

		System.gc();
        long endMem = getMemory();
        long endtime = System.currentTimeMillis();

        System.out.println ("Size for inputting: " + (endMem-startMem));
        System.out.println ("Average size: " + ((endMem-startMem) / ((double)counter)));
        System.out.println ("Time taken for inputting: " + (endtime-starttime));
		boolean running = true;
		Scanner s = new Scanner(System.in); 	// Our text input scanner
		while(running)
		{
			System.out.println("What is your query?");
			
			String textinput = s.nextLine();
			if(textinput.equals("####"))
				running = false;
			else
			{
				ArrayList<String> l = t.getKeysForPrefix(textinput);
				System.out.println("Your results are: ");
				for(int i = 0; i < l.size(); i++)
				{
					System.out.println(l.get(i));
					System.out.println(t.get(l.get(i)));
					System.out.println();
				}
				System.out.println(l.size() + " movies were found"); // Bonus: Shows full count
			}
			
		}
		
	}

    private static long getMemory()
    {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

}
