package com.baharsateli.deeplearning;
/*
 * NamedEntity Embedding
 * Copyright (C) 2018, Bahar Sateli
 *
 * This file is part of a free software: you can redistribute and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;

import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import org.nd4j.linalg.io.ClassPathResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Created by baharsateli
 */
public class Learner {

    private static Logger log = LoggerFactory.getLogger(Learner.class);
    private static Word2Vec model = null;
    private static String outputFile = "output.zip";

    public static void main(String args[]) throws Exception {
        String filePath = new ClassPathResource("raw_sentences.txt").getFile().getAbsolutePath();

        log.info("Load and vectorize sentences from file " + filePath);
        // We use the default tokenizer which splits on white spaces
        model = setup(new BasicLineIterator(filePath), new DefaultTokenizerFactory());

        log.info("Fitting the model....");
        model.fit();

        log.info("Saving the model ...");
        // this saves the model as a ZIP file in the project root directory
        WordVectorSerializer.writeWord2VecModel(model, outputFile);

        // Alternatively, you could save it to a text file, though it's been deprecated
        //WordVectorSerializer.writeWordVectors(model, "pathToWriteto.txt");

        // An example of what to do with these named entity vectors.
        getNClosest("day", 10);
    }

    private static Word2Vec setup(final SentenceIterator iterator, final TokenizerFactory tokenizer){
        // Common pre-processing
        tokenizer.setTokenPreProcessor(new CommonPreprocessor());
        log.info("Building the model (Word2Vec) ...");
        return new Word2Vec.Builder()
                .minWordFrequency(5)
                .iterations(1)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .iterate(iterator)
                .tokenizerFactory(tokenizer)
                .build();
    }

    private static void getNClosest(final String entity, final int num){
        Collection<String> lst = model.wordsNearestSum(entity, num);
        log.info("10 Words closest to '" + entity + "': {}", lst);
    }
}
