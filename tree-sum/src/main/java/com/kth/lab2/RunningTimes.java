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
import edu.princeton.cs.introcs.StdIn;
public class RunningTimes {

  public static void main(String[] args){
    if(args.length != 2){
      System.out.println("Usage: java -jar runningtimes.jar Operation N");
      System.out.println("Example: java -jar runningtimes.jar 2sum 1000");
      System.out.println("Operation could be 2sum, 2sumfast, 3sum, 3sumfast");
      System.exit(-1);
    }
    String operation = "3sum";
    int N = StdIn.readInt();
    double time = 0;
    if(operation.equals("2sum")){
      time = DoublingRatio.timeTrialTwoSum(N);
    }else if(operation.equals("2sumfast")){
      time = DoublingRatio.timeTrialTwoSumFast(N);
    }else if(operation.equals("3sum")){
      time = DoublingRatio.timeTrialThreeSum(N);
    }else if(operation.equals("3sumfast")){
      time = DoublingRatio.timeTrialThreeSumFast(N);
    }else{
      System.out.println("Wrong operation ["+operation+"] it must be " +
          "one of the following [2sum, " +
          "2sumfast, 3sum, 3sumfast]");
      System.exit(-1);
    }

    System.out.println(N + " " + time);
  }
}
