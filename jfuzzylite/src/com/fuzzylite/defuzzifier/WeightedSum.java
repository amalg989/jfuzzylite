/*
 Author: Juan Rada-Vilela, Ph.D.
 Copyright (C) 2010-2014 FuzzyLite Limited
 All rights reserved

 This file is part of jfuzzylite.

 jfuzzylite is free software: you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation, either version 3 of the License, or (at your option)
 any later version.

 jfuzzylite is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with jfuzzylite.  If not, see <http://www.gnu.org/licenses/>.

 fuzzylite™ is a trademark of FuzzyLite Limited.
 jfuzzylite™ is a trademark of FuzzyLite Limited.

 */
package com.fuzzylite.defuzzifier;

import com.fuzzylite.term.Accumulated;
import com.fuzzylite.term.Term;
import com.fuzzylite.term.Activated;

public class WeightedSum extends Defuzzifier {

    @Override
    public double defuzzify(Term term, double minimum, double maximum) {
        Accumulated takagiSugeno = (Accumulated) term;
        double sum = 0.0;
        for (Term t : takagiSugeno.getTerms()) {
            Activated thresholded = (Activated) t;

            double w = thresholded.getDegree();
            double z = Tsukamoto.tsukamoto(thresholded,
                    takagiSugeno.getMinimum(), takagiSugeno.getMaximum());
            //Traditionally, activation is the AlgebraicProduct
            sum += thresholded.getActivation().compute(w, z);
        }
        return sum;
    }

}
