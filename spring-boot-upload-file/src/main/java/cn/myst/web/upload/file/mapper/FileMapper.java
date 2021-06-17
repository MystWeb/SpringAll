package cn.myst.web.upload.file.mapper;

import cn.myst.web.upload.file.model.file.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ziming.xing
 * Create Date：2020/12/25
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {
    int updateBatchSelective(List<File> list);

    int batchInsert(@Param("list") List<File> list);
}