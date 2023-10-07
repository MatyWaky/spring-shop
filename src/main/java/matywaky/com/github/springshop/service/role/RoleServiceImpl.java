package matywaky.com.github.springshop.service.role;

import matywaky.com.github.springshop.dto.RoleDto;
import matywaky.com.github.springshop.model.Product;
import matywaky.com.github.springshop.model.Role;
import matywaky.com.github.springshop.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<RoleDto> findAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(this::mapToRoleDto)
                .collect(Collectors.toList());
    }

    private RoleDto mapToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());
        roleDto.setDescription(role.getDescription());
        return roleDto;
    }
}
