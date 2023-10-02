package bjit.ursa.authserver.service.impl;

import bjit.ursa.authserver.entity.RoleEntity;
import bjit.ursa.authserver.entity.RoleEnum;
import bjit.ursa.authserver.exception.RoleDoesNotExistException;
import bjit.ursa.authserver.repositoty.RoleRepository;
import bjit.ursa.authserver.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public RoleEntity addRole(RoleEnum roleName) {
        if (roleRepository.findByRoleName(roleName.name()).isEmpty()) {
            RoleEntity role = new RoleEntity();
            role.setRoleName(roleName.name());
            return roleRepository.save(role);
        }
        return null;
    }

    @Override
    public RoleEntity getRole(String roleName) {
        Optional<RoleEntity> roleEntity = roleRepository.findByRoleName(roleName);
        if (roleEntity.isPresent()) {
            return roleEntity.get();
        } else {
            throw new RoleDoesNotExistException("Your Requested '" + roleName + "' Role Does Not Exist");
        }
    }
}