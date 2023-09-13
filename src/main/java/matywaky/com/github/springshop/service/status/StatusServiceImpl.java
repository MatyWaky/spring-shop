package matywaky.com.github.springshop.service.status;

import matywaky.com.github.springshop.model.Status;
import matywaky.com.github.springshop.repository.StatusRepository;

public class StatusServiceImpl implements StatusService {

    private StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status findStatusByName(String name) {
        return statusRepository.findByName(name);
    }
}
