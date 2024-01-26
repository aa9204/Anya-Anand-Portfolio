public class MarkovModel {

    // number of ASCII characters
    private static final int ASCII = 128;
    // how many times the k-gram appears in the text
    private ST<String, Integer> st1;
    // how many times each ASCII character succeeds the k-gram in the text
    private ST<String, int[]> st2;
    // order of Markov Model
    private int order;

    // creates a Markov model of order k based on the specified text
    public MarkovModel(String text, int k) {
        order = k;
        // create circular string
        String cstring = text + text.substring(0, order);
        st1 = new ST<String, Integer>();
        st2 = new ST<String, int[]>();

        for (int i = 0; i < text.length(); i++) {
            String kgram = cstring.substring(i, i + order);
            // create char variable for letter following kgram
            char letter = cstring.charAt(i + order);
            if (!st1.contains(kgram)) {
                st1.put(kgram, 1);
            }
            else st1.put(kgram, st1.get(kgram) + 1);

            // create new array c for ASCII characters
            int[] c = new int[ASCII];
            c[letter] += 1;
            if (!st2.contains(kgram)) {
                st2.put(kgram, c);
            }
            else {
                c = st2.get(kgram);
                c[letter] += 1;
                st2.put(kgram, c);
            }
        }
    }

    // returns the order of the model (also known as k)
    public int order() {
        return order;
    }

    // returns a String representation of the model
    public String toString() {
        // st2 is the second symbol table (corresponding to the two-argument
        // freq() method)
        StringBuilder result = new StringBuilder();
        for (String key : st2) {
            result.append(key + ": ");

            // get the character frequency array
            int[] frequency = st2.get(key);

            // for each non-zero entry, append the character and the frequency
            // trailing space is allowed
            for (int i = 0; i < frequency.length; i++) {
                if (frequency[i] != 0) {
                    result.append((char) i + " " + frequency[i] + " ");
                }
            }
            // append a newline character
            result.append("\n");
        }
        return result.toString();
    }

    // returns the # of times 'kgram' appeared in the input text
    public int freq(String kgram) {
        if (kgram.length() != order)
            throw new IllegalArgumentException("kgram is incorrect length");
        if (!st1.contains(kgram))
            return 0; // if kgram is not in ST1
        else
            return st1.get(kgram); // get kgram's frequency
    }

    // returns the # of times 'c' followed 'kgram' in the input text
    public int freq(String kgram, char c) {
        if (kgram.length() != order)
            throw new IllegalArgumentException("kgram is incorrect length");
        if (!st1.contains(kgram))
            return 0; // if kgram is not in ST1
        else return st2.get(kgram)[c]; // return frequency of following char
    }

    // returns a random character, chosen with weight proportional to the
    // number of times each character followed 'kgram' in the input text
    public char random(String kgram) {
        if (kgram.length() != order)
            throw new IllegalArgumentException("kgram is incorrect length");
        if (!st1.contains(kgram)) {
            throw new IllegalArgumentException("kgram does not appear in text");
        }
        // create new array f which gets frequency array from st2
        int[] f = st2.get(kgram);
        int randomIndex = StdRandom.discrete(f);
        return (char) randomIndex;
    }

    // tests all instance methods to make sure they're working as expected
    public static void main(String[] args) {
        String text1 = "banana";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println(model1.order());
        StdOut.println(model1.random("an"));
        StdOut.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
        StdOut.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
        StdOut.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
        StdOut.println("freq(\"na\")         = " + model1.freq("na"));
        StdOut.println();

        String text3 = "one fish two fish red fish blue fish";
        MarkovModel model3 = new MarkovModel(text3, 4);
        StdOut.println(model3.order());
        StdOut.println(model3.random("ish"));
        StdOut.println("freq(\"ish \", 'r') = " + model3.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model3.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model3.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model3.freq("tuna"));
    }
}
