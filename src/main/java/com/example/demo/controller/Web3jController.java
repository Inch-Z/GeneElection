package com.example.demo.controller;

import com.example.demo.base.Result;
import com.example.demo.base.Status;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.web3j.model.Election;
import org.web3j.protocol.Web3j;
import org.web3j.tuples.generated.Tuple8;

import java.io.IOException;
import java.math.BigInteger;

@Slf4j
@Controller
public class Web3jController {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Election election;

    @RequestMapping("version")
    @ResponseBody
    public Result version() {
        try {
            String version = web3j.web3ClientVersion().send().getWeb3ClientVersion();
            return Result.success(version);
        } catch (IOException e) {
            log.error("", e);
            return Result.error(Status.ETH_ERROR);
        }
    }

    @RequestMapping("store")
    @ResponseBody
    public Result store(@RequestParam("userPk") String userPk,
                        @RequestParam("idi") String idi,
                        @RequestParam("ki") String ki,
                        @RequestParam("ci") String ci,
                        @RequestParam("key1") String key1,
                        @RequestParam("key2") String key2,
                        @RequestParam("key3") String key3) {
        try {
            election.store(userPk, idi, ki, ci, key1, key2, key3).send();
            return Result.success("");
        } catch (Exception e) {
            log.error("", e);
            return Result.error(Status.ETH_ERROR);
        }
    }

    @RequestMapping("count")
    @ResponseBody
    public Result count() {
        try {
            BigInteger count = election.count().send();
            return Result.success(count);
        } catch (Exception e) {
            log.error("", e);
            return Result.error(Status.ETH_ERROR);
        }
    }

    @RequestMapping("info")
    @ResponseBody
    public Result info(@RequestParam("idi") String idi) {
        try {
            Tuple8<BigInteger, String, String, String, String, String, String, String> tuple = election.map(idi).send();
            Gene gene = new Gene();
            gene.createTime = tuple.component1();
            gene.userPk = tuple.component2();
            gene.idi = tuple.component3();
            gene.ki = tuple.component4();
            gene.ci = tuple.component5();
            gene.key1 = tuple.component6();
            gene.key2 = tuple.component7();
            gene.key3 = tuple.component8();
            return Result.success(gene);
        } catch (Exception e) {
            log.error("", e);
            return Result.error(Status.ETH_ERROR);
        }
    }

    @Data
    public static class Gene {
        BigInteger createTime;
        String userPk;
        String idi;
        String ki;
        String ci;
        String key1;
        String key2;
        String key3;
    }
}
