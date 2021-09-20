/*
 * MIT License
 *
 * UpVote
 * Copyright (c) 2021-2021 Aaron Haim.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.ashwork.upvote.database.domain.algorithm;

import net.ashwork.upvote.database.domain.dao.GetDao;
import net.ashwork.upvote.database.domain.dao.InsertDao;
import net.ashwork.upvote.database.util.DatabaseHelper;
import net.ashwork.upvote.database.util.ExceptionHelper;
import net.ashwork.upvote.domain.algorithm.WeightedTallyAlgorithm;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * An algorithm used to tally all or a specific subset of entries with some
 * weighted association and return some response about the tallied results.
 * The result of the supplied algorithm will then be persisted to some table
 * containing the weights.
 *
 * @param <ENTRY> the type of the entry data
 * @param <WEIGHT> the type of the weights on the data
 * @param <RESPONSE> the type of the response to the tally
 * @since 1.0.0
 */
public class DatabaseWeightedTallyAlgorithm<ENTRY, WEIGHT, DAO extends InsertDao<WEIGHT> & GetDao<WEIGHT>, RESPONSE> implements WeightedTallyAlgorithm<ENTRY, WEIGHT, RESPONSE> {

    private final DAO dao;
    private final Supplier<RESPONSE> persistAllSuccess;
    private final Function<Throwable, RESPONSE> throwingError;
    private final WeightedTallyAlgorithm<ENTRY, WEIGHT, List<WEIGHT>> algorithm;

    /**
     * A simple constructor.
     *
     * @param dao the domain access object of the table
     * @param persistAllSuccess a supplied response for successful insertions of the weights
     * @param throwingError a function to convert a thrown throwable to a response
     * @param algorithm an algorithm to calculate the new weights of the tally
     */
    public DatabaseWeightedTallyAlgorithm(final DAO dao, final Supplier<RESPONSE> persistAllSuccess, final Function<Throwable, RESPONSE> throwingError, final WeightedTallyAlgorithm<ENTRY, WEIGHT, List<WEIGHT>> algorithm) {
        this.dao = dao;
        this.persistAllSuccess = persistAllSuccess;
        this.throwingError = throwingError;
        this.algorithm = algorithm;
    }

    @Override
    public RESPONSE execute(List<ENTRY> entries) {
        return this.execute(entries, ExceptionHelper.getOrDefault(this.dao::getAll, Collections::emptyList));
    }

    @Override
    public RESPONSE execute(List<ENTRY> entries, List<WEIGHT> weights) {
        return DatabaseHelper.persistToDatabase(this.dao, this.algorithm.execute(entries, weights), this.persistAllSuccess, this.throwingError);
    }
}
