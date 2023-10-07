package matywaky.com.github.springshop.service.role;

import matywaky.com.github.springshop.dto.RoleDto;
import matywaky.com.github.springshop.model.Role;

import java.util.List;

public interface RoleService {
    Role findRoleByName(String name);
    List<RoleDto> findAllRoles();
}
