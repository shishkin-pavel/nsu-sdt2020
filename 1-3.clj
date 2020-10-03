(ns lab1.core
  (:gen-class))

(defn my-map [f data]
  (reduce (fn [x y] (conj x (f y)))
          []
          data))

(defn my-filter [f data]
  (reduce (fn [a b]
              (if (f b)
                  (conj a b)
                  a))
          []
          data))

(defn my-filter2 [f data]
  (reduce #(if (f %2)
               (conj %1 %2)
               %1)
          []
          data))

(defn my-filter-nw [f data]
  (reduce #((if (f %2)
               (conj %1 %2)
               %1))
          []
          data))

#((if (f %2) (conj %1 %2) %1))

(println (my-map #(* %1 %1) '(2 4 3)))
(println (my-filter2 even? (range 1 10)))
(println (my-filter-nw even? (range 1 10)))

