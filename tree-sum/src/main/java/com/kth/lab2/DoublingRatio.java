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

import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdRandom;

import java.util.Set;

public class DoublingRatio {

  private interface SumHandler{
    int count(int[] a);
  }

  private static double timeTrial(int N, SumHandler handler) {
    int MAX = 10000000;
    Set<Integer> numbers = Sets.newHashSetWithExpectedSize(N);
    while (numbers.size() < N){
      numbers.add(StdRandom.uniform(-MAX, MAX));
    }
    Stopwatch timer = new Stopwatch();
    int cnt = handler.count(Ints.toArray(numbers));
    return timer.elapsedTime();
  }

  public static double timeTrialTwoSum(int N){
    return timeTrial(N, new SumHandler() {
      @Override
      public int count(int[] a) {
       return TwoSum.count(a);
      }
    });
  }

  public static double timeTrialTwoSumFast(int N){
    return timeTrial(N, new SumHandler() {
      @Override
      public int count(int[] a) {
        return TwoSumFast.count(a);
      }
    });
  }


  public static double timeTrialThreeSum(int N){
    return timeTrial(N, new SumHandler() {
      @Override
      public int count(int[] a) {
        return ThreeSum.count(a);
      }
    });
  }


  public static double timeTrialThreeSumFast(int N){
    return timeTrial(N, new SumHandler() {
      @Override
      public int count(int[] a) {
        return ThreeSumFast.count(a);
      }
    });
  }

}
