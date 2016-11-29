package com.xmmxjy.generator.factory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ToolUtil {
    public ToolUtil() {
    }

    public static void writeFile(File file, String mesInfo) throws IOException {
        if(file == null) {
            throw new IllegalStateException("logFile can not be null!");
        } else {
            FileWriter txtWriter = new FileWriter(file, true);
            txtWriter.write(mesInfo + "\n");
            txtWriter.flush();
        }
    }
}
