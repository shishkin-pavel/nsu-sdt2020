(ns lab_4.tests
  (:use lab_4.task_4)
  (:require [clojure.test :as test]))

(test/deftest dnf-steps
  (test/testing
    (test/is
      (=
        (negation (disjunction (disjunction (negation (variable :x)) (variable :y)) (negation (disjunction (negation (variable :y)) (variable :z)))))
        (complex-operations-to-basis (negation (disjunction (disjunction (negation (variable :x)) (variable :y)) (negation (disjunction (negation (variable :y)) (variable :z)))))))))
  (test/testing
    (test/is
      (=
        (remove-negation (negation (disjunction (disjunction (negation (variable :x)) (variable :y)) (negation (disjunction (negation (variable :y)) (variable :z))))))
        (conjunction (conjunction (variable :x) (negation (variable :y))) (disjunction (negation (variable :y)) (variable :z))))))
  (test/testing
    (test/is
      (=
        (simplify (conjunction (conjunction (variable :x) (negation (variable :y))) (disjunction (negation (variable :y)) (variable :z))))
        (conjunction (disjunction (variable :z) (negation (variable :y))) (negation (variable :y)) (variable :x)))))
  (test/testing
    (test/is
      (=
        (cartesian-product (disjunction (variable :x) (variable :y)) (disjunction (variable :z) (variable :c)))
        (list (conjunction (variable :x) (variable :z)) (conjunction (variable :x) (variable :c)) (conjunction (variable :y) (variable :z)) (conjunction (variable :y) (variable :c)))))
    (test/is
      (=
        (cartesian-product (disjunction (variable :x) (variable :y)) (variable :z))
        (list (conjunction (variable :x) (variable :z)) (conjunction (variable :y) (variable :z)))))
    (test/is
      (=
        (cartesian-product (variable :x) (disjunction (variable :y) (variable :z)))
        (list (conjunction (variable :x) (variable :y)) (conjunction (variable :x) (variable :z)))))
    (test/is
      (=
        (cartesian-product (variable :x) (variable :y))
        (conjunction (variable :x) (variable :y)))))
  (test/testing
    (test/is
      (=
        (apply-distribution-law (conjunction (disjunction (variable :z) (negation (variable :y))) (negation (variable :y)) (variable :x)))
        (disjunction (conjunction (conjunction (variable :z) (negation (variable :y))) (variable :x)) (conjunction (conjunction (negation (variable :y)) (negation (variable :y))) (variable :x)))))))

(test/deftest dnf
  (test/testing
    (test/is
      (=
        (build-dnf (negation (disjunction (disjunction (negation (variable :x)) (variable :y)) (negation (disjunction (negation (variable :y)) (variable :z))))))
        (disjunction (conjunction (variable :x) (negation (variable :y))) (conjunction (variable :x) (negation (variable :y)) (variable :z))))))
  (test/testing
    (test/is
      (=
        (build-dnf (negation (disjunction (disjunction (negation (variable :x)) (variable :y)) (negation (implication (variable :y) (variable :z))))))
        (disjunction (conjunction (variable :x) (negation (variable :y))) (conjunction (variable :x) (negation (variable :y)) (variable :z)))))))

(test/run-tests 'lab_4.tests)