/**
 * The StringList object is a representation of a String as a linked list (ADT)
 * of {@link CharNode} objects.
 * 
 * @author Raskanskyz
 * @version (1.0)
 * 
 */
public class StringList {
	private CharNode _head;

	/**
	 * Default constructor of the StringList Class.
	 */
	public StringList() {
		_head = null;
	}

	/**
	 * Initializes a StringList object from 'node'.
	 * 
	 * @param node
	 *            The node from which to initialize this StringList object. @
	 */
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

	// -----------MY CONSTRUCTORS------------------------
	// **************************************************
	/**
	 * Initializes a StringList object from 's'.
	 * 
	 * @param s
	 *            The String from which to initialize this StringList object.
	 */
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

	/**
	 * Initializes a StringList object from 'other'.
	 * 
	 * @param other
	 *            The StringList object from which to initialize this StringList
	 *            object.
	 * 
	 */
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

	// -------------PUBLIC METHODS-------------------------
	// ****************************************************
	/**
	 * Finds a char at a specific index.
	 * 
	 * @param i
	 *            The index from which to retrieve the char
	 * @return the char located at 'i'.
	 */
	public char charAt(int i) {
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

	/**
	 * Concatenates the specified string to the end of this string.
	 * 
	 * @param str
	 *            The String that is concatenated to the end of this String.
	 * @return The String that represents the concatenation of this StringList's
	 *         characters followed by the string argument's characters.
	 */
	public StringList concat(StringList str) {
		if (_head == null)
			return str;
		StringList tempList = new StringList(_head);
		tempList.getLastNode().setNext(str.copyHead());
		return tempList;
	}// concat

	/**
	 * Returns the index within this StringList of the first occurrence of the
	 * specified character.
	 * 
	 * @param ch
	 *            A character.
	 * @return The index of the first occurrence of the character in the
	 *         character sequence represented by this StringList.
	 */
	public int indexOf(int ch) {
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

	/**
	 * Returns the index within this StringList of the first occurrence of the
	 * specified character, starting the search at the specified index.
	 * 
	 * @param ch
	 *            A character.
	 * @param fromIndex
	 *            The index to start the search from.
	 * @return The index of the first occurrence of the character in the
	 *         character sequence represented by this object that is greater
	 *         than or equal to fromIndex, or -1 if the character does not
	 *         occur.
	 */
	public int indexOf(int ch, int fromIndex) {
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

	/**
	 * Compares this string to the specified object. The result is true if the
	 * argument is null or is a StringList object that represents the same
	 * sequence of characters as this StringList.
	 * 
	 * @param str
	 *            The StringList to compare this StringList against.
	 * @return true if this String represents the same sequence of characters as
	 *         the specified StringList, false otherwise.
	 */
	public boolean equals(StringList str) {
		CharNode origin = copyHead();
		CharNode target = str.copyHead();
		return equals(origin, target);

	}// equals

	/**
	 * Compares two StringList's lexicographically.
	 * 
	 * @param str
	 *            The StringList to be compared to.
	 * @return The value '0' if the argument StringList is equal to this
	 *         StringList, '-1' if this StringList is lexicographically less
	 *         than the given StringList argument, and '1' if this string is
	 *         lexicographically greater than the given StringList argument.
	 */
	public int compareTo(StringList str) {
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

	/**
	 * Returns a new StringList that it's String representation is a substring
	 * of this StringLists String representation. The substring begins with the
	 * character at the specified index and extends to the end of this
	 * StringList.
	 * 
	 * @param i
	 *            The beginning index, inclusive.
	 * @return The StringList with the specified substring.
	 */
	public StringList substring(int i) {
		StringList subList = new StringList(this);
		int counter = 0, fineTune = 0;
		if (i == 0)
			return subList;
		while (subList._head != null) {
			if (counter + subList._head.getValue() > i) {
				while (counter + fineTune < i) {
					fineTune++;
				}// while fineTuning
				subList._head.setValue(subList._head.getValue() - fineTune);
				return subList;
			}// if
			counter += subList._head.getValue();
			subList._head = subList._head.getNext();
		}// while
		return subList;

	}// subString

	public StringList substring(int i, int j) {
		return this.clipFromIndex(j).substring(i);
	}// subString

	/**
	 * Returns the length of this StringLists String representation.
	 * 
	 * @return The length of the sequence of characters represented by this
	 *         StringList.
	 */
	public int length() {
		int counter = 0;
		CharNode newNode = copyHead();
		while (newNode != null) {
			counter += newNode.getValue();
			newNode = newNode.getNext();
		}// while
		return counter;
	}// length

	/*
	 * Returns the specified StringList's String representation with a quotation
	 * mark both at the beginning and the end of this StringList's String
	 * representation.
	 */
	public String toString() {
		String temp = "\"";
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
		return temp + "\"";
	}// toString

	// -----------------PRIVATE METHODS-------------------
	// ****************************************************
	/**
	 * Creates a pointer to this StringList's root CharNode.
	 * 
	 * @return A pointer to this StringList's root CharNode.
	 */
	private CharNode copyHead() {
		if (_head == null)
			return null;
		return new CharNode(_head.getData(), _head.getValue(), _head.getNext());
	}

	/**
	 * Creates a pointer to a given StringList's root CharNode.
	 * 
	 * @param other
	 *            The given StringList.
	 * @return A pointer to the given StringList's root CharNode.
	 */
	private CharNode copyHead(StringList other) {
		if (other._head == null)
			return null;
		return new CharNode(other._head.getData(), other._head.getValue(),
				other._head.getNext());
	}

	/**
	 * Returns the last CharNode in this StringList.
	 * 
	 * @return The last CharNode in this StringList.
	 */
	private CharNode getLastNode() {
		CharNode temp;
		if (_head == null)
			return null;
		for (temp = copyHead(); temp.getNext() != null; temp = temp.getNext()) {
		}// for
		return temp;
	}// getLastNode

	/**
	 * Clips this StringList's String representation from the given index point,
	 * inclusive.
	 * 
	 * @param i
	 *            The index point from which to clip.
	 * @return The StringList's String representation excluding characters from
	 *         point 'i'.
	 */
	private StringList clipFromIndex(int i) {
		int counter = 0, fineTune = 0;
		StringList subList = new StringList(this);
		CharNode temp = subList._head;
		if (i == 0) {
			subList._head = null;
			return subList;
		}// if
		while (temp != null) {
			if (counter + temp.getValue() >= i) {
				while (fineTune < i - counter)
					fineTune++;

				temp.setValue(fineTune);
				temp.setNext(null);
				return subList;
			}
			counter += temp.getValue();
			temp = temp.getNext();
		}

		return subList;

	}// clipFromIndex

	/**
	 * Overrides The <CODE>public boolean equals(String str)</CODE> method.
	 * 
	 * @param origin
	 *            The given StringList
	 * @param target
	 *            The StringList to compare to.
	 * @return if both 'origin' and 'target' are null, or if each of both
	 *         StringList's CharNodes contain the same 'data' and 'value', false
	 *         otherwise.
	 * @see {@link #equals(StringList)}
	 */
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

	/**
	 * Compares Two given CharNodes whether they are equal in 'data' and in
	 * 'value'.
	 * 
	 * @param origin
	 *            The given CharNode.
	 * @param target
	 *            The CharNode to compare to.
	 * @return true if 'origin' and 'target' are equal in 'data' and in 'value',
	 *         false otherwise.
	 * 
	 */
	private boolean compareNodes(CharNode origin, CharNode target) {
		if (origin == null && target == null)
			return true;
		if ((origin == null && target != null)
				|| (target == null && origin != null))
			return false;
		return (origin.getData() == target.getData() && origin.getValue() == target
				.getValue());
	}// compareNodes

	public static void main(String[] args) {
		StringList list = new StringList("aaabbbcccdddeeefffggg");
		System.out.println(list.substring(0, 0));
		System.out.println(list.substring(0, 1));
		System.out.println(list.substring(0, 2));
		System.out.println(list.substring(0, 3));
		System.out.println(list.substring(0, 4));
		System.out.println(list.substring(0, 5));
		System.out.println(list.substring(0, 6));
		System.out.println(list.substring(0, 7));
		System.out.println(list.substring(0, 8));
		System.out.println(list.substring(0, 9));
		System.out.println(list.substring(0, 10));
		System.out.println(list.substring(0, 11));
		System.out.println(list.substring(0, 12));
		System.out.println(list.substring(0, 13));

	}
}