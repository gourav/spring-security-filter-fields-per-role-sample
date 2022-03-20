package blog.explained.so;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsServiceConfiguration {

  @Bean
  public UserDetailsService inMemoryUserDetailsService(PasswordEncoder encoder) {

    UserDetails john = User.withUsername("john")
      .password("doe")
      .passwordEncoder(encoder::encode)
      .authorities("USER")
      .build();

    UserDetails steve = User.withUsername("sam")
      .password("flynn")
      .passwordEncoder(encoder::encode)
      .authorities("ADMIN")
      .build();

    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

    manager.createUser(john);
    manager.createUser(steve);

    return manager;

  }

  @Bean
  public PasswordEncoder encoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}
