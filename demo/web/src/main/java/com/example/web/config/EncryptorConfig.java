package com.example.web.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JIA
 */

@Configuration
public class EncryptorConfig {

    @Bean(name = "encryptorBean")
    public StringEncryptor stringEncryptor() {

        PooledPBEStringEncryptor pooledPBEStringEncryptor = new PooledPBEStringEncryptor();

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword("Hcinfos");

        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");

        pooledPBEStringEncryptor.setConfig(config);

        return pooledPBEStringEncryptor;

    }
}
