package Experimemts;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by i852841 on 5/28/18.
 */
public class MapsExp {

    public static void main(String[] args) {

        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        Map<String, String> map3;

        map1.put("K1", "1_V1");
        map1.put("K2", "1_V2");
        map1.put("K3", "1_V3");
        map1.put("K2", "2_V2");
        map1.put("K4", "2_V4");

        map3 = Stream.of(map1, map2).flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        map3.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));

    }

}
