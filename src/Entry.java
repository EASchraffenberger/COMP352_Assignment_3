
public class Entry {
	
	//variables:
	private int key = 0;
	private float value = 0;
	
	//constructor method:
	public Entry()
	{
		this.key = key;
		this.value = value;
	}
	
	public Entry(int thisKey, float thisValue)
	{
		this.key = thisKey;
		this.value = thisValue;
	}
	
	//accessor methods:
	public int getKey()
	{
		return this.key;
	}
	
	public float getValue()
	{
		return this.value;
	}

	//mutator methods:
	public void setKey(int thisKey)
	{
		this.key = thisKey;
	}
	
	public void setValue(float thisValue)
	{
		this.value = thisValue;
	}
	
	//toString method returns a statement including the key and value of an Entry
	
	public String toString()
	{
		return "This entry has key " + this.getKey() + "and value " + this.getValue() + ".";
	}
}
