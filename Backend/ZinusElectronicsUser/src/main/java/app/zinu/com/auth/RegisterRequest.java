package app.zinu.com.auth;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import app.zinu.com.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	
	private Integer user_id;
	private String firstName;
	private String lastName;
	
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate dob;
	private String address;
	private String phoneNum;
	private String email;
	private String password;
	private Role role;
}
