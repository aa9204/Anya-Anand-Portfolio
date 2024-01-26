public class TextGenerator {
    // generate a random string of text using a Markov Model
    public static void main(String[] args) {
        // read in k and T from command line
        int k = Integer.parseInt(args[0]);
        final int T = Integer.parseInt(args[1]);
        // read in all of the input text, including spaces
        String text = StdIn.readAll();
        // create new Markov Model and kgram
        MarkovModel model = new MarkovModel(text, k);
        String kgram = text.substring(0, k);
        StdOut.print(kgram);
        // generate loop for T-k transitions to print random characters
        for (int i = 0; i < (T - k); i++) {
            char random = model.random(kgram);
            StdOut.print(random);
            if (k != 0) {
                // update kgram to store new char and discard first char
                kgram = kgram.substring(1, k) + random;
            }
        }
    }
}
