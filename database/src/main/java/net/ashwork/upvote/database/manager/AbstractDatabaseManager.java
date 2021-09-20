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

import net.ashwork.upvote.database.domain.dao.InsertDao;
import net.ashwork.upvote.database.util.DatabaseHelper;
import net.ashwork.upvote.manager.AbstractInputManager;

import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * An abstract implementation of {@link AbstractInputManager} for a database.
 *
 * @param <ENTRY> the type of the entry data
 * @param <DBO> the type of the database object
 * @param <DAO> the type of the database access object
 * @param <RESPONSE> the type of the response to the action
 * @since 1.0.0
 */
public abstract class AbstractDatabaseManager<ENTRY, DBO, DAO extends InsertDao<DBO>, RESPONSE> implements AbstractInputManager<ENTRY, RESPONSE> {

    protected final DAO dao;
    protected final Function<ENTRY, DBO> transformer;
    protected final IntFunction<RESPONSE> persistSuccess;
    protected final Function<Throwable, RESPONSE> throwingError;

    /**
     * A simple constructor.
     *
     * @param dao the domain access object of the table
     * @param transformer a function to transform the entry to a database object
     * @param persistSuccess a function to convert a successful insertion to a response
     * @param throwingError a function to convert a thrown throwable to a response
     */
    protected AbstractDatabaseManager(final DAO dao, final Function<ENTRY, DBO> transformer, final IntFunction<RESPONSE> persistSuccess, final Function<Throwable, RESPONSE> throwingError) {
        this.dao = dao;
        this.transformer = transformer;
        this.persistSuccess = persistSuccess;
        this.throwingError = throwingError;
    }

    @Override
    public RESPONSE putEntry(ENTRY entry) {
        return DatabaseHelper.persistToDatabase(this.dao, this.transformer.apply(entry), this.persistSuccess, this.throwingError);
    }
}
