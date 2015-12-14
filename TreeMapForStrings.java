import java.util.ArrayList;


/*
 * Adam Tigar and Holt Maki
 * CS 201 Data Structures
 * May 11, 2015
 */

public class TreeMapForStrings {
	private TreeMapForStrings left;
	private TreeMapForStrings right;
	private String key;
	private String value;
	
	public TreeMapForStrings()
	{
		key = null;
		value = null;
	}
	
	public void put(String key, String value)
	{
		if(right == null)		// Creates new BST if a side is null
		{
			right = new TreeMapForStrings();
		}
		
		if(left == null)
		{
			left = new TreeMapForStrings();
		}
		
		if(this.key==null)  // Fills empty space if starting new BST
		{
			this.key = key;
			this.value = value;
		}
		
		else if(this.key.compareTo(key) > 0)
		{
			right.put(key, value);
		}
		
		else if(this.key.compareTo(key) < 0)
		{
			left.put(key, value);
		}
		
		else if(this.key.equals(key)) // Replaces value if updated movie description is inserted
		{
			this.value = value;
		}
	}
	
	public String get(String key) //Gets the value for the key
	{
		if(this.key == key)
		{
			return this.value;
		}
		else
		{
			try
			{
				return this.key.compareTo(key) > 0 ? right.get(key) : left.get(key); //If > 0 return right.get(key) else left.get(key)
			}
			catch(NullPointerException e)
			{
				return null;
			}
		}
	}
	
	public ArrayList<String> getKeysForPrefix(String prefix)
	{
		if(key == null)
		{
			return new ArrayList<String>();
		}
		else if(key.length() < prefix.length())
		{
			if(key.compareTo(prefix) < 0)
				return left.getKeysForPrefix(prefix);
			else
				return right.getKeysForPrefix(prefix);
		}
		else if(key.substring(0, prefix.length()).equalsIgnoreCase(prefix))
		{
			ArrayList<String> l = left.getKeysForPrefix(prefix);
			ArrayList<String> r = right.getKeysForPrefix(prefix);
			
			//Add key to l
			l.add(key);
			
			//Add right to left so that the array is ordered.
			for(String a: r)
			{
				l.add(a);
			}
			
			return l;
		}
		else
		{
			return key.substring(0, prefix.length() > key.length() ? prefix.length(): key.length()).compareTo(prefix) > 0 
					? right.getKeysForPrefix(prefix) : left.getKeysForPrefix(prefix);
		}
	}

}
