package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaMapProgram {

	public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<String, String>();
        
        map.put("Rajesh", "Jaya");
        map.put("Shashi", "Yash");
        map.put("Shubham", "Jaya");
        map.put("Prerna", "Shubham");
        map.put("Sanjeev", "Shubham");
        map.put("PremLatta", "Shubham");
        
		/*
		 * Set<Entry<String, String>> entrySet = map.entrySet(); for (Entry<String,
		 * String> entry : entrySet) {
		 * System.out.println(entry.getValue()+" : "+entry.getKey()); }
		 */
        
        Map<String, ArrayList<String>> reverseMap = new HashMap<>(
        	    map.entrySet().stream()
        	        .collect(Collectors.groupingBy(Map.Entry::getValue)).values().stream()
        	        .collect(Collectors.toMap(
        	                item -> item.get(0).getValue(),
        	                item -> new ArrayList<>(
        	                    item.stream()
        	                        .map(Map.Entry::getKey)
        	                        .collect(Collectors.toList())
        	                ))
        ));

        
        
        	System.out.println(reverseMap);
        System.out.println("-------------------------");
	}

}
