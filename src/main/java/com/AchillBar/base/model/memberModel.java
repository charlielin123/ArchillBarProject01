package com.AchillBar.base.model;

import java.util.Base64;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

@Entity
@Table(name = "Member")
public class memberModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "m_id")
	private Integer m_id;

	@Column(name = "admin")
	private Boolean admin;

	@Column(name = "m_name")
	private String memberName;

	@Column(name = "pws")
	private String password;

	@Column(name = "sex")
	private String sex;

	@Column(name = "phone")
	private String phone;

	@Column(name = "Email")
	private String email;

	@Lob
	@Column(name = "photo")
	private byte[] photo;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Birthday")
	private Date birthday;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "regDate")
	private Date regDate;

	@PrePersist
	public void onCreate() {
		if (regDate == null) {
			regDate = new Date();
			updateDate = regDate;
		}
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate")
	private Date updateDate;

	@Transient
	private ResponseEntity<byte[]> img;


	public memberModel() {
	}

	public Integer getM_id() {
		return this.m_id;
	}

	public void setM_id(Integer m_id) {
		this.m_id = m_id;
	}

	public Boolean isAdmin() {
		return this.admin;
	}


	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getImg() {
		

		return "data:image/jpeg;base64,"+ new String(Base64.getEncoder().encode(this.photo)) ;
	}

	public void setImg(ResponseEntity<byte[]> img) {
		this.img = img;
	}


}
