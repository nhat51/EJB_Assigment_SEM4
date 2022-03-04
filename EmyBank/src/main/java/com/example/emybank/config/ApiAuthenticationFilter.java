package com.example.emybank.config;

import com.auth0.jwt.JWT;
import com.example.emybank.dto.UserDto;
import com.google.gson.Gson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ApiAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    final AuthenticationManager authenticationManager;

    public ApiAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String jsonData = request.getReader().lines().collect(Collectors.joining());
            Gson gson = new Gson();
            UserDto userDto = gson.fromJson(jsonData, UserDto.class);
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userDto.getName(),userDto.getPassword());
                    return authenticationManager.authenticate(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User)authResult.getPrincipal(); //user của spring
        Calendar currentTime = Calendar.getInstance();
        currentTime.add(Calendar.DATE, 7);
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(currentTime.getTime())
                .withIssuer("EmyBank")
                .withClaim("role",user.getAuthorities().iterator().next().getAuthority())
                .sign(SecurityBean.algorithm());
        HashMap<String,String> map = new HashMap<>();
        map.put("access_token",accessToken);
        response.setContentType("application/json");
        response.getWriter().println(new Gson().toJson(map));
    }
}
