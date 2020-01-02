package org.sustudent.cherry.services.user.cache;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

  public static long parallelSum(long n) {
    return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
  }

  public static void main(String[] args) {

//    List<String> list = getList();
//    System.out.println("strings: size  " + list.size());
//    Long start = System.nanoTime();
//
//    Map<String, String> each1 = each1(list);
//    Long end = System.nanoTime();
//    System.out.println(end - start);
//
//    Map<String, String> each2 = each2(list);
//    System.out.println(System.nanoTime() - end);
//
//    System.out.println("each1: size  " + each1.size());
//    System.out.println("each2: size  " + each2.size());
//    System.out.println(each2.size() == each1.size());
  }

  public static List<String> getList() {
    Random random = new Random();
    List<String> list =
        Stream.iterate(1, n ->
            (int) (Math.random() * 200000) + 1
        ).limit(1000).map(value ->
            value.toString()
        ).collect(Collectors.toList());

    return list;
  }

  public static Map<String, String> each1(List<String> strings) {
    Map<String, String> map = new ConcurrentHashMap<>((int) ((strings.size()) / 0.75F + 1F));
    System.out.println("处理数："  + (Runtime.getRuntime().availableProcessors() - 1) );
    strings.parallelStream().forEach(v -> {
      map.put("循环——" + v, v);
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    return map;
  }

  public static Map<String, String> each2(List<String> strings) {
    Map<String, String> map = new ConcurrentHashMap<>((int) ((strings.size()) / 0.75F + 1F));
    strings.forEach(v -> {
      map.put("循环——" + v, v);
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    return map;
  }
}
