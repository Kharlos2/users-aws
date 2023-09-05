package com.example.usersservice.infrastructure.configuration;

import com.example.usersservice.domain.api.IUserServicePort;
import com.example.usersservice.domain.spi.IUserPersistencePort;
import com.example.usersservice.domain.usecase.UserUseCase;
import com.example.usersservice.infrastructure.out.jpa.adapter.UserAdapter;
import com.example.usersservice.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.example.usersservice.infrastructure.out.jpa.repository.IUserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserEntityRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

}
