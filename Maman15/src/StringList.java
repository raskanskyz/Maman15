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
		CharNode temp = _head;
		for (int i = 0; i < s.length(); i++) {
			int value = 1;
			while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
				value++;
				i++;
			}// while next char is identical
			if (_head == null) {
				_head = new CharNode(s.charAt(i), value, null);
				temp = _head;
			}// if
			else {
				temp.setNext(new CharNode(s.charAt(i), value, null));
				temp = temp.getNext();
			}// else
		}// for
	}// CTOR1

	public StringList(StringList other) {
		if (other._head == null)
			_head = null;
		else {

			_head = new CharNode(other._head.getData(), other._head.getValue(),
					null);
			for (CharNode ptr = other._head.getNext(), last = _head; ptr != null; ptr = ptr
					.getNext()) {
				last.setNext(new CharNode(ptr.getData(), ptr.getValue(), ptr
						.getNext()));
				last = last.getNext();
			}

		}
	}// CTOR2
		// -----------MY CONSTRUCTORS-----------------

	// -------------METHODS------------------------------
	/* OKAY */public char charAt(int i) {
		CharNode newNode = copyHead();
		int counter = 0;
		while (newNode.getNext() != null && counter <= i) {
			if (counter + newNode.getNext().getValue() > i)
				return newNode.getData();
			counter += newNode.getValue();
			newNode = newNode.getNext();
		}// while
		return newNode.getData();
	}// charAt

	/* OKAY */public StringList concat(StringList str) {
		StringList tempList = new StringList(_head);
		tempList.getLastNode().setNext(str.copyHead());
		return tempList;
	}// concat

	/* OKAY */public int indexOf(int ch) {
		CharNode newNode = copyHead();
		int valueCounter = 0;
		while (newNode.getNext() != null) {
			if (newNode.getData() == ch)
				return valueCounter;
			else {
				valueCounter += newNode.getValue();
				newNode = newNode.getNext();
			}// else
		}// while
		if (newNode.getData() == ch)
			return valueCounter;
		else
			return -1;

	}// indexOf

	/* OKAY */public int indexOf(int ch, int fromIndex) {
		CharNode newNode = copyHead();
		int indexOf = 0;
		if (fromIndex < 0)
			return -1;
		if (fromIndex < newNode.getValue() && newNode.getData() == ch)
			return fromIndex;
		while (newNode != null && indexOf + newNode.getValue() < fromIndex) {
			indexOf += newNode.getValue();
			newNode = newNode.getNext();
		}// while
		if (newNode == null)
			return -1;
		while (newNode != null && newNode.getData() != ch) {
			indexOf += newNode.getValue();
			newNode = newNode.getNext();
		}// while
		if (newNode == null)
			return -1;
		return indexOf;

	}// indexOf

	/* OKAY */public boolean equals(StringList str) {
		CharNode origin = copyHead();
		CharNode target = str.copyHead();
		return equals(origin, target);

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

	/* OKAY */public int length() {
		int counter = 0;
		CharNode newNode = copyHead();
		while (newNode != null) {
			counter += newNode.getValue();
			newNode = newNode.getNext();
		}// while
		return counter;
	}// length

	/* OKAY */public String toString() {
		String temp = "";
		for (CharNode tempNode = _head; tempNode != null; tempNode = tempNode
				.getNext()) {
			int counter = 0;
			if (tempNode.getValue() == 1)
				temp += tempNode.getData();
			else {
				while (counter < tempNode.getValue()) {
					temp += tempNode.getData();
					counter++;
				}// while
			}// else
		}// forLoop
		return temp;
	}// toString

	// -------------METHODS------------------------------

	// -----------------PRIVATE METHODS-------------------
	private boolean nodeEquals(StringList node) {
		return (_head.getData() == node._head.getData() && _head.getValue() == node._head
				.getValue());
	}// nodeEquals

	private CharNode copyHead() {
		if (_head == null)
			return null;
		return new CharNode(_head.getData(), _head.getValue(), _head.getNext());
	}

	private CharNode getLastNode() {
		CharNode temp;
		for (temp = copyHead(); temp.getNext() != null; temp = temp.getNext()) {
		}// for
		return temp;
	}// getLastNode

	@SuppressWarnings("unused")
	private CharNode copyHead(StringList other) {
		return new CharNode(other._head.getData(), other._head.getValue(),
				other._head.getNext());
	}

	private boolean equals(CharNode origin, CharNode target) {
		if (origin == null && target == null)
			return true;
		if ((origin == null && target != null)
				|| (target == null && origin != null))
			return false;
		else {
			return (equals(origin.getNext(), target.getNext()) && compareNodes(
					origin, target));
		}
	}// equals

	private boolean compareNodes(CharNode origin, CharNode target) {
		return (origin.getData() == target.getData() && origin.getValue() == target
				.getValue());
	}// compareNodes

	// -----------------PRIVATE METHODS-------------------
	public static void main(String[] args) {
		StringList hello = new StringList("Hello");
		StringList world = new StringList(" World");
		System.out.println(hello.toString());
		System.out.println(world.toString());
		System.out.println(hello.concat(world).toString());
		System.out.println("hello is still: " + hello.toString());
		System.out.println("World is still: " + world.toString());
		System.out.println("*******************");
		System.out.println(hello.equals(world));
		System.out.println(world.length());
	}
}