package org.ufba.raide.java.testsmell;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;

import org.apache.commons.lang3.StringUtils;
import org.ufba.raide.java.refactoring.views.AssertionRouletteView;
import org.ufba.raide.java.refactoring.views.DuplicateAssertView;
import org.ufba.raide.java.testsmell.smell.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class TestSmellDetector {
	private String nameClass;
	private String filePathClass;
	
    public String getFilePathClass() {
		return filePathClass;
	}

	public void setFilePathClass(String filePathClass) {
		this.filePathClass = filePathClass;
	}

	private List<AbstractSmell> testSmells;
    private ArrayList<TestSmellDescription> lista = new ArrayList<TestSmellDescription>();;
    /**
     * Instantiates the various test smell analyzer classes and loads the objects into an List
     */
    public TestSmellDetector(String tipoSmell) {
        initializeSmells(tipoSmell);
        //testSmells = new ArrayList<>();
    }

    public String getNameClass() {
		return nameClass;
	}

	public void setNameClass(String nameClass) {
		this.nameClass = nameClass;
	}

	public void initializeSmells(String tipoTestSmell){
        testSmells = new ArrayList<>();
        
        if (tipoTestSmell.equals(DuplicateAssertView.getMessageDialogTitle())) {
        	testSmells.add(new DuplicateAssert(getNameClass(), getFilePathClass()));        	
        }
        else if (tipoTestSmell.equals(AssertionRouletteView.getMessageDialogTitle())) {
        	testSmells.add(new AssertionRoulette(getNameClass(), getFilePathClass()));        	
        }
        //testSmells.add(new AssertionRoulette(getNameClass(), getFilePathClass()));
        
        /*testSmells.add(new ConditionalTestLogic());
        testSmells.add(new ConstructorInitialization());
        testSmells.add(new DefaultTest());
        testSmells.add(new EmptyTest());
        testSmells.add(new ExceptionCatchingThrowing());
        testSmells.add(new GeneralFixture());
        testSmells.add(new MysteryGuest());
        testSmells.add(new PrintStatement());
        testSmells.add(new RedundantAssertion());
        testSmells.add(new SensitiveEquality());
        testSmells.add(new VerboseTest());
        testSmells.add(new SleepyTest());
        testSmells.add(new EagerTest());
        testSmells.add(new LazyTest());
        testSmells.add(new DuplicateAssert());
        testSmells.add(new UnknownTest());
        testSmells.add(new IgnoredTest());
        testSmells.add(new ResourceOptimism());
        testSmells.add(new MagicNumberTest());
        testSmells.add(new DependentTest());*/
    }

    /**
     * Factory method that provides a new instance of the TestSmellDetector
     *
     * @return new TestSmellDetector instance
     */
    public static TestSmellDetector createTestSmellDetector(String tipoSmell) {
        return new TestSmellDetector(tipoSmell);
    }

    /**
     * Provides the names of the smells that are being checked for in the code
     *
     * @return list of smell names
     */
    public List<String> getTestSmellNames() {
        return testSmells.stream().map(AbstractSmell::getSmellName).collect(Collectors.toList());
    }

    /**
     * Loads the java source code file into an AST and then analyzes it for the existence of the different types of test smells
     */
    public TestFile detectSmells(TestFile testFile, String tipoSmell) throws IOException {
    	setNameClass(testFile.getTestFileName());
    	setFilePathClass(testFile.getTestFilePath());
    	
        CompilationUnit testFileCompilationUnit=null, productionFileCompilationUnit=null;
        FileInputStream testFileInputStream, productionFileInputStream;
          
        if(!StringUtils.isEmpty(testFile.getTestFilePath())) {
            testFileInputStream = new FileInputStream(testFile.getTestFilePath());
            testFileCompilationUnit = JavaParser.parse(testFileInputStream);  
        }
        if(!StringUtils.isEmpty(testFile.getProductionFilePath())){
            productionFileInputStream = new FileInputStream(testFile.getProductionFilePath());
            productionFileCompilationUnit = JavaParser.parse(productionFileInputStream);
        }

        initializeSmells(tipoSmell);
        //lista = new ArrayList<TestSmellDescription>();
        for (AbstractSmell smell : testSmells) {
            try {
            	//lista = (ArrayList<TestSmellDescription>) smell.runAnalysis(testFileCompilationUnit, productionFileCompilationUnit,testFile.getTestFileNameWithoutExtension(),testFile.getProductionFileNameWithoutExtension());
            	lista.addAll((ArrayList<TestSmellDescription>) smell.runAnalysis(testFileCompilationUnit, productionFileCompilationUnit,testFile.getTestFileNameWithoutExtension(),testFile.getProductionFileNameWithoutExtension()));
            } catch (FileNotFoundException e) {
                testFile.addSmell(null);
                continue;
            }
            testFile.addSmell(smell);
        }

        return testFile;

    }

	public ArrayList<TestSmellDescription> getLista() {
		return lista;
	}

	public void setLista(ArrayList<TestSmellDescription> lista) {
		this.lista = lista;
	}


}
