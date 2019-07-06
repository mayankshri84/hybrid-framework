package com.runner;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;

import com.test.utils.Utils;

public class TestRunner {
	public static void main(String[] args) {

		try {
			Utils utils = new Utils();
			List<String> stories = utils.storyReader("Regression");
			for(String story : stories)
			{
				String[] storyPart = story.split(":");
				utils.writeProperty("test.case", storyPart[0]);
				utils.writeProperty("test.data", storyPart[1]);
				InvocationRequest request = new DefaultInvocationRequest();
				request.setPomFile(new File("pom.xml"));
				Invoker invoker = new DefaultInvoker();
				request.setGoals(Arrays.asList("install"));
				Properties prop = new Properties();
				prop.setProperty("cucumber.options", " --tags "+storyPart[0]+"");
				// prop.setProperty("data", "myData");
				request.setProperties(prop);
				System.out.println(prop.getProperty("cucumber.options"));
				// System.out.println(System.getenv("MAVEN_HOME"));
				invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
				InvocationResult result = invoker.execute(request);
				System.out.println(result.getExitCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
