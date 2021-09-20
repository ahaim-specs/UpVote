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

package net.ashwork.upvote.domain;

/**
 * A helper that specifies that a particular object entry is a vote made up of the
 * user who made the vote, the item being voted on, the vote the user cast, and the
 * time the vote was made.
 *
 * @apiNote
 * The types within this class can be an object wrapper of a composite type. This
 * just specifies that it defines what that type is within the entry.
 *
 * @param <USER> the type of the user
 * @param <ITEM> the type of the item
 * @param <VOTE> the type of the vote
 * @param <TIME> the type of the time
 * @since 1.0.0
 */
public interface VoteEntry<USER, ITEM, VOTE, TIME> extends UserEntry<USER>, TimestampEntry<TIME> {

    /**
     * Gets the item being voted on.
     *
     * @return the item of the entry
     */
    ITEM getItem();

    /**
     * Gets the vote cast by the user.
     *
     * @return the vote of the entry
     */
    VOTE getVote();
}
