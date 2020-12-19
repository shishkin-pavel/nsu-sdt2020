(ns lab3.two-two)

(defn make-parts
  [n, collection]
  (if (empty? collection)
    ()
    (lazy-seq (cons (take n collection) (make-parts n (drop n collection))))))


(defn dummy-pred [x]
  (do
    (Thread/sleep 10)
    (identity x)))

(defn my-parallel-lazy-filter [part-size, pred, coll]
  (let [threads 10,
        parts (make-parts part-size coll)
        tasks (take threads parts)
        rests (drop (reduce + (map count tasks)) coll)
        result (mapcat deref (doall (map (fn [task] (future (doall (filter pred task)))) tasks)))]
    (if (empty? rests)
      result
      (lazy-cat result (my-parallel-lazy-filter part-size pred rests)))))

(def natural-row
  (lazy-seq (cons 1 (map inc natural-row))))

(defn -main [& args]
  (time (doall (my-parallel-lazy-filter 10 dummy-pred (range 1 100))))
  (time (doall (filter dummy-pred (range 1 100)))))