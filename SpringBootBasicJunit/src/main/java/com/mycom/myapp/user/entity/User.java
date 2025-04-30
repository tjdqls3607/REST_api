package com.mycom.myapp.user.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="user")
@Setter
@Getter
@ToString
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	
	// user 엔티티를 가지고 올때 항상 userRole 또한 가져와야한다
	@OneToMany(fetch=FetchType.EAGER)	
//	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST) // #5
	@ToString.Exclude
	private List<UserRole> userRoles;
}
