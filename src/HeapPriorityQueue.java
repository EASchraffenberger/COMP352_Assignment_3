
public class HeapPriorityQueue {
	
	//variables:
	private Entry[] heap = new Entry[0];
	private HeapType heapType = HeapType.MIN;
	
	//constructor methods:
	public HeapPriorityQueue()
	{
		this.heap = heap;
		this.heapType = heapType;
	}
	
	public HeapPriorityQueue(int[] keyArray, float[]valueArray, HeapType thisHeapType)
	{
		this.heapType = thisHeapType;
		for(int index = 0; index < Math.min(keyArray.length, valueArray.length); index++)
		{
			heap[index] = new Entry(keyArray[index], valueArray[index]);
		}
		heapify();
	}
	
	//heapify sorts entire heap via downheap
	public void heapify()
	{
		int initialIndex = parentIndex(heap.length-1);
		for(int i = initialIndex; i >= 0; i--)
		{
			downheap(i);
		}
	}
	
	//increaseHeapSize creates a new heap that has length 2* prior heap's length,
	//the second half empty.
	public void increaseHeapSize(Entry[] oldHeap)
	{
		Entry[] temp = new Entry[2*oldHeap.length];
		//enter current heap values in new heap
		for(int i = 0; i <= oldHeap.length; i++)
		{
			temp[i] = oldHeap[i];
		}
		//redefine current heap to be newly constructed heap
		oldHeap = temp;
	}
	
	//parentIndex method returns index of parent of entry at index "index"
	public int parentIndex(int index)
	{
		return (index-1)*2;
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
		return ((heap[leftChildIndex(index)] != null) && (leftChildIndex(index) < heap.length));
	}
	
	//hasRightChild returns true if the entry at index "index" has a right child,
	//false otherwise
	public boolean hasRightChild(int index)
	{	
		return ((heap[rightChildIndex(index)] != null) && (rightChildIndex(index) < heap.length));
	}
	
	//swap exchanges entries at indices i and j
	public void swap(int i, int j)
	{
		Entry temp = new Entry(heap[i].getKey(), heap[i].getValue());
		heap[i].setKey(heap[j].getKey());
		heap[i].setValue(heap[j].getValue());
		heap[j].setKey(temp.getKey());
		heap[j].setValue(temp.getValue());
	}
	
	//upheap swaps entry at index "index" higher up the branch as needed 
	//until heap order is restored.
	public void upheap(int index)
	{
		while(index > 0)
		{
			int p = parentIndex(index);
			if(heap[index].getKey() == heap[p].getKey())
			{
				break;
			}
			switch (this.heapType)
			{
				case MIN:
				{	
					if(heap[index].getKey() < heap[p].getKey())
					{
						swap(index, p);
						index = p;
					}
					break;
				}
				
				case MAX:
				{
					if(heap[index].getKey() > heap[p].getKey())
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
	}
	
	//***methods specified by assignment
	
	//remove() returns key & value of last (highest key if minheap, highest key if
	//maxheap) entry in heap, deletes entry form heap, and restores heap order.
	
	public Entry remove()
	{
		Entry returnValue = new Entry(heap[0].getKey(), heap[0].getValue());
		//swap root of heap with last entry
		swap(0, heap.length-1);
		//delete last Entry;
		heap[heap.length - 1] = null;
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
		if (heap[heap.length-1] != null)
		{
			increaseHeapSize(this.heap);
		}
		heap[heap.length] = newEntry;
		//restore heap order via upheap
		upheap(heap.length);
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
		return (this.size() == 0);
	}
	
	//size returns number of Entries in heap
	public int size()
	{
		int i =0;
		while(heap[i] != null)
		{
			i++;
		}
		return i;
	}
}
