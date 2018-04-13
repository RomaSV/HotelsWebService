package api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccountService {
    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    String logout (HttpServletRequest request, HttpServletResponse response);
}
