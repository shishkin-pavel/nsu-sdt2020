(ns laba1-3.core
  (:gen-class))

(defn my-map [f coll]
  (reduce (fn [one two] (conj one (f two) )) [] coll))


(defn my-filter [f coll]
  (reduce (fn [one two] (if (f two) (conj one two) one)) [] coll ))


(my-filter even? (range 10))
;(my-map vector '(1 2 3))