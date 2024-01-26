import java.awt.Color;

public class BeadFinder {
    private int width; // width of image
    private int height; // height of image
    private Queue<Blob> blobs; // queue of blobs in image
    private boolean[][] visited; // keeps track of visited pixels

    //  finds all blobs in the specified picture using luminance threshold tau
    public BeadFinder(Picture picture, double tau) {
        width = picture.width();
        height = picture.height();
        visited = new boolean[width][height];
        blobs = new Queue<Blob>();


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = picture.get(i, j);
                double luminance = Luminance.intensity(c);
                // if pixel is not visited and is bright enough, create a new
                // blob and call dfs
                if (!visited[i][j] && luminance >= tau) {
                    Blob newBlob = new Blob();
                    dfs(i, j, picture, tau, newBlob);
                    blobs.enqueue(newBlob);
                }
                else visited[i][j] = true;
            }
        }
    }

    // recursively search for all foreground pixels in the same blob
    private void dfs(int x, int y, Picture pic, double tau, Blob b) {


        // base case if out of bounds
        if (x >= width || y >= height) return;
        if (x < 0 || y < 0) return;

        // base case if visited
        if (visited[x][y]) return;

        // base case if luminance is not bright enough
        Color c = pic.get(x, y);
        double luminance = Luminance.intensity(c);
        if (luminance < tau) {
            visited[x][y] = true; // mark dark pixel as visited
            return;
        }

        b.add(x, y); // add to blob
        visited[x][y] = true; // mark pixel as visited

        // recursively check surrounding pixels
        dfs(x + 1, y, pic, tau, b);
        dfs(x - 1, y, pic, tau, b);
        dfs(x, y + 1, pic, tau, b);
        dfs(x, y - 1, pic, tau, b);
    }

    // finds number of beads that are greater than or equal to minimum for
    // blob array size
    private int numBeads(int min) {
        int count = 0;
        for (Blob b : blobs) {
            if (b.mass() >= min)
                count++;
        }
        return count;
    }

    // returns all beads (blobs with >= min pixels) in a new blob array
    public Blob[] getBeads(int min) {
        Blob[] b = new Blob[numBeads(min)];
        int count = 0;
        for (Blob a : blobs) {
            if (a.mass() >= min) {
                b[count] = a;
                count++;
            }
        }
        return b;
    }

    //  test client, as described below
    public static void main(String[] args) {
        // reading in min, tau, and file name from command line
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        String file = args[2];

        // create picture and BeadFinder objects
        Picture pic = new Picture(file);
        BeadFinder bf = new BeadFinder(pic, tau);

        // get array of blobs containing at least min pixels and print all blobs
        // in the array
        Blob[] allBlobs = bf.getBeads(min);
        for (int i = 0; i < allBlobs.length; i++) {
            StdOut.println(allBlobs[i]);
        }
    }
}
