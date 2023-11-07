package com.label.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.label.core.pojo.vo.Label.LabelDataItem;
import com.label.core.pojo.vo.model.Model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModelDataMapper extends BaseMapper<LabelDataItem> {

        Long insertIntoModel (@Param("key")String key,
                              @Param("createTime")String createTime,
                              @Param("name")String name,
                              @Param("updateTime")String updateTime,
                              @Param("json")String json);


        Long DeleteModelByKey(@Param("key") String key);


        Long updateModel(@Param("key")String key,
                         @Param("name")String name,
                         @Param("upDateTime") String upDateTime,
                         @Param("json") String json);

        List<Model> getAllModel();

        String getModelByKey(@Param("key") String key);

        Long setMainModel(@Param("key") String key);

}
