package ru.spbstu.telematics.stu.collections;
/**
 * ������� ��� �������. �������� � ���� ������, ������� ������ ���� � ��� ����
 * ������ ��� ���������� ���������� �������� (������������� �� ���� ����������).
 * ��� ���������� � �����, ������� ��� �������� ������, ������ ������ ���������.
 */
public interface ISimpleHashtable {

	/**
	 * ����� ������� �� �����
	 * 
	 * @param key
	 * @return
	 */
	Object get(Object key);

	/**
	 * �������� � ������� ������ �� ������ �����
	 * 
	 * @param key
	 * @param value
	 */
	void put(Object key, Object value);

	/**
	 * ������� ������� � ������. ���� ������ ����, �� ������� ������ �� ������.
	 * ���� ���, �� ������� null
	 * 
	 * @param key
	 * @return
	 */
	Object remove(Object key);

	/**
	 * ������� ���������� ��������� � �������
	 * 
	 * @return
	 */
	int size();
}
