package web;

import com.typesafe.config.Config;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import com.typesafe.config.ConfigFactory;

import java.util.List;
import java.util.Map;

@Component
public class ConfigAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Config config = ConfigFactory.load();
        for (int i = 1; i <= config.getObject("users").unwrapped().size(); i++) {
            Map<String, Object> confUser = config.getObject("users." + i).unwrapped();
            if (confUser.get("userName").equals(username) && confUser.get("password").equals(password)) {
                List<GrantedAuthority> grantedAuths =
                        AuthorityUtils.commaSeparatedStringToAuthorityList(confUser.get("role").toString());

                return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
            }
        }
        throw new BadCredentialsException("External system authentication failed");
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
