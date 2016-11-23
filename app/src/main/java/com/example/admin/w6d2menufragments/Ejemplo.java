package com.example.admin.w6d2menufragments;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SparseArrayCompat;
import android.util.SparseArray;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by admin on 11/23/2016.
 */

public class Ejemplo {
    public static void main(String[] args) {

        SparseArrayCompat sparseArray = new SparseArrayCompat();
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(2, 125);
        arrayMap.put("user1", new User("Alberto","Bernal", 25));
        arrayMap.put("numero1", 125);
        arrayMap.put(1,"Hola");
        arrayMap.put("user2", new User("Alberto","Salgado", 33));
        sparseArray.append(1,"Quiubo");
        sparseArray.append(2, 6);
        sparseArray.append(3, "Google");
        sparseArray.append(2, 23);


        for (int i = 0; i < sparseArray.size(); i++){
            System.out.println(sparseArray.get(sparseArray.keyAt(i)));
        }

        Set set = arrayMap.keySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()){
            System.out.println(arrayMap.get(iterator.next()));
        }
    }
}
