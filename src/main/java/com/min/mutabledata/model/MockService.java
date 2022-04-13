package com.min.mutabledata.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MockService {
    @Autowired
    private MockRepository mockRepository;

    public List<MockEntity> findAllPage(int offset, int limit){
        return mockRepository.findAll(PageRequest.of(offset,limit)).toList();
    }

    public List<MockEntity> getAll(){
        return (List<MockEntity>) mockRepository.findAll();
    }

    public Optional<MockEntity> get(Long id){
        return mockRepository.findById(id);
    }

    public MockEntity insert(MockEntity mockEntity) {
        return mockRepository.save(mockEntity);
    }

    public MockEntity update(Long id, MockEntity mockEntity) {
        mockEntity.setId(id);
        return mockRepository.save(mockEntity);
    }

    public void delete(Long id) {
        mockRepository.deleteById(id);
    }

    public MockEntity patch(Long id, MockEntity mockEntity) {
        Optional<MockEntity> optional = get(id);
        mockEntity.setId(id);
        return optional.map(entity -> mockRepository.save(compPatch(mockEntity, entity))).orElse(null);
    }

    public MockEntity compPatch(MockEntity target, MockEntity res){
        String app_name = target.getApp_name()!=null?target.getApp_name():res.getApp_name();
        String url = target.getApp_name()!=null?target.getApp_name():res.getApp_name();
        String company = target.getApp_name()!=null?target.getApp_name():res.getApp_name();
        String email = target.getApp_name()!=null?target.getApp_name():res.getApp_name();

        target.setApp_name(app_name);
        target.setUrl(url);
        target.setCompany(company);
        target.setEmail(email);

        return target;
    }
}
