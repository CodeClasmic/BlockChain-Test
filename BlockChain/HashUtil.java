import java.security.MessageDigest; // Import Java's built-in hashing library

// Utility class for hashing using SHA-256
class HashUtil {
    
    // Method to apply SHA-256 hashing to an input string
    public static String applySHA256(String input) {
        try {
            // Get an instance of SHA-256 hashing algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Apply SHA-256 to input string and generate the hash as a byte array
            byte[] hash = digest.digest(input.getBytes());

            // Convert byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b); // Convert byte to hex
                if (hex.length() == 1) hexString.append('0'); // Ensure 2-character hex format
                hexString.append(hex);
            }

            // Return the final hashed value as a hex string
            return hexString.toString();

        } catch (Exception e) {
            // If an error occurs (e.g., SHA-256 algorithm not found), throw a runtime error
            throw new RuntimeException(e);
        }
    }
}
