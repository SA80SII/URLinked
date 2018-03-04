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

	@Override
	public boolean add(Object e) {
		add(size, e);
		return true;
	}

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

	@Override
	public boolean addAll(Collection c) {
		addAll(size, c);
		return true;
	}

	@Override
	public boolean addAll(int index, Collection c) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		if (c == null) {
			throw new NullPointerException();
		}

		ensureCapacity(size + c.size());
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		data[index] = c;
		size++;
		return false;
	}

	@Override
	public void clear() {
		data = new Object[capacity];
		capacity = 10;
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i <= size; i++) {
			if (data[i] == o) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		     for (Object e : c)
		            if (!contains(e))
		                return false;
		    return true;
		}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new RuntimeException("OUT OF BOUNDS");
		}
		return (E) data[index];
	}

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

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
	public boolean removeAll(Collection c) {
		if (c == null) {
			throw new NullPointerException();
		}
		if (!containsAll(c)) {
			return false;
		}
		remove(indexOf(c));
		return true;
	}

	@Override
	public Object set(int index, Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public URList subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

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