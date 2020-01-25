package com.daniel.rest.webservices.restfulwebservices.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  // encrypt password using https://www.browserling.com/tools/bcrypt
  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "daniel",
        "$2a$10$Bs.E9b.qamvgGPvXSVZ9vOoQYBEwEuHfHyg.3nZTJTtVmQ84BALaS", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(2L, "Daniel",
            "$2a$10$.aXv8SqaNfm9M12HKcetXealOYUP3bgqPQj3vTj/EniS.03ig8rTW", "ROLE_USER_2"));
  }

  //$2a$10$.aXv8SqaNfm9M12HKcetXealOYUP3bgqPQj3vTj/EniS.03ig8rTW

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


