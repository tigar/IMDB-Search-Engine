/**
    Adam Tigar
    CS 201
    May 18, 2015
**/

public class HashMapForStrings
{
    public static class Node
    {
        public String title;
        public String plot;
        public Node next;

        public Node(String key, String value)
        {
            title = key;
            plot = value;
            next = null;
        }
    }
    
    private Node head = null;
    private Node[] hashArray; //Lifted from initial java assignment parts 5-9
    private static int arraySize;
    int hash;
    
    public HashMapForStrings()
    {
        arraySize = 100; 
        hashArray = new Node[arraySize]; 
    }
    
    public void put(String key, String value)
        {
        hash = key.hashCode() % hashArray.length; // Lifted from the book on how to account for hashCode()
        if (hash < 0)
            hash += hashArray.length;
        Node temp = new Node(null, null); 

        
        while (temp != null)
        {
            if (temp.title == key)
            {
                temp.plot = value;
            }
            temp = temp.next;
        }
        Node addition = new Node(key, value); // add a title if not already there
        addition.next = hashArray[hash];
        hashArray[hash] =  addition;
        }
        
    public String get(String key)
        {
        int hash = key.hashCode() % hashArray.length;
        if (hash < 0)
        {
            hash += hashArray.length;
        }

        Node cur = new Node(null, null);
        cur = hashArray[hash];

        if (cur == null)
        {
            return null;

        }
        while (cur.title.compareTo(key) != 0)        // Traverse the linked list to find the matching key
        {
            if (cur.next == null)
            {
                return null;
            }
            cur = cur.next;
        }
        return cur.plot;
        }

     
}