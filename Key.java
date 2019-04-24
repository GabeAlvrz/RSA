import java.math.BigInteger;

public class Key {
    private final String EXPONENT, N;

    public Key(String exponent, String n) {
        this.EXPONENT = exponent;
        this.N = n;
    }

    public String getExponent() {
        return this.EXPONENT;
    }

    public String getN() {
        return this.N;
    }

    public int size() {
        return new BigInteger(this.N).bitLength();
    }
}