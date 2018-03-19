public class HeapPriorityQueue 
{
	//inner class: entry
		protected class Entry
		{
			
			//variables:
			private int key = 0;
			private float value = 0;
			
			//constructor method:
			protected Entry()
			{
				this.key = key;
				this.value = value;
			}
			
			protected Entry(int thisKey, float thisValue)
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
				return "key = " + this.getKey() + ", value = " + this.getValue() + ".";
			}
			
			
		}
	
	//variables:
	public Entry[] heap = new Entry[0];
	private HeapType heapType = HeapType.MIN;
	
	//constructor methods:
	public HeapPriorityQueue()
	{
		this.heap = heap;
		this.heapType = heapType;
	}
	
	public HeapPriorityQueue(int[] keyArray, float[] valueArray, HeapType thisHeapType)
	{
		this.heapType = thisHeapType;
		this.heap = new Entry[keyArray.length];
		for(int i = 0; i < this.heap.length; i++)
		{
			this.heap[i] = new Entry(keyArray[i], valueArray[i]);
		}
		heapify();
	}
	
	//printheap prints to screen the key and value elements of the heap, starting
	//from index 0 to the last index
	public void printHeap()
	{
		System.out.println("Key     Value");
		
		for(int i = 0; i <= this.getLastEntryIndex(); i++)
		{
			System.out.println(this.heap[i].getKey() + "     " + this.heap[i].getValue());
		}
		System.out.println();
	}
	
	//heapify sorts entire heap via downheap
	public void heapify()
	{
		int initialIndex = parentIndex(this.getLastEntryIndex());
		while(initialIndex >= 0)
		{
			downheap(initialIndex);
			initialIndex--;
		}
	}
	
	//increaseHeapSize creates a new heap that has length 2* prior heap's length,
	//the second half empty.
	public void increaseHeapSize(HeapPriorityQueue oldHeap)
	{
		HeapPriorityQueue temp = new HeapPriorityQueue();
		temp.heap = new Entry[2*(oldHeap.heap.length)];
		temp.heapType = oldHeap.heapType;
		//enter current heap values in new heap
		for(int i = 0; i < oldHeap.heap.length; i++)
		{	
			int tempKey = oldHeap.heap[i].getKey();
			float tempValue = oldHeap.heap[i].getValue();
			temp.heap[i] = new Entry(tempKey, tempValue);
		}
		//initialize remainder of temp heap with default entry variables
		for(int i =oldHeap.heap.length; i < temp.heap.length; i++)
		{
			temp.heap[i] = new Entry();
		}
		//redefine current heap to be newly constructed heap
		oldHeap.heap = temp.heap;
	}
	
	//isFull returns true if the last entry in the heap is not null, false otherwise
	public boolean isFull()
	{
		if(this.heap[heap.length-1] != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//getLastEntryIndex returns the index of the last non-null entry in a partially
	//filled heap, going backwards from the final entry
	public int getLastEntryIndex()
	{
		int i = this.heap.length - 1;
		//System.out.println("In getLastEntryIndex method, i is " + i);
		while((this.heap[i].getKey() == 0) && (this.heap[i].getValue() == 0))
		{
		//	System.out.println("In while loop of getLastEntryIndex, i is " + i + ", heap[i].getKey() is " 
		//+ this.heap[i].getKey() + ", and heap[i].getValue is() " + heap[i].getValue());
			i--;
		}
		return (i);
	}
	
	//parentIndex method returns index of parent of entry at index "index"
	public int parentIndex(int index)
	{
		return (index-1)/2;
	}
	
	//leftChildIndex method returns index of left child of entry at index "index"
	public int leftChildIndex(int index)
	{
		return 2*index + 1;
	}
	
	//rightChildIndex method returns index of right child of entry at index "index"
	public int rightChildIndex(int index)
	{
		return 2*index + 2;
	}
	
	//hasLeftChild returns true if the entry at index "index" has a left child, 
	//false otherwise
	public boolean hasLeftChild(int index)
	{
		
		if(this.leftChildIndex(index) > (this.getLastEntryIndex()) || heap.length == 1)
		{
			return false;
		}
		else if(this.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	//hasRightChild returns true if the entry at index "index" has a right child,
	//false otherwise
	public boolean hasRightChild(int index)
	{	
		
		if(this.rightChildIndex(index) > (this.getLastEntryIndex()) || this.heap.length == 1)
		{	
			return false;
		}
		else if(this.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	
	//swap exchanges entries at indices i and j
	public void swap(int i, int j)
	{
		Entry temp = new Entry(this.heap[i].getKey(), this.heap[i].getValue());
		this.heap[i].setKey(this.heap[j].getKey());
		this.heap[i].setValue(this.heap[j].getValue());
		this.heap[j].setKey(temp.getKey());
		this.heap[j].setValue(temp.getValue());
	}
	
	//upheap swaps entry at index "index" higher up the branch as needed 
	//until heap order is restored.
	public void upheap(int index)
	{
		while(index > 0)
		{
			int indexKey = this.heap[index].getKey();
			int p = parentIndex(index);
			int pKey = this.heap[parentIndex(index)].getKey();
			if(indexKey == pKey)
			{
				break;
			}
			switch (this.heapType)
			{
				case MIN:
				{	
					if(indexKey < pKey)
					{
						swap(index, p);
						index = p;
					}
					break;
				}
				
				case MAX:
				{
					if(indexKey > pKey)
					{
						swap(index, p);
						index = p;
					}
					break;
				}
			}
		}
		
	}
	
	//downheap swaps entry at index "index," starting at the root, 
	//with the highest (if maxheap) or lowest (if minheap)-keyed child 
	//(as needed) until heap order is restored.
	public void downheap(int index)
	{
		while(hasLeftChild(index))
		{	
			int leftIndex = leftChildIndex(index);
			int childToSwapIndex = leftIndex;
			if(hasRightChild(index))
			{
				int rightIndex = rightChildIndex(index);
				//if minheap and right child has a lower key than left child, 
				//right child becomes child to swap
				if(this.heapType == HeapType.MIN && heap[leftIndex].getKey() > heap[rightIndex].getKey())
				{
					childToSwapIndex = rightIndex;
				}
				//if maxheap and right child has a higher key than left child, 
				//right child becomes child to swap
				if(this.heapType == HeapType.MAX && heap[leftIndex].getKey() < heap[rightIndex].getKey())
				{
					childToSwapIndex = rightIndex;
				}
			}
			//if the child to swap has an equal or higher(if minheap) or lower (if maxheap)
			//value to the entry at index "index," exit while loop.
			if((this.heapType == HeapType.MIN && heap[index].getKey() <= heap[childToSwapIndex].getKey())
				|| (this.heapType == HeapType.MAX && heap[index].getKey() >= heap[childToSwapIndex].getKey()))
			{
				break;
			}
			//otherwise, swap entry at index "index" with child to swap
			swap(childToSwapIndex, index);
			index = childToSwapIndex;
				
			}
		}
	
	//***methods specified by assignment
	
	//remove() returns key & value of last (highest key if minheap, highest key if
	//maxheap) entry in heap, deletes entry form heap, and restores heap order.
	
	public Entry remove()
	{
		Entry returnValue = new Entry(heap[0].getKey(), heap[0].getValue());
		//swap root of heap with last entry
		swap(0, this.getLastEntryIndex());
		//delete last Entry;
		heap[this.getLastEntryIndex()].setKey(0);
		heap[this.getLastEntryIndex()].setValue(0);
		//restore heap order using downheap method
		downheap(0);
		return returnValue;
	}
	
	//insert(k,v) inserts an Entry of specified key and value and restores
	//heap order via upheap:
	public void insert(int k, float v)
	{
		Entry newEntry = new Entry(k,v);
		//increase size of array if array is full
		if (this.isFull())
		{
			increaseHeapSize(this);
		}
		int insertIndex = (this.getLastEntryIndex() + 1);
		System.out.println("In insert method, insertIndex is " + insertIndex);
		this.heap[insertIndex].setKey(newEntry.getKey());
		this.heap[insertIndex].setValue(newEntry.getValue());
		System.out.println("In Insert method, after setting new entry's values, key is " + this.heap[insertIndex].getKey()
				+ " and value is " + this.heap[insertIndex].getValue()); 
		//restore heap order via upheap
		upheap(insertIndex);
	}
	

	//top() returns key & value of of last (highest key if minheap, highest key if
	//maxheap) entry in heap, without removing entry
	public Entry top()
	{
		return heap[0];
	}
	
	//toggle switches minheap to maxheap and vice versa
	public void toggle()
	{
		if (this.heapType == HeapType.MAX)
		{
			switchToMin();
		}
		if (this.heapType == HeapType.MIN)
		{
			switchToMax();
		}
	}
	
	//switchToMin switches maxheap to minheap, otherwise does nothing
	public void switchToMin()
	{
		if (this.heapType == HeapType.MAX)
		{
			heapify();
			this.heapType = HeapType.MIN;
		}
	}
	
	//switchtoMax switches minheap to maxheap, otherwise does nothing
	public void switchToMax()
	{
		if (this.heapType == HeapType.MIN)
		{
			heapify();
			this.heapType = HeapType.MAX;
		}
	}
	
	//state returns enum type (min or max) of heap
	public HeapType state()
	{
		return this.heapType;
	}
	
	//isEmpty returns true if heap is empty, false otherwise
	public boolean isEmpty()
	{
		if(this == null || this.heap[0] == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//size returns number of Entries in heap
	public int size()
	{
		if(this.isFull())
		{
			return (this.heap.length);
		}
		else
		{
			return (this.getLastEntryIndex() + 1);
		}
	}
	
}
