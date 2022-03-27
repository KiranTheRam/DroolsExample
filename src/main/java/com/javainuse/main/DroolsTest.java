package com.javainuse.main;

import java.io.IOException;
import java.io.InputStreamReader;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;
import com.javainuse.model.Product;
// http://www.jbug.jp/trans/jboss-rules3.0.2/ja/html/ch01s06.html#:~:text=A%20RuleBase%20is%20a%20runtime,is%20maintained%2C%20unless%20configured%20otherwise.

public class DroolsTest {

	public static void main(String[] args) throws DroolsParserException, IOException {
		DroolsTest droolsTest = new DroolsTest();
		droolsTest.executeDrools();
	}

	public void executeDrools() throws DroolsParserException, IOException {
		String ruleFile = "/com/rule/Rules.drl";

		PackageBuilder packageBuilder = new PackageBuilder();
		packageBuilder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( ruleFile) ) );

		org.drools.core.rule.Package rulesPackage = packageBuilder.getPackage();

//		A RuleBase contains one more more packages of rules
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage(rulesPackage);

//		Working Memory: Storage with Facts, where they are used for pattern matching and can be modified, inserted and removed.
//		Holds references to all data that has been "asserted" into it
		WorkingMemory workingMemory = ruleBase.newStatefulSession();

//		Product 1
		Product product = new Product();
		product.setType("gold");
		workingMemory.insert(product);		//		Tests the object against 2 conditions (either gold or diamond)
		workingMemory.fireAllRules();		//		Insert product into workingMemory

//		Product 2
		Product productTwo = new Product();
		productTwo.setType("diamond");
		workingMemory.insert(productTwo);
		workingMemory.fireAllRules();

//		Product 3
		Product productThree = new Product();
		productThree.setType("silver");
		workingMemory.insert(productThree);
		workingMemory.fireAllRules();

		System.out.println("The discount for " + product.getType() + " product. : " + product.getDiscount());
		System.out.println("The discount for " + productTwo.getType() + " product. : " + productTwo.getDiscount());
		System.out.println("The discount for " + productThree.getType() + " product. : " + productThree.getDiscount());
	}
}