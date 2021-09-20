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

package net.ashwork.upvote.database.util;

import net.ashwork.upvote.database.domain.dao.InsertDao;

import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * A utility class to handle interactions with a database.
 *
 * @since 1.0.0
 */
public final class DatabaseHelper {

    /**
     * Inserts an entry into a table and returns some response pertaining to
     * whether the insertion was successful or not.
     *
     * @param dao the domain access object of the table
     * @param dbo the database object being inserted
     * @param success a function to convert a successful insertion to a response
     * @param error a function to convert a thrown throwable to a response
     * @param <DBO> the type of the database object
     * @param <DAO> the type of the domain access object
     * @param <RESPONSE> the type of the response of the insertion
     * @return the response created as a result of the insertion
     * @see InsertDao
     */
    public static <DBO, DAO extends InsertDao<DBO>, RESPONSE> RESPONSE persistToDatabase(final DAO dao, final DBO dbo, final IntFunction<RESPONSE> success, final Function<Throwable, RESPONSE> error) {
        return ExceptionHelper.getOrDefault(() -> success.apply(dao.insert(dbo)), error);
    }

    /**
     * Inserts multiple entries into a table and returns some response pertaining
     * to whether the insertions were successful or not.
     *
     * @param dao the domain access object of the table
     * @param dbos the database objects being inserted
     * @param success a supplied response for successful insertions
     * @param error a function to convert a thrown throwable to a response
     * @param <DBO> the type of the database object
     * @param <DAO> the type of the domain access object
     * @param <RESPONSE> the type of the response of the insertions
     * @return the response created as a result of the insertions
     * @see InsertDao
     */
    public static <DBO, DAO extends InsertDao<DBO>, RESPONSE> RESPONSE persistToDatabase(final DAO dao, final List<DBO> dbos, final Supplier<RESPONSE> success, final Function<Throwable, RESPONSE> error) {
        return ExceptionHelper.getOrDefault(() -> {
            dao.insertAll(dbos);
            return success.get();
        }, error);
    }
}
