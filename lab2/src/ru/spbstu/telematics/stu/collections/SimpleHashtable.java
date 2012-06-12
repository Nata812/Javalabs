package ru.spbstu.telematics.stu.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashtable implements ISimpleHashtable, Iterable<Object> { 
	
	public int num = 20;
	private int count = 0;
	private Object[] arr;
	private int idx;
	
	public SimpleHashtable() {			// конструктор
		arr = (Object[]) new Object[num];
	}
	
	@Override
	public Object get (Object key) {   // берем элемент по ключу
		int c=key.hashCode();
		idx = c%arr.length;	
		System.out.println("Элемент " + arr[idx]+ " по ключу " + c + " " + "(индекс="+idx+")");	
		return(arr[idx]);				
	}
	
	@Override	
	public void put (Object key, Object value) {   // кладем в таблицу объект по адресу ключа
//		System.out.println("test");	
		if (count == arr.length) 
			increase();
		int c=key.hashCode();
		int idx = c%arr.length;		
		arr[idx]=value; 
		count++;
		System.out.println("Значение помещено в таблицу. "+ "Индекс="+idx+", value="+value);		
	}
	
	private void increase() {			// Увеличение размера массива
		Object tmp[] = (Object[]) new Object[arr.length*2];
		System.arraycopy(arr, 0, tmp, 0, arr.length);  									
		arr = tmp;  				
	}

	@Override
	public Object remove (Object key) {  // удаляем элемент с ключом
	//	Object res = null;
	////	arr[key%size] = null
	//	if (arr.equals(key)) {  
	//		int c=key.hashCode();  
		//	int idx = c%arr.length;
			//res = arr[idx];
			//arr[idx] = null;
			Object tmp=arr[key.hashCode()%arr.length];
			arr[key.hashCode()%arr.length] = null;
			//		} 
		return (tmp);
	}

	@Override					
	public int size() {
		int l = 0;
		l = arr.length;
		System.out.println(l);
		return l;
	}

	
	private class Itr implements Iterator<Object> {
		private int index = 0;			// счетчик
		@Override
		public boolean hasNext() {		//возвращает true, если есть следующий элемент в очереди 
			if (index<count)
				return true;
			else
				return false;	
		}							
	

		@Override
		public Object next() {			// возвращает следующий элемент очереди с увеличением счетчика 
			if (index==count)
				throw new NoSuchElementException();
			else {
				Object result = arr[index];
				index++;
				return result;
			}
		}

		@Override
		public void remove() { 
			Object[] tmp = (Object[]) new Object[arr.length-1];
			if (index > 0)
				System.arraycopy(arr, 0, tmp, 0, index);
			System.arraycopy(arr, index+1, tmp, index, arr.length-index-1);
			if (count>0)
				count--;
			arr = tmp;
		
		}

	}	

	public Iterator<Object> iterator(){   //возвращает итератор 
	return new Itr();
	}

	
	public static void main(String[] args) {
		SimpleHashtable element = new SimpleHashtable();
		int a1 = 12;
		int a2 = 234;
		int b1 = 34;
		int b2 = 655;
		int c1 = 67;
		int c2 = 578;
		element.size();
		element.put(a1, a2);
		element.put(b1, b2);
		element.put(c1, c2);
		element.size();
		element.remove(b1);
		element.get(a1);
		element.get(c1);
		element.get(b1);
	}
} 