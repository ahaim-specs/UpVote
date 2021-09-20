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

package net.ashwork.upvote.database.manager;

import net.ashwork.upvote.database.domain.dao.GetDao;
import net.ashwork.upvote.database.domain.dao.InsertDao;
import net.ashwork.upvote.domain.VoteEntry;
import net.ashwork.upvote.domain.algorithm.TallyAlgorithm;
import net.ashwork.upvote.manager.VoteManager;

import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * An implementation of {@link VoteManager} for a database.
 *
 * @param <USER> the type of the user
 * @param <ITEM> the type of the item
 * @param <VOTE> the type of the vote
 * @param <TIME> the type of the time
 * @param <ENTRY> the type of the entry holding the vote
 * @param <DBO> the type of the database object holding the vote
 * @param <DAO> the type of the domain access object holding votes
 * @param <RESPONSE> the type of the response to the actions
 */
public class DatabaseVoteManager<USER, ITEM, VOTE, TIME, ENTRY extends VoteEntry<USER, ITEM, VOTE, TIME>, DBO, DAO extends InsertDao<DBO> & GetDao<DBO>, RESPONSE> extends AbstractDatabaseManager<ENTRY, DBO, DAO, RESPONSE> implements VoteManager<USER, ITEM, VOTE, TIME, ENTRY, RESPONSE> {

    private final TallyAlgorithm<DBO, RESPONSE> algorithm;

    /**
     * A simple constructor.
     *
     * @param dao the domain access object of the table
     * @param transformer a function to transform the vote entry to a database object
     * @param algorithm an algorithm to tally all vote database objects within the table
     * @param persistSuccess a function to convert a successful insertion to a response
     * @param throwingError a function to convert a thrown throwable to a response
     */
    public DatabaseVoteManager(final DAO dao, final Function<ENTRY, DBO> transformer, final TallyAlgorithm<DBO, RESPONSE> algorithm, final IntFunction<RESPONSE> persistSuccess, final Function<Throwable, RESPONSE> throwingError) {
        super(dao, transformer, persistSuccess, throwingError);
        this.algorithm = algorithm;
    }

    @Override
    public RESPONSE tally() {
        try {
            return this.algorithm.execute(this.dao.getAll());
        } catch (final Throwable t) {
            return this.throwingError.apply(t);
        }
    }
}
