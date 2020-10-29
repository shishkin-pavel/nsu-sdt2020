(ns lab1)

;;select
(defn my-map [f coll]
  (reduce
    (fn [res val] 
      (conj res (f val)))
    []
    coll))

;;where
(defn my-filter [pred coll]
  (reduce
    (fn [res val] 
      (if (pred val) 
        (conj res val) 
        res))
    []
    coll))

(my-map inc [1 2 3 4 5])

(my-filter even? (range 10))