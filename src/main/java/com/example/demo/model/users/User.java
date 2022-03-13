package com.example.demo.model.users;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(
            name = "users_data",
            sequenceName = "user_data",
            allocationSize =50
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_data"
    )
    private UUID id;
    private String username;
    private String hashPassword;
    private byte[] salt;

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String password) {

        this.hashPassword = Hashing(password);
    }

    public User(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        if(this.salt == null)
        {
            this.salt = saltGenerator(16);
        }
        this.hashPassword = Hashing(password);

    }

    private String Hashing(String password)
    {
        if(this.salt == null)
        {
            this.salt = saltGenerator(16);
        }
        return Base64.getEncoder().encodeToString(hash(password.toCharArray(),this.salt));
    }
    private byte[] saltGenerator(int n)
    {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[n];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] hash(char[] password,byte[] salt)
    {
        PBEKeySpec spec = new PBEKeySpec(password, salt, 10000, 256);
        Arrays.fill(password, Character.MIN_VALUE);
        try
        {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        }
        finally
        {
            spec.clearPassword();
        }

    }

}
