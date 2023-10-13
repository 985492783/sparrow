package com.sparrow.convert;

import com.sparrow.common.entity.ExecutorData;
import com.sparrow.common.entity.ExecutorDataDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 985492783@qq.com
 * @date 2023/10/13 14:39
 */
@Mapper
public interface ExecutorConvert {
    
    ExecutorConvert INSTANCE = Mappers.getMapper(ExecutorConvert.class);
    
    ExecutorData map(ExecutorDataDO executorDataDO);
    
    ExecutorDataDO map(ExecutorData executorData);
    
    List<ExecutorData> map(List<ExecutorDataDO> executorDataDO);
}
