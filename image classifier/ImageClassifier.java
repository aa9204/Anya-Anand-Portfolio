import java.awt.Color;

public class ImageClassifier {
    // Creates a feature vector (1D array) from the given picture.
    public static double[] extractFeatures(Picture picture) {
        int width = picture.width();
        int height = picture.height();
        StdOut.println(width);
        StdOut.println(height);
        double[][] matrix = new double[width][height];
        double[] vector = new double[width * height];
        int i = 0;
        for (int row = 0; row < picture.height(); row++) {
            for (int col = 0; col < picture.width(); col++) {
                Color a = picture.get(col, row);
                matrix[col][row] = a.getRed();
                vector[i] = matrix[col][row];
                i++;
            }
        }
        return vector;
    }


    // See below.
    public static void main(String[] args) {
        In training = new In(args[0]);
        int mTrain = training.readInt();
        int widthTrain = training.readInt();
        int heightTrain = training.readInt();
        MultiPerceptron train = new MultiPerceptron(mTrain, widthTrain * heightTrain);
        while (!training.isEmpty()) {
            String filenameTrain = training.readString();
            int labelTrain = training.readInt();
            Picture a = new Picture(filenameTrain);
            double[] arrTrain = extractFeatures(a);
            train.trainMulti(arrTrain, labelTrain);


        }

        In testing = new In(args[1]);
        int mTest = testing.readInt();
        int widthTest = testing.readInt();
        int heightTest = testing.readInt();
        MultiPerceptron test = new MultiPerceptron(mTest, widthTest * heightTest);
        while (!testing.isEmpty()) {
            String filenameTest = testing.readString();
            int labelTest = testing.readInt();
            Picture b = new Picture(filenameTest);
            double[] arrTest = extractFeatures(b);
            if (test.predictMulti(arrTest) != labelTest) {
                StdOut.print(filenameTest + ", ");
                StdOut.print("label = " + labelTest + ", ");
                StdOut.print("predict = " + test.predictMulti(arrTest));


            }


        }
        // COMMENT OUT PRINT STATEMENTS
        // StdOut.println(m);
        // StdOut.println(width);
        // StdOut.println(height);
        // StdOut.println(filename1);
        // StdOut.println(label1);
        // StdOut.println(filename2);
        // StdOut.println(label2);


        // double[] arr = extractFeatures(a);
        // for (int i = 0; i < arr.length; i++) {
        //     StdOut.print(arr[i]);
        // }
    }
}


