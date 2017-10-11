package parametervector;

import java.util.Iterator;

public class Vector<T> implements Iterable<T> {
	private T[] values;
	private int size = 0; // Points out the next writable cell
	
	/* Constructor */
	@SuppressWarnings("unchecked")
	public Vector(int size) throws NegativeArraySizeException {
		// Already this.size = 0;
		this.values = (T[]) new Object[size]; // initialized with zeros in Java
	}	
	// Getter
	public int size() {
		return this.size;
	}
	// Other methods
	@SuppressWarnings("unchecked")
	public void ensureCapacity(int capacity) {
		/**
		 * Important method.
		 * Increases array size if not sufficient.
		 * In that case : new size = max(capacity, 2 * old size)
		 * @param capacity : minimum capacity to be ensured of
		 */
		// if the length is not enough (otherwise, do nothing)
		int len = this.values.length;
		if(len < capacity) {
			int newCapacity = Integer.max(2*len,capacity);
			T[] tmpValues = values.clone(); // Temp copy of this.values
			values = (T[])new Object[newCapacity]; // New size (initialized with zeros)
			for(int i=0;i<len;i++) {
				values[i] = tmpValues[i]; // Refilling new this.values with previous values
			}
		}
	}

	public void resize(int size) {
		/**
		 *  Changes the index place "size" to the given size.
		 *  Call ensureCapacity first to make sure in case of given size > this.len.
		 *  @param size : new index place
		 */
		this.ensureCapacity(size);
		this.size = size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public void add(T element) {
		// this.size should never be strictly greater than this.values.length
		this.ensureCapacity(size + 1);
		this.values[size++] = element;
	}
	
	public void set(int index, T element) {
		/**
		 *  Modifies at given index if correctly located
		 *  Do nothing otherwise
		 *  @param index : location to modify
		 *  @param element : value replacing the old
		 */
		if(index < this.size) {
			this.values[index] = element;
		}
	}
	
	public T get(int index) {
		/**
		 * Returns the element at given "index" location, zero otherwise.
		 * @param index : location to read
		 */
		if(index < size) {
			return this.values[index];
		}
		else {
			return null;
		}
	}
	public int getLength() {
		return this.values.length;
	}
	@Override
	public Iterator<T> iterator() {
		return new VectorIterator(this);
	}
	
	public void addAll(Vector<T> elements) {
		for (T elt : elements) {
			this.add(elt);
		}
	}
	
	class VectorIterator implements Iterator<T> {
		private T[] values;
		private int index = 0;
		
		@SuppressWarnings("unchecked")
		public VectorIterator(Vector<T> vectorT) {
			this.values = (T[]) new Object[vectorT.size];
			for (int i=0;i<vectorT.size;i++) {
				this.values[i] = vectorT.values[i];
			}
		}

		@Override
		public boolean hasNext() {
			return this.index < this.values.length;
		}

		@Override
		public T next() {
			return this.values[index++];
		}
	}
}
