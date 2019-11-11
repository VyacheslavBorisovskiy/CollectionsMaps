package com.borisovskiy.collectionsmaps.model;

import com.borisovskiy.collectionsmaps.R;
import com.borisovskiy.collectionsmaps.dto.CalculationDTO;

import java.util.ArrayList;
import java.util.List;

public class Model implements IModel {
    @Override
    public List<CalculationDTO> getCollectionsItems() {
        List<CalculationDTO> calculationDTOItems = new ArrayList<>();

        CalculationDTO calculationDTO1 = new CalculationDTO(R.string.add_start_arraylist_label, "Adding to start in ArrayList");
        calculationDTOItems.add(calculationDTO1);

        CalculationDTO calculationDTO2 = new CalculationDTO(R.string.add_start_linkedlist_label, "Adding to start in LinkedList");
        calculationDTOItems.add(calculationDTO2);

        CalculationDTO calculationDTO3 = new CalculationDTO(R.string.add_start_copywritelist_label, "Adding to start in CopyWriteArrayList");
        calculationDTOItems.add(calculationDTO3);

        CalculationDTO calculationDTO4 = new CalculationDTO(R.string.add_middle_arraylist_label, "Adding to middle in ArrayList");
        calculationDTOItems.add(calculationDTO4);

        CalculationDTO calculationDTO5 = new CalculationDTO(R.string.add_middle_linkedlist_label, "Adding to middle in LinkedList");
        calculationDTOItems.add(calculationDTO5);

        CalculationDTO calculationDTO6 = new CalculationDTO(R.string.add_middle_copywritelist_label, "Adding to middle in CopyWriteArrayList");
        calculationDTOItems.add(calculationDTO6);

        CalculationDTO calculationDTO7 = new CalculationDTO(R.string.add_end_arraylist_label, "Adding to end in ArrayList");
        calculationDTOItems.add(calculationDTO7);

        CalculationDTO calculationDTO8 = new CalculationDTO(R.string.add_end_linkedlist_label, "Adding to end in LinkedList");
        calculationDTOItems.add(calculationDTO8);

        CalculationDTO calculationDTO9 = new CalculationDTO(R.string.add_end_copywritelist_label, "Adding to end in CopyWriteArrayList");
        calculationDTOItems.add(calculationDTO9);

        CalculationDTO calculationDTO10 = new CalculationDTO(R.string.search_arraylist_label, "Search in ArrayList");
        calculationDTOItems.add(calculationDTO10);

        CalculationDTO calculationDTO11 = new CalculationDTO(R.string.search_linkedlist_label, "Search in LinkedList");
        calculationDTOItems.add(calculationDTO11);

        CalculationDTO calculationDTO12 = new CalculationDTO(R.string.search_copywritelist_label, "Search in CopyWriteArrayList");
        calculationDTOItems.add(calculationDTO12);

        CalculationDTO calculationDTO13 = new CalculationDTO(R.string.remove_start_arraylist_label, "Removing from start in ArrayList");
        calculationDTOItems.add(calculationDTO13);

        CalculationDTO calculationDTO14 = new CalculationDTO(R.string.remove_start_linkedlist_label, "Removing from start in LinkedList");
        calculationDTOItems.add(calculationDTO14);

        CalculationDTO calculationDTO15 = new CalculationDTO(R.string.remove_start_copywritelist_label, "Removing from start in CopyWriteArrayList");
        calculationDTOItems.add(calculationDTO15);

        CalculationDTO calculationDTO16 = new CalculationDTO(R.string.remove_middle_arraylist_label, "Removing from middle in ArrayList");
        calculationDTOItems.add(calculationDTO16);

        CalculationDTO calculationDTO17 = new CalculationDTO(R.string.remove_middle_linkedlist_label, "Removing from middle in LinkedList");
        calculationDTOItems.add(calculationDTO17);

        CalculationDTO calculationDTO18 = new CalculationDTO(R.string.remove_middle_copywritelist_label, "Removing from middle in CopyWriteArrayList");
        calculationDTOItems.add(calculationDTO18);

        CalculationDTO calculationDTO19 = new CalculationDTO(R.string.remove_end_arraylist_label, "Removing from end in ArrayList");
        calculationDTOItems.add(calculationDTO19);

        CalculationDTO calculationDTO20 = new CalculationDTO(R.string.remove_end_linkedlist_label, "Removing from end in LinkedList");
        calculationDTOItems.add(calculationDTO20);

        CalculationDTO calculationDTO21 = new CalculationDTO(R.string.remove_end_copywritelist_label, "Removing from end in CopyWriteArrayList");
        calculationDTOItems.add(calculationDTO21);

        return calculationDTOItems;
    }

    @Override
    public List<String> getCollectionsTags() {
        List<CalculationDTO> calculationDTOItems = getCollectionsItems();
        List<String> tags = new ArrayList<>();

        for (int i = 0; i < calculationDTOItems.size(); i++) {
            tags.add(calculationDTOItems.get(i).getTag());
        }

        return tags;
    }

    @Override
    public List<CalculationDTO> getMapsItems() {
        List<CalculationDTO> calculationDTOItems = new ArrayList<>();

        CalculationDTO calculationDTO1 = new CalculationDTO(R.string.add_new_treemap_label, "Adding new in TreeMap");
        calculationDTOItems.add(calculationDTO1);

        CalculationDTO calculationDTO2 = new CalculationDTO(R.string.add_new_hashmap_label, "Adding new in HashMap");
        calculationDTOItems.add(calculationDTO2);

        CalculationDTO calculationDTO3 = new CalculationDTO(R.string.search_treemap_label, "Search by key in TreeMap");
        calculationDTOItems.add(calculationDTO3);

        CalculationDTO calculationDTO4 = new CalculationDTO(R.string.search_hashmap_label, "Search by key in HashMap");
        calculationDTOItems.add(calculationDTO4);

        CalculationDTO calculationDTO5 = new CalculationDTO(R.string.remove_treemap_label, "Removing in TreeMap");
        calculationDTOItems.add(calculationDTO5);

        CalculationDTO calculationDTO6 = new CalculationDTO(R.string.remove_hashmap_label, "Removing in HashMap");
        calculationDTOItems.add(calculationDTO6);

        return calculationDTOItems;
    }

    @Override
    public List<String> getMapsTags() {
        List<CalculationDTO> calculationDTOItems = getMapsItems();
        List<String> tags = new ArrayList<>();

        for (int i = 0; i < calculationDTOItems.size(); i++) {
            tags.add(calculationDTOItems.get(i).getTag());
        }

        return tags;
    }
}

