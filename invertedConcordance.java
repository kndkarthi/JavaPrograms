
//Terry Schmidt, Class: CSC 403, Summer 2014 - wrote invertConcordance() method
public class InvertedConcordance {

    // write the concordance to a file
    private static void serialize (String fileName, ST<String, SET<Integer>> concordance) {
        Out out = new Out (fileName);
        out.println (concordance.size ());
        for (String s : concordance.keys ()) {
            SET<Integer> set = concordance.get(s);
            if (set == null) set = new SET<Integer>();

            out.println (s);            
            out.println (set.size ());       
            for (int k : set)
                out.println (k);
        }
        out.close ();
    }
    // read the concordance from a file
    private static ST<String, SET<Integer>> deserialize (String fileName) {
        ST<String, SET<Integer>> concordance = new ST<String, SET<Integer>> ();
        In in = new In (fileName);
        int sizeConcordance = in.readInt ();
        for (int iConcordance = 0; iConcordance < sizeConcordance; iConcordance++) {
            String key = in.readString ();
            int sizeSet = in.readInt ();
            SET<Integer> set = new SET<Integer>();
            for (int iSet = 0; iSet < sizeSet; iSet++) 
                set.add (in.readInt ());
            concordance.put (key, set);
        }
        in.close ();
        return concordance;
    }
    public static ST<String, SET<Integer>> createConcordance (String[] words) {
        ST<String, SET<Integer>> st = new ST<String, SET<Integer>>();
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            if (!st.contains(s)) {
                st.put(s, new SET<Integer>());
            }
            SET<Integer> set = st.get(s);
            set.add(i);
        }
        return st;
    }
    
    public static String[] invertConcordance (ST<String, SET<Integer>> st) {
    	//figure out the length of the file / the maximum index in the concordance
    	int textRecreationLength = 0;
    	for(String key : st.keys()) {
    	  for(Integer i : st.get(key)) {
    	    if(i > textRecreationLength) {
    	      textRecreationLength = i;
    	    }
    	  }
    	}    

    	//Create the string array for recreating the file
    	String[] textRecreation = new String[textRecreationLength + 1];

    	//Reconstruct the file
    	for(String key : st.keys()) {
    	  for(Integer i : st.get(key)) {
    	    textRecreation[i] = key;
    	  }
    	}

    	return textRecreation;
    }
    
    
    private static void saveWords (String fileName, String[] words) {
        int MAX_LENGTH = 70;
        Out out = new Out (fileName);
        int length = 0;
        for (String word : words) {
            length += word.length ();
            if (length > MAX_LENGTH) {
                out.println ();
                length = word.length ();
            }
            out.print (word);
            out.print (" ");
            length++;
        }
        out.close ();
    }
    public static void main(String[] args) {
        String fileName = "data/tale.txt";
        In in = new In (fileName);
        String[] words = in.readAll().split("\\s+");
        
        ST<String, SET<Integer>> st = createConcordance (words);
        StdOut.println("Finished building concordance");

        // write to a file and read back in (to check that serialization works)
        serialize ("data/concordance-tale.txt", st);
        st = deserialize ("data/concordance-tale.txt");
        
        words = invertConcordance (st);
        saveWords ("data/reconstructed-tale.txt", words);
    }
}
