package extras.lp2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class RSA {
    private static int gcd (final int e, final int z) {
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }

    public static void main(String[] args) {
        System.out.println();

        final Scanner scanner = new Scanner(System.in);

        // take prime numbers as input:
        System.out.println("Enter prime numbers p&q: ");
        final int p = scanner.nextInt();
        final int q = scanner.nextInt();

        final int n = p * q;
        final int z = (p-1) * (q-1);
        System.out.println("Value of z: " + z);

        System.out.println("Message to be encrypted(enter integer): ");
        final int message = scanner.nextInt();

        int e;
        for (e = 2; e < z; e++) {
            // e is for public key component
            if (gcd(e, z) == 1)
                break;
        }
        System.out.println("The value for exponent(e): " + e);

        // private key exponent(d)
        int d = 0;
        for (int i = 0; i < 10; i++) {
            final int x = 1 + (i * z);

            if (x % e == 0) {
                d = x/e;
                break;
            }
        }

        System.out.println("The value of d: " + d);
        final double c = Math.pow(message, e) % n;
        System.out.println("Encrypted message: " + c);

        // converting int value of n to BigInteger
        final BigInteger N = BigInteger.valueOf(n);

        // converting float value to c to BigInteger
        final BigInteger C = BigDecimal.valueOf(c).toBigInteger();
        final BigInteger messageBack = (C.pow(d)).mod(N);
        System.out.println("Decrypted message: " + messageBack);

        scanner.close();
    }
}
