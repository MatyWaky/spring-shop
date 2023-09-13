package matywaky.com.github.springshop.service.permission;

import matywaky.com.github.springshop.model.Permission;
import matywaky.com.github.springshop.repository.PermissionRepository;

public class PermissionServiceImpl implements PermissionService {

    private PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission findPermissionByName(String name) {
        return permissionRepository.findByName(name);
    }
}
