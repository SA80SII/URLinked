import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;


public class URArrayList<E> implements URList<E> {
//class variables defined
	private int size;
	private static int capacity = 10;
	//array data is modified to fulfill arraylist specs
	private Object[] data;;

	public URArrayList() {
		//begins with size 0 capacity 10
		data = new Object[capacity];
		size = 0;
	}
//adds an object by calling method add(index,object)
	@Override
	
	public boolean add(Object e) {
		add(size, e);
		//return assumes object calling this method exists
		return true;
	}
//
	@Override
	//adds an object at a specified index
	public void add(int index, Object element) {
		//throws OOB exceptions and null pointer if element is null, using parameters
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		if (element == null) {
			throw new NullPointerException();
		}
//calls ensurecapacity
		ensureCapacity(size);
		//for loop iterates through remaining characters to allow a new memeber within
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		data[index] = element;
		size++;

	}
//
	@Override
	public boolean addAll(Collection c) {
		//uses addAll method below (index and collection constructor)
		addAll(size, c);
		//return assumes object calling this method exists
		return true;
	}
//
	@Override
	//adds a collection at specified location within AL
	public boolean addAll(int index, Collection c) {
		//throws nullpointer and OOB exceptions using parameters
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		if (c == null) {
			throw new NullPointerException();
		}
		//parses collection to array
		Object[] a=(Object[])c.toArray();
		//calls ensureCapacity of the combined sizes
		ensureCapacity(size + a.length);
		//
		if (index!=size-1){
			//moves through required array indexes and superimposes to after the end of the collection will be
		for (int j=index;j<size;j++){
			data[j+a.length]=data[j];
		}
		}
		//adds data from array
		for (int i=0;i<a.length;i++){
		data[index]=(a[i]);	
		index++;
		size++;
		}
		//return assumes object calling this method exists
		return false;
	}
//
	@Override
	//replaces AL with new array with same initial properties as original arraylist
	public void clear() {
		data = new Object[capacity];
		capacity = 10;
		size = 0;
	}
//
	@Override
	//checks if AL contains a specified object
	public boolean contains(Object o) {
		for (int i = 0; i <= size; i++) {
			if (data[i] == o) {
				//returns true if an instance of specified object is found
				return true;
			}
		}
		//return assumes object calling this method exists
		return false;
	}
// 
	@Override
	//checks if an instance of each member of a collection is called
	public boolean containsAll(Collection c) {
		int counter=0;
		//checks for each element of collection if an instance exists within AL
		     for (Object e : c) {
		            for (int j=0;j<size;j++){
		            	if (e.equals(data[j])){
		            		//counter++ for each time a correct match is found
		            		counter++;
		            		//moves on to next value if instance is found
		            		break;
		            	}
		                
		            }		            
		     }
		     //when the size of c and number of found values from c within AL are equivalent (or dubiously greater), returns true
		     if (counter>=c.size()){
	            	return true;
	            }else{
	            	//return assumes object calling this method exists
		    return false;
	            }
		}
//
	@Override
	//returns value at a specified index
	public E get(int index) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		if (data[index] == null) {
			throw new NullPointerException();
		}
		//throws exceptions if data is not type E, if nullpointer at index, or if index out of bounds
		//return assumes object calling this method exists
		return (E) data[index];

	}
//
	@Override
	//Finds index of an object
	public int indexOf(Object o) {
		//if object is null, throws exception
		if (o == null) {
			throw new NullPointerException();
		}
		//if object is found, index is returned
		for (int i = 0; i <= size; i++) {
			if (data[i] == o) {
				return i;
			}
		}
		//if object is unfound, returns -1
		return -1;
	}
//
	@Override
	public boolean isEmpty() {
		//returns true for AL being empty, whenever size = 0l
		//return assumes object calling this method exists
		return size == 0;
	}
