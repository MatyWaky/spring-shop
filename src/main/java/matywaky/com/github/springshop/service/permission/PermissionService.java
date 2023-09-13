package matywaky.com.github.springshop.service.permission;

import matywaky.com.github.springshop.model.Permission;

public interface PermissionService {
    Permission findPermissionByName(String name);
}
