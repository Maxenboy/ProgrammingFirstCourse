package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.*;

public class JSONDatabase 
{

	/*********************************************
	 * private constants
	 *********************************************/
	private static final String databasePath = "testdb.json.txt";
	private JSONObject jsonObject;
	
	/********************************************
	 * Backup counter
	 ********************************************/
	private int bkpCounter;
	
	
	/*********************************************
	 * Constructors
	 *********************************************/
	
	/**
	 *  Private constructor prevents instantiation from other classes
	 *  Reads the database file specified in the databasePath above
	 *  and instantiates a new JSONObject from its content
	 */
    private JSONDatabase() 
    {
    	StringBuilder sb = new StringBuilder();
    	try {
			BufferedReader reader = new BufferedReader(new FileReader(databasePath));
			String line = reader.readLine();
			while(line!=null)
			{
				sb.append(line);
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Cannot find configuration file, im going to die now...");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Cannot read configuration file, im going to die now...");
			System.exit(1);
		}
    	JSONTokener tokener = new JSONTokener(sb.toString());
    	try {
			jsonObject = new JSONObject(tokener);
		} catch (JSONException e) {
			e.printStackTrace();
			System.err.println("Cannot parse json FOOL!, im going to die now...");
			System.exit(1);
		}
		try {
			JSONObject bkpEntry = (JSONObject) jsonObject.get("Backup");
			bkpCounter = bkpEntry.getInt("Antal");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Singleton getter method
     * @return		a singleton instance of JSONDatase
     */
    public static JSONDatabase getInstance() 
    {
        return SingletonHolder.instance;
    }

    /**
    * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
    * or the first access to SingletonHolder.INSTANCE, not before.
    */
    private static class SingletonHolder 
    { 
    	public static final JSONDatabase instance = new JSONDatabase();
    }
    
    /*********************************************
	 * Getters
	 *********************************************/
    /**
	 * Returns entry in JSON databse from key. Ignores case.
	 * @param key
	 * @return
	 */
	public JSONObject getEntry(String key) {
		JSONObject entry = null;
		entry = (JSONObject) getJSONValueForKey(key);
		if (entry == null && key.split(" ").length > 1) {
			String[] nameArr = key.split(" ");
			key = nameArr[0].substring(0, 1).toUpperCase() + nameArr[0].substring(1) + " "
				+ nameArr[1].substring(0, 1).toUpperCase() + nameArr[1].substring(1);
			entry = (JSONObject) getJSONValueForKey(key);
		}
		if (entry == null) {
			key = key.toUpperCase();
			entry = (JSONObject) getJSONValueForKey(key);
		}
		return entry;
	}
    
    /**
     * 
     * @param key	identifier key 
     * @return 		value of object associated with key
     */
    public Object getJSONValueForKey(String key)
    {
    	if(jsonObject.has(key))
    	{
			try {
				return jsonObject.get(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
    	}
    	return null;
    }
    
    /***********************************************************
     * Misc. methods :-P
     **********************************************************/
    
    /**
     * Puts value in the database with the key key. For testing purposes only.
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
    	try {
			jsonObject.put(key, value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Replaces the journal entry for patient key with the String journal
     * @param key Name of the patient
     * @param journal The journal
     * @throws JSONException if key is not in database
     */
    public void writeJournal(String key, String journal) throws JSONException {
    	JSONObject entry = null;
    	JSONObject bkp = null;
    	String oldJournal = "";
    	try {
    		entry = (JSONObject) jsonObject.get(key);
    	} catch (JSONException e) {
    		String[] nameArr = key.split(" ");
			key = nameArr[0].substring(0, 1).toUpperCase() + nameArr[0].substring(1) + " "
				+ nameArr[1].substring(0, 1).toUpperCase() + nameArr[1].substring(1);
			entry = (JSONObject) getJSONValueForKey(key);
			
    	}
    	try {
    		bkp = (JSONObject) jsonObject.get("Backup");
    		oldJournal = entry.getString("Jour");
    	} catch (JSONException e) {
    		System.out.println("Could not load journal backup. Journal will be overwritten.");
    		oldJournal = "";
    	}
    	bkpCounter++;
    	if (bkp != null) {
    		bkp.put("Antal", bkpCounter);
    		bkp.put(Integer.toString(bkpCounter), oldJournal);
    	}
    	entry.put("Jour", journal);
    	writeJSONtoFile();
    }

    public void createNewJournal(String name, HashMap<String, String> attributes){
    	try {
			jsonObject.accumulate(name, attributes);
			writeJSONtoFile();
		} catch (JSONException e) {
			e.printStackTrace();
		}
    }
  
    /**
     * Removes the entry called key from the database.
     * @param key
     * @return true if entry was removed, false if it does not exist
     */
    public boolean deleteEntry(String key) {
    	JSONObject entry = null;
		entry = (JSONObject) getJSONValueForKey(key);
		if (entry == null && key.split(" ").length > 1) {
			String[] nameArr = key.split(" ");
			key = nameArr[0].substring(0, 1).toUpperCase() + nameArr[0].substring(1) + " "
				+ nameArr[1].substring(0, 1).toUpperCase() + nameArr[1].substring(1);
			entry = (JSONObject) getJSONValueForKey(key);
		}
		if (entry == null) {
			key = key.toUpperCase();
			entry = (JSONObject) getJSONValueForKey(key);
		}
    	entry = (JSONObject) jsonObject.remove(key);
    	writeJSONtoFile();
    	return entry != null;
    }
    

    /**
     * Writes the JSONDatabase to file <i>testdb.json.txt</i>. 
     */
    public void writeJSONtoFile() {
    	FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(databasePath);
	    	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    	bufferedWriter.write(jsonObject.toString());
	    	bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public String print() {
    	return jsonObject.toString();
    }
}