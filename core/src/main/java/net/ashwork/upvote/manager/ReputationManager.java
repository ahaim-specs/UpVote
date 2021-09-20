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

import net.ashwork.upvote.domain.ReputationEntry;

/**
 * A manager that collects the reputation of users.
 *
 * @param <USER> the type of the user
 * @param <REPUTATION> the type of the reputation
 * @param <TIME> the type of the time
 * @param <ENTRY> the type of the entry holding the reputation
 * @param <RESPONSE> the type of the response to the action
 *
 * @see ReputationEntry
 * @since 1.0.0
 */
public interface ReputationManager<USER, REPUTATION, TIME, ENTRY extends ReputationEntry<USER, REPUTATION, TIME>, RESPONSE> extends AbstractInputManager<ENTRY, RESPONSE> {

    /**
     * A wrapper around {@link #putEntry(Object)} to give a more meaningful name
     * in context.
     *
     * @param entry the reputation entry to put into the manager
     * @return the response created as a result of the operation
     * @see #putEntry(Object)
     */
    default RESPONSE setUserReputation(final ENTRY entry) {
        return this.putEntry(entry);
    }
}
