public class Perceptron {
    // create instance variable int numInputs for number of weights
    private int numInputs;
    // create instance variable double[] weights for weights array
    private double[] weights;

    // Creates a perceptron with n inputs. It should create an array
    // of n weights and initialize them all to 0.
    public Perceptron(int n) {
        numInputs = n;
        weights = new double[n];
    }

    // Returns the number of inputs numInputs.
    public int numberOfInputs() {
        return numInputs;
    }

    // Returns the weighted sum of the weight vector and x.
    public double weightedSum(double[] x) {
        double sum = 0.0;
        for (int i = 0; i < x.length; i++) {
            double s = (weights[i] * x[i]);
            sum += s;
        }
        return sum;
    }

    // Predicts the label (+1 or -1) of input x. It returns +1
    // if the weighted sum is positive and -1 if it is negative (or zero).
    public int predict(double[] x) {
        if (weightedSum(x) > 0)
            return +1;
        else return -1;
    }

    // Trains this perceptron on the labeled (+1 or -1) input x.
    // The weights vector is updated accordingly.
    public void train(double[] x, int label) {
        if (predict(x) != label) {
            if (predict(x) == +1)
                for (int i = 0; i < x.length; i++)
                    weights[i] -= x[i];
            else if (predict(x) == -1)
                for (int i = 0; i < x.length; i++)
                    weights[i] += x[i];
        }
    }

    // Returns a String representation of the weight vector, with the
    // individual weights separated by commas and enclosed in parentheses.
    // Example: (2.0, 1.0, -1.0, 5.0, 3.0)
    public String toString() {
        String w = "";
        for (int i = 0; i < weights.length; i++) {
            if (i < weights.length - 1)
                w = w + weights[i] + ", ";
            else w = w + weights[i];
        }
        return "(" + w + ")";
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        // testing implementation of each method
        Perceptron a = new Perceptron(3);
        StdOut.println(a.numberOfInputs());
        double[] test = { 2.0, 5.0, 1.0 };
        StdOut.println(a.predict(test));
        StdOut.println(a.weightedSum(test));

        // testing Perceptron.java implementation
        int n = 3;

        double[] training1 = { 3.0, 4.0, 5.0 };  // yes
        double[] training2 = { 2.0, 0.0, -2.0 };  // no
        double[] training3 = { -2.0, 0.0, 2.0 };  // yes
        double[] training4 = { 5.0, 4.0, 3.0 };  // no

        Perceptron perceptron = new Perceptron(n);
        StdOut.println(perceptron);
        perceptron.train(training1, +1);
        StdOut.println(perceptron);
        perceptron.train(training2, -1);
        StdOut.println(perceptron);
        perceptron.train(training3, +1);
        StdOut.println(perceptron);
        perceptron.train(training4, -1);
        StdOut.println(perceptron);
    }
}
