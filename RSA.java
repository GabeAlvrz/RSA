import java.math.BigInteger;
import java.util.Random;

public class RSA {
    public GeneratedKeys genKey(int keySize) {
        BigInteger p = this.getRandomPrime(keySize);
        BigInteger q;
        do {
            q = this.getRandomPrime(keySize);
        } while (p.equals(q));

        BigInteger n = p.multiply(q);

        BigInteger one = new BigInteger("1");
        BigInteger phiM = p.subtract(one).multiply(q.subtract(one));

        BigInteger e = new BigInteger("65537"); // using 65537 instead of random prime

        BigInteger d = e.modInverse(phiM);

        return new GeneratedKeys(n.toString(), e.toString(), d.toString());
    }

    private BigInteger getRandomPrime(int keySize) {
        Random rand = new Random();

        // Ensure the product of two primes will result in an int of keySize bites
        int max = (int) Math.pow(2, keySize / 2) - 1;
        int min = new BigInteger("3").shiftLeft(keySize / 2 - 2).intValue();

        BigInteger prime;
        do {
            int random = rand.nextInt(max - min) + min;
            prime = new BigInteger(String.valueOf(random));
        } while (!prime.isProbablePrime(99));

        return prime;
    }

    public String encrypt(String input, Key key) {
        BigInteger e = new BigInteger(key.getExponent());
        BigInteger n = new BigInteger(key.getN());

        String result = "";

        // Encrypt in blocks of size = keySize 
        for (int i = 0; i < input.length(); i += (key.size() / 4)) {
            int start = i;
            int end = start + (key.size() / 4);

            if (end > input.length()) {
                end = input.length();
            }

            String sub = input.substring(start, end);

            // Hex into integer form
            BigInteger temp = new BigInteger(sub, 16);

            // Encrypt using m^3 mod n
            BigInteger enc = temp.modPow(e, n);

            // Encrypted int to hex form
            String encHex = enc.toString(16);

            // Pad encrypted block if needed
            if (encHex.length() < 12) {
                encHex = String.format("%" + (key.size() / 4) + "s", encHex).replace(' ', '0');
            }

            result += encHex;
            
        }

        return result;
    }
}