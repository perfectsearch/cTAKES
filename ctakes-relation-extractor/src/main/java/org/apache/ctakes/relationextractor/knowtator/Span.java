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
package org.apache.ctakes.relationextractor.knowtator;

import com.google.common.base.Objects;

/**
 * Represents span of a named entity
 * 
 * @author dmitriy dligach
 *
 */
public class Span {
	
	public int start;
	public int end;
	
	public Span(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public boolean equals(Object object) {
		
		boolean isEqual = false;
		
		if(object instanceof Span) {
			Span span = (Span) object;
			isEqual = ((this.start == span.start) && (this.end == span.end));
		}
		
		return isEqual;
	}
	
	@Override
  public int hashCode()
  {
  	return Objects.hashCode(start, end);
  }
	
	@Override
	public String toString() {
		return String.format("%d -- %d", start, end);
	}
}