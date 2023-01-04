package com.spark.springboot.jasyptConfig;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class EncConfig {
	
	@Value("${jasypt.encryptor.password}") private String key;
	    @Bean(name = "jasyptStringEncryptor")
	    public StringEncryptor getPasswordEncryptor() {
	        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
	        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
	         
	        config.setPassword(key); // encryptor's private key
	         
	        config.setAlgorithm("PBEWithMD5AndDES");
	        config.setKeyObtentionIterations("1000");
	        config.setPoolSize("1");
	        config.setProviderName("SunJCE");
	        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
	        config.setStringOutputType("base64");
	         
	        encryptor.setConfig(config);
	         
	        return encryptor;
	    }
}
