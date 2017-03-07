package com.softuniGameStore.mapper;

import org.modelmapper.PropertyMap;

public interface ModelParser {

    <S, D> D convert(S source, Class<D> destination);

    <S, D> D convert(S source, Class<D> destination, PropertyMap<S, D> propertyMap);
}
