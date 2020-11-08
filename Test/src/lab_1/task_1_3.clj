(ns lab_1.task_1_3)

(defn my-map [f coll]
  (reduce #(conj %1 (f %2)) [] coll))

(defn my-filter [f coll]
  (reduce #(if (f %2) (conj %1 %2) %1) [] coll))

(defn my-filter-rec [f coll]
  (if
    (empty? coll)
    `()
    (loop [res [] coll coll]
      (if (empty? coll)
        (apply list res)
        (if (f (first coll))
          (recur (conj res (first coll)) (rest coll))
          (recur res (rest coll)))))))