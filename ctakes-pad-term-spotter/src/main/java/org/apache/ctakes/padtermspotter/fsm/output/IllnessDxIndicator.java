/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ctakes.padtermspotter.fsm.output;

import org.apache.ctakes.core.fsm.output.BaseTokenImpl;



/**
 *
 * @author Mayo Clinic
 */

public class IllnessDxIndicator extends BaseTokenImpl
{
	   public static final int BREAST_STATUS = 4;
	   public static final int BRAIN_STATUS = 5;
	   public static final int COLON_STATUS = 6;
	   public static final int PAD_STATUS = 7;
	   
	   private int iv_status;
	    
	public IllnessDxIndicator(int startOffset, int endOffset, int status)	
	{
		super(startOffset, endOffset);
        iv_status = status;
    }

    public int getStatus()
    {
        return iv_status;
    }
}
