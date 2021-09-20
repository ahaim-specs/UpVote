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

package net.ashwork.upvote.manager;

import net.ashwork.upvote.domain.VoteEntry;

/**
 * A manager that collects votes and tallies them.
 *
 * @param <USER> the type of the user
 * @param <ITEM> the type of the item
 * @param <VOTE> the type of the vote
 * @param <TIME> the type of the time
 * @param <ENTRY> the type of the entry holding the vote
 * @param <RESPONSE> the type of the response to the actions
 *
 * @see VoteEntry
 * @since 1.0.0
 */
public interface VoteManager<USER, ITEM, VOTE, TIME, ENTRY extends VoteEntry<USER, ITEM, VOTE, TIME>, RESPONSE> extends AbstractInputManager<ENTRY, RESPONSE> {

    /**
     * A wrapper around {@link #putEntry(Object)} to give a more meaningful name
     * in context.
     *
     * @param entry the vote entry to put into the manager
     * @return the response created as a result of the operation
     * @see #putEntry(Object)
     */
    default RESPONSE castVote(final ENTRY entry) {
        return this.putEntry(entry);
    }

    /**
     * Tallies the result of the collected votes.
     *
     * @return the response created as a result of the tally
     */
    RESPONSE tally();
}
