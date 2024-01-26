public class BeadTracker {

    // determine how far a bead moves from one time i to the next time i + 1
    public static void main(String[] args) {
        // parse min, tau, and delta from command line
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);

        // declare variable for distance
        double distance;

        // traverse images, storing 2 at a time
        for (int i = 3; i < args.length - 1; i++) {
            // store two files at a time
            String filename1 = args[i];
            String filename2 = args[i + 1];

            // create picture objects, one for each file
            Picture pic1 = new Picture(filename1);
            Picture pic2 = new Picture(filename2);

            // create BeadFinder object for each picture and return Blob array
            BeadFinder bf1 = new BeadFinder(pic1, tau);
            Blob[] blobs1 = bf1.getBeads(min);
            BeadFinder bf2 = new BeadFinder(pic2, tau);
            Blob[] blobs2 = bf2.getBeads(min);

            // traverse beads at t + 1
            for (int j = 0; j < blobs2.length; j++) {
                // set smallest distance to infinity for comparison
                double smallest = Double.POSITIVE_INFINITY;
                // traverse beads at t, and find distance from bead at t + 1
                // to closest bead at t
                for (int k = 0; k < blobs1.length; k++) {
                    // compute distance
                    distance = blobs2[j].distanceTo(blobs1[k]);
                    // determine minimum/closest bead in t to bead in t + 1
                    if (distance < smallest) {
                        smallest = distance;
                    }
                }
                // only print if distance is not greater than delta (still the
                // same bead not a different one)
                if (smallest <= delta) {
                    StdOut.printf("%.4f", smallest);
                    StdOut.println();
                }
            }
        }
    }
}
