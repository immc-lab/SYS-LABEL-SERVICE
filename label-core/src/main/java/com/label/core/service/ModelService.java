package com.label.core.service;

import com.label.core.pojo.vo.model.Model;

import java.util.List;

public interface ModelService {
    public boolean updateModel(String key,String json,String name);

    public boolean deleteByKey(String key);

    public boolean insertModel(String key,String name,String json);

    public List<Model> getAllModel();

    public String getModelByKey(String key);

    public boolean setMainModel(String key);

    public String getMainModel();
}
