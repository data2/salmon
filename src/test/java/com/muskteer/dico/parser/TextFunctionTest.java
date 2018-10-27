package com.muskteer.dico.parser;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.muskteer.dico.common.exception.InnerException;
import com.muskteer.dico.common.util.DicoClassLoader;
import org.junit.Test;

import java.io.IOException;

public class TextFunctionTest {

    @Test
    public void excute() throws IOException, InnerException {
        String contents = Resources.toString(
                DicoClassLoader.getClassLoader().getResource("com/barnett/db/engine/inner/Test.co"), Charsets.UTF_8);
        // System.out.println(contents);
        String s = TextFunction.excute(contents, "InsertOrderInfo");
        System.out.println(s);
    }
}