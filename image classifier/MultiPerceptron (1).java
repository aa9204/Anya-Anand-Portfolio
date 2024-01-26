public class MultiPerceptron {
    private int classes; // number of classes
    private int numInputs; // length of the input feature vector
    private Perceptron[] perceptrons; // array of Perceptron objects

    // Creates a multi-perceptron object with m classes and n inputs.
    // It creates an array of classes perceptrons, each with numInputs inputs.
    public MultiPerceptron(int m, int n) {
        classes = m;
        numInputs = n;
        perceptrons = new Perceptron[m];
        for (int i = 0; i < m; i++) {
            perceptrons[i] = new Perceptron(n);
        }
    }

    // Returns the number of classes.
    public int numberOfClasses() {
        return classes;
    }

    // Returns the number of inputs (length of the feature vector).
    public int numberOfInputs() {
        return numInputs;
    }

    // Returns the predicted label (between 0 and m-1) for the given input.
    public int predictMulti(double[] x) {
        int index = -1; // makes sure array isn't empty
        double value = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < perceptrons.length; i++) {
            double sum = perceptrons[i].weightedSum(x);
            if (sum > value) {
                index = i;
                value = sum;
            }
        }
        return index;
    }

    // Trains this multi-perceptron on the labeled (between 0 and m-1) input.
    public void trainMulti(double[] x, int label) {
        for (int i = 0; i < perceptrons.length; i++) {
            if (i == label)
                perceptrons[i].train(x, +1);
            else perceptrons[i].train(x, -1);
        }
    }

    // Returns a String representation of this MultiPerceptron, with
    // the string representations of the perceptrons separated by commas
    // and enclosed in parentheses.
    // Example with m = 2 and n = 3: ((2.0, 0.0, -2.0), (3.0, 4.0, 5.0))
    public String toString() {
        String p = "";
        for (int i = 0; i < classes; i++) {
            if (i < classes - 1)
                p = p + perceptrons[i] + ", ";
            else p = p + perceptrons[i];
        }
        return "(" + p + ")";
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        MultiPerceptron a = new MultiPerceptron(2, 3);
        StdOut.println(a.numberOfClasses());
        StdOut.println(a.numberOfInputs());
        double[] test = { 3.0, 2.5, 3.0 };
        StdOut.println(a.predictMulti(test));
        StdOut.println(a);
        StdOut.println();

        int m = 2;
        int n = 3;

        double[] training1 = { 3.0, 4.0, 5.0 };  // class 1
        double[] training2 = { 2.0, 0.0, -2.0 };  // class 0
        double[] training3 = { -2.0, 0.0, 2.0 };  // class 1
        double[] training4 = { 5.0, 4.0, 3.0 };  // class 0

        MultiPerceptron perceptron = new MultiPerceptron(m, n);
        StdOut.println(perceptron);
        perceptron.trainMulti(training1, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training2, 0);
        StdOut.println(perceptron);
        perceptron.trainMulti(training3, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training4, 0);
        StdOut.println(perceptron);
    }
}
