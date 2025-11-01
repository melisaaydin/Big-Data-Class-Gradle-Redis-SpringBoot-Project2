package com.example.bigdata.config;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; // Spring anotasyonu

import java.net.URI;

@Configuration // Spring konfigurasyon anotasyonu
public class HadoopConfig {
    @Bean
    public FileSystem fileSystem() throws Exception {
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration(); 
        
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        conf.set("hadoop.security.authentication", "simple");
        return FileSystem.get(new URI("hdfs://localhost:9000"), conf);
    }
}
