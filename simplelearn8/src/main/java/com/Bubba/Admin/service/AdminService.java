package com.Bubba.Admin.service;

import com.Bubba.Admin.shared.MenuDto;
import com.Bubba.Admin.shared.UserDto;

public interface AdminService {

   String getRoleFromToken(String token);
   String generateToken(String role);
   MenuDto createMenu(MenuDto menuDetails);
   UserDto createUser(UserDto userDetails);

}