//
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new URArrayListIterator(data);
	}
	//creates specific iterator class
		private class URArrayListIterator implements Iterator<E> {
		    private Object[] itr;
		    private int index = 0;
		    
		    //Sets up object for use
			public URArrayListIterator(Object [] x ) {
				itr = x;
			}

			@Override
			public boolean hasNext() {
//hasnext method returns true or false based off of length comparison between index of next and length
				 if(index < itr.length){
			            return true;
			        }else{
			            return false;
			        }
				//return assumes object calling this method exists
			}
			@Override
			//next iterates by increasing the index. returns element
			public E next() {
				int x = index;
				index++;
				//return assumes object calling this method exists
				return (E) itr[x];
				}
			
		}
//
	@Override
	//removes element of AL at index
	public E remove(int index) {
		//throws nullpointer and OOB exceptions using parameters
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		//if data at index is null, throws nullpointer
		if (data[index] == null) {
			throw new NullPointerException();
		}
		//parses removed variable, to be returned
		E removed = (E) data[index];
		//modifies to fill the gap where index was removed
		for (int i = index + 1; i < size; i++) {
			data[i - 1] = data[i];
		}
		size--;
		return removed;
	}
// 
	@Override
	//removes object o at its first occurance
	public boolean remove(Object o) {
		//throws its own null exception
		if (o == null) {
			throw new NullPointerException();
		}
		//throws OOB exception, immediately. Else, calls other constructor of remove
		if (indexOf(o) == -1) {
			return false;
		}
		remove(indexOf(o));
		return true;
	}

	
	//
	@Override
	//removes first instance of each value within a given collection
	public boolean removeAll(Collection c) {
		//throws nullpoint exceptions if c is null
		if (c == null) {
			throw new NullPointerException();
		}
		//parses to array, throws exceptions if type mismatch
		E[] a=(E[])c.toArray();
		//if containsall is true for c, returns false
		if (!containsAll(c)) {
			return false;
		}else{//otherwise removes each individual number, per below
			for (int i=0;i<a.length;i++)
				//removes first instance of number using indexOf method
		remove(indexOf(a[i]));
			//return assumes object calling this method exists
		return true;
		}
		
	}
//
	@Override
	//sets element at given index 
	public Object set(int index, Object element) {
//throws OOB and null pointer exceptions by default
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		if (element == null) {
			throw new NullPointerException();
		}
		//val at index is set equal to element
		data[index] = element;
		//return assumes object calling this method exists
		return data;
	}
//
	@Override
	
	public int size() {
		return size;
	}
//
	@Override
	//creates sublist of type ArrayList from index(inclusive) to index(exclusive)
	public URList subList(int fromIndex, int toIndex) {
		//throws proper OOB exceptions
		if (fromIndex<0||toIndex<0||fromIndex>(size()-1)||toIndex>(size()-1)){
			throw new IndexOutOfBoundsException();
		}
		//creates new arraylist
		URList<E> a = new URArrayList<E>();
		for (int i=fromIndex;i<toIndex;i++){
			//adds tokens using cast, causing automatic mismatch exceptions to be thrown
			a.add((E)data[i]);
		}
		//returns created arraylist
		//return assumes object calling this method exists
		return a;
	}

//
@Override
//creates an array with all of the same data, excluding nulls, using for loop iteration
	public Object[] toArray() {
		Object[] k = new Object [size];
		for (int i = 0; i< size; i++) {
			k[i] = data[i];
		}
		//return assumes object calling this method exists
		return k;
	}

	// Increases the capacity of this ArrayList instance, if necessary,
	// to ensure that it can hold at least the number of elements specified
	// by the minimum capacity argument.
	public void ensureCapacity(int minCapacity) {
		if (minCapacity >= capacity) {
			//doubles capacity if exceeded by new mincapacity
			capacity = capacity * 2;
			Object tempData[] = new Object[capacity];
			// Copy old array into new array
			for (int i = 0; i < size; i++) {
				tempData[i] = data[i];
				//copies data using iterative for loop
			}
			data = tempData;
		}
	}

	// Returns the current capacity of the list
	public int getCapacity() {
		//returns capacity value.
		return capacity;

	}

}