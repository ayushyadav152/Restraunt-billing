package com.Bubba.Admin.data;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<AdminEntity, Long> {

    AdminEntity findByid(String id);

}
