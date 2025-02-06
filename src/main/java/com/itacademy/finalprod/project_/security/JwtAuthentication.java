package com.itacademy.finalprod.project_.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

    public class JwtAuthentication extends AbstractAuthenticationToken {

        private final String username;


        public JwtAuthentication(String username) {
            super(Collections.emptyList());  // Передаем пустой список ролей
            this.username = username;
            setAuthenticated(true);  // Устанавливаем флаг аутентификации
        }

        @Override
        public Object getCredentials() {
            return null;  // Нет необходимости в пароле для JWT
        }

        @Override
        public Object getPrincipal() {
            return username;  // Возвращаем имя пользователя
        }

}
