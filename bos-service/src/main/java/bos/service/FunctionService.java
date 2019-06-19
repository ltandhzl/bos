package bos.service;

import bos.dao.FunctionDao;
import bos.pojo.Function;
import bos.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FunctionService {
    @Autowired
    private FunctionDao functionDao;

    public List<Function> findAllFunction() {
        return functionDao.findAllFunction();
    }

    public int getAllFunctionSize(PageUtils pageUtils) {

        return functionDao.getAllFunctionSize(pageUtils);
    }

    public List<Function> getAllFunction(PageUtils pageUtils) {
        return functionDao.getAllFunction(pageUtils);
    }

    public void addFunction(Function model) {
        functionDao.save(model);
    }

    public Function findParentNameById(String id) {
        return  functionDao.findParentNameById(id);
    }

    public void editFunction(Function model) {
        functionDao.editFunction(model);
    }
}
