package object;

public class Pet {

	private String name;
	private String type;
	private String color;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setPet(String name, String type, Integer age, String color) {
		this.setName(name);
		this.setType(type);
		this.setAge(age);
		this.setColor(color);
	}

}
