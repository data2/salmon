package com.muskteer.dico.factory;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class BuildFactoryTest {

    @Test
    public void test() {
        Pattern p = Pattern.compile("\\#\\w+\\#");
        Matcher m = p.matcher("sfssf   #aiW_# sdf  #sd#");
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}