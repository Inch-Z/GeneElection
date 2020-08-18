pragma solidity ^0.5.0;

contract election {
    address owner;
    constructor () public {
        owner = msg.sender;
    }

    Gene[] genes;
    mapping(string => Gene) public map;
    struct Gene {
         uint createTime;
         string userPk;
         string idi;
         string ki;
         string ci;
         string key1;
         string key2;
         string key3;
    }

    // 存储基因
    function store(string memory userPk, string memory idi, string memory ki, string memory ci,
                    string memory key1, string memory key2, string memory key3) public {
        require(owner == msg.sender);
        Gene memory gene = Gene(now, userPk, idi, ki, ci, key1, key2, key3);
        genes.push(gene);
        map[idi] = gene;
    }

    // 基因检测
    function mpc(string memory key) public view returns (string memory, string memory) {
        for(uint i = 0; i < genes.length; i++) {
            Gene memory gene = genes[i];
            if(equals(key, gene.key1) || equals(key, gene.key2) || equals(key, gene.key3)) {
                return (gene.idi, gene.ci);
            }
        }
        return ("", "");
    }

    // 基因交易
    // function transaction(string memory requirePk, string memory ownerPk, string memory idi) public {

    // }

    function count() public view returns(uint) {
        return genes.length;
    }

    function equals(string memory a, string memory b) internal pure returns (bool) {
        if (bytes(a).length != bytes(b).length) return false;
        else return keccak256(bytes(a)) == keccak256(bytes(b));
    }

}