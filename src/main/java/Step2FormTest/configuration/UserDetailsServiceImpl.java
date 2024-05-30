package Step2FormTest.configuration;

import Step2FormTest.models.User;
import Step2FormTest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(s);

        if(user == null){
            throw new UsernameNotFoundException("Could not find user!");
        }

        return new MyUserDetails(user);
    }
}
