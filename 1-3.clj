(ns lab1.core
  (:gen-class))

(defn my-map [f data]
  (reduce #(conj %1 (f %2))
          []
          data))

(defn my-filter [f data]
  (reduce (fn [a b]
              (if (f b)
                  (conj a b)
                  a))
          []
          data))

(println (my-map #(* %1 %1) '(2 4 3)))
(println (my-filter even? (range 1 10)))
