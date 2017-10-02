import java.lang.Object;
import java.util.*;
import java.util.Map;
import java.io.*;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.NullPointerException;


class Information{

	private String image;
	private String expirationDate;
	private String couponNumber;

	Information(){}
	// Constructor with 3 parameters
	Information(String image, String expirationDate, String couponNumber){
		this.image = image;
		this.expirationDate = expirationDate;
		this.couponNumber = couponNumber;
	}
	// Setters
	public void setImage(String image){ this.image = image; }
	public void setExpirationDate(String expirationDate){ this.expirationDate = expirationDate; }
	public void setCouponNumber(String couponNumber){ this.couponNumber = couponNumber; }
	// Getters
	public String getImage(){ return image; }
	public String getExpirationDate(){ return expirationDate; }
	public String getCouponNumber(){ return couponNumber; }
	// Returns the string of the object's member variables
	public String toString(){
		String wholeString = this.getImage() + " " + this.getExpirationDate() + " " + this.getCouponNumber();
		return wholeString;		
	}

};

class Hashing {

	private HashMap<String, List<Information> > hashTable = new HashMap<String, List<Information> >();
	private String storeName;
	private Information infoObject;

	Hashing(){}
	// Creates a mapping which maps the information with the name of store
	public void create(String storeName, String image, String expirationDate, String couponNumber){
		infoObject = new Information(image, expirationDate, couponNumber);
		createKey(storeName);
		hashTable.get(storeName).add(infoObject);
	}
	// set
	public void setStoreName(String storeName){ this.storeName = storeName; }
	// get
	public String getStoreName(){ return storeName; }
	// Returns the size of the hash map
	public int size(){ return hashTable.size(); }
	// Checks if the key exist on the map
	public boolean containsKey(String key){ return hashTable.containsKey(key); }
	// Print the keys in the map
	public void printKeys(){
		System.out.println(hashTable.keySet());
	}
	// Print the keys and the values of a specified key
	public void printMap(String storeName){
		if(containsKey(storeName)){
			for(int index = 0; index < hashTable.get(storeName).size(); index++){
				System.out.println( storeName + ": " + hashTable.get(storeName).get(index).toString());
			}
		}
	}
	// Creates key with an empty vector
	public void createKey(String storeName){
		if(!containsKey(storeName)){
			hashTable.put(storeName, new Vector<>());
		}
		// Does nothing if there is a key
		return;
	}
	// Prints all stores corresponding with their values
	public void printAll(){
		for(Map.Entry<String, List<Information> > entry : hashTable.entrySet()){
			String storeName = entry.getKey();
			for(Information info : entry.getValue()){
				System.out.println(storeName + ": " + info.toString());
			}
		}
	}
	// Read entries from the map and places them into a text file
	public void write(){
		try{
			FileWriter fileWriter = new FileWriter("stores.txt");
			BufferedWriter buffWrite = new BufferedWriter(fileWriter);
			// Goes through the map
			for(Map.Entry<String, List<Information> > entry : hashTable.entrySet()){
				String storeName = entry.getKey();
				// Places the store name and the Infomation details into the buffer
				for(Information info : entry.getValue()){
					buffWrite.write(storeName + " " + info.toString());
					buffWrite.newLine();
				}
			}
			// Closes buffer
			buffWrite.close();
		}
		// Not really sure what this does. Need a catch statement.
		catch(IOException ex) {
            ex.printStackTrace();
		}
	}
	// Read entries from file and places it into the map
	public void read(){
		String fileLine = "";
		try{
			FileReader fileReader = new FileReader("stores.txt");
			BufferedReader buffReader = new BufferedReader(fileReader);
			// Read until end of file
			while(( fileLine = buffReader.readLine() ) != null){
				// Split the file line into tokens
				String[] tokens = fileLine.split(" ");
				String storeName = tokens[0];
				String image = tokens[1];
				String expirationDate = tokens[2];
				String couponNumber = tokens[3];
				// Creates a hash map
				create(storeName, image, expirationDate, couponNumber);
			}
		}
		// Not really sure what this does. Need a catch statement.
		catch(IOException ex) {
	        ex.printStackTrace();
		}
	}
	// Removes a key from the map
	public boolean removeKey(String storeName){
		if(containsKey(storeName)){
			hashTable.remove(storeName);
			return true;
		}
		return false;
	}
};



public class hash{
	public static void main(String args[]){
		
		Hashing object = new Hashing();
		object.read();
		// Known bug
		object.create("TestCase", "99999", "999/999/999", "99999");
		object.printAll();
		object.write();
	}
}