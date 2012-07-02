package ru.itva.propertyframework.logic;

import java.util.Set;
import java.util.HashSet;

public class Style {
  private Long id;
  private boolean is_mandatory;
  private boolean is_multiple;
  private Set t_classes = new HashSet();
  private Set object_values = new HashSet();
  private Long family_id;

  public Style() {
  }

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public boolean isIs_mandatory() {
	return is_mandatory;
}

public void setIs_mandatory(boolean is_mandatory) {
	this.is_mandatory = is_mandatory;
}

public boolean isIs_multiple() {
	return is_multiple;
}

public void setIs_multiple(boolean is_multiple) {
	this.is_multiple = is_multiple;
}

public Set getT_classes() {
	return t_classes;
}

public void setT_classes(Set t_classes) {
	this.t_classes = t_classes;
}

public Set getObject_values() {
	return object_values;
}

public void setObject_values(Set object_values) {
	this.object_values = object_values;
}

public Long getFamily_id() {
	return family_id;
}

public void setFamily_id(Long family_id) {
	this.family_id = family_id;
}
  
}