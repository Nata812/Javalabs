package ru.spbstu.telematics.stu.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashtable implements ISimpleHashtable, Iterable<Object> { 
	public int num = 30;
	private int count = 0;
	private Object[] arr; 
	public void Array (int num) {
		arr = (Object[]) new Object[num];
	}
	
	public Object get (Object key) {   // ����� ������� �� �����
		int c=key.hashCode();
		System.out.println("������� �� �����:" + c);	
		return(c);			
	}
	
	public void put (Object key, Object value) {   // ������ � ������� ������ �� ������ �����
	//		if (arr.length != 0){
	//	System.out.println("test");	
		if (sizeof(arr) != null) {
			System.out.println("test");	
			if (count == arr.length) 
				increase();
			int c=key.hashCode();
			int idx = c%arr.length;		
			arr[idx]=value; 
			count++;
			System.out.println("�������� �������� � �������. " + idx);
		}		
	}
	
	private Object sizeof(Object[] arr2) {
		return null;
	}



	private void increase() {			// ���������� ������� �������
		Object tmp[] = (Object[]) new Object[arr.length*2];
		System.arraycopy(arr, 0, tmp, 0, arr.length);
		arr = tmp;
	}

	
	public Object remove (Object key) {  // ������� ������� � ������
		Object res = null;
		if (sizeof(arr) != null) {
		if (arr.equals(key)) {
			
			int c=key.hashCode();
			int idx = c%arr.length;
			res = arr[idx];
			arr[idx] = null;
			} 
		}
		return (res);
	}


	public int size() {
		int l = 0;
		if (sizeof(arr) != null) 
		l = arr.length;
		System.out.println(l);
		return l;
	}

	
	private class Itr implements Iterator<Object> {
		private int index = 0;			// �������
		@Override
		public boolean hasNext() {		//���������� true, ���� ���� ��������� ������� � ������� 
			if (index<count)
				return true;
			else
				return false;
	}
	

	@Override
	public Object next() {			// ���������� ��������� ������� ������� � ����������� �������� 
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

	public Iterator<Object> iterator(){   //���������� �������� 
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
		element.remove(b1);
		element.get(a1);
		element.get(a2);
		element.get(b1);
	}
}