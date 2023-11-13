package com.label.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.label.core.pojo.entity.UserAccount;
import com.label.core.pojo.vo.Label.GetSaveEditDataItem;
import com.label.core.pojo.vo.Label.LabelDataItem;
import com.label.core.pojo.vo.Label.commitItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LabelDataMapper extends BaseMapper<LabelDataItem> {
      String getLabelAudioDataByKey(@Param("key")String key);

      long batchInsert(@Param("key")String key,@Param("list")List<commitItem> list);

      long upData(@Param("text")String text,@Param("key")String key);

      long remove(String key);

     String getSaveEditData(@Param("key") String key);

     Long saveLabelData(@Param("key") String key,@Param("json") String json);

}
