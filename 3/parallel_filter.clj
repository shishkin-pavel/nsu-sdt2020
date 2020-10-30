(ns parallel-filter
    (:gen-class))
(defn parallel-filter
    ([pred coll] (parallel-filter pred coll (+ 2 (.. Runtime getRuntime availableProcessors))))
    ([pred coll thread_count] 
        (reduce concat (map deref (doall (map #(future (filter pred %)) (partition-all (Math/ceil (/ (count coll) thread_count)) coll)))))))