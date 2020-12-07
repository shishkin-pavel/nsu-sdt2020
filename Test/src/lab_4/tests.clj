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

(defn nor [expr1 expr2]
  (cons ::nor (list expr1 expr2)))

(defn nor-calc [expr vr new-vr]
  (let [res (map #(calculate % vr new-vr) (rest expr))]
    (cond
      (every? constant? res) (constant (or (constant-value (first res)) (not (constant-value (second res)))))
      (and (constant? (first res)) (constant-value (first res))) (constant true)
      (and (constant? (second res)) (not (constant-value (second res)))) (constant true)
      :else (apply nor res))))

(defn nor-map-to-basis [expr]
  (let [res (map #(complex-operations-to-basis %) (rest expr))]
    (disjunction (first res) (negation (second res)))))

(test/deftest extend-api
  (test/testing
    (test/is (add-new-function ::nor nor-calc nor-map-to-basis)))
  (test/testing
    (test/is (constant-value (calculate (nor (variable :x) (variable :y)) (variable :x) (constant true))))
    (test/is (constant-value (calculate (nor (variable :x) (variable :y)) (variable :y) (constant false))))
    (test/is (constant-value (calculate (nor (constant true) (variable :y)) (variable :y) (constant true)))))
  (test/testing
    (test/is
      (=
        (build-dnf (negation (disjunction (disjunction (negation (variable :x)) (variable :y)) (negation (nor (negation (variable :y)) (negation (variable :z)))))))
        (disjunction (conjunction (variable :x) (negation (variable :y))) (conjunction (variable :x) (negation (variable :y)) (variable :z)))))))

(test/run-tests 'lab_4.tests)