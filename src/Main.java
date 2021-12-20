import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        register("titodkm@gmail.com", "changeme");
        register("titodkmgmail.com", "error");
        register("dario@example.com", "admin");
        System.out.println(login("titodkm@gmail.com", "admin"));
        System.out.println(login("dario@example.com", "admin"));
    }

    static boolean mailExists(String mail) {
        for(User user : users) {
            if(user.getEmail().equals(mail)) {
                System.err.println("El correo electrónico " + mail + " ya existe en la base de datos");
                return true;
            }
        }
        return false;
    }

    public static boolean register(String email, String password) {
        if(mailExists(email) || !Utils.isEmailValid(email)) {
            return false;
        }
        String encryptedPassword = Utils.encryptPassword(password);
        users.add(new User(email, encryptedPassword));
        return true;
    }

    public static int login(String email, String password) {
        if(!mailExists(email))
            return -1;

        String encryptedPassword = Utils.encryptPassword(password);

        for(User user : users) {
            if(user.getEmail().equals(email) && user.getPassword().equals(encryptedPassword))
                return 1;
        }
        return -2;
    }
}
