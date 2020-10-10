(ns clojuretest.lab1_3)

(defn my-map
  [func lst]
  (reduce (fn [acc item] (conj acc (func item))) [] lst))
;(fn (fn (fn [] 2) 3) 4)

(defn my-filter
  [condition lst]
  (reduce (fn [acc item] (if (condition item)
                           (conj acc item)
                           acc)) [] lst))

(println (my-filter even? `(1 2 3)))
(println (my-map inc [1 2 3]))