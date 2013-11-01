/*   Copyright 2013 Juan Rada-Vilela

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.fuzzylite.term;

import com.fuzzylite.Op;
import static com.fuzzylite.Op.str;

/**
 *
 * @author jcrada
 */
public class SShape extends Term {

    protected double start, end;

    public SShape(String name) {
        this(name, Double.NaN, Double.NaN);
    }

    public SShape(String name, double start, double end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public double membership(double x) {
        if (Double.isNaN(x)) {
            return Double.NaN;
        }
        //from Octave smf.m
        double average = (start + end) / 2;
        double difference = end - start;

        if (Op.isLE(x, start)) {
            return 0.0;
        } else if (Op.isLE(x, average)) {
            return 2 * Math.pow((x - start) / difference, 2);
        } else if (Op.isLt(x, end)) {
            return 1.0 - 2.0 * Math.pow((x - end) / difference, 2);
        }

        return 1.0;
    }

    @Override
    public String toString() {
        String result = SShape.class.getSimpleName();
        result += "(" + Op.join(", ", str(start), str(end)) + ")";
        return result;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }
}