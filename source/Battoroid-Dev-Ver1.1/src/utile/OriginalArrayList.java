package utile;

public class OriginalArrayList<E> {
	private Object[] array;
	
	@SafeVarargs
	public OriginalArrayList(E... object){
		array = new Object[object.length];
		for(int i=0;i<object.length;i++){
			array[i] = object[i];
 		}
	}
	
	public void add(@SuppressWarnings("unchecked") E... object){
		array = composeArray(array,object);
	}
	
	@SuppressWarnings("unchecked")
	public E get(int index){
		return (E) array[index];
	}
	
	public void remove(int index){
		Object[] array = new Object[this.array.length - 1];
		int i=0;
		for(i=0;i<index;i++){
			array[i] = this.array[i];
		}
		for(;i<this.array.length-1;i++){
			array[i] = this.array[i+1];
		}
		this.array = array;
	}
	
	public void clear() {
		array = new Object[0];
	}

	public int size() {
		return array.length;
	}
	
	// ”z—ñ‡¬
	private static Object[] composeArray(Object[] array1,Object[] array2){
		Object[] array = new Object[array1.length + array2.length]; 
		for(int i = 0;i < array1.length + array2.length;i++){
			if(i < array1.length){
				array[i] = array1[i];
			}
			else{
				array[i] = array2[i - array1.length];
			}
		}
		return array;
	}
}
