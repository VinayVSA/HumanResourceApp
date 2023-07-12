package com.example.hra.entities;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.util.Random;
public class CountryIdGenerator implements IdentifierGenerator {
    private Random random = new Random();
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder();
        while (randomString.length() < 2) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }
}
