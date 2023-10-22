package com.sparrow.convert;

import com.sparrow.common.entity.LogMessage;
import com.sparrow.common.entity.LogMessageDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 985492783@qq.com
 * @date 2023/10/22 22:59
 */
@Mapper
public interface LogConvert {
    
    LogConvert INSTANCE = Mappers.getMapper(LogConvert.class);
    
    LogMessage map(LogMessageDO logMessageDO);
}
