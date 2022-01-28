package com.kth.lab3;

import java.util.Iterator;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Stopwatch;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();

        URL url = ClassLoader.getSystemResource("kap1.txt");

        if (url != null) {
            System.out.println("Reading from: " + url);
        } else {
            System.out.println("Couldn't find file: kap1.txt");
        }

        In input = new In(url);

        Stopwatch sw = new Stopwatch();
        while (!input.isEmpty()) {
            String line = input.readLine().trim();
            String[] words = line.split("(\\. )|:|,|;|!|\\?|( - )|--|(\' )| ");
            String lastOfLine = words[words.length - 1];

            if (lastOfLine.endsWith(".")) {
                words[words.length - 1] = lastOfLine.substring(0, lastOfLine.length() - 1);
            }


            for (String word : words) {
                String word2 = word.replaceAll("\"|\\(|\\)", "");

                if (word2.isEmpty()) {
                    continue;
                }

                //System.out.println(word2);
                trie.put(word2.toLowerCase());
            }
        }
                System.out.println("1) What are the 10 words with the highest frequency?");
        System.out.println(Arrays.toString(minMaxFrequency(trie, true)) + "\n");

        System.out.println("2) What are the 10 words with the lowest frequency?");
        System.out.println(Arrays.toString(minMaxFrequency(trie, false)) + "\n");

        System.out.println("3) Which prefix of length 2 has the highest frequency?");
        System.out.println(prefixLengthHighestFrequency(trie, 2) + "\n");

        System.out.println("4) What is the letter that the most different words start with? (Not frequency this time.)");
        System.out.println(mostCommonFirstLetter(trie) + "\n");
      
    }

    public static Entry[] minMaxFrequency (Trie trie, boolean max) {
        final int reverser = max == true ? 1 : -1;

        PriorityQueue<Entry<String, Integer>> pq = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Entry<String, Integer> e1 = (Entry<String, Integer>)o1;
                Entry<String, Integer> e2 = (Entry<String, Integer>)o2;
                if (e1.getValue() > e2.getValue()) { return -1 * reverser; }
                if (e2.getValue() > e1.getValue()) { return 1 * reverser; }
                return 0;
            }
        });

        Iterator<java.util.Map.Entry<String, Integer>> iterator = trie.iterator("");
        while (iterator.hasNext()) {
            pq.add(iterator.next());
        }

        Entry[] res = new Entry[10];
        for (int i = 0; i < res.length; i += 1) {
            res[i] = pq.poll();
        }

        return res;
    }

    public static String prefixLengthHighestFrequency (Trie trie, int length) {
        Iterator<java.util.Map.Entry<String, Integer>> iterator = trie.iterator("");

        String maxPrefix = null;
        int maxCount = 0;

        String currPrefix = null;
        int currentCount = 0;

        while (iterator.hasNext()) {
            Entry<String, Integer> word = iterator.next();

            if (word.getKey().length() >= length) {
                String comparePrefix = word.getKey().substring(0, length);
                if(!comparePrefix.equals(currPrefix)) {
                    if (currentCount > maxCount || maxPrefix == null) {
                        maxCount = currentCount;
                        maxPrefix = currPrefix;
                    }
                    currentCount = 0;
                    currPrefix = comparePrefix;
                }
                currentCount += word.getValue();
            }
        }

        if (currentCount > maxCount) {
            maxPrefix = currPrefix;
        }

        return maxPrefix;
    }

    public static String mostCommonFirstLetter (Trie trie) {
        Iterator<java.util.Map.Entry<String, Integer>> iterator = trie.iterator("");

        String maxPrefix = null;
        int maxCount = 0;

        String currPrefix = null;
        int currentCount = 0;

        while (iterator.hasNext()) {
            Entry<String, Integer> word = iterator.next();

            if (word.getKey().length() >= 1) {
                String comparePrefix = word.getKey().substring(0, 1);
                if(!comparePrefix.equals(currPrefix)) {
                    if (currentCount > maxCount || maxPrefix == null) {
                        maxCount = currentCount;
                        maxPrefix = currPrefix;
                    }
                    currentCount = 0;
                    currPrefix = comparePrefix;
                }
                currentCount += 1;
            }
        }

        if (currentCount > maxCount) {
            maxPrefix = currPrefix;
        }

        return maxPrefix;
    }
}