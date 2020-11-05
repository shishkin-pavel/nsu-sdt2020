(ns lab2.lab2-2)

(defn exponentiation [x]
  (* x x))

(defn trap [func x1 x2]
  (* (/ (+ (func x1) (func x2)) 2) (- x2 x1)))

(defn lazy-integral [step func]
  (map first (iterate (fn [[acc x]] [(+ (trap func x (+ x step)) acc) (+ x step)]) [0 0])))

(defn integral [x step collection]
  (nth collection (/ x step)))

(def lazy-integral-coll (lazy-integral 1 exponentiation))

(println (time (integral 10 1 lazy-integral-coll)))
(println (time (integral 10 1 lazy-integral-coll)))
(println (time (integral 5 1 lazy-integral-coll)))
(println (time (integral 15 1 lazy-integral-coll)))