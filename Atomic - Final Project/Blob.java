public class Blob {
    private int pixels; // number of pixels
    private int xsum; // sum of x-coordinates to calculate center of mass
    private int ysum; // sum of y-coordinates to calculate center of mass
    private double cx; // x-coordinate of center of mass
    private double cy; // y-coordinate of center of mass

    //  creates an empty blob
    public Blob() {
        xsum = 0;
        ysum = 0;
        pixels = 0;
        cx = 0;
        cy = 0;
    }

    //  adds pixel (x, y) to this blob
    public void add(int x, int y) {
        pixels += 1;
        xsum += x;
        ysum += y;
        cx = xsum / (double) pixels;
        cy = ysum / (double) pixels;
    }

    //  number of pixels added to this blob
    public int mass() {
        return pixels;
    }


    //  Euclidean distance between the center of masses of the two blobs
    public double distanceTo(Blob that) {
        if (this.mass() == 0 || that.mass() == 0) return Double.NaN;

        double dx = this.cx - that.cx;
        double dy = this.cy - that.cy;
        return Math.sqrt(dx * dx + dy * dy);
    }

    //  string representation of this blob
    public String toString() {
        if (mass() == 0) return "0 (NaN, NaN)";
        return String.format("%2d (%8.4f, %8.4f)", mass(), cx, cy);

    }

    //  tests this class by directly calling all instance methods
    public static void main(String[] args) {
        Blob test1 = new Blob();
        Blob test2 = new Blob();
        test1.add(2, 2);
        StdOut.println("test1 after adding: " + test1);
        test1.add(3, 4);
        StdOut.println("test1 after adding again: " + test1);
        StdOut.println("number of pixels for test 1: " + test1.mass());
        test2.add(1, 5);
        StdOut.println("test 2 after adding: " + test2);
        StdOut.println("Distance = " + test1.distanceTo(test2));
    }
}
