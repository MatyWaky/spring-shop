package matywaky.com.github.springshop.service.role;

import matywaky.com.github.springshop.model.Role;
import matywaky.com.github.springshop.repository.RoleRepository;

public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
