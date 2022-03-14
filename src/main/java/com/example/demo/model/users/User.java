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
@Table(name="UsersData")
public class User {
    @javax.persistence.Id
    @Column(name = "id", nullable = false)
    private Long id;

    @SequenceGenerator(
            name = "user_data",
            sequenceName = "user_data",
            allocationSize =50
    )

    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "users_data"
    )

    @Column(name="UUID")
    private UUID uuid;
    @Column(name="Username")
    private String username;
    @Column(name="hashPassword")
    private String hashPassword;
    @Column(name="Salt")
    private byte[] salt;


    public byte[] getSalt() {
        return this.salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }


    public Long getId(long id) {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashPassword() {
        return this.hashPassword;
    }

    public void setHashPassword(String password) {

        this.hashPassword = Hashing(password);
    }

    public User(long id,UUID uuid, String username, String password) {
        this.id = id;
        this.uuid = uuid;
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
