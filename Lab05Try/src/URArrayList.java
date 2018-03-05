import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;


public class URArrayList<E> implements URList<E> {

	private int size;
	private static int capacity = 10;
	private Object[] data;;

	public URArrayList() {
		data = new Object[capacity];
		size = 0;
	}
//works
	@Override
	public boolean add(Object e) {
		add(size, e);
		return true;
	}
//works
	@Override
	public void add(int index, Object element) {

		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		if (element == null) {
			throw new NullPointerException();
		}

		ensureCapacity(size);
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		data[index] = element;
		size++;

	}
//works
	@Override
	public boolean addAll(Collection c) {
		addAll(size, c);
		return true;
	}
//works
	@Override
	public boolean addAll(int index, Collection c) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		if (c == null) {
			throw new NullPointerException();
		}
		Object[] a=(Object[])c.toArray();
		ensureCapacity(size + a.length);
		if (index!=size-1){
		for (int j=index;j<size;j++){
			data[j+a.length]=data[j];
		}
		}
		for (int i=0;i<a.length;i++){
		data[index]=(a[i]);	
		index++;
		size++;
		}
		return false;
	}
//works
	@Override
	public void clear() {
		data = new Object[capacity];
		capacity = 10;
		size = 0;
	}
//works
	@Override
	public boolean contains(Object o) {
		for (int i = 0; i <= size; i++) {
			if (data[i] == o) {
				return true;
			}
		}
		return false;
	}
// works
	@Override
	public boolean containsAll(Collection c) {
		int counter=0;
		     for (Object e : c) {
		            for (int j=0;j<size;j++){
		            	if (e.equals(data[j])){
		            		counter++;
		            		break;
		            	}
		                
		            }		            
		     }
		     if (counter>=c.size()){
	            	return true;
	            }else{
		    return false;
	            }
		}
//works
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new RuntimeException("OUT OF BOUNDS");
		}
		return (E) data[index];

	}
//works
	@Override
	public int indexOf(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i <= size; i++) {
			if (data[i] == o) {
				return i;
			}
		}
		return -1;
	}
//works
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
//works
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new URArrayListIterator(data);
	}
		private class URArrayListIterator implements Iterator<E> {
		    private Object[] itr;
		    private int index = 0;
		    
			public URArrayListIterator(Object [] x ) {
				itr = x;
			}

			@Override
			public boolean hasNext() {
				 if(index < itr.length){
			            return true;
			        }else{
			            return false;
			        }
			}
			@Override
			public E next() {
				int x = index;
				index++;
				return (E) itr[x];
				}
			
		}
//works
	@Override
	public E remove(int index) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		E removed = (E) data[index];
		for (int i = index + 1; i < size; i++) {
			data[i - 1] = data[i];
		}
		size--;
		return removed;
	}
// works
	@Override
	public boolean remove(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		if (indexOf(o) == -1) {
			return false;
		}
		remove(indexOf(o));
		return true;
	}

	
	//works
	@Override
	public boolean removeAll(Collection c) {
		if (c == null) {
			throw new NullPointerException();
		}
		E[] a=(E[])c.toArray();
		if (!containsAll(c)) {
			return false;
		}else{
			for (int i=0;i<a.length;i++)
		remove(indexOf(a[i]));
		return true;
		}
		
	}
//works
	@Override
	public Object set(int index, Object element) {

		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		if (element == null) {
			throw new NullPointerException();
		}
		
		data[index] = element;

	
		return data;
	}
//works
	@Override
	public int size() {
		return size;
	}
//works
	@Override
	public URList subList(int fromIndex, int toIndex) {
		if (fromIndex<0||toIndex<0||fromIndex>(size()-1)||toIndex>(size()-1)){
			throw new IndexOutOfBoundsException();
		}
		URList<E> a = new URArrayList<E>();
		for (int i=fromIndex;i<toIndex;i++){
			a.add((E)data[i]);
		}
		return a;
	}

//works
@Override
	public Object[] toArray() {
		Object[] k = new Object [size];
		for (int i = 0; i< size; i++) {
			k[i] = data[i];
		}
		return k;
	}

	// Increases the capacity of this ArrayList instance, if necessary,
	// to ensure that it can hold at least the number of elements specified
	// by the minimum capacity argument.
	public void ensureCapacity(int minCapacity) {
		if (minCapacity >= capacity) {
			capacity = capacity * 2;
			Object tempData[] = new Object[capacity];
			// Copy old array into new array
			for (int i = 0; i < size; i++) {
				tempData[i] = data[i];
			}
			data = tempData;
		}
	}

	// Returns the current capacity of the list
	public int getCapacity() {
		return capacity;

	}

}