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
		if (_head == null)
			return str;
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

	/* OKAY */public int compareTo(StringList str) {
		CharNode origin = copyHead();
		CharNode target = copyHead(str);

		while (origin != null && target != null && compareNodes(origin, target)) {
			origin = origin.getNext();
			target = target.getNext();
		}// while !=null and equal

		if (origin != null && target != null)
			if (origin.getData() > target.getData())
				return 1;
			else if (origin.getData() < target.getData())
				return -1;
		if (origin == null && target == null)
			return 0;
		if (origin == null && target != null)
			return -1;
		return 1;

	}// compareTo

	/* OKAY */public StringList subString(int i) {
		int count = 0;
		StringList subList = new StringList(this);
		while (subList._head != null && count + subList._head.getValue() <= i) {
			count += subList._head.getValue();
			subList._head = subList._head.getNext();
		}// while
		if (subList._head == null)
			return subList;
		for (int j = 1; count + j <= i; j++)
			subList._head.setValue(j);
		return subList;
	}// subString

	public StringList subString(int i, int j) {
		return null;

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
	private CharNode copyHead() {
		if (_head == null)
			return null;
		return new CharNode(_head.getData(), _head.getValue(), _head.getNext());
	}

	private CharNode getLastNode() {
		CharNode temp;
		if (_head == null)
			return null;
		for (temp = copyHead(); temp.getNext() != null; temp = temp.getNext()) {
		}// for
		return temp;
	}// getLastNode

	private void clipLastNode() {
		if (_head == null)
			return;
		CharNode newNode = copyHead();
		CharNode prev = null;
		while (newNode.getNext() != null) {
			newNode = newNode.getNext();
			prev = _head.getNext();
		}
		prev.setNext(null);
	}

	private CharNode copyHead(StringList other) {
		if (other._head == null)
			return null;
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
		if (origin == null && target == null)
			return true;
		if ((origin == null && target != null)
				|| (target == null && origin != null))
			return false;
		return (origin.getData() == target.getData() && origin.getValue() == target
				.getValue());
	}// compareNodes

	// -----------------PRIVATE METHODS-------------------
	public static void main(String[] args) {
		StringList hello = new StringList("Happy");
		StringList world = new StringList("def");
		System.out.println(hello.toString());
		System.out.println(world.toString());
		System.out.println(hello.concat(world).toString());
		System.out.println("hello is still: " + hello.toString());
		System.out.println("World is still: " + world.toString());
		System.out.println("*******************");
		System.out.println(hello.equals(world));
		System.out.println(world.length());
		System.out.println(hello.compareTo(world));
		System.out.println(hello.subString(2) + " is a subString of "
				+ hello.toString());
	}
}