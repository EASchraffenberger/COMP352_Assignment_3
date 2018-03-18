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
	
	public static void priorityHeapMethodDemo(int[] keyArray, float[] valueArray, HeapPriorityQueue thisHeap, Random randomGenerator)
	{
		System.out.println("Key and value arrays before heapifying:");
		System.out.println("Key     Value");
		for(int i = 0; i <keyArray.length; i++)
		{
			System.out.println(keyArray[i] + "     " + valueArray[i]);
		}
		System.out.println();
		System.out.println("Testing required method: isEmpty()");
		System.out.println("Is heap empty? " + thisHeap.isEmpty());
		System.out.println("Testing required method: size(): ");
		System.out.println("Size of heap: " + thisHeap.size());
		System.out.println("Heap in minHeap order:");
		thisHeap.printHeap();
		System.out.println("Testing required method: top()");
		System.out.println("Top of heap is " + thisHeap.top());
		System.out.println("Testing required methods: toggle() and switchToMax():");
		System.out.println("Now swapping heaptype:");
		thisHeap.toggle();
		System.out.println("Testing required method: state()");
		System.out.println("Now re-heapifying with heapType " + thisHeap.state());
		thisHeap.heapify();
		thisHeap.printHeap();
		System.out.println("Now testing required method: insert()");
		int insertKey = randomGenerator.nextInt(15);
		float insertValue = (float)(randomGenerator.nextInt(9) + randomGenerator.nextDouble() + randomGenerator.nextDouble()/10);
		System.out.println("Inserting using key " + insertKey + " and value " + insertValue);
		thisHeap.insert(insertKey, insertValue);
		thisHeap.printHeap();
		System.out.println("Testing required method: switchToMin():");
		thisHeap.switchToMin();
		System.out.println("Now re-heapifying with heapType " + thisHeap.state());
		thisHeap.heapify();
		thisHeap.printHeap();
		System.out.println("Now testing required method: Remove()");
		thisHeap.remove();
		thisHeap.printHeap();
	}
	
	
	
	public static void main(String[] args) {
		
		Random randomGenerator = new Random();
		
		int arrayLength = randomGenerator.nextInt(15);
		
		int[] keyArray = new int[arrayLength];
		float[] valueArray = new float[arrayLength];
		createEntryVariableArrays(keyArray, valueArray, arrayLength, randomGenerator);
		HeapPriorityQueue thisHeap = new HeapPriorityQueue(keyArray, valueArray, HeapType.MIN);
		for(int i = 0; i < 10; i++)
		{
			
		System.out.println("***NOW RUNNING TEST #" + i + "***");
		priorityHeapMethodDemo(keyArray, valueArray, thisHeap, randomGenerator);
		}
		
		
	}

}


