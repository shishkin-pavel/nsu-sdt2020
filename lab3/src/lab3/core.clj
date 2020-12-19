(ns lab3.core)

(defn filter-part [pred size coll from]
  (let [result (->> coll
                    (drop (* size from))
                    (take size)
                    (filter pred)
                    (doall))]
    (println "DONE")
    result))

(defn multi-filter [pred coll part-size]
  (->> (range 0 (/ (count coll) part-size))
       (map #(future (filter-part pred part-size coll %)))
       (doall)
       (map deref)
       (apply concat)))

(defn my-even [x]
  (do
    (Thread/sleep 10)
    (even? x)))

(defn -main [& args]
  (println "MULTI")
  (time (doall (multi-filter my-even (range 1 800) 100)))
  (println "DEFAULT")
  (time (doall (filter my-even (range 1 800)))))