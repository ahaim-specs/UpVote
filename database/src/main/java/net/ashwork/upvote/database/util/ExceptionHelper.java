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

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A utility class to handle methods throwing unwanted exception data.
 *
 * @since 1.0.0
 */
public final class ExceptionHelper {

    /**
     * Gets a result or defaults if a throwable is thrown.
     *
     * @apiNote
     * {@code defaultObject} should never throw a throwable.
     *
     * @param supplier the supplied result
     * @param defaultObject the defaulted result if a throwable is thrown
     * @param <T> the type of the result
     * @return the result of the supplier or its default if a throwable is thrown
     */
    public static <T> T getOrDefault(final ThrowingSupplier<T> supplier, final Supplier<T> defaultObject) {
        return getOrDefault(supplier, t -> defaultObject.get());
    }

    /**
     * Gets a result or defaults if a throwable is thrown.
     *
     * @apiNote
     * {@code defaultObject} should never throw a throwable.
     *
     * @param supplier the supplied result
     * @param defaultObject a function to construct a default object from the
     *                      thrown throwable
     * @param <T> the type of the result
     * @return the result of the supplier or its default if a throwable is thrown
     */
    public static <T> T getOrDefault(final ThrowingSupplier<T> supplier, final Function<Throwable, T> defaultObject) {
        try {
            return supplier.get();
        } catch (final Throwable t) {
            return defaultObject.apply(t);
        }
    }
}
