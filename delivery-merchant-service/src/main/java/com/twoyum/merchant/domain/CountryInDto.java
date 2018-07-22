package com.twoyum.merchant.domain;

import com.amazonaws.services.dynamodbv2.xspec.S;
import lombok.Data;

@Data
public class CountryInDto {
    private String typeCode;//语言类型
    private String token;
}
