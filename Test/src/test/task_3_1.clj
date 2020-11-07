(ns test.task-3-1)


(defn my-parallel-filter [pred batches]
  (->> batches
       (map #(future (doall (filter pred %))))
       (doall)
       (mapcat deref)
       (doall)))

(defn get-bathes [coll size]
  (partition-all size coll))

(defn heavy-calc [sleep f x]
  (Thread/sleep sleep)
  (f x))

(defn -main [& _]
  (time (doall (filter #(heavy-calc 100 even? %) (range 20))))
  (time (my-parallel-filter #(heavy-calc 100 even? %) (get-bathes (range 20) 4))))
