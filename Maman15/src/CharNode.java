/**
 * @author Unknown
 * @version (Unknown)
 * 
 */
public class CharNode {
	private char _data;
	private int _value;
	private CharNode _next;

	/**
	 * Constructs a CharNode object to use with StringList.
	 * 
	 * @param c
	 *            The char of this CharNode.
	 * @param val
	 *            The number of sequential occurrences of this CharNodes char.
	 * @param n
	 *            The link to the next CharNode (Memory Address).
	 * @see {@link StringList}
	 */
	public CharNode(char c, int val, CharNode n) {
		_data = c;
		_value = val;
		_next = n;
	}

	/**
	 * @return The Next CharNode in the StringList.
	 */
	public CharNode getNext() {
		return _next;
	}

	/**
	 * @param node
	 *            Sets the next CharNode in the StringList.
	 */
	public void setNext(CharNode node) {
		_next = node;
	}

	/**
	 * @return The value of this CharNode.
	 */
	public int getValue() {
		return _value;
	}

	/**
	 * @param v
	 *            Sets this CharNode's value.
	 */
	public void setValue(int v) {
		_value = v;
	}

	/**
	 * @return The char of this CharNode.
	 */
	public char getData() {
		return _data;
	}

	/**
	 * @param c
	 *            Sets this Charnode's char.
	 */
	public void setData(char c) {
		_data = c;
	}
}