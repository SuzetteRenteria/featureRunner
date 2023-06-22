package com.example.jokesapp;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class numberConverter{

    public static void fillmp1(SortedMap<Integer, String> map){

        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");
    }


    public static void printmp1(SortedMap<Integer, String> map){
        for(Map.Entry<Integer, String> m: map.entrySet()){
            System.out.println("KEY: " + m.getKey() + " VAL: " + m.getValue());
        }
    }

    public static int userDec(){
        System.out.println("Enter Decimal Value: ");
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    public static String userRom(){
        System.out.println("Enter Roman Value: ");
        Scanner scan = new Scanner(System.in);
        return scan.next().toUpperCase();
    }
    public static SortedMap<String, Integer> mp1Reverse(SortedMap<Integer, String> ogmap){
        /*
        In this example, we start with a SortedMap originalMap where the keys are integers and the
        values are strings. We use the entrySet() method to obtain a Set of key-value pairs from the
         originalMap. Then, we stream over the entries using stream().

        Within the stream, we use the collect() operation to accumulate the inverted entries into a
        new TreeMap. The TreeMap::new method reference creates a new empty TreeMap. The
        (map, entry) -> map.put(entry.getValue(), entry.getKey()) lambda expression is used to put
        each inverted entry into the TreeMap. Finally, the TreeMap::putAll method reference is used
        to combine all the inverted entries into the resulting invertedMap.

        Lastly, we use the forEach() method on the invertedMap to print each entry with the inverted
         keys and values.
         */

        SortedMap<String, Integer> invertedMap = ogmap.entrySet()
                .stream()
                .collect(
                        TreeMap::new,
                        (map, entry) -> map.put(entry.getValue(), entry.getKey()),
                        TreeMap::putAll);
        return  invertedMap;
    }

    public static String decToRom(int dec){
        //NOTE: THIS DOESN'T WORK FOR DECIMAL VALUES BIGGER THAN 3999
        SortedMap<Integer, String> map = new TreeMap<Integer, String>(Comparator.reverseOrder());
        fillmp1(map);
        StringBuilder res = new StringBuilder();

        if(map.containsValue(dec)){
            res.append(map.get(dec));
            return res.toString();
        }

        while(dec != 0){
            int lowBndK = 0;
            String lowBndV = " ";

            //find lower bound in map of dec

            for(Map.Entry<Integer, String> m: map.entrySet()){
                if(m.getKey() <= dec) {
                    lowBndK = m.getKey();
                    lowBndV = m.getValue();
                    break;
                }
            }

            if(dec/lowBndK <= 3) {
                int t = dec / lowBndK;
                if(t < 1){
                    break;
                }
                /*
                //print out to check lower bound and value associated with it
                //System.out.println("i:  " + dec / lowBndK + " t: " + t + " v: " + lowBndV);
                */
                for (int i = 0; i < t; i++) {
                    res.append(lowBndV);
                    dec -= lowBndK;
                }
            }
        }

        return res.toString();
    }

    public static int romToDec(String rom){
        StringBuilder inp = new StringBuilder();
        SortedMap<Integer, String> map = new TreeMap<Integer, String>(Comparator.reverseOrder());
        fillmp1(map);
        SortedMap<String, Integer> mp1Inv = mp1Reverse(map);
        inp.append(rom.toUpperCase()); //from string to string builder

        /* PRINT mp1Inv check
                mp1Inv.forEach((key, value) -> System.out.println("KEY: " + key + " -> " + " VAL: " + value));
         */
        if(mp1Inv.containsKey(rom)){
            return mp1Inv.get(rom);
        }

        int res = 0;
        for(int i = 0; i < rom.length(); i++){
            int iVal = mp1Inv.get(String.valueOf(rom.charAt(i)));

            if((i + 1) < rom.length()){
                int kVal = mp1Inv.get(String.valueOf(rom.charAt(i + 1)));

                if(iVal < kVal){
                    res += kVal - iVal;
                    i++;
                }
                if(iVal >= kVal){
                    res += iVal;
                }
            }else{
                res += iVal;
            }
        }
        return res;
    }



/*
    public static void main(String[] args) {

        SortedMap<Integer, String> mp1 = new TreeMap<Integer, String>(Comparator.reverseOrder());
        fillmp1(mp1);
        //printmp1(mp1);

        //decimal to roman numeral

        int userInputDec = userDec();
        String ansRom = decToRom(userInputDec);
        System.out.println("ROMAN TRANSLATION:   " + ansRom);

        //roman numeral to decimal
        String userInputRom = userRom();
        int ansDec = romToDec(userInputRom);
        System.out.println("DECIMAL TRANSLATION: " + ansDec);
    }
    */
}