package entity;

import java.io.PrintWriter;
import java.util.Date;

public class Pet {
	
	private int userId;
	private String name;
	private String tag;
	private char gender;
	private String breed;
	private Date dob;
	private String country;
	private String description;
	
	/**
	 * @param userId
	 * @param name
	 * @param tag
	 * @param gender
	 * @param breed
	 * @param dob
	 * @param country
	 * @param description
	 */
	public Pet(int userId, String name, String tag, char gender, String breed,
			Date dob, String country, String description) {
		super();
		this.userId = userId;
		this.name = name;
		this.tag = tag;
		this.gender = gender;
		this.breed = breed;
		this.dob = dob;
		this.country = country;
		this.description = description;
	}
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * @return the gender
	 */
	public char getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}
	/**
	 * @return the breed
	 */
	public String getBreed() {
		return breed;
	}
	/**
	 * @param breed the breed to set
	 */
	public void setBreed(String breed) {
		this.breed = breed;
	}
	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
