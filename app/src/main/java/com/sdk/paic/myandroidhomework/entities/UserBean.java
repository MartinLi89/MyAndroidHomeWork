package com.sdk.paic.myandroidhomework.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author ex-lisuyang001
 * @date 2017/11/16
 */
@Entity
public class UserBean {

	@Id(autoincrement = true)
	Long id;

	String name;

	String age;
	String weight;
	String abc;

	public UserBean(String name, String age, String weight, String abc) {
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.abc = abc;
	}

	@Generated(hash = 930129587)
	public UserBean(Long id, String name, String age, String weight, String abc) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.abc = abc;
	}

	@Generated(hash = 1203313951)
	public UserBean() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAbc() {
		return this.abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}
}
