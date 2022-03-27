package com.javainuse.model;

import java.io.IOException;
import java.io.InputStreamReader;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;
import com.javainuse.model.Product;

public class Flag {
    private String status;
    private int progress;

//    public Flag(String status, int progress) {
//        if (progress < 0 || progress > 100) {
//            System.err.println("Invalid progress amount entered");
//        } else if (progress > 0 && progress < 50) {
//            this.progress = progress;
//            this.status = "red";
//        } else if (progress > 50 && progress < 70) {
//            this.progress = progress;
//            this.status = "yellow";
//        } else if (progress > 70) {
//            this.progress = progress;
//            this.status = "green";
//        }
//    }

    public Flag(String status, int progress) {
        this.status = status;
        this.progress = progress;
    }

    public Flag() {
        this.status = "red";
        this.progress = 0;
    }

    public String getStatus() {
        return status;
    }

    public int getProgress() {
        return progress;
    }

    //    In the unlikely event the status needs to manually be set
    public void setStatus(String status) {
        this.status = status;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }


}
