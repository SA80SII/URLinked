import java.util.ArrayList;
import java.util.Arrays;

public class TestClass {

	public static <E> void main(String[] args) {
		URLinkedList<Integer> list = new URLinkedList<Integer>();

		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);





		System.out.println(list.get(0));
		System.out.println(list.size());
	


		System.out.println(list.get(2));
		System.out.println(list.get(5));
		System.out.println(Arrays.toString(list.toArray()));
		list.remove(2);
		System.out.println(Arrays.toString(list.toArray()));
		list.remove(3);
		System.out.println(Arrays.toString(list.toArray()));
		list.remove(0);
		System.out.println(Arrays.toString(list.toArray()));

		URArrayList<Object> list1 = new URArrayList<Object>();
		list1.add('g');
		list1.add('c');
		list1.add('t');
		list1.add(3);
		list1.add(4);
		list1.add(5);
		ArrayList<Object> list2 = new ArrayList<Object>();
		list2.add('g');
		list2.add('c');
		list2.add('t');
		list2.add(3);
		list2.add(4);
		list2.add(5);
		list1.addAll(list2);
		System.out.println(Arrays.toString(list1.toArray()));
		list1.removeAll(list2);
		System.out.println(Arrays.toString(list1.toArray()));
		System.out.println(list1.containsAll(list2));
		list1.removeAll(list2);
		list1.removeAll(list2);

		System.out.println(list1.containsAll(list2));
		System.out.println(Arrays.toString(list1.toArray()));

			}

}
