public class GeneratedKeys {
    private final String N, PUBLIC_KEY, PRIVATE_KEY;

    public GeneratedKeys(String n, String publicKey, String privateKey) {
        this.N = n;
        this.PUBLIC_KEY = publicKey;
        this.PRIVATE_KEY = privateKey;
    }

    public Key getPublicKey() {
        return new Key(this.PUBLIC_KEY, this.N);
    }

    public Key getPrivateKey() {
        return new Key(this.PRIVATE_KEY, this.N);
    }

    public String toString() {
        return String.format("n: %s\ne: %s\nd: %s", this.N, this.PUBLIC_KEY, this.PRIVATE_KEY);
    }
}