package com.zeeshan.tms.service.mapper;

import com.zeeshan.tms.domain.AssetType;
import com.zeeshan.tms.service.dto.AssetTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AssetType} and its DTO {@link AssetTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface AssetTypeMapper extends EntityMapper<AssetTypeDTO, AssetType> {}
