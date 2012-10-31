/*
 * Copyright: (c) 2012  Children's Hospital Boston, Regents of the University of Colorado 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Dmitriy Dligach
 */
package org.chboston.cnlp.ctakes.relationextractor.data;

import java.util.Random;

/**
 * Class for generating samples from a three dimensional Categorical distribution. 
 * Can be useful for splitting data into train/dev/test.
 * 
 * @author dmitriy dligach
 *
 */
public class CategoricalDistribution {

	Random random;
	
	public final int OUTCOME1 = 1;
	public final int OUTCOME2 = 2;
	public final int OUTCOME3 = 3;
	
	double p1;
	double p2;
	double p3;
	
	CategoricalDistribution(double p1, double p2, double p3) {
		
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		
		random = new Random(0);
	}
	
	public int sample() {
		
		double r = random.nextDouble();
		
		if(r < p1) {
			return OUTCOME1;
		}
		if(r >= p1 && r < p1 + p2) {
			return OUTCOME2;
		}
		if(r >= p1 + p2) {
			return OUTCOME3;
		}
		else {
			return -1; // shouldn't happen
		}
	}
	
	public static void main(String[] args) {
		
		CategoricalDistribution categ = new CategoricalDistribution(0.6, 0.2, 0.2);
		
		for(int i = 0; i < 10000; i++) {
			int outcome = categ.sample();
			System.out.println(outcome);
		}
	}
}
