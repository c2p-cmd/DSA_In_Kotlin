package extras.lp2;

import java.util.*;

public class Encryption {
    private static final String key = "HACK";

    public static void main(String[] args) {
        final Scanner s = new Scanner(System.in);

        final Map<Integer, Integer> keyMap = setPermutationOrder();

        System.out.println("Enter message: ");
        final String msg = s.nextLine();
        final String cipher = encryptMessage(msg, keyMap);

        System.out.println("Cipher: " + cipher);
        System.out.println("Decrypted: " + decryptMessage(cipher, keyMap));
        s.close();
    }

    private static Map<Integer, Integer> setPermutationOrder() {
        final Map<Integer, Integer> keyMap = new TreeMap<>();
        for (int i = 0; i < key.length(); i++) {
            keyMap.put((int) key.charAt(i), i);
        }
        return keyMap;
    }

    static String encryptMessage(final String msg, final Map<Integer, Integer> keyMap) {
        StringBuilder cipher = new StringBuilder();

        /* calculate column of the matrix*/
        final int col = key.length();

        /* calculate Maximum row of the matrix*/
        int row = msg.length()/col;

        if (msg.length() % col != 0)
            row += 1;

        char[][] matrix = new char[row][col];

        for (int i=0,k=0; i < row; i++)
        {
            for (int j=0; j<col; )
            {
                if (k >= msg.length()) {
                    /* Adding the padding character '_' */
                    matrix[i][j] = '_';
                    break;
                }

                if( Character.isAlphabetic(msg.charAt(k)) || msg.charAt(k) ==' ') {
                    /* Adding only space and alphabet into matrix */
                    if (msg.charAt(k) != ' ')
                        matrix[i][j] = msg.charAt(k);
                    else
                        matrix[i][j] = '$';
                    j++;
                }
                k++;
            }
        }

        final int rows = row;
        keyMap.forEach((k,v) -> {
            for (int i = 0; i < rows; i++) {
                if (Character.isAlphabetic(matrix[i][v]) || matrix[i][v] == '$' || matrix[i][v] == '_')
                    cipher.append(matrix[i][v]);
            }
        });

        return cipher.toString();
    }

    static String decryptMessage(final String cipher,  final Map<Integer, Integer> keyMap) {
        /* calculate row and column for cipher Matrix */
        final int col = key.length();

        final int row = cipher.length()/col;
        final char[][] cipherMat= new char[row][col];

        /* add character into matrix column wise */
        for (int j=0,k=0; j<col; j++) {
            for (int i = 0; i < row; i++) {
                cipherMat[i][j] = cipher.charAt(k++);
            }
        }

        /* update the order of key for decryption */
        final int[] index = new int[]{ 0 };
        // System.out.println("Before: " + keyMap);
        keyMap.forEach((k,v) -> {
             keyMap.put(k, index[0]);
             index[0]++;
        });
        // System.out.println("After: " + keyMap);


	    /* Arrange the matrix column wise according
	    to permutation order by adding into new matrix */
        final char[][] decCipher = new char[row][col];
        int k = 0;
        for (int l=0,j; l < key.length(); k++) {
            final int lookUpKey = key.charAt(l++);
            j = keyMap.get(lookUpKey);
            for (int i=0; i<row; i++) {
                decCipher[i][k] = cipherMat[i][j];
            }
        }

        /* getting Message using matrix */
        final StringBuilder msg = new StringBuilder();
        for (int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if (decCipher[i][j] == '$')
                    msg.append(" ");
                else if (decCipher[i][j] != '_')
                    msg.append(decCipher[i][j]);
            }
        }
        return msg.toString();
    }
}
