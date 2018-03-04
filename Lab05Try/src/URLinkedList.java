import java.util.Collection;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class URLinkedList<E> implements URList<E>{
	 transient URNode<E> head;
	 transient URNode<E> tail;
	 transient int size = 0;
	 
	 
	@Override
	public boolean add(E e) {
		addLast(e);
		return true;	
	}

	@Override

	public void add(int index, E element) {

		URNode<E> first = new URNode<E>(null,null,null);
		URNode<E> secn = new URNode<E>(null,null,null);
		URNode<E> thir = new URNode<E>(null,null,null);
		int c=0;
		if (index!=0&&(index<0||index>=size())){
			c++;
			throw new IndexOutOfBoundsException();		
		}
		if (index==0){
			first=head;
			secn=new URNode<E>(element,null,first);
			first.setPrev(secn);
			head=secn;
		}
		
		else if (index==(size()-1)){
			first=tail.prev();
			secn=new URNode<E>(element,first,tail);
			first.setNext(secn);
			tail.setPrev(secn);
		}
		else {
			first=head;
			int counter=0;
			URLinkedListIterator i = new URLinkedListIterator();
			first=head;
			while (i.hasNext()==true && counter<index){
				counter++;
				first = first.next();
			}
			
			secn=first.prev();
			thir=new URNode<E>(null,null);
			thir.setElement(element);
			thir.setPrev(secn);
			thir.setNext(first);
			first.setPrev(thir);
			secn.setNext(thir);
	
		first.setElement(element);
		}
		size+=1-c;
		
	}
	

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object o) {
		int counter=0;
		URNode<E> one = head;
		while (one!=tail){
			
			one=one.next();
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) {
	if(head== null) {
		throw new NoSuchElementException();
	}
	URNode<E> curr = head;
	if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException();
	}
	else {
		int i = 0;
		while(i != index) {
			curr = curr.next();
			i++;
		}
		return curr.element();		
	}
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		return new URLinkedListIterator();
	}
	
	private class URLinkedListIterator implements Iterator<E> {
		URNode<E> curr;
		public URLinkedListIterator() {
			curr = head;
		}

		@Override
		public boolean hasNext() {
			if(curr.next() != null){
				return true;
			}
		return false;
		}

		@Override
		public E next() {
			if(hasNext()){
				curr = curr.next();
			}
			return curr.element();
		}
		
	}

	@Override
	public E remove(int index) {
		URNode<E> curr = head;
		URNode<E> place;
		int i = 0;
		while(i <= index-1) {
			curr = curr.next();
			i++;
		}
		if (i==0){
			if (head==tail){
				curr=new URNode<E> (null,null,null);
			}
			else{
			place=curr.next();
			place.setPrev(null);
			head=place;
			}
		}
		else{
			place=curr.next();
			place.setPrev(curr.prev());
			curr.prev().setNext(place);
			
		}
		size--;
		return curr.element();
	}

	@Override
	public boolean remove(Object o) {
		remove(o);
		return true;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object set(int index, Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public URList subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		Object [] array = new Object[size];
		URNode<E> f = head;
		for(int i = 0; i< size; i++) {
			array[i] = f.element();
			f = f.next();
		}
		return array;
	}
	// In
	// Inserts the specified element at the beginning of this list.
	public void addFirst(E e) {
		final URNode<E> f = head;
		final URNode<E> newNode = new URNode<E>(e,null,f);
		head = newNode;
		if (head==null) {
			head=tail = newNode;
		}
		else {
			head.setPrev(newNode);
		}
		size++;
		
	}
	// Appends the specified element to the end of this list.
	public void addLast(E e) {
		URNode<E> newNode = new URNode<E>(e,tail,null);
		if (head == null) {
			head = newNode;	
			tail = head;
		}
		else {
		tail.setNext(newNode);
		tail = newNode;
		}
		size++;
	}
	
	// Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
	public  E  peekFirst() {
		if(head== null) {
			throw new NoSuchElementException();
		}
		URNode<E> curr = head;
		return curr.element();

		}
	// Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
	public E peekLast() {
		if(tail== null) {
			throw new NoSuchElementException();
		}
		URNode<E> curr = tail;
		return curr.element();	
	}
	// Retrieves and removes the first element of this list, or returns null if this list is empty.
	public E pollFirst() {
		if(head==null) {
			throw new NoSuchElementException();
		}
		URNode<E> curr = head;
		if (tail!=curr){
			head=curr.next();
			}
		size--;
		return curr.element();
		
	}
	// Retrieves and removes the last element of this list, or returns null if this list is empty.
	public E pollLast() {
		if(tail==null) {
			throw new NoSuchElementException();
		}
		URNode<E> curr = tail;
		if (head!=curr){
		tail=curr.prev();
		}
		size--;
		return curr.element();
		
		
	}

}

