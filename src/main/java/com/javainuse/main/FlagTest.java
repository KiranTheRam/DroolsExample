package com.javainuse.main;

import java.io.IOException;
import java.io.InputStreamReader;

import com.javainuse.model.Flag;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;
import com.javainuse.model.Product;
// http://www.jbug.jp/trans/jboss-rules3.0.2/ja/html/ch01s06.html#:~:text=A%20RuleBase%20is%20a%20runtime,is%20maintained%2C%20unless%20configured%20otherwise.

public class FlagTest {

    public static void main(String[] args) throws DroolsParserException, IOException {
        DroolsTest droolsTest = new DroolsTest();
        droolsTest.executeDrools();
    }

    public void executeDrools() throws DroolsParserException, IOException {
        String ruleFile = "/com/rule/FlagRules.drl";

        PackageBuilder packageBuilder = new PackageBuilder();
        packageBuilder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( ruleFile) ) );

        org.drools.core.rule.Package rulesPackage = packageBuilder.getPackage();

//		A RuleBase contains one more more packages of rules
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage(rulesPackage);

//		Working Memory: Storage with Facts, where they are used for pattern matching and can be modified, inserted and removed.
//		Holds references to all data that has been "asserted" into it
        WorkingMemory workingMemory = ruleBase.newStatefulSession();

//		Flag 1
        Flag flag1 = new Flag();
        flag1.setProgress(100);
        workingMemory.insert(flag1);		//		Tests the object against 2 conditions (either gold or diamond)
        workingMemory.fireAllRules();		//		Insert product into workingMemory

//		Flag 2
        Flag flag2 = new Flag();
        flag2.setProgress(50);
        workingMemory.insert(flag2);
        workingMemory.fireAllRules();

//		flag 3
        Flag flag3 = new Flag();
        flag3.setProgress(50);
        workingMemory.insert(flag3);
        workingMemory.fireAllRules();

        System.out.println("Flag One has a progress of " + flag1.getProgress() + " The status is: " + flag1.getStatus());
        System.out.println("Flag Two has a progress of " + flag2.getProgress() + " The status is: " + flag2.getStatus());
        System.out.println("Flag Three has a progress of " + flag3.getProgress() + " The status is: " + flag3.getStatus());
    }
}