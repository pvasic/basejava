package com.javaops.util;

import com.javaops.model.Resume;
import com.javaops.model.Section;
import com.javaops.model.TextSection;
import org.junit.Assert;
import org.junit.Test;

import static com.javaops.storage.ResumeTestData.UUID_1;
import static com.javaops.storage.ResumeTestData.getRESUME1;

public class JsonParserTest {

    @Test
    public void testResume() throws Exception {
        Resume r1 = getRESUME1(UUID_1, "name1");
        String json = JsonParser.write(r1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(r1, resume);
    }

    @Test
    public void write() throws Exception {
        Section section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        Assert.assertEquals(section1, section2);
    }
}