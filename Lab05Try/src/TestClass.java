import java.util.Arrays;

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

		System.out.println(list.isEmpty());



		System.out.println(list.get(0));
		System.out.println(list.size());
	
		System.out.println(Arrays.toString(list.toArray()));
		URList<Integer> list2= list.subList(0, 3);
		System.out.println(Arrays.toString(list.toArray()));
		System.out.println(Arrays.toString(list2.toArray()));
		System.out.println(list.remove(2));
		
		System.out.println(list.get(2));
		System.out.println(list.get(4));
		System.out.println(Arrays.toString(list.toArray()));
		list.remove(2);
		System.out.println(Arrays.toString(list.toArray()));
		list.remove(3);
		System.out.println(Arrays.toString(list.toArray()));
		list.remove(0);
		System.out.println(Arrays.toString(list.toArray()));
		System.out.print(list.pollLast());
		System.out.println(Arrays.toString(list.toArray()));
		System.out.print(list.pollFirst());
		System.out.println(Arrays.toString(list.toArray()));

			}

}
