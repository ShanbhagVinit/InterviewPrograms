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
package com.sunilsahoo.designpatterns.poison.pill;

import java.util.Date;

import com.sunilsahoo.designpatterns.poison.pill.Message.Headers;

/**
 * Class responsible for producing unit of work that can be expressed as message and submitted to
 * queue
 */
public class Producer {

  private final MqPublishPoint queue;
  private final String name;
  private boolean isStopped;

  /**
   * Constructor
   */
  public Producer(String name, MqPublishPoint queue) {
    this.name = name;
    this.queue = queue;
    this.isStopped = false;
  }

  /**
   * Send message to queue
   */
  public void send(String body) {
    if (isStopped) {
      throw new IllegalStateException(String.format(
          "Producer %s was stopped and fail to deliver requested message [%s].", body, name));
    }
    Message msg = new SimpleMessage();
    msg.addHeader(Headers.DATE, new Date().toString());
    msg.addHeader(Headers.SENDER, name);
    msg.setBody(body);

    try {
      queue.put(msg);
    } catch (InterruptedException e) {
      // allow thread to exit
      System.err.println(e);
    }
  }

  /**
   * Stop system by sending poison pill
   */
  public void stop() {
    isStopped = true;
    try {
      queue.put(Message.POISON_PILL);
    } catch (InterruptedException e) {
      // allow thread to exit
      System.err.println(e);
    }
  }
}