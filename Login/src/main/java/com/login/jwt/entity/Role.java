package com.login.jwt.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Role {

    @Id
    private String roleName;
    private String roleDescription;

//    public String getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }
//
//    public String getRoleDescription() {
//        return roleDescription;
//    }
//
//    public void setRoleDescription(String roleDescription) {
//        this.roleDescription = roleDescription;
//    }
}
