package ru.itva.propertyframework.logic;

public class ObjectValue {
	private Long id;
	private Long object_id;
	private Long style_id;
	private String value;

	public ObjectValue() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getObject_id() {
		return object_id;
	}

	public void setObject_id(Long object_id) {
		this.object_id = object_id;
	}

	public Long getStyle_id() {
		return style_id;
	}

	public void setStyle_id(Long style_id) {
		this.style_id = style_id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}