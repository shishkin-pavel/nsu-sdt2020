(ns test.task-3-1)


(defn my-parallel-filter [pred batches]
  (->> batches
       (map #(future (doall (filter pred %))))
       (doall)
       (map deref)
       (apply concat)
       (doall)))

(defn get-bathes [coll size]
  (partition-all size coll))

(defn heavy-calc [sleep f x]
  (Thread/sleep sleep)
  (f x))

(defn -main [& _]
  (println (time (count (doall (filter #(heavy-calc 1 even? %) (range 1000))))))
  (println (time (count (my-parallel-filter #(heavy-calc 1 even? %) (get-bathes (range 1000) 100))))))
