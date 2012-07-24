/*
 * Copyright: (c) 2012  Children's Hospital Boston, Regents of the University of Colorado 
 *
 * Except as contained in the copyright notice above, or as used to identify
 * MFMER as the author of this software, the trade names, trademarks, service
 * marks, or product names of the copyright holder shall not be used in
 * advertising, promotion or otherwise in connection with this software without
 * prior written authorization of the copyright holder.
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
 * @author Steven Bethard
 */
package org.chboston.cnlp.ctakes.relationextractor.eval;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.uima.collection.CollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.cleartk.util.Options_ImplBase;
import org.cleartk.util.cr.FilesCollectionReader;
import org.cleartk.util.cr.XReader;
import org.kohsuke.args4j.Option;
import org.uimafit.factory.CollectionReaderFactory;
import org.uimafit.factory.JCasFactory;
import org.uimafit.util.JCasUtil;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import edu.mayo.bmi.uima.core.type.relation.BinaryTextRelation;
import edu.mayo.bmi.uima.core.type.textsem.EntityMention;

public class PrintRelationStatistics {

  public static class Options extends Options_ImplBase {

    @Option(
        name = "--train-dir",
        usage = "specify the directory contraining the XMI training files (for example, /NLP/Corpus/Relations/mipacq/xmi/train)",
        required = true)
    public File trainDirectory;
  }

  public static final String GOLD_VIEW_NAME = "GoldView";

  public static void main(String[] args) throws Exception {
    Options options = new Options();
    options.parseOptions(args);

    CollectionReader reader = CollectionReaderFactory.createCollectionReader(
        XReader.class,
        FilesCollectionReader.PARAM_ROOT_FILE,
        options.trainDirectory.getPath());

    Multiset<Integer> mentionsBetweenCounts = HashMultiset.create();
    JCas jCas = JCasFactory.createJCasFromPath("../common-type-system/desc/common_type_system.xml");
    while (reader.hasNext()) {
      reader.getNext(jCas.getCas());
      JCas goldView = jCas.getView(GOLD_VIEW_NAME);
      for (BinaryTextRelation relation : JCasUtil.select(goldView, BinaryTextRelation.class)) {
        Annotation arg1 = relation.getArg1().getArgument();
        Annotation arg2 = relation.getArg2().getArgument();
        int mentionsBetween;
        if (arg1.getBegin() < arg2.getBegin()) {
          mentionsBetween = JCasUtil.selectCovered(
              goldView,
              EntityMention.class,
              arg1.getEnd(),
              arg2.getBegin()).size();
        } else {
          mentionsBetween = -JCasUtil.selectCovered(
              goldView,
              EntityMention.class,
              arg2.getEnd(),
              arg1.getBegin()).size();
        }
        mentionsBetweenCounts.add(mentionsBetween);
      }
    }
    
    List<Integer> mentionsBetweenKeys = new ArrayList<Integer>(mentionsBetweenCounts.elementSet());
    Collections.sort(mentionsBetweenKeys);
    for (Integer mentionsBetween : mentionsBetweenKeys) {
      System.err.printf("%d x%d\n", mentionsBetween, mentionsBetweenCounts.count(mentionsBetween));
    }
  }

}
