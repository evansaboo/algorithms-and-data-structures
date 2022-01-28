/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kth.lab2;

import java.util.Arrays;

public class ThreeSumFast {

  private static boolean containsDuplicates(int[] a) {
    for (int i = 1; i < a.length; i++)
      if (a[i] == a[i-1]) return true;
    return false;
  }

  public static int count(int[] a) {
    int N = a.length;
    Arrays.sort(a);
    if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
    int cnt = 0;
    for (int i = 0; i < N; i++) {
      for (int j = i+1; j < N; j++) {
        int k = Arrays.binarySearch(a, -(a[i] + a[j]));
        if (k > j) cnt++;
      }
    }
    return cnt;
  }
}
