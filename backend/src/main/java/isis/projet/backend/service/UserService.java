package isis.projet.backend.service;

import isis.projet.backend.dao.UserRepository;
import isis.projet.backend.dto.UserDTO;
import isis.projet.backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? userMapper.toDto(user) : null;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setNom(userDTO.getNom());
            user.setMail(userDTO.getMail());
            User updatedUser = userRepository.save(user);
            return userMapper.toDto(updatedUser);
        }
        return null;
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}