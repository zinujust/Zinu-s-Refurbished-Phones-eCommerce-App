package app.zinu.com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PasswordChangeRequest {
		
	private String user;
	private String oldPassword;
	private String newPassword;
}
