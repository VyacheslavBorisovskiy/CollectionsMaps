package com.borisovskiy.collectionsmaps.model;

import com.borisovskiy.collectionsmaps.dto.CalculationDTO;

import java.util.List;

public interface IModel {

    List<CalculationDTO> getCollectionsItems();

    List<String> getCollectionsTags();

    List<CalculationDTO> getMapsItems();

    List<String> getMapsTags();


}