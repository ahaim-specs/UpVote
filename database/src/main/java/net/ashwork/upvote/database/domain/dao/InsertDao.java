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

package net.ashwork.upvote.database.domain.dao;

import java.util.List;

/**
 * A domain access object for inserting data into the table.
 *
 * @param <DBO> the type of the database object
 * @since 1.0.0
 */
public interface InsertDao<DBO> {

    /**
     * Inserts a single entry into the table.
     *
     * @param dbo the database object being inserted
     * @return the id of the inputted entry
     * @throws Throwable if an error occurred while trying to insert a row into
     *                   the table
     */
    int insert(final DBO dbo) throws Throwable;

    /**
     * Inserts a list of entries into the table.
     *
     * @param dbos a list of database objects being inserted
     * @throws Throwable if an error occurred while trying to insert many rows
     *                   into the table
     */
    void insertAll(final List<DBO> dbos) throws Throwable;
}
