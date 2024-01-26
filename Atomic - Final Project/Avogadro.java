public class Avogadro {

    // reads the radial displacements from standard input and estimates
    // Boltzmann’s constant and Avogadro’s number
    public static void main(String[] args) {
        int count = 0; // keep track of number of radical displacements
        double sumR = 0.0; // sum of each displacement squared
        double pTom = 0.175e-6; // converting from pixels to meters
        double t = 297; // absolute temperature in Kelvin
        double n = 9.135e-4; // viscosity of water at room temperature in N * s * m ^-2
        double p = 0.5e-6; // radius of bead in meters
        double gasR = 8.31446; // universal gas constant R

        while (!StdIn.isEmpty()) {
            double r = StdIn.readDouble() * pTom;
            sumR += r * r;
            count++;
        }
        double d = sumR / (2 * count); // calculate self diffusion constant
        double k = (d * 6 * Math.PI * n * p) / t; // calculate Boltzmann
        double nA = gasR / k; // calculate avogadro using R (gas constant) and k

        StdOut.print("Boltzmann = ");
        StdOut.printf("%7.4e", k);
        StdOut.print("\n" + "Avogadro = ");
        StdOut.printf("%7.4e", nA);
    }
}
