package com.eversmile.eve.app;

import com.eversmile.eve.app.common.AppConstants;
import com.eversmile.eve.app.model.user.AppRole;
import com.eversmile.eve.app.repository.user.IAppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class EveAppApiApplication  implements CommandLineRunner {

	@Autowired private IAppRoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(EveAppApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Optional<AppRole> role = roleRepository.findByRoleName(AppConstants.APP_DEFAULT_ROLE);
		if(role.isEmpty()){
			AppRole appRole = AppRole.builder()
					.roleName(AppConstants.APP_DEFAULT_ROLE)
					.isActive(true)
					.build();
			roleRepository.save(appRole);
		}

	}
}
