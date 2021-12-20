import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class Utils {
    static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isEmailValid(String mail) {
        Boolean valid = EMAIL_REGEX.matcher(mail).find();
        if(!valid) {
            System.err.println("El correo electrónico " + mail + " tiene un formato incorrecto");
        }
        return valid;
    }

    public static String encryptPassword(String plainPassword) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(plainPassword.getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error while trying to encrypt password: " + e.getMessage());
        }
        return "";
    }
}
