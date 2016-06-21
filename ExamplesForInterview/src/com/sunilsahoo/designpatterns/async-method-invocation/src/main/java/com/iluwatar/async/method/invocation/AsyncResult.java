/**
 * The MIT License
 * Copyright (c) 2014 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sunilsahoo.designpatterns.async.method.invocation;

import java.util.concurrent.ExecutionException;

/**
 * 
 * AsyncResult interface
 */
public interface AsyncResult<T> {

  /**
   * Status of the async task execution.
   *
   * @return <code>true</code> if execution is completed or failed
   */
  boolean isCompleted();

  /**
   * Gets the value of completed async task.
   *
   * @return evaluated value or throws ExecutionException if execution has failed
   * @throws ExecutionException if execution has failed, containing the root cause
   * @throws IllegalStateException if execution is not completed
   */
  T getValue() throws ExecutionException;

  /**
   * Blocks the current thread until the async task is completed.
   *
   * @throws InterruptedException if the execution is interrupted
   */
  void await() throws InterruptedException;
}