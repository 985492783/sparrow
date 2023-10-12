package com.sparrow.convert;

import com.sparrow.common.entity.InstanceDO;
import com.sparrow.common.entity.Instance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 985492783@qq.com
 * @date 2023/10/12 22:39
 */
@Mapper
public interface InstanceConvert {

    InstanceConvert INSTANCE = Mappers.getMapper(InstanceConvert.class);
    
    Instance map(InstanceDO instanceDO);
    
    InstanceDO map(Instance instance);
}
