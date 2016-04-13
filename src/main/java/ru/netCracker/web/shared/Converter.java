package ru.netCracker.web.shared;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rewweRrr on 22.03.2016
 */
public class Converter {

    public List<List<Integer>> arrayListToList(ArrayList<ArrayList<Integer>> arrayLists) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        for (ArrayList<Integer> arrayList : arrayLists) {
            List<Integer> list = new ArrayList<Integer>();
            for (Integer i : arrayList) {
                list.add(i);
            }
            lists.add(list);
        }
        return lists;
    }

    public ArrayList<ArrayList<Integer>> listToArrayList(List<List<Integer>> lists) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<ArrayList<Integer>>();
        for (List<Integer> list : lists) {
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            for (Integer i : list) {
                arrayList.add(i);
            }
            arrayLists.add(arrayList);
        }
        return arrayLists;
    }
}
