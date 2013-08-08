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
package org.apache.ctakes.dictionary.lookup.strtable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Mayo Clinic
 */
public class StringTable
{
    // key = indexed field name (String), value = VALUE MAP 
    private Map iv_nameMap = new HashMap();

    // ROW MAP	
    // key = indexed field value (String), value = set of StringTableRows 

    public StringTable(String[] indexedFieldNames)
    {
        for (int i = 0; i < indexedFieldNames.length; i++)
        {
            iv_nameMap.put(indexedFieldNames[i], new HashMap());
        }
    }

    public void addRow(StringTableRow strTableRow)
    {
        Iterator indexedFieldNameItr = iv_nameMap.keySet().iterator();
        while (indexedFieldNameItr.hasNext())
        {
            String indexedFieldName = (String) indexedFieldNameItr.next();
            Map valueMap = (Map) iv_nameMap.get(indexedFieldName);

            String indexedFieldValue =
                strTableRow.getFieldValue(indexedFieldName);
            Set rowSet = (Set) valueMap.get(indexedFieldValue);
            if (rowSet == null)
            {
                rowSet = new HashSet();
            }
            rowSet.add(strTableRow);

            valueMap.put(indexedFieldValue, rowSet);
        }
    }

    public StringTableRow[] getRows(String indexedFieldName, String fieldVal)
    {
        Map valueMap = (Map) iv_nameMap.get(indexedFieldName);
        Set rowSet = (Set) valueMap.get(fieldVal);
        if (rowSet != null)
        {
			return (StringTableRow[]) rowSet.toArray(
				new StringTableRow[rowSet.size()]);
        }
        else
        {
        	return new StringTableRow[0];
        }
    }
    
    public StringTableRow[] getAllRows()
    {
        Collection col = new HashSet();
        Iterator valueMapItr = iv_nameMap.values().iterator();
        while (valueMapItr.hasNext())
        {
            Map valueMap = (Map)valueMapItr.next();
            Iterator valueItr = valueMap.values().iterator();
            while (valueItr.hasNext())
            {
                Set rowSet = (Set)valueItr.next();
                if (rowSet.size() > 0)
                {
                    col.addAll(rowSet);                    
                }
            }
        }
        
		return (StringTableRow[]) col.toArray(
				new StringTableRow[col.size()]);
    }
}
