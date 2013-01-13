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
	public char charAt(int i) {
		for (int ptr = 0; ptr < i; ptr++) {
			_head = _head.getNext();
		}
		return _head.getData();
	}// charAt

	public StringList concat(StringList str) {
		StringList concatedList = new StringList(_head);
		while (concatedList._head.getNext() != null) {
			concatedList._head = concatedList._head.getNext();
		}
		concatedList._head.setNext(str._head);
		return concatedList;
	}// concat

	public int indexOf(int ch) {
		int indexPoint = 0;
		while (_head.getNext() != null) {
			if (_head.getData() == charAt(ch)) {
				return indexPoint;
			} else {
				_head = _head.getNext();
				indexPoint++;
			}
		}
		return -1;
	}// indexOf

	public int indexOf(int ch, int fromIndex) {
		int pointer = 0;
		while (_head.getNext() != null && pointer < fromIndex) {
			_head = _head.getNext();
			pointer++;
		}
		if (_head.getNext() == null)
			return -1;

		int indexPoint = pointer;
		while (_head.getNext() != null) {
			if (_head.getData() == charAt(ch)) {
				return indexPoint;
			} else {
				_head = _head.getNext();
				indexPoint++;
			}
		}
		return -1;
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

	public StringList subString (int i ) {
		
		StringList temp = new StringList(_head);
		int pointer = 0;
		
		while (temp._head.getNext() != null && pointer < i) {
			temp._head = temp._head.getNext();
			pointer++;
		}//while
		
		return temp;
	}
	
	public StringList subString (int i, int j ){
		
		
	}//sub (i,j)
	
	// -------------METHODS------------------------------

	// -----------------PRIVATE METHODS-------------------
	private boolean nodeEquals(StringList node) {
		return (_head.getData() == node._head.getData() && _head.getValue() == node._head
				.getValue());
	}// nodeEquals

	// -----------------PRIVATE METHODS-------------------
	public static void main(String[] args) {
		char a = 'a';
		char A = 'A';
		System.out.println();
	}
}