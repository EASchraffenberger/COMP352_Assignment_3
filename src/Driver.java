import java.util.Random;
public class Driver {
	
	//createKeyArray creates an array of key values for each entry in the heap
	public static void createEntryVariableArrays(int[] keyArray, float[] valueArray, int arrayLength, Random randomGenerator)
	{
		for(int i = 0; i < arrayLength; i++)
		{
			keyArray[i] = randomGenerator.nextInt(10);
			valueArray[i] = (float)(randomGenerator.nextInt(9) + randomGenerator.nextDouble() + randomGenerator.nextDouble()/10);
		}
	}
	
	public static void priorityHeapMethodDemo(int[] keyArray, float[] valueArray, HeapPriorityQueue thisHeap)
	{
		System.out.println("Key and value arrays before heapifying:");
		System.out.println("Key     Value");
		for(int i = 0; i <keyArray.length; i++)
		{
			System.out.println(keyArray[i] + "     " + valueArray[i]);
		}
		System.out.println();
		System.out.println("Size of heap: " + thisHeap.size());
		System.out.println("Heap in minHeap order:");
		System.out.println("Key     Value");
		thisHeap.printHeap();
		System.out.println("Now swapping heaptype:");
		thisHeap.toggle();
		System.out.println("Now re-heapifying with heapType " + thisHeap.state());
		thisHeap.heapify();
		thisHeap.printHeap();
	}
	
	
	
	public static void main(String[] args) {
		
		Random randomGenerator = new Random();
		
		int arrayLength = randomGenerator.nextInt(15);
		
		int[] keyArray = new int[arrayLength];
		float[] valueArray = new float[arrayLength];
		createEntryVariableArrays(keyArray, valueArray, arrayLength, randomGenerator);
		HeapPriorityQueue thisHeap = new HeapPriorityQueue(keyArray, valueArray, HeapType.MIN);
		priorityHeapMethodDemo(keyArray, valueArray, thisHeap);
		
		
		
	}

}


