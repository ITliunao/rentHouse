package com.xmmxjy.generator;

import com.xmmxjy.generator.def.FtlDef;
import com.xmmxjy.generator.factory.CodeGenerateFactory;

import java.util.concurrent.Callable;

public class CodeGenerateTask implements Callable<Boolean> {
    private TableInfo tableInfo;

    public CodeGenerateTask(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public Boolean call() throws Exception {
        boolean flag = false;

        try {
            CodeGenerateFactory.codeGenerateByFTL(this.tableInfo.getTableName(), this.tableInfo.getTableComment(), FtlDef.KEY_TYPE_02);
            flag = true;
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return Boolean.valueOf(flag);
    }
}
