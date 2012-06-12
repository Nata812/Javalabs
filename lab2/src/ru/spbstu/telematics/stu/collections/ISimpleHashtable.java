package ru.spbstu.telematics.stu.collections;
/**
 * Простая хеш таблица. Содержит в себе массив, который должен быть в два раза
 * больше чем количество записанных объектов (увеличивается по мере добавления).
 * При добавлении в ячеку, которая уже содержит запись, старая запись стирается.
 */
public interface ISimpleHashtable {

	/**
	 * Взять элемент по ключу
	 * 
	 * @param key
	 * @return
	 */
	Object get(Object key);

	/**
	 * Положить в таблицу объект по адресу ключа
	 * 
	 * @param key
	 * @param value
	 */
	void put(Object key, Object value);

	/**
	 * Удалить элемент с ключом. Если запись есть, то вернуть ссылку на объект.
	 * Если нет, то вернуть null
	 * 
	 * @param key
	 * @return
	 */
	Object remove(Object key);

	/**
	 * Вернуть количество элементов в таблице
	 * 
	 * @return
	 */
	int size();
}
