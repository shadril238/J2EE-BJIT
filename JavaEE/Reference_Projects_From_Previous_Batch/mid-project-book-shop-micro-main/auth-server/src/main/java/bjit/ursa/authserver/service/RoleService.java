package bjit.ursa.authserver.service;

import bjit.ursa.authserver.entity.RoleEntity;
import bjit.ursa.authserver.entity.RoleEnum;

public interface RoleService {
    public RoleEntity addRole(RoleEnum role);

    public RoleEntity getRole(String roleName);
}