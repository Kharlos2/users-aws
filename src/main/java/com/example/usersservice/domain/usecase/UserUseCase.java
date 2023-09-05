package com.example.usersservice.domain.usecase;

import com.example.usersservice.application.exception.*;
import com.example.usersservice.domain.api.IUserServicePort;
import com.example.usersservice.domain.model.User;
import com.example.usersservice.domain.spi.IUserPersistencePort;
import com.example.usersservice.domain.usecase.validation.Validations;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public void saveUser(User user){
        if (Validations.emptyFields(user)) throw new EmptyFields();
        else if (Validations.cellSize(user.getPhone())) throw new CellVeryLong();
        else if (Validations.cellStructure(user.getPhone())) throw new InvalidCell();
        else if (Validations.emailStructure(user.getEmail())) throw new InvalidEmail();
        else if (Validations.younger(user.getBirthdate())) throw new Younger();
        else if (Validations.correctAge(user.getBirthdate())) throw new InvalidAge();
        else if (Validations.validateDocument(user.getDocumentNumber())) throw new DocumentWithLetters();
        userPersistencePort.saveUser(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userPersistencePort.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }

    @Override
    public User getUserByEmail(String email) {
        return userPersistencePort.getUserByEmail(email);
    }

    @Override
    public void deleteUser(Long id) {
        userPersistencePort.deleteUser(id);
    }


}
