package ru.itva.propertyframework.logic;

import java.util.Set;
import java.util.HashSet;

public class Family {
	private Long id;
	private String name;
	private String description;
	private Set styles = new HashSet();

	public Family() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Set getStyles() {
		return styles;
	}

	public void setStyles(Set styles) {
		this.styles = styles;
	}
}