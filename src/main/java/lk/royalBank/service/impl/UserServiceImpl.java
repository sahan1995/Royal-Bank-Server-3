package lk.royalBank.service.impl;

import lk.royalBank.dto.ClientDTO;
import lk.royalBank.dto.EmployeeDTO;
import lk.royalBank.dto.UserDTO;
import lk.royalBank.entity.User;
import lk.royalBank.repository.UserRepository;
import lk.royalBank.service.UserService;
import lk.royalBank.util.LoginDTO;
import lk.royalBank.util.LoginUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void addUser(String UserName, UserDTO userDTO) {
        if (!UserName.equals(userDTO.getUserName())) {
            throw new RuntimeException("ID's Are Not Same");
        }

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userRepository.save(user);

    }

    @Override
    public LoginUserDTO login(LoginDTO loginDTO) {
        Optional<User> login = userRepository.findById(loginDTO.getUserName());

        if (login.isPresent()) {
            User user = login.get();
            RestTemplate restTemplate = new RestTemplate();
            if(user.getUserName().equals(loginDTO.getUserName()) && user.getPassword().equals(loginDTO.getPassword())){
                if (user.getRole().equals("Client")) {
                    System.out.println(user.getID());
                    ClientDTO cliientDTO = restTemplate.getForEntity("http://192.168.1.101:8080/api/v1/clients/" + user.getID(), ClientDTO.class).getBody();
                    return new LoginUserDTO(cliientDTO.getClientID(),cliientDTO.getFname(),cliientDTO.getLname(),"Client");
                }else{
//                    calling server 2 service for get employee by ID

                    System.out.println(user.getID());

                        ResponseEntity<EmployeeDTO> responseEntity = restTemplate.getForEntity("http://192.168.1.101:8080/api/v1/employees/"+user.getID(), EmployeeDTO.class);

                        EmployeeDTO employeeDTO = responseEntity.getBody();

                        return new LoginUserDTO(employeeDTO.getEmpID(), employeeDTO.getFname(), employeeDTO.getLname(),employeeDTO.getRole());




                }
            }else {
                System.out.println("2");
                throw new RuntimeException("User Name or Password Invalid !");
            }


        }else{

            throw new RuntimeException("User Name or Password Invalid !");
        }


    }
}
