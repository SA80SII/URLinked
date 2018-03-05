import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class URLinkedList<E> implements URList<E>{
	 transient URNode<E> head;
	 transient URNode<E> tail;
	 transient int size = 0;
	 
	 
	@Override
	//Return assumes that the object calling "add" method exists
	public boolean add(E e) {
		//calls internally the addLast method
		addLast(e);
		return true;
		
	}

	@Override

	public void add(int index, E element) {
		//tracks placement using three nodes
		URNode<E> first = new URNode<E>(null,null,null);
		URNode<E> secn = new URNode<E>(null,null,null);
		URNode<E> thir = new URNode<E>(null,null,null);
		//counter intialized
		int c=0;
		//exceptions if pointing to null or using an OOB index
		if (element == null) {
			c++;
			throw new NullPointerException();
			
		}
		if (index!=0&&(index<0||index>=size())){
			c++;
			throw new IndexOutOfBoundsException();
			
		}
		//sets new head node if index=0
		if (index==0){
			first=head;
			secn=new URNode<E>(element,null,first);
			first.setPrev(secn);
			head=secn;
		}
		
		//sets new tail node if index corresponds to tail
		else if (index==(size()-1)){
			first=tail.prev();
			secn=new URNode<E>(element,first,tail);
			first.setNext(secn);
			tail.setPrev(secn);
		}
		//sets new doubly linked node by first iterating up to middle node and then adding a new node
		//taken order is node at index specified becomes "index+1"
		else {
			first=head;
			int counter=0;
			URLinkedListIterator i = new URLinkedListIterator();
			first=head;
			while (i.hasNext()==true && counter<index){
				counter++;
				first = first.next();
			}
			//reorders and sets parameters
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
		//throws nullpointer if c is null
		if (c == null) {
			throw new NullPointerException();
		}
	//creates an array of elements in c, and automatically throws exceptions if elements are incompatible
		E[] a=(E[])c.toArray();
		//creates node
		URNode<E> curr = tail;
		for (int i=0;i<a.length;i++){
			//creates new urnode to parse in. adds
			URNode<E> next=new URNode<E>(a[i],curr,null);
			size++;
			curr.setNext(next);
			curr=curr.next();
		}
		tail=curr;
		//returns true so long as no exceptions are thrown, assumes object called upon exists
		return true;
	}

	@Override
	//adds collection at an index, moving existing element at index to the index directly antecedent to collection
	public boolean addAll(int index, Collection c) {
		//throws exceptions when null pointers/indexOOB
		if (c == null) {
			throw new NullPointerException();
		}
		if (index!=0&&(index<0||index>=size())){
			throw new IndexOutOfBoundsException();		
		}
		//parses collection to array, throwing automatic exceptions when incompatible members exist
		E[] a=(E[])c.toArray();
		//creates current node, and moves up indexes
		URNode<E> curr = head;
		for (int j=0;j<index;j++){
			curr=curr.next();
		}
		//adds remainder of the list afterward (only requires one linkage) by using placeholder curr.next
		URNode<E> addlast = curr.next();
		for (int i=0;i<a.length;i++){
			//for loop for adding collection vals
			URNode<E> next=new URNode<E>(a[i],curr,null);
			size++;
			curr.setNext(next);
			curr=curr.next();
		}
		//recompletes the list
		curr.setNext(addlast);
		//return assumes the object caleld upon exists
		return true;
	}

	@Override
	public void clear() {
		//sets head to null and to equal tail, so that garbage collector will remove remaining data
		URNode<E> curr = head;
		curr.setNext(null);
		curr=tail;
		curr.setElement(null);
		size=0;
	}

	@Override
	public boolean contains(Object o) {
		//checks head to tail for a value (while statement), returns false if value not found. return assumes object called by exists
		int counter=0;
		URNode<E> one = head;
		while (one!=tail){
			
			one=one.next();
			if (one.element()==o){
				return true;
			}
		}
		return false;
	}

	@Override
	//checks to see if at least one instance of every element in c is contained by object called by.
	public boolean containsAll(Collection c) {
		//throws null pointer exceptions
		if (c == null) {
			throw new NullPointerException();
		}
		E[] a=(E[])c.toArray();
		URNode<E> curr = head;
		int counter=0;
		for (int i=0;i<(a.length);i++){
			curr=head;
			//moves through the calling linkedlist once for each element
			for (int j = 0;j<size;j++){
				if (a[i].equals(curr.element())){
					counter++;
					//breaks when one instance of each is found, else finishes
					break;
				}
				curr=curr.next();
			}
			
		}
		//if counter is the same as the size of c, the whole of c is contained
		if (a.length==counter){
			return true;
		}else{
		return false;}
	}

	@Override
	//retrieves element at an index
	public E get(int index) {
		//if the object calling get() is null, there are no elements in the LL, throws nosuchelement exception
	if(head== null) {
		throw new NoSuchElementException();
	}
	URNode<E> curr = head;
	//throws exceptions for OOB index
	if (index < 0 || index >= size) {
		throw new IndexOutOfBoundsException();
	}
	else {
		int i = 0;
		while(i != index) {
			curr = curr.next();
			i++;
		}
		//return assumes object calling this method exists
		//returns element at index specified
		return curr.element();		
	}
	}

	@Override
	//finds index of first instance of a given object
	public int indexOf(Object o) {
		//throws exception if parameter is null
		if (o == null) {
			throw new NullPointerException();
		}
		//sets a head node
		URNode<E> node = head;
		//moves through entire LL until object is found, returns if object is found (checks using comparison ==)
		for (int i=0;i<size;i++){
			if (node.element().equals(o)){
				return i;
			}
			node=node.next();
		}
		//returns -1 if there is no index that has this value
		//return assumes object calling this method exists
		return -1;
			
		}

	@Override
	//Checks to see if size is 0, indicating emptiness
	//returns true if 0, false otherwise
	//return assumes object calling this method exists
	public boolean isEmpty() {
		if (size()==0){
			return true;
		}else{	
		return false;
	}
	}

	@Override
	public Iterator iterator() {
		//returns new iterator, 	//return assumes object calling this method exists
		return new URLinkedListIterator();
	}
	
	private class URLinkedListIterator implements Iterator<E> {
		URNode<E> curr;
		//uses current node, sets as head
		public URLinkedListIterator() {
			curr = head;
		}

		@Override
		//hasnext boolean checks for null, true if next is not null
		public boolean hasNext() {
			if(curr.next() != null){
				return true;
			}else{
		return false;
		}}

		@Override
		//if hasnext is true, iterates
		public E next() {
			if(hasNext()){
				curr = curr.next();
			}
			//return assumes object calling this method exists
			return curr.element();
		}
		
	}

	@Override
	//removes value at an index
	public E remove(int index) {
		//throws bounds exceptions
		if (index!=0&&(index<0||index>=size())){
			throw new IndexOutOfBoundsException();		
		}
		//uses a node to hold place variable and iterate using current variable
		URNode<E> curr = head;
		URNode<E> place;
		int i = 0;
		//iterates through index
		while(i <= index-1) {
			curr = curr.next();
			i++;
		}
		if (i==0){
			//if size=1, changes first to null
			if (head==tail){
				curr=new URNode<E> (null,null,null);
			
			}
			else{
				//for head condition, sets head.next as new head
			place=curr.next();
			place.setPrev(null);
			head=place;
			}
			//if specifies tail, sets new tail, sets current tail to null
		}else if(curr==tail){
			tail=curr.prev();
			curr=new URNode<E> (null,null,null);
		}
		else{
			place=curr.next();
			place.setPrev(curr.prev());
			curr.prev().setNext(place);
			
		}
		//decreases size
		//return assumes object calling this method exists
		size--;
		return curr.element();
	}

	@Override
	public boolean remove(Object o) {
		//removes first instance of obj from list
		//throws nullpointer if object specified is null
		if (o == null) {
			throw new NullPointerException();
		}
		//uses front and back node to join ends after removing a member
		URNode<E> node = head;
		URNode<E> front;
		URNode<E> back;
		
		int i=0;
		//uses counter
		for (int j=0;j<size;j++){
			if (node.equals(o)){
				//iterates through, checks if current node that equals object is head or tail, uses same characteristics as remove (above) to rejoin
				if (node==tail){
					front=node.prev();
					front.setNext(null);
					tail=front;	
				}else if (node==head){
					front=node.next();
					front.setPrev(null);
					head=front;
				}else{
					front=node.prev();
					back=node.next();
					front.setNext(back);
					back.setPrev(front);
				}
				//decreases size
				size--;
				i++;
				//breaks once return is applicable
				break;
				
			}
			node=node.next();
		}
		//i=1 if o is found (prev if(node==tail) statement)
		if (i==0){
	 return false;}
		else{
			return true;}
		//return assumes object calling this method exists
	}

	@Override
	//removes one instance of every element in collection
	public boolean removeAll(Collection c) {
		//throws exception if collection is null
		if (c == null) {
			throw new NullPointerException();
		}
		//checks if LL contains the c, to begin with
		if (containsAll(c)==true){
			//automatically throws exceptions if unable to parse c to an array
		E[] a=(E[])c.toArray();
		URNode<E> curr = head;
		int counter=0;
		//iterates, deleting first instance of each element
		for (int i=0;i<(a.length);i++){
			curr=head;
			for (int j = 0;j<size;j++){
				if (a[i].equals(curr.element())){
					//uses remove to cause deletion
					remove(curr);
					break;
				}
				curr=curr.next();
			}
			
		}
		//return assumes object calling this method exists
		return true;
		}else return false;
	}

	@Override
	public E set(int index, E element) {
		//sets element of object at index equal to E element
		//throws index OOB and nullpointer based off of parameters
		if (element == null) {
			throw new NullPointerException();
		}
		if (index!=0&&(index<0||index>=size())){
			throw new IndexOutOfBoundsException();		
		}
		//makes head node
		URNode<E> place= head;
		//iterates through from front until index node
		for (int i=0;i<index;i++){
			place=place.next();
		}
		//sets element at index equal to element
		place.setElement(element);
		//return assumes object calling this method exists
		return element;
	}

	@Override
	//calls size parameter as int
	public int size() {
		// TODO Auto-generated method stub
		//return assumes object calling this method exists
		return size;
	}

	@Override
	//creates a sublist of same type (arraylist or linkedlist)
	public URList<E> subList(int fromIndex, int toIndex) {
		//throws OOB exception for indexes
		if (fromIndex<0||toIndex<0||fromIndex>(size()-1)||toIndex>(size()-1)){
			throw new IndexOutOfBoundsException();
		}
		//makes new LL to be modified and returned
		URList<E> a = new URLinkedList<E>();
		//creates nodes to be used for iteration
		URNode<E> first = head;
		URNode<E> sec = head;
		for(int i = 0; i< fromIndex; i++) {
			first=first.next();
		}
		//iterates to from, through to
		a.add(first.element());
		sec=first;
		for (int i=fromIndex;i<toIndex-1;i++){
		sec=sec.next();
		a.add(sec.element());
		}
		//returns LL
		return a;
	}

	@Override
	//parses LL to array of type Object[]
	public Object[] toArray() {
		Object [] array = new Object[size];
		URNode<E> f = head;
		//uses head node to iterate from while parsing into array
		//generates automatic exceptions if incompatible 
		for(int i = 0; i< size; i++) {
			array[i] = f.element();
			f = f.next();
		}
		//return assumes object calling this method exists
		return array;
	}

	// Inserts the specified element at the beginning of this list.
	public void addFirst(E e) {
		//throws exceptions when null
		if (e == null) {
			throw new NullPointerException();
		}
	//tracks head node and creates new node at beginning
		final URNode<E> f = head;
		final URNode<E> newNode = new URNode<E>(e,null,f);
		if (head==null) {
			head=tail= newNode;
		}
		else {
			head.setPrev(newNode);
		}
		head=newNode;
		//increases size
		size++;
		
	}
	// Appends the specified element to the end of this list.
	public void addLast(E e) {
		//throws exception when null pointer called
		if (e == null) {
			throw new NullPointerException();
		}
	
		URNode<E> newNode = new URNode<E>(e,tail,null);
		if (head == null) {
			head = newNode;	
			tail = head;
			//creates and paramaterizes new node at tail
		}
		else {
		tail.setNext(newNode);
		tail = newNode;
		}
		size++;
	}
	
	// Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
	public  E  peekFirst() {
		//throws if element doesnt exist
		if(head== null) {
			throw new NoSuchElementException();
		}
		
		URNode<E> curr = head;
		//return assumes object calling this method exists
		//returns head
		return curr.element();

		}
	// Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
	public E peekLast() {
		//throws if element DNE
		if(tail== null) {
			throw new NoSuchElementException();
		}
		//returns tail
		//return assumes object calling this method exists
		URNode<E> curr = tail;
		return curr.element();	
	}
	// Retrieves and removes the first element of this list, or returns null if this list is empty.
	public E pollFirst() {
		//throws exception if there is no such element
		if(head==null) {
			throw new NoSuchElementException();
		}
		URNode<E> curr = head;
		if (tail!=curr){
			head=curr.next();
			}
		//decreases size via setting head equal to head.next, leaving garbage collector to remove
		size--;
		//return assumes object calling this method exists
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
		//makes tail equal to tail.prev
		size--;
		//return assumes object calling this method exists
		return curr.element();
		
		
	}

}

