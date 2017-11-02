import java.lang.*;
import java.util.*;
import java.util.Map;
import java.io.*;
import java.io.BufferedWriter;
import java.nio.file.*;
import java.nio.file.Paths;
import java.lang.NullPointerException;

class Information{

	private String image;
	private String expirationDate;
	private String couponNumber;

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
		return this.getImage() + " " + this.getExpirationDate() + " " + this.getCouponNumber();
	}
}

class Database{

	private HashMap<String, List<Information> > hashTable = new HashMap<>();

	// Creates a mapping which maps the information with the name of store
	public void create(String storeName, String image, String expirationDate, String couponNumber){
		// Creates a store with empty ArrayList
		createStore(storeName);
		// If there is already a coupon number in the ArrayList, don't create a new one
		if(doesValueExist(storeName, couponNumber)) { return; }
		Information infoObject = new Information(image, expirationDate, couponNumber);
		Information noImage = new Information(null, expirationDate, couponNumber);
		// Checks if the image is found
		if(image == null){
			hashTable.get(storeName).add(noImage);
			return;
		}
		hashTable.get(storeName).add(infoObject);
	}
	// Returns the size of the hash map
	public int size(){ return hashTable.size(); }
	// Checks if the key exist on the map
	public boolean containStore(String storeName){ return hashTable.containsKey(storeName); }
	// Print the keys in the map
	public void printKeys(){ System.out.println(hashTable.keySet()); }
	// Print the keys and the values of a specified key
	public void printMap(String storeName){
		if(containStore(storeName)){
			for(int index = 0; index < hashTable.get(storeName).size(); index++){
				System.out.println( storeName + ": " + hashTable.get(storeName).get(index).toString());
			}
		}
	}
	// Creates store with an empty ArrayList
	public void createStore(String storeName){
		hashTable.putIfAbsent(storeName, new ArrayList<>());
	}
	// Prints all stores corresponding with their values
	public void printAll(){
		for(Map.Entry<String, List<Information> > entry : hashTable.entrySet()){
			String storeName = entry.getKey();
			for(Information infoObject : entry.getValue()){
				System.out.println(storeName + ": " + infoObject.toString());
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
				for(Information infoObject : entry.getValue()){
					buffWrite.write(storeName + " " + infoObject.toString());
					buffWrite.newLine();
				}
			}
			// Closes buffer
			buffWrite.close();
		}
		// Not really sure what this does. Need a catch statement.
		catch(IOException ex){ 
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
			while(( fileLine = buffReader.readLine() ) != null ){
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
	public boolean removeStore(String storeName){
		if(!containStore(storeName)){
			return false;
		}
		hashTable.remove(storeName);
		return true;
	}
	// Removes a coupon from the map based on coupon number
	public boolean removeValue(String storeName, String couponNumber){
		// If store does not exist do nothing
		if(!containStore(storeName)){ return false; }
		System.out.println("Your coupon number for deletion: " + couponNumber);
		System.out.println("Store has coupons: " + hashTable.get(storeName));
		for(int index = 0; index < hashTable.get(storeName).size(); index++){
			if(hashTable.get(storeName).get(index).getCouponNumber().equals(couponNumber)){
				hashTable.get(storeName).remove(index);
				return true;
			}
		}
		return false;
	}
	// Checks if there is already a coupon number in the list
	public boolean doesValueExist(String storeName, String couponNumber){
		for(int index = 0; index < hashTable.get(storeName).size(); index++){
			if(hashTable.get(storeName).get(index).getCouponNumber().equals(couponNumber)){
				return true;
			}
		}
		return false;
	}
}

public class Main{
	public static void main(String args[]){

		Database object = new Database();
		object.read();
		object.printAll();
		object.write();
	}
}