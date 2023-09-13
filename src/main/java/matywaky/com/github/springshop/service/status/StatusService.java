package matywaky.com.github.springshop.service.status;

import matywaky.com.github.springshop.model.Status;

public interface StatusService {

    Status findStatusByName(String name);
}
