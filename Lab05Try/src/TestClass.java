import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class TestClass {

	public static <E> void main(String[] args) {
		URLinkedList<Integer> list = new URLinkedList<Integer>();
		System.out.println(list.isEmpty());

		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		ArrayList<String> al2 = new ArrayList<String>();
        al2.add("Text1");
        al2.add("Text2");
        al2.add("Text3");
		list.addAll(3,al2);
		


		System.out.println(Arrays.toString(list.toArray()));
		
			URList<E> list2=new URLinkedList<E>();
			list2=(URList<E>) list.subList(2, 4);
		System.out.println(Arrays.toString(list2.toArray()));
}
}