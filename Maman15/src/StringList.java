public class StringList {
	private CharNode _head;

	public StringList() {
		_head = null;
	}

	public StringList(CharNode node) {
		if (node == null)
			_head = null;
		else {
			_head = new CharNode(node.getData(), node.getValue(), null);
			for (CharNode ptr = node.getNext(), last = _head; ptr != null; ptr = ptr
					.getNext()) {
				last.setNext(new CharNode(ptr.getData(), ptr.getValue(), ptr
						.getNext()));
				last = last.getNext();
			}
		}
	}

	// -----------MY CONSTRUCTORS-----------------
	public StringList(String s) {

	}// CTOR1

	// -----------MY CONSTRUCTORS-----------------

	// -------------METHODS------------------------------
	/* OKAY */public char charAt(int i) {
		StringList temp = new StringList(_head);
		int counter = 0;
		while (temp._head.getNext() != null && counter < i) {
			temp._head = temp._head.getNext();
			counter++;
		}// while
		return temp._head.getData();
	}// charAt

	/* REVIEW */public StringList concat(StringList str) {
		StringList concatedList = new StringList(_head);
		while (concatedList._head.getNext() != null) {
			concatedList._head = concatedList._head.getNext();
		}
		concatedList._head.setNext(str._head);
		return concatedList;
	}// concat

	/* OKAY */public int indexOf(int ch) {
		StringList temp = new StringList(_head);
		int valueCounter = 0;
		while (temp._head.getNext() != null) {
			if (temp._head.getData() == ch)
				return valueCounter;
			else {
				valueCounter += temp._head.getValue();
				temp._head = temp._head.getNext();

			}// else

		}// while
		if (temp._head.getData() == ch)
			return valueCounter;
		else
			return -1;

	}// indexOf

	/* OKAY */public int indexOf(int ch, int fromIndex) {
		int valueCounter = 0;
		StringList temp = new StringList(_head);
		while (valueCounter < fromIndex) {
			valueCounter += temp._head.getValue();
			temp._head = temp._head.getNext();
		}// while
		return valueCounter + temp.indexOf(ch);

	}// indexOf

	public boolean equals(StringList str) {
		if ((_head.getNext() == null && str._head.getNext() != null)
				|| (_head.getNext() != null && str._head.getNext() == null)) {
			return false;
		}
		if (_head.getNext() == null && str._head.getNext() == null)
			return (nodeEquals(str));

		else {
			_head = _head.getNext();
			str._head = str._head.getNext();
			return (equals(str));
		}
	}// equals

	public int compareTo(StringList str) {
		if (equals(str))
			return 0;
		while (nodeEquals(str)
				&& (_head.getNext() != null && str._head.getNext() != null)) {
			_head = _head.getNext();
			str._head = str._head.getNext();
		}// while
		if (_head.getNext() == null)
			return -1;
		if (str._head.getNext() == null)
			return 1;

		if (_head.getData() != str._head.getData()) {
			if (_head.getData() > str._head.getData())
				return 1;
			else
				return -1;

		}// if !=data

		if (_head.getValue() > str._head.getValue())
			if (str._head.getNext().getData() > _head.getData())
				return -1;
			else
				return 1;
		else if (_head.getNext().getData() > str._head.getData())
			return 1;
		else
			return -1;

	}// compareTo

	public StringList subString(int i) {

		StringList temp = new StringList(_head);
		int pointer = 0;

		while (temp._head.getNext() != null && pointer < i) {
			temp._head = temp._head.getNext();
			pointer++;
		}// while

		return temp;
	}

	public StringList subString(int i, int j) {
		int pointer = 0;
		StringList temp2 = new StringList();
		StringList temp = subString(i);
		CharNode last = temp2._head;
		while (pointer < j - i) {
			if (temp2._head == null) {
				temp2._head = temp._head;
				pointer++;
			} else {
				last.setNext(new CharNode(temp._head.getNext().getData(),
						temp._head.getNext().getValue(), temp._head.getNext()
								.getNext()));
				last = last.getNext();
				temp._head = temp._head.getNext();
				pointer++;
			}// else
		}// while
		return temp2;
	}// subString

	// -------------METHODS------------------------------

	// -----------------PRIVATE METHODS-------------------
	private boolean nodeEquals(StringList node) {
		return (_head.getData() == node._head.getData() && _head.getValue() == node._head
				.getValue());
	}// nodeEquals

	// -----------------PRIVATE METHODS-------------------
	public static void main(String[] args) {
		CharNode node7 = new CharNode('b', 2, null);
		CharNode node6 = new CharNode('a', 1, node7);
		CharNode node5 = new CharNode('d', 1, node6);
		CharNode node4 = new CharNode('c', 2, node5);
		CharNode node3 = new CharNode('b', 2, node4);
		CharNode node2 = new CharNode('A', 1, node3);
		CharNode node1 = new CharNode('b', 1, node2);
		CharNode node = new CharNode('A', 2, node1);

		StringList sl = new StringList(node);

		System.out.println(sl.charAt(2));
		System.out.println(sl.indexOf('a'));
		System.out.println(sl.indexOf('b', 3));

	}
}