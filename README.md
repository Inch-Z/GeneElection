# Getting Started

## 安装geth
1. Windows：略
2. CentOS：
```
$ yum -y update
$ yum -y install golang
$ git clone https://github.com/ethereum/go-ethereum
$ cd go-ethereum/
$ make geth
$ ls -al build/bin/geth
如果出错，尝试命令：
$ mv /usr/local/include/iconv.h /usr/local/include/iconv.h.back
```

## 初始化
1. 创世块
$ geth init genesis.json

2. 启动
$ ./startup.sh

## 基本命令
1. 创建用户
$ personal.newAccount("123456")

2. 查询余额
$ eth.getBalance(eth.accounts[0])

3. 开始挖矿
$ miner.start(1)

4. 停止挖矿
$ miner.stop()


## 编写合约
https://remix.ethereum.org/

## 通过Java部署合约
1. 安装Maven插件
https://github.com/web3j/web3j-maven-plugin
```
<plugin>
    <groupId>org.web3j</groupId>
    <artifactId>web3j-maven-plugin</artifactId>
    <version>4.5.11</version>
    <configuration>
        <soliditySourceFiles/>
    </configuration>
</plugin>
```

2. 将*.sol放到resources目录下，运行插件。

3. 代码部署，参考test/java/com/example/demo/ContractDeploy.java
```
Election election = Election.deploy(web3j, credentials, new DefaultGasProvider()).send();
System.out.println(election.getContractAddress());
```

4. 修改配置：application.properties
```
web3j.url=http://127.0.0.1:8545(区块链网络地址)
credentials.source=keystone文件名，例如：UTC--xxx-xx-xx-xx-xx.xxxxxxxxxx--xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
credentials.password=创建账户的密码
contract.address=合约地址，通过./test/java/com.expamle.demo/ComtractDeploy中deploy方法获得
```

## 接口
### 实际使用将localhost:8080替换为服务器地址
http://localhost:8080/store?userPk=admin&idi=hello&ki=ki&ci=ci&key1=test1&key2=test2&key3=test3
```
{
  "code": 200,
  "message": "OK",
  "data": ""
}
```
http://localhost:8080/info?idi=hello
```
{
  "code": 200,
  "message": "OK",
  "data": {
    "now": 1589809481,
    "userPk": "admin",
    "idi": "hello",
    "ki": "ki",
    "ci": "ci",
    "key1": "test1",
    "key2": "test2",
    "key3": "test3"
  }
}
```
http://localhost:8080/count
```
{
  "code": 200,
  "message": "OK",
  "data": 1
}
```