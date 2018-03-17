
import java.util.Random;
public class Driver {
	
	//createKeyArray creates an array of key values for each entry in the heap
	public static void createEntryArrays(int[] keyArray, float[] valueArray, int arrayLength, Random randomGenerator)
	{
		for(int i = 0; i < arrayLength; i++)
		{
			keyArray[i] = randomGenerator.nextInt(10);
			//System.out.println("keyArray[" + i + "] is " + keyArray[i]);
			valueArray[i] = (float)(randomGenerator.nextInt(9) + randomGenerator.nextDouble() + randomGenerator.nextDouble()/10);
			//System.out.println("valueArray[" + i + "] is " + valueArray[i]);
		}
	}
	
	public static void main(String[] args) {
		
		Random randomGenerator = new Random();
		
		int arrayLength = randomGenerator.nextInt(15);
		
		int[] keyArray = new int[arrayLength];
		float[] valueArray = new float[arrayLength];
		createEntryArrays(keyArray, valueArray, arrayLength, randomGenerator);
		System.out.println("array length is " + arrayLength);
		HeapPriorityQueue thisHeap = new HeapPriorityQueue(keyArray, valueArray, HeapType.MIN);
		
		for(int i = 0; i < arrayLength; i++)
		{
			thisHeap.heap[i].toString();
		}
	}

}
