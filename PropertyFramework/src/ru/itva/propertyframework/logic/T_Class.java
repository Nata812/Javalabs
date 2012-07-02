package ru.itva.propertyframework.logic;

import java.util.HashSet;
import java.util.Set;

public class T_Class {
	private Long id;
	private String name;
	private String description;
	private Set styles = new HashSet();
	private Set objects = new HashSet();

	public T_Class() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getStyles() {
		return styles;
	}

	public void setStyles(Set styles) {
		this.styles = styles;
	}

	public Set getObjects() {
		return objects;
	}

	public void setObjects(Set objects) {
		this.objects = objects;
	}
}
