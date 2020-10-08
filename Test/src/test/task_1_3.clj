(ns test.task_1_3)

(defn my-map [f coll]
  (reduce #(conj %1 (f %2)) [] coll))


(defn my-filter [cond coll]
  (if
    (empty? coll)
    `()
    (if
      (cond (first coll))
      (cons (first coll) (my-filter cond (rest coll)))
      (my-filter cond (rest coll)))))
