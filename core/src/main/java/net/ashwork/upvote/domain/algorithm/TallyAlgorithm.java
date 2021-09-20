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

package net.ashwork.upvote.domain.algorithm;

import java.util.List;

/**
 * An algorithm used to tally all or a specific subset of entries and return some
 * response about the tallied results.
 *
 * @apiNote
 * A response is used abstractly here. The response should be meaningful based on
 * the implementation context whether that would be the resulting calculation or
 * whether the calculation computed successfully.
 *
 * @param <ENTRY> the type of the entry data
 * @param <RESPONSE> the type of the response to the tally
 * @since 1.0.0
 */
@FunctionalInterface
public interface TallyAlgorithm<ENTRY, RESPONSE> {

    /**
     * Executes a tally on the entries and returns some meaningful response.
     *
     * @param entries a list of entries to tally
     * @return the response created as a result of the tally
     */
    RESPONSE execute(final List<ENTRY> entries);
}
