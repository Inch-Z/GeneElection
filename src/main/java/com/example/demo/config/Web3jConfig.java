package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.model.Election;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.net.URL;

@Slf4j
@Configuration
public class Web3jConfig {

    @Value("${web3j.url}")
    private String URL;

    @Value("${credentials.source}")
    private String SOURCE;

    @Value("${credentials.password}")
    private String PASSWORD;

    @Value("${contract.address}")
    private String ADDRESS;

    @Bean
    public Web3j web3j() {
        Web3jService web3jService = new HttpService(URL);
        log.info("加载web3j====>：" + URL);
        return Web3j.build(web3jService);
    }

    @Bean
    public Credentials credentials() throws Exception {
        URL url = getClass().getClassLoader().getResource(SOURCE);
        log.info("加载credentials====>：" + SOURCE);
        if (url == null) throw new Exception("钱包不存在！！！");
        return WalletUtils.loadCredentials(PASSWORD, url.getPath());
    }

    @Bean
    public Election election() throws Exception {
        log.info("加载election====>：" + ADDRESS);
        return Election.load(ADDRESS, web3j(), credentials(), new DefaultGasProvider());
    }
}
