(ns test.task-3-2)

(defn get-bathes [size coll]
  (take-while #(boolean (seq %))
              (map first
                   (iterate (fn [[_ rest]] (split-at size rest)) (split-at size coll)))))

(defn lazy-filter [pred coll chankSize batchSize]
  (lazy-cat
    (->> (take (* chankSize batchSize) coll)
         (get-bathes chankSize)
         (map #(future (doall (filter pred %))))
         (doall)
         (mapcat deref))
    (lazy-filter pred (drop (* chankSize batchSize) coll) chankSize batchSize)))

(defn heavy-calc [sleep f x]
  (Thread/sleep sleep)
  (f x))

(defn -main [& _]
  (time (count (filter #(heavy-calc 1 even? %) (range 1000))))
  (time (nth (lazy-filter #(heavy-calc 1 even? %) (range) 3 20) 10)))