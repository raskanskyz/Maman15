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

	/* REVIEW */public StringList concat(StringList str) {
		StringList concatedList = new StringList(_head);
		while (concatedList._head.getNext() != null) {
			concatedList._head = concatedList._head.getNext();
		}
		concatedList._head.setNext(str._head);
		return concatedList;
	}// concat

	/* REVIEW */public int indexOf(int ch) {
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

	public int indexOf(int ch, int fromIndex) {
		CharNode newNode = copyHead();
		int indexPoint = 0;
		while (indexPoint < fromIndex && newNode != null) {
			if (indexPoint + newNode.getValue() <= fromIndex) {
				indexPoint += newNode.getValue();
				newNode = newNode.getNext();
			}// if
			else
				indexPoint++;
		}// while
		if (newNode == null)
			return -1;
		// first loop finished

	}// indexOf

	public boolean equals(StringList str) {
		StringList temp = new StringList(_head);

		if ((temp._head.getNext() == null && str._head.getNext() != null)
				|| (temp._head.getNext() != null && str._head.getNext() == null)) {
			return false;
		}// if
		if (temp._head.getNext() == null && str._head.getNext() == null)
			return (temp.nodeEquals(str));
		return false;
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

	private CharNode copyHead() {
		return new CharNode(_head.getData(), _head.getValue(), _head.getNext()/*
																			 * <--
																			 * New
																			 * charNode
																			 * ?
																			 */);
	}

	// -----------------PRIVATE METHODS-------------------
	public static void main(String[] args) {
		StringList list = new StringList("aaabbbaaa");
		System.out.println(list.indexOf('a', 4));
	}
}