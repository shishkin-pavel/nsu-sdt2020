(ns laba3-1.core
  (:gen-class))

(defn heavy-inc [n]
  (Thread/sleep 100)
  (filter even? n))
 
(defn main [coll size_bloc]
  (loop [acc [], new_coll coll]
    (if (>= size_bloc (count new_coll)) (conj acc (take size_bloc new_coll))
      (recur (conj acc (take size_bloc new_coll)) (drop size_bloc new_coll) )
    )
    )
)

(defn my-filter [coll size_bloc]
  (apply concat
  (doall
   (map deref
        (doall
         (map #(future (heavy-inc %)) (main coll size_bloc)))))))

;(my-filter '((1 2) (3 4) (8)))
(time (my-filter '(1 2 3 4 5 6) 2))