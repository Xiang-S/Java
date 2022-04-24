package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TableDao {
    static List<Table> list = new ArrayList<>();
    //按照感染人数升序排序
    static void orderByTotalCases() {
        Collections.sort(list, new Comparator<Table>() {
            @Override
            public int compare(Table o1, Table o2) {
                return o1.getTotalCases() - o2.getTotalCases();
            }
        });
    }
    //查找死亡数最多
    static int maxDeaths() {
        int val = 0;
        for (Table t : list) {
            val = t.getTotalDeath() > val ? t.getTotalDeath() : val;
        }
        return val;
    }
    //输出最多死亡数的国家所有数据
    static void printMaxDeathsCountry() {
        for (Table t : list) {
            if (t.getTotalDeath() == maxDeaths()) {
                System.out.println(t);
            }
        }
    }
    //查找总测试数最多
    static int maxTotalTests() {
        int val = 0;
        for (Table t : list) {
            val = t.getTotalTests() > val ? t.getTotalTests() : val;
        }
        return val;
    }
    //输出总测试数最多的国家所有数据
    static void printMaxTotalTestsCountry() {
        for (Table t : list) {
            if (t.getTotalTests() == maxTotalTests()) {
                System.out.println(t);
            }
        }
    }
    //打印表格所有数据
    static void print() {
        for (Table t : list) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) throws IOException {
        Document doc;
        String filePath = "COVID-19.html";
        File file = new File(filePath);
        doc = Jsoup.parse(file, "UTF-8");
        Elements rows = doc.select("tr");
        boolean isHeader = true;
        for (Element row : rows) {
            int i = 0;
            if (isHeader) {
                isHeader = false;
                continue;
            }
            Table table = new Table();
            list.add(table);
            Elements cols = row.select("td");
            for (Element col : cols) {
                switch (i) {
                    case 0:
                        table.setCountry(col.text());
                        break;
                    case 1:
                        table.setTotalCases(Integer.parseInt(col.text()));
                        break;
                    case 3:
                        table.setTotalDeath(Integer.parseInt(col.text()));
                        break;
                    case 9:
                        table.setTotalTests(Integer.parseInt(col.text()));
                        break;
                }
                ++i;
            }
        }
        print();
    }
}