package generaltests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	private static final int maxtry =2;
	private int count =0;

		@Override
	public boolean retry (final ITestResult iTestResult) {
	       if (!iTestResult.isSuccess ()) {
	           if (this.count < maxtry) {
	     
	               this.count++;
	               return true;
	           }
	       }
	       return false;
	
	
	
	}
}
