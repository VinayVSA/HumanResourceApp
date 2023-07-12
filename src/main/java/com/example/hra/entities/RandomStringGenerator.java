package com.example.hra.entities;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;
public class RandomStringGenerator implements IdentifierGenerator {
    private Random random = new Random();
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        while (randomString.length() < 10) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }



        return randomString.toString();
    }
}