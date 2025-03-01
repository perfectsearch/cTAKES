package org.apache.ctakes.assertion.train;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ctakes.assertion.eval.AssertionEvaluation;
import org.apache.ctakes.assertion.pipelines.RunJudgeAttributeInstances;
import org.apache.ctakes.assertion.util.AssertionConst;
import org.kohsuke.args4j.Option;

import scala.actors.threadpool.Arrays;

public class ReadPreprocessTrainTestJudge {

	public static void main(String[] args) throws Exception {
	
		ReadAndPreprocessForAttributeModels.main(null);
		
		TrainAttributeModels.main(null);
		
		TestAttributeModels.main(null);


		// Set up parameters for judging test.
		// Output of test step becomes input for runjudge
		ArrayList<String> params;
		params = new ArrayList<String>();
		
		//@Option(
		//		name = "--input-dir",
		//		usage = "where to read the fully-annotated xmi data from",
		//		required = true)
		//		public String inputDir = AssertionConst.evalOutputDir;
		params.add("--input-dir");
		params.add(AssertionConst.evalOutputDir);

		//@Option(
		//		name = "--output-dir",
		//		usage = "where to write the 'adjudicated' instances in xmi format to",
		//		required = true)
		//		public String outputDir = AssertionConst.instanceGatheringOutputDir;
		params.add("--output-dir");
		params.add(AssertionConst.instanceGatheringOutputDir);
		
		String [] parmsAsArray;
		parmsAsArray = params.toArray(new String[] {});
		
		RunJudgeAttributeInstances.main(parmsAsArray);

	}
}
