package com.zeeshan.tms.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssetTypeMapperTest {

    private AssetTypeMapper assetTypeMapper;

    @BeforeEach
    public void setUp() {
        assetTypeMapper = new AssetTypeMapperImpl();
    }
}
