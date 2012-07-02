package ru.itva.propertyframework.logic;

import java.util.HashSet;
import java.util.Set;

public class T_Object {
	private Long id;
	private String object_name;
	private Long class_id;
	private Set object_values = new HashSet();

	public T_Object() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObject_name() {
		return object_name;
	}

	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}

	public Long getClass_id() {
		return class_id;
	}

	public void setClass_id(Long class_id) {
		this.class_id = class_id;
	}

	public Set getObject_values() {
		return object_values;
	}

	public void setObject_values(Set object_values) {
		this.object_values = object_values;
	}
}
