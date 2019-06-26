package com.mappractice.demo.service;

import com.mappractice.demo.domain.User;
import com.mappractice.demo.domain.UserRepository;
import com.mappractice.demo.dto.LoginDTO;
import com.mappractice.demo.dto.UserReturnDTO;
import com.mappractice.demo.dto.UserSignUpDTO;
import com.mappractice.demo.exception.UnAuthorizedException;
import com.mappractice.demo.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserReturnDTO create(UserSignUpDTO userSignUpDTO) {
        if (!userSignUpDTO.passwordConfirm()) {
            throw new UnAuthorizedException("unmatch password");
        }
        User savedAccount = userRepository.save(userSignUpDTO.toEntity());
        return new UserReturnDTO(savedAccount);
    }

    public UserReturnDTO delete(Long id) {
        User account = findById(id);
        userRepository.delete(account);
        return new UserReturnDTO(account);
    }

    public UserReturnDTO update(Long id, UserSignUpDTO userSignUpDTO) {
        User foundUser = userRepository.findById(id)
                .filter(account -> account.matchPassword(userSignUpDTO.getPassword()))
                .orElseThrow(UnAuthorizedException::new);
        foundUser.update(userSignUpDTO);
        return new UserReturnDTO(userRepository.save(foundUser));
    }

    public List<UserReturnDTO> getList() {
        return userRepository.findAll().stream()
                .map(UserReturnDTO::new)
                .collect(Collectors.toList());
    }

    public UserReturnDTO get(Long id) {
        return new UserReturnDTO(findById(id));
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public UserReturnDTO login(HttpSession session, LoginDTO loginDTO) {
        User user = userRepository.findByName(loginDTO.getName()).orElseThrow(UnAuthorizedException::new);
        if(!user.matchPassword(loginDTO.getPassword())){
            throw new UnAuthorizedException("unvalid password");
        }
        session.setAttribute(SessionUtils.USER_SESSION_KEY, user);
        return new UserReturnDTO(user);
    }
}
