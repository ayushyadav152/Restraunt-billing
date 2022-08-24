package com.Bubba.Admin.service;

import com.Bubba.Admin.data.AdminEntity;

import org.springframework.core.env.Environment;
import com.Bubba.Admin.data.AdminRepository;
import com.Bubba.Admin.data.UserEntity;
import com.Bubba.Admin.data.UserRepository;
import com.Bubba.Admin.shared.MenuDto;
import com.Bubba.Admin.shared.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service

public class AdminServiceImpl implements AdminService{

    AdminRepository adminRepository;
    UserRepository userRepository;

    Environment environment;

    @Autowired
   public AdminServiceImpl(AdminRepository adminRepository, UserRepository userRepository,  Environment environment)

    {
        this.environment = environment;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public MenuDto createMenu(MenuDto menuDetails)
    {
        menuDetails.setItemid(UUID.randomUUID().toString());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        AdminEntity adminEntity = modelMapper.map(menuDetails,  AdminEntity.class );

        adminRepository.save(adminEntity);

        return null;
    }

//    public String generateToken(String email,String role)
//    {
//        String jwtToken= Jwts.builder()
//                .claim("name",email )
//                .setSubject(role)
//                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time")  )))
//                .signWith(SignatureAlgorithm.HS512, environment.getP("token.secret"))
//                .compact();
//        return jwtToken;
//
//    }

    @Override
    public String generateToken(String role){


        return Jwts.builder()
                //  .setClaims(claims)
                .setSubject(role)
                .signWith(SignatureAlgorithm.HS512, "Hidde87n9hjm8i9")
                .compact();
    }
    private  Claims getClaimsFromToken(String token){
        Claims claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey("Hidde87n9hjm8i9")
                    .parseClaimsJws((token))
                    .getBody();
        } catch(Exception exp){
            claims = null;
        }
        return claims;
    }



    @Override
    public String getRoleFromToken(String token) {
        String username;

        try{
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch(Exception e){
            username = null;
        }
        return username;
    }




    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());
//        userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDetails,  UserEntity.class );

        userRepository.save(userEntity);

        return null;
    }
}
