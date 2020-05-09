package com.javaops.run;

import com.javaops.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainSql {

    private static SqlHelper sqlHelper = new SqlHelper(() -> DriverManager.getConnection("jdbc:postgresql://localhost:5432/resumes", "postgres", "postgres"));

    public static void main(String[] args) {


        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM list_section;")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    List<String> list = Stream.of(rs.getString("items").split("\n")).collect(Collectors.toList());
                    list.forEach(s -> {
                        System.out.println("строка " + s);
                    });
                }
            }
            return null;
        });

    }


}
