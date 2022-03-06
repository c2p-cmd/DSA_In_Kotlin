package extras.lp2;

import java.io.*;

public class EncryptionMain {
    public static void main(String[] args) {
        final TranspositionCipher helper = new TranspositionCipher();
        final String cipherText = helper.doEncryption("ram is god");
        System.out.println("CipherText: " + cipherText);
        System.out.println("Decrypted: " + helper.doDecryption(cipherText));
    }

    static class Transposition
    {
        public static void main(String args[])throws IOException
        {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            int key_length,i,l,r,q,ro=0,co=0;
            System.out.println("Enter length of key");
            key_length=Integer.parseInt(br.readLine());
            int[] key =new int[key_length];
            System.out.println("Enter key");
            for(i=0;i < key_length;i++)
            {
                key[i]=Integer.parseInt(br.readLine());
            }
            System.out.println("Enter Plaintext");
            String plain_text;
            plain_text=br.readLine();
            l=plain_text.length();
            System.out.println("length:" +l);
            q=l/key_length;
            System.out.println("Q:" +q);
            r=l%key_length;
            if(r!=0)
            {
                q=q+1;
            }
            char c[][]=new char[q][key_length];
            char t[][]=new char[q][key_length];
            for(i=0;i < l;i++)
            {
                if(co==key_length)
                {
                    ro++;
                    co=0;
                }
                c[ro][co]=plain_text.charAt(i);
                co++;
            }
            for(i=0;i < key_length;i++)
            {
                int temp=key[i];
                temp=temp-1;
                for(int j=0;j < q;j++)
                {
                    try {
                        t[j][i] = c[j][temp];
                    } catch (Exception ignored) {

                    }
                }
            }
            System.out.println("Matrix form is:");
            for(i=0;i < q;i++)
            {
                for(int j=0;j < key_length;j++)
                {
                    System.out.print(c[i][j]  + " ");
                }
                System.out.println();
            }
            System.out.println("Cipher text is:");
            for(i=0;i < key_length;i++)
            {
                for(int j=0;j < q;j++)
                {
                    System.out.print(t[j][i]);
                }
            }
        }
    }
}
