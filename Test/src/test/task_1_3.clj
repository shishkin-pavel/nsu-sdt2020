(ns test.task_1_3)

(defn my-map [f coll]
  (reduce #(conj %1 (f %2)) [] coll))

(defn my-filter [cond coll]
  (reduce #(if (cond %2) (concat %1 [%2]) %1) [] coll))
