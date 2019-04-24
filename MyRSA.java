import java.util.Scanner;
import java.math.BigInteger;
import javax.xml.bind.DatatypeConverter;

public class MyRSA {
    public static void printHelp() {
        System.out.println();
        System.out.println("Usage: MyRSA OPTIONS\n");
        System.out.println("Program Options:");
        System.out.println("\t--genKey");
        System.out.println("\t--encode [ASCII]");
        System.out.println("\t--decode [HEX]");
        System.out.println("\t--encrypt e n [MESSAGE]");
        System.out.println("\t--decrypt d n [MESSAGE]");
        System.out.println();
    }

    public static String getUserInput(String action) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter message to " + action);
        return sc.nextLine();
    }

    public static String toHex(String input) {
        return String.format("%x", new BigInteger(1, input.getBytes()));
    }

    public static String toASCII(String input) {
        byte[] bytes = DatatypeConverter.parseHexBinary(input);
        return new String(bytes);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            printHelp();
            System.exit(1);
        }

        RSA rsa = new RSA();

        String command = args[0];
        if (command.equalsIgnoreCase("--encode") || command.equalsIgnoreCase("--decode")) {
            String input;

            if (args.length < 2) {
                input = getUserInput(command.replaceFirst("--", ""));
            } else {
                input = args[1];
            }

            switch (command) {
            case "--encode":
                System.out.println("Encoded message: " + toHex(input));
                break;
            default:
                System.out.println("Decoded message: " + toASCII(input));
            }
        } else if (command.equalsIgnoreCase("--genkey")) {
            GeneratedKeys keys = rsa.genKey(48); // generate 48-bit size keys
            System.out.println(keys);

        } else if (command.equalsIgnoreCase("--encrypt") || command.equalsIgnoreCase("--decrypt")) {
            if (args.length < 3) {
                System.out.println("Missing values");
                printHelp();
                System.exit(1);
            }

            String input;

            if (args.length < 4) {
                input = getUserInput(command.replaceFirst("--", ""));
            } else {
                input = args[3];
            }
            Key key = new Key(args[1], args[2]);
            System.out.println("Result: " + rsa.encrypt(input, key));
        } else {
            System.out.println("Unknown command");
            printHelp();
            System.exit(1);
        }
    }
}