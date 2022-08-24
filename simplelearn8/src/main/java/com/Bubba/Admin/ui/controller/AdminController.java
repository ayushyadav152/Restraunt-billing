package com.Bubba.Admin.ui.controller;

import com.Bubba.Admin.data.AdminRepository;
import com.Bubba.Admin.data.UserEntity;
import com.Bubba.Admin.data.UserRepository;
import com.Bubba.Admin.service.AdminService;
import com.Bubba.Admin.shared.MenuDto;
import com.Bubba.Admin.shared.UserDto;
import com.Bubba.Admin.ui.model.CreateMenuRequestModel;
import com.Bubba.Admin.ui.model.CreateUserRequestModel;
import com.Bubba.Admin.ui.model.LoginRequestModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tippy")

public class AdminController {


    @Autowired
    Environment environment;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    AdminService adminService;

    @PostMapping("/createuser")
    public String createUser(@RequestBody CreateUserRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        adminService.createUser(userDto);
        return "new user will be created here";
    }

    @PostMapping
    public String createitem(@RequestHeader String jwt, @RequestBody CreateMenuRequestModel menuDetails) {

        String roleIs = adminService.getRoleFromToken(jwt);

        if (roleIs.equals("admin")) {


            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            MenuDto menuDto = modelMapper.map(menuDetails, MenuDto.class);
            adminService.createMenu(menuDto);
            return "new tem will be created here";
        }
        return "not allowed";
    }

//    @PostMapping(value = "/login",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
//    )
//    public String  AuthenticateUser(@RequestBody LoginRequestModel loginRequestModel, HttpServletResponse res)
//    {
//
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//
//        UserDto userDto = modelMapper.map(loginRequestModel,UserDto.class);
//        res.addHeader("token",AdminService.loginUser(userDto));
//
//        return "User is authenticated";
//
//    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestModel loginRequestModel) {
        String requestEmail = loginRequestModel.getEmail();
        String requestPassword = loginRequestModel.getPassword();
        UserEntity userEntity = userRepository.findByEmail(requestEmail);

        if (userEntity == null) {
            //NO USER
        }
        if (!userEntity.getPassword().equals(requestPassword)) {
            //WRONG PASSWORD
        }
        String role = userEntity.getRole();

        String token = adminService.generateToken(role);

        String returnValue = "LOGIN__SUCCESSFUL  :  " + token;
        return returnValue;

    }

    @PutMapping
    public String updateitem() {
        return "item will be updated here ";
    }

    @DeleteMapping(path="/remove/{id}")
    public String deleteitem(@RequestHeader String jwt,@PathVariable Long id) {

        String roleIs = adminService.getRoleFromToken(jwt);

        if (roleIs.equals("admin")){

            adminRepository.deleteById(id);
            return "item will be deleted here";
         }
        return "not allowed";
    }


}
