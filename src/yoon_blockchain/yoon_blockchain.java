package src.yoon_blockchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class yoon_blockchain {
	/* 
	// main 1
	public static void main(String[] args) {
		Block genesisBlock = new Block("Hi im the first block", "0");
		System.out.println("Hash for block 1 : " + genesisBlock.hash);
		
		Block secondBlock = new Block("Hi im the second block", genesisBlock.hash);
		System.out.println("Hash for block 2 : " + secondBlock.hash);
		
		Block thirdBlock = new Block("Hi im the third block", secondBlock.hash);
		System.out.println("Hash for block 3 : " + thirdBlock.hash);
	}
	
	//main 2
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static void main(String[] args) {
		//add out blocks to the blockchain ArrayList
		blockchain.add(new Block("first block", "0"));
		blockchain.add(new Block("second block", blockchain.get(blockchain.size()-1).hash));
		blockchain.add(new Block("third block", blockchain.get(blockchain.size()-1).hash));
	
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println(blockchainJson);
	}
	*/
	
	//main 3
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 0;

	public static void main(String[] args) {	
		//add our blocks to the blockchain ArrayList:
		blockchain.add(new Block("Hi! im the first block", "0"));
		System.out.println("Trying to Mine block 1... ");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("Yo! im the second block",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 2... ");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Ha! im the third block",blockchain.get(blockchain.size()-1).hash));	
		System.out.println("Trying to Mine block 3... ");
		blockchain.get(2).mineBlock(difficulty);
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}
	
	
	public static Boolean isChainValid() {//무결성 검사 -> 블록에 변화생기면 return false
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
	
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
	/*
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}*/
	
}