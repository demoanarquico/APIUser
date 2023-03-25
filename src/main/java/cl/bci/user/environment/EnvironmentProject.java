package cl.bci.user.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentProject {
	
	@Value("${user.email}")
	private String exprEmail;
	
	@Value("${user.pass}")
	private String exprPass;

	public String getExprEmail() {
		return exprEmail;
	}

	public String getExprPass() {
		return exprPass;
	}

}
